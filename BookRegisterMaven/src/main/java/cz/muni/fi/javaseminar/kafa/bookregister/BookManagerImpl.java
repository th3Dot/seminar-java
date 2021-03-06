/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister;

import cz.muni.fi.javaseminar.kafa.common.DBUtils;
import cz.muni.fi.javaseminar.kafa.common.ServiceFailureException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Oldrich Faldik
 */
@Repository("bookManager")
public class BookManagerImpl implements BookManager {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    private static final RowMapper<Book> BOOK_MAPPER = new RowMapper<Book>() {

        @Override
        public Book mapRow(ResultSet rs, int i) throws SQLException {
            return resultSetToBook(rs);
        }

        private Book resultSetToBook(ResultSet rs) throws SQLException {
            Book book = Book.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .isbn(rs.getString("isbn"))
                    .published(rs.getDate("published").toLocalDate())
                    .authorId(rs.getLong("author_id"))
                    .build();

            return book;
        }
    };

    private void validate(Book book) throws IllegalArgumentException {
        if (book == null) {
            throw new IllegalArgumentException("book is null");
        }

        if (book.getName() == null) {
            throw new IllegalArgumentException("name column is null");
        }

        if (book.getIsbn() == null) {
            throw new IllegalArgumentException("ISBN column is null");
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void createBook(Book book) {
        validate(book);
        if (book.getId() != null) {
            throw new IllegalArgumentException("book id is already set");
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int updated = jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps
                    = connection.prepareStatement("INSERT INTO BOOK (name,isbn,published,author_id) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getName());
            ps.setString(2, book.getIsbn());
            Date date = Date.valueOf(book.getPublished());
            ps.setDate(3, date);
            if (book.getAuthorId() == null) {
                ps.setNull(4, Types.BIGINT);
            } else {
                ps.setLong(4, book.getAuthorId());
            }
            return ps;
        },
                keyHolder);

        book.setId(keyHolder.getKey().longValue());

        DBUtils.checkUpdatesCount(updated, book, true);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBook(Book book) {
        validate(book);
        if (book.getId() == null) {
            throw new IllegalArgumentException("book id is null");
        }
        int updated = jdbcTemplate.update("UPDATE Book SET name = ?, isbn = ?, published = ?, author_id = ? WHERE id = ?", book.getName(), book.getIsbn(), Date.valueOf(book.getPublished()), book.getAuthorId(), book.getId());
        DBUtils.checkUpdatesCount(updated, book, false);
    }

    @Override
    @Transactional
    public void deleteBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("book is null");
        }
        if (book.getId() == null) {
            throw new IllegalArgumentException("book id is null");
        }

        int updated = jdbcTemplate.update("DELETE FROM BOOK WHERE id=?", book.getId());
        DBUtils.checkUpdatesCount(updated, book, false);
    }

    @Override
    @Transactional(readOnly = true)
    public Book findBookById(Long id) {
        List<Book> foundBooks = jdbcTemplate.query("SELECT * FROM book WHERE id = ?", BOOK_MAPPER, id);
        if (foundBooks.size() > 1) {
            throw new ServiceFailureException(
                    "Internal error: More entities with the same id found "
                    + "(source id: " + id + ", found " + foundBooks);
        }

        return foundBooks.isEmpty() ? null : foundBooks.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("author is null");
        }
        return jdbcTemplate
                .query("SELECT * FROM book WHERE author_id = ?", BOOK_MAPPER, author.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return jdbcTemplate
                .query("SELECT * FROM book", BOOK_MAPPER);
    }

}
