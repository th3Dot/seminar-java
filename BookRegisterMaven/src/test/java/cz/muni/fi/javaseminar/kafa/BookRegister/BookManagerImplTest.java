/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.BookRegister;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author martin.kalenda, Oldrich Faldik
 */
public class BookManagerImplTest {

    private BookManager bookManager;
    private String testBookName;
    private String testBookISBN;
    private LocalDate testBookPublishDate;
    private Book testBook;
    private DataSource dataSource;

    public BookManagerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    private static DataSource prepareDataSource() throws SQLException {
        EmbeddedDataSource ds = new EmbeddedDataSource();
        //we will use in memory database
        ds.setDatabaseName("memory:bookregmgr-test");
        ds.setCreateDatabase("create");
        return ds;
    }
    
    @Before
    public void setUp() throws SQLException {
        dataSource = prepareDataSource();
        bookManager = new BookManagerImpl(dataSource);
        
        
        
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("CREATE TABLE BOOK (" +
    "id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
    "name VARCHAR(50)," +
    "isbn VARCHAR(50)," +
    "published DATE" +
    ")").executeUpdate();
        }
        
        
        testBookName = "Test Book";
        testBookISBN = "Test-ISBN";
        testBookPublishDate = LocalDate.now();
        //clock namockovat na localdate
        testBook = new Book();
        testBook.setIsbn(testBookISBN);
        testBook.setName(testBookName);
        testBook.setPublished(testBookPublishDate);

    }

    @After
    public void tearDown() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("DROP TABLE BOOK").executeUpdate();
        }
    }

    /**
     * Test of createBook method, of class BookManagerImpl.
     */
    @Test
    public void testCreateBook() {
        bookManager.createBook(testBook);
        Book retrievedBook = bookManager.findBookById(testBook.getId());
        assertNotNull(retrievedBook);
        assertEquals(testBookName, retrievedBook.getName());
        assertEquals(testBookPublishDate, retrievedBook.getPublished());
    }

    /**
     * Test of updateBook method, of class BookManagerImpl.
     */
    @Test
    public void testUpdateBook() {
        String newBookName = "newBookName";
        String newBookISBN = "newISBN";
        LocalDate newBookPublished = LocalDate.now();
        bookManager.createBook(testBook);
        Book retrievedBook = bookManager.findBookById(testBook.getId());
        assertNotNull(retrievedBook);
        retrievedBook.setName(newBookName);
        bookManager.updateBook(retrievedBook);
        retrievedBook = null;
        retrievedBook = bookManager.findBookById(testBook.getId());
        assertEquals(newBookName, retrievedBook.getName());
        retrievedBook.setIsbn(newBookISBN);
        bookManager.updateBook(retrievedBook);
        retrievedBook = null;
        retrievedBook = bookManager.findBookById(testBook.getId());
        assertEquals(newBookISBN, retrievedBook.getIsbn());
        retrievedBook.setPublished(newBookPublished);
        bookManager.updateBook(retrievedBook);
        retrievedBook = null;
        retrievedBook = bookManager.findBookById(testBook.getId());
        assertEquals(newBookPublished, retrievedBook.getPublished());
    }

    /**
     * Test of deleteBook method, of class BookManagerImpl.
     */
    @Test
    public void testDeleteBook() {
        bookManager.createBook(testBook);
        Book retrievedBook = bookManager.findBookById(testBook.getId());
        assertNotNull(retrievedBook);
        bookManager.deleteBook(testBook);
        retrievedBook = bookManager.findBookById(testBook.getId());
        assertNull(retrievedBook);
    }

}
