/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.BookRegister;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.apache.ibatis.jdbc.ScriptRunner;
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

    String aSQLScriptFilePath = "scriptDB.sql";
    
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
        
        try {
            try(Connection connection = dataSource.getConnection()){
			// Initialize object for ScripRunner
			ScriptRunner sr = new ScriptRunner(connection);

			// Give the input file to Reader
			Reader reader = new BufferedReader(
                               new FileReader(aSQLScriptFilePath));

			// Exctute script
			sr.runScript(reader);
            }
		} catch (Exception e) {
			System.err.println("Failed to Execute" + aSQLScriptFilePath
					+ " The error is " + e.getMessage());
		}
        
        /*try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("CREATE TABLE BOOK (" +
    "id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
    "name VARCHAR(50)," +
    "isbn VARCHAR(50)," +
    "published DATE" +
    ")").executeUpdate();
        }*/
        
        
        testBookName = "Test Book";
        testBookISBN = "Test-ISBN";
        testBookPublishDate = LocalDate.of(2003, Month.MARCH, 1);
        //clock namockovat na localdate
        testBook = new Book();
        testBook.setIsbn(testBookISBN);
        testBook.setName(testBookName);
        testBook.setPublished(testBookPublishDate);

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
