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
    
    private Author a1;
    private Author a2;
    
    public AuthorManagerImplTest() {
    }
    
    
    
    @Before
    public void setUp() {
        this.manager = new AuthorManagerImpl();
         
        this.a1 = new Author();
        this.a1.setFirstname("Oldrich");
        this.a1.setSurname("Faldik");
        this.a1.setNationality("Czech");
        this.a1.setDateOfBirth(LocalDate.of(1990, 10, 20));
        
        
        this.a2= new Author();
        this.a2.setFirstname("Karel");
        this.a2.setSurname("Soukup");
        this.a2.setNationality("Czech");
        this.a2.setDescription("Stredovek");
        this.a2.setDateOfBirth(LocalDate.of(1450, 11, 12));
         
         
    }
    
    

    /**
     * Test of createAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testCreateAuthor() {
        manager.createAuthor(this.a1);
        Long authorId = this.a1.getId();
        
        assertThat(this.a1.getId(), is(not(equalTo(null))));
        Author result = manager.findAuthorById(authorId);
        assertThat(result, is(equalTo(this.a1)));
        
        assertThat(result, is(not(sameInstance(this.a1))));
       
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
        manager.createAuthor(this.a1);
        Long authorId = this.a1.getId();
            
        this.a1.setSurname("Novak");
        manager.updateAuthor(this.a1);
        
        this.a1 = manager.findAuthorById(authorId);
        
        assertThat(this.a1.getSurname(), is(equalTo("Novak")));
        assertThat(this.a1.getFirstname(), is(equalTo("Oldrich")));
        assertThat(this.a1.getDescription(), is(equalTo("Novodoby autor")));
        assertThat(this.a1.getNationality(), is(equalTo("Czech")));
        assertThat(this.a1.getDateOfBirth(), is(equalTo(LocalDate.of(1990, 10, 20))));
            
    }

    /**
     * Test of deleteAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testDeleteAuthor() {
        manager.createAuthor(this.a1);
        manager.createAuthor(this.a2);
       
        assertNotNull(manager.findAuthorById(this.a1.getId()));
        assertNotNull(manager.findAuthorById(this.a2.getId()));

        manager.deleteAuthor(this.a1);

        assertNull(manager.findAuthorById(this.a1.getId()));
        assertNotNull(manager.findAuthorById(this.a2.getId()));
        
    }

    /**
     * Test of findAllAuthors method, of class AuthorManagerImpl.
     */
    @Test
    public void testFindAllAuthors() {
        manager.createAuthor(this.a1);
        manager.createAuthor(this.a2);
        
        List<Author> expResult = Arrays.asList(a2,a1);
        
        List<Author> result = manager.findAllAuthors();
        
        assertEquals(expResult, result);
       
    }

    /**
     * Test of findAuthorById method, of class AuthorManagerImpl.
     */
    @Test
    public void testFindAuthorById() {
        manager.createAuthor(this.a1);
        Long authorId = this.a1.getId();
        
        Author r1 = manager.findAuthorById(authorId);
        
        assertEquals(this.a1, r1);
    }
    
}
