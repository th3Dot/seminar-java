/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.BookRegister;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author martin.kalenda
 */
public class AuthorManagerImpl implements AuthorManager {

    private final DataSource dataSource;

    public AuthorManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void validate(Author author) throws IllegalArgumentException {
        if (author == null) {
            throw new IllegalArgumentException("author is null");
        }

        if (author.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("date of birth is in future");
        }
    }

    private Long getKey(ResultSet keyRS, Author author) throws ServiceFailureException, SQLException {
        if (keyRS.next()) {
            if (keyRS.getMetaData().getColumnCount() != 1) {
                throw new ServiceFailureException("Internal Error: Generated key"
                        + "retriving failed when trying to insert author " + author
                        + " - wrong key fields count: " + keyRS.getMetaData().getColumnCount());
            }
            Long result = keyRS.getLong(1);
            if (keyRS.next()) {
                throw new ServiceFailureException("Internal Error: Generated key"
                        + "retriving failed when trying to insert author " + author
                        + " - more keys found");
            }
            return result;
        } else {
            throw new ServiceFailureException("Internal Error: Generated key"
                    + "retriving failed when trying to insert author " + author
                    + " - no key found");
        }
    }

    @Override
    public void createAuthor(Author author) {

        validate(author);
        if (author.getId() != null) {
            throw new IllegalArgumentException("author id is already set");
        }

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "INSERT INTO AUTHOR (firstname,surname,description,nationality,dateofbirth) VALUES (?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, author.getFirstname());
            st.setString(2, author.getSurname());
            st.setString(3, author.getDescription());
            st.setString(4, author.getNationality());
            
            Date date = Date.valueOf(author.getDateOfBirth());
            st.setDate(5, date);
            
            int addedRows = st.executeUpdate();
            if (addedRows != 1) {
                throw new ServiceFailureException("Internal Error: More rows ("
                        + addedRows + ") inserted when trying to insert author " + author);
            }

            ResultSet keyRS = st.getGeneratedKeys();
            author.setId(getKey(keyRS, author));

        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when inserting author " + author, ex);
        }

    }

    @Override
    public void updateAuthor(Author author) {
        validate(author);
        if (author.getId() == null) {
            throw new IllegalArgumentException("author id is null");
        }
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "UPDATE author SET firstname = ?, surname = ?, description = ?, nationality = ?, dateofbirth = ? WHERE id = ?")) {

            st.setString(1, author.getFirstname());
            st.setString(2, author.getSurname());
            st.setString(3, author.getDescription());
            st.setString(4, author.getNationality());
            Date date = Date.valueOf(author.getDateOfBirth());
            st.setDate(5, date);
            st.setLong(6, author.getId());

            int count = st.executeUpdate();
            if (count == 0) {
                throw new EntityNotFoundException("author " + author + " was not found in database!");
            } else if (count != 1) {
                throw new ServiceFailureException("Invalid updated rows count detected (one row should be updated): " + count);
            }
        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when updating author " + author, ex);
        }
    }

    @Override
    public void deleteAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("author is null");
        }
        if (author.getId() == null) {
            throw new IllegalArgumentException("author id is null");
        }
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "DELETE FROM author WHERE id = ?")) {

            st.setLong(1, author.getId());

            int count = st.executeUpdate();
            if (count == 0) {
                throw new EntityNotFoundException("Author " + author + " was not found in database!");
            } else if (count != 1) {
                throw new ServiceFailureException("Invalid deleted rows count detected (one row should be updated): " + count);
            }
        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when updating author " + author, ex);
        }
    }

    @Override
    public List<Author> findAllAuthors() {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT id,firstname,surname,description,nationality,dateofbirth FROM author")) {

            ResultSet rs = st.executeQuery();

            List<Author> result = new ArrayList<>();
            while (rs.next()) {
                result.add(resultSetToAuthor(rs));
            }
            return result;

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving all authors", ex);
        }
    }

    private Author resultSetToAuthor(ResultSet rs) throws SQLException {
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstname(rs.getString("firstname"));
        author.setSurname(rs.getString("surname"));
        author.setDescription(rs.getString("description"));
        author.setNationality(rs.getString("nationality"));
        
        Date date = rs.getDate("dateofbirth");
        LocalDate localD = date.toLocalDate();
        
        author.setDateOfBirth(localD);
        return author;
    }

    @Override
    public Author findAuthorById(Long id) {
           try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT * FROM author WHERE id = ?")) {

            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Author author = resultSetToAuthor(rs);

                if (rs.next()) {
                    throw new ServiceFailureException(
                            "Internal error: More entities with the same id found "
                            + "(source id: " + id + ", found " + author + " and " + resultSetToAuthor(rs));
                }

                return author;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving author with id " + id, ex);
        }

}
    
}   
