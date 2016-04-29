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
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author martin.kalenda
 */
@Repository("authorManager")
public class AuthorManagerImpl implements AuthorManager {

    private JdbcTemplate jdbcTemplate;
    private Clock clock;
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public Clock getClock() {
        return clock;
    }

    private void validate(Author author) throws IllegalArgumentException {
        if (author == null) {
            throw new IllegalArgumentException("author is null");
        }

        if (author.getDateOfBirth().isAfter(LocalDate.now(clock))) {
            throw new IllegalArgumentException("date of birth is in future");
        }

    }

    private static final RowMapper<Author> AUTHOR_MAPPER = new RowMapper<Author>() {

        private Author resultSetToAuthor(ResultSet rs) throws SQLException {
            Author author = Author.builder()
                    .id(rs.getLong("id"))
                    .firstname(rs.getString("firstname"))
                    .surname(rs.getString("surname"))
                    .description(rs.getString("description"))
                    .nationality(rs.getString("nationality"))
                    .dateOfBirth(rs.getDate("dateofbirth").toLocalDate())
                    .build();

            return author;
        }

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            return resultSetToAuthor(rs);
        }
    };

    @Override
    @Transactional(readOnly = false)
    public void createAuthor(Author author) {
        validate(author);
        if (author.getId() != null) {
            throw new IllegalArgumentException("author id is already set");
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int updated = jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps
                    = connection.prepareStatement("INSERT INTO AUTHOR (firstname,surname,description,nationality,dateofbirth) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getFirstname());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getDescription());
            ps.setString(4, author.getNationality());

            Date date = Date.valueOf(author.getDateOfBirth());
            ps.setDate(5, date);

            return ps;
        },
                keyHolder);

        author.setId(keyHolder.getKey().longValue());

        DBUtils.checkUpdatesCount(updated, author, true);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateAuthor(Author author) {
        validate(author);
        if (author.getId() == null) {
            throw new IllegalArgumentException("author id is null");
        }
        int updated = jdbcTemplate.update("UPDATE author SET firstname = ?, surname = ?, description = ?, nationality = ?, dateofbirth = ? WHERE id = ?", author.getFirstname(), author.getSurname(), author.getDescription(), author.getNationality(), Date.valueOf(author.getDateOfBirth()), author.getId());
        DBUtils.checkUpdatesCount(updated, author, false);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("author is null");
        }
        if (author.getId() == null) {
            throw new IllegalArgumentException("author id is null");
        }
        try {
            int updated = jdbcTemplate.update("DELETE FROM author WHERE id=?", author.getId());
            DBUtils.checkUpdatesCount(updated, author, false);
        } catch (DataAccessException e) {
            System.err.println("Could not delete author. Some book is probably assigned to him.");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAllAuthors() {
        return jdbcTemplate
                .query("SELECT * FROM author", AUTHOR_MAPPER);
    }

    @Override
    public Author findAuthorById(Long id) {
        List<Author> foundAuthors = jdbcTemplate.query("SELECT * FROM author WHERE id = ?", AUTHOR_MAPPER, id);
        if (foundAuthors.size() > 1) {
            throw new ServiceFailureException(
                    "Internal error: More entities with the same id found "
                    + "(source id: " + id + ", found " + foundAuthors);
        }
        return foundAuthors.isEmpty() ? null : foundAuthors.get(0);
    }

}
