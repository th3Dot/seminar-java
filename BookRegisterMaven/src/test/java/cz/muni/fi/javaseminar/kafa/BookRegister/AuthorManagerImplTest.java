/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.BookRegister;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;

import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Oldrich Faldik, martin.kalenda
 */
public class AuthorManagerImplTest {
    
    private AuthorManagerImpl manager;
    
    private Author authorOlda;
    private Author authorKarel;
    
    public AuthorManagerImplTest() {
    }
    
    
    
    @Before
    public void setUp() {
        this.manager = new AuthorManagerImpl();
         
        authorOlda = new Author();
        authorOlda.setFirstname("Oldrich");
        authorOlda.setSurname("Faldik");
        authorOlda.setNationality("Czech");
        authorOlda.setDateOfBirth(LocalDate.of(1990, 10, 20));
        
        
        authorKarel= new Author();
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

    
     @Test(expected=IllegalArgumentException.class)
     public void createNullAuthor(){
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
        
        List<Author> expResult = Arrays.asList(a2,a1);
        
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
