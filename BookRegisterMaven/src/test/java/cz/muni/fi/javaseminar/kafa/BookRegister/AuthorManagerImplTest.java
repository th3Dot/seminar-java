/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.BookRegister;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Oldrich Faldik, martin.kalenda
 */
public class AuthorManagerImplTest {

    private AuthorManagerImpl manager;
    private DataSource dataSource;
    private Author authorOlda;
    private Author authorKarel;

    public AuthorManagerImplTest() {
    }
    
    private static DataSource prepareDataSource() throws SQLException {
        EmbeddedDataSource ds = new EmbeddedDataSource();
        //we will use in memory database
        ds.setDatabaseName("memory:bookregmgr-testAuth");
        ds.setCreateDatabase("create");
        return ds;
    }
    
    @After
    public void tearDown() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("DROP TABLE AUTHOR").executeUpdate();
            
        }
        try (Connection connection = dataSource.getConnection()) {
           
            connection.prepareStatement("DROP TABLE BOOK").executeUpdate();
        }
        
    }   
    
    @Before
    public void setUp() throws SQLException, FileNotFoundException {
        
       dataSource = prepareDataSource();
        
       // String aSQLScriptFilePath = "/Users/olda/javaSem3/seminar-java/dbJavaSeminar.sql";
        
       
        //manager = new GraveManagerImpl(dataSource);
        this.manager = new AuthorManagerImpl(dataSource);
    
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("CREATE TABLE BOOK (" +
    "id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
    "name VARCHAR(50)," +
    "isbn VARCHAR(50)," +
    "published DATE" +
    ")").executeUpdate();
        }
        
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement(" CREATE TABLE AUTHOR (" +
    "id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
    "firstname VARCHAR(50)," +
    "surname VARCHAR(50)," +
    "description VARCHAR(50)," +
    "nationality VARCHAR(50)," +
    "dateofbirth DATE," +
    "book_id BIGINT," +
    "FOREIGN KEY (book_id)" +
    "REFERENCES BOOK (id))").executeUpdate();
        }
        
        
        authorOlda = new Author();
        authorOlda.setFirstname("Oldrich");
        authorOlda.setSurname("Faldik");
        authorOlda.setNationality("Czech");
        authorOlda.setDescription("Novodoby autor");
        authorOlda.setDateOfBirth(LocalDate.of(1990, 10, 20));

        authorKarel = new Author();
        authorKarel.setFirstname("Karel");
        authorKarel.setSurname("Soukup");
        authorKarel.setNationality("Czech");
        authorKarel.setDescription("Stredovek");
        authorKarel.setDateOfBirth(LocalDate.of(1450, 11, 12));

    }

    /**
     * Test of createAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testCreateAuthor() {
        manager.createAuthor(authorOlda);
        Long authorId = authorOlda.getId();

        assertThat(authorOlda.getId(), is(not(equalTo(null))));
        Author result = manager.findAuthorById(authorId);
        assertThat(result, is(equalTo(authorOlda)));

        assertThat(result, is(not(sameInstance(authorOlda))));

    }

    @Test(expected = IllegalArgumentException.class)
    public void createNullAuthor() {
        manager.createAuthor(null);

    }

    /**
     * Test of updateAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testUpdateAuthor() {
        manager.createAuthor(authorOlda);
        Long authorId = authorOlda.getId();

        authorOlda.setSurname("Novak");
        manager.updateAuthor(authorOlda);

        authorOlda = manager.findAuthorById(authorId);

        assertThat(authorOlda.getSurname(), is(equalTo("Novak")));
        assertThat(authorOlda.getFirstname(), is(equalTo("Oldrich")));
        assertThat(authorOlda.getDescription(), is(equalTo("Novodoby autor")));
        assertThat(authorOlda.getNationality(), is(equalTo("Czech")));
        assertThat(authorOlda.getDateOfBirth(), is(equalTo(LocalDate.of(1990, 10, 20))));

    }

    /**
     * Test of deleteAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testDeleteAuthor() {
        manager.createAuthor(authorOlda);
        manager.createAuthor(authorKarel);

        assertNotNull(manager.findAuthorById(authorOlda.getId()));
        assertNotNull(manager.findAuthorById(authorKarel.getId()));

        manager.deleteAuthor(authorOlda);

        assertNull(manager.findAuthorById(authorOlda.getId()));
        assertNotNull(manager.findAuthorById(authorKarel.getId()));

    }

    /**
     * Test of findAllAuthors method, of class AuthorManagerImpl.
     */
    @Test
    public void testFindAllAuthors() {
        manager.createAuthor(authorOlda);
        manager.createAuthor(authorKarel);

        List<Author> expResult = Arrays.asList(authorOlda, authorKarel);

        List<Author> result = manager.findAllAuthors();

        assertEquals(expResult, result);

    }

    /**
     * Test of findAuthorById method, of class AuthorManagerImpl.
     */
    @Test
    public void testFindAuthorById() {
        manager.createAuthor(authorOlda);
        Long authorId = authorOlda.getId();

        Author r1 = manager.findAuthorById(authorId);

        assertEquals(authorOlda, r1);
    }

}
