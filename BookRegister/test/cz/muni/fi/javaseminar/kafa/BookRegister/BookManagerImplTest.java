/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.BookRegister;

import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author martin.kalenda
 */
public class BookManagerImplTest {
    
    private final BookManager bookManager = new BookManagerImpl();
    private final String testBookName = "Test Book";
    private final Long testBookId = 1L;
    private final String testBookISBN = "Test-ISBN";
    private final LocalDate testBookPublishDate = LocalDate.now();
    private final Book testBook = new Book(testBookId, testBookName, testBookISBN, testBookPublishDate);
    
    
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
