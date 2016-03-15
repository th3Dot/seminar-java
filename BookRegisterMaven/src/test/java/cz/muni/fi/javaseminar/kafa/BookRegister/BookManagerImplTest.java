/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.BookRegister;

import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author martin.kalenda, Oldrich Faldik
 */
public class BookManagerImplTest {
    
    private BookManager bookManager;
    private String testBookName;
    private Long testBookId;
    private String testBookISBN;
    private LocalDate testBookPublishDate;
    private Book testBook;
    
    
    public BookManagerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        bookManager = new BookManagerImpl();
        testBookName = "Test Book";
        testBookId = 1L;
        testBookISBN = "Test-ISBN";
        testBookPublishDate = LocalDate.now();
        //clock namockovat na localdate
        testBook = new Book(testBookId, testBookName, testBookISBN, testBookPublishDate);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createBook method, of class BookManagerImpl.
     */
    @Test
    public void testCreateBook() {
        bookManager.createBook(testBook);
        Book retrievedBook = bookManager.findBookById(testBookId);
        assertNotNull(retrievedBook);
        assertEquals(testBookId, retrievedBook.getId());
        assertEquals(testBookName, retrievedBook.getName());
        assertEquals(testBookId, retrievedBook.getIsbn());
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
        Book retrievedBook = bookManager.findBookById(testBookId);
        assertNotNull(retrievedBook);
        retrievedBook.setName(newBookName);
        bookManager.updateBook(retrievedBook);
        retrievedBook = null;
        retrievedBook = bookManager.findBookById(testBookId);
        assertEquals(newBookName, retrievedBook.getName());
        retrievedBook.setIsbn(newBookISBN);
        bookManager.updateBook(retrievedBook);
        retrievedBook = null;
        retrievedBook = bookManager.findBookById(testBookId);
        assertEquals(newBookISBN, retrievedBook.getIsbn());
        retrievedBook.setPublished(newBookPublished);
        bookManager.updateBook(retrievedBook);
        retrievedBook = null;
        retrievedBook = bookManager.findBookById(testBookId);
        assertEquals(newBookPublished, retrievedBook.getPublished());
    }

    /**
     * Test of deleteBook method, of class BookManagerImpl.
     */
    @Test
    public void testDeleteBook() {
        bookManager.createBook(testBook);
        Book retrievedBook = bookManager.findBookById(testBookId);
        assertNotNull(retrievedBook);
        bookManager.deleteBook(testBook);
        retrievedBook = bookManager.findBookById(testBookId);
        assertNull(retrievedBook);
    }
    
}
