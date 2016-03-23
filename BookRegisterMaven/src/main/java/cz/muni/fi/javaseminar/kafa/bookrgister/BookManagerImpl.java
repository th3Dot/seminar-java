/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookrgister;

import cz.muni.fi.javaseminar.kafa.common.ServiceFailureException;
import cz.muni.fi.javaseminar.kafa.common.EntityNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Oldrich Faldik
 */
public class BookManagerImpl implements BookManager {

    private final DataSource dataSource;
    
    

    
    
    
    public BookManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
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
    
    private Book resultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setName(rs.getString("name"));
        book.setIsbn(rs.getString("isbn"));
        
        
        Date date = rs.getDate("published");
        LocalDate localD = date.toLocalDate();
        book.setPublished(localD);
         
        return book;
    }
    
    private Long getKey(ResultSet keyRS, Book book) throws ServiceFailureException, SQLException {
        if (keyRS.next()) {
            if (keyRS.getMetaData().getColumnCount() != 1) {
                throw new ServiceFailureException("Internal Error: Generated key"
                        + "retriving failed when trying to insert book " + book
                        + " - wrong key fields count: " + keyRS.getMetaData().getColumnCount());
            }
            Long result = keyRS.getLong(1);
            if (keyRS.next()) {
                throw new ServiceFailureException("Internal Error: Generated key"
                        + "retriving failed when trying to insert book " + book
                        + " - more keys found");
            }
            return result;
        } else {
            throw new ServiceFailureException("Internal Error: Generated key"
                    + "retriving failed when trying to insert book " + book
                    + " - no key found");
        }
    }
    
    
    
    @Override
    public void createBook(Book book) {
        validate(book);
        if (book.getId() != null) {
            throw new IllegalArgumentException("book id is already set");
        }

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "INSERT INTO BOOK (name,isbn,published) VALUES (?,?,?)",
                        Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, book.getName());
            st.setString(2, book.getIsbn());

            Date date = Date.valueOf(book.getPublished());
            st.setDate(3, date);
            
            int addedRows = st.executeUpdate();
            if (addedRows != 1) {
                throw new ServiceFailureException("Internal Error: More rows ("
                        + addedRows + ") inserted when trying to insert book " + book);
            }

            ResultSet keyRS = st.getGeneratedKeys();
            book.setId(getKey(keyRS, book));

        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when inserting book " + book, ex);
        }
    }

    @Override
    public void updateBook(Book book) {
        
        validate(book);
        if (book.getId() == null) {
            throw new IllegalArgumentException("book id is null");
        }
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                    "UPDATE Book SET name = ?, isbn = ?, published = ? WHERE id = ?")) {

            st.setString(1, book.getName());
            st.setString(2, book.getIsbn());
            
            Date date = Date.valueOf(book.getPublished());
            st.setDate(3, date);
            st.setLong(4, book.getId());

            int count = st.executeUpdate();
            if (count == 0) {
                throw new EntityNotFoundException("Book " + book + " was not found in database!");
            } else if (count != 1) {
                throw new ServiceFailureException("Invalid updated rows count detected (one row should be updated): " + count);
            }
        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when updating book " + book, ex);
        }
        
    }

    @Override
    public void deleteBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("book is null");
        }
        if (book.getId() == null) {
            throw new IllegalArgumentException("book id is null");
        }
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                    "DELETE FROM book WHERE id = ?")) {

            st.setLong(1, book.getId());

            int count = st.executeUpdate();
            if (count == 0) {
                throw new EntityNotFoundException("Book " + book + " was not found in database!");
            } else if (count != 1) {
                throw new ServiceFailureException("Invalid deleted rows count detected (one row should be updated): " + count);
            }
        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when updating grave " + book, ex);
        }
    }

    @Override
    public Book findBookById(Long id) {

         try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT id,name,isbn,published FROM book WHERE id = ?")) {

            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Book book = resultSetToBook(rs);

                if (rs.next()) {
                    throw new ServiceFailureException(
                            "Internal error: More entities with the same id found "
                            + "(source id: " + id + ", found " + book + " and " + resultSetToBook(rs));
                }

                return book;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving book with id " + id, ex);
        }
    }

    @Override
    public List<Book> findBooksByAuthor(Author author) {
           try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT book.id,book.name,book.isbn,book.published FROM book JOIN auhor ON book.id=auhor.book_id  WHERE author.id = ?")) {

            st.setLong(1, author.getId());
            ResultSet rs = st.executeQuery();
            
            List<Book> result = new ArrayList<>();
            while (rs.next()) {
                result.add(resultSetToBook(rs));
            }

            if (!result.isEmpty()) {
                Book book = resultSetToBook(rs);

                

                return result;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving books of author with id " + author.getId(), ex);
        }
    }

    @Override
    public List<Book> findAllBooks() {
         try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT id,name,isbn,published FROM book")) {

            ResultSet rs = st.executeQuery();

            List<Book> result = new ArrayList<>();
            while (rs.next()) {
                result.add(resultSetToBook(rs));
            }
            return result;

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving all books", ex);
        }
    }
    
    
}
