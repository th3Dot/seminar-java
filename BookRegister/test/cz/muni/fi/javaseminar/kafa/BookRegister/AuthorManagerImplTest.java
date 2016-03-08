/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.BookRegister;

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
public class AuthorManagerImplTest {
    
    public AuthorManagerImplTest() {
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
     * Test of createAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testCreateAuthor() {
        System.out.println("createAuthor");
        Author author = null;
        AuthorManagerImpl instance = new AuthorManagerImpl();
        instance.createAuthor(author);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testUpdateAuthor() {
        System.out.println("updateAuthor");
        Author author = null;
        AuthorManagerImpl instance = new AuthorManagerImpl();
        instance.updateAuthor(author);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testDeleteAuthor() {
        System.out.println("deleteAuthor");
        Author author = null;
        AuthorManagerImpl instance = new AuthorManagerImpl();
        instance.deleteAuthor(author);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllAuthors method, of class AuthorManagerImpl.
     */
    @Test
    public void testFindAllAuthors() {
        System.out.println("findAllAuthors");
        AuthorManagerImpl instance = new AuthorManagerImpl();
        List<Author> expResult = null;
        List<Author> result = instance.findAllAuthors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAuthorById method, of class AuthorManagerImpl.
     */
    @Test
    public void testFindAuthorById() {
        System.out.println("findAuthorById");
        Long id = null;
        AuthorManagerImpl instance = new AuthorManagerImpl();
        Author expResult = null;
        Author result = instance.findAuthorById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
