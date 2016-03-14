/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.BookRegister;

import java.time.LocalDate;
import java.util.ArrayList;
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
    
    public AuthorManagerImplTest() {
    }
    
    
    
    @Before
    public void setUp() {
        
         this.manager = new AuthorManagerImpl();
    }
    
    

    /**
     * Test of createAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testCreateAuthor() {
        System.out.println("createAuthor");
        Author author = new Author();
        author.setFirstname("Oldrich");
        author.setSurname("Faldik");
        author.setNationality("Czech");
        author.setDateOfBirth(LocalDate.of(1990, 10, 20));
        manager.createAuthor(author);
        
        Long authorId = author.getId();
        
        
        assertThat("saved author has null id", author.getId(), is(not(equalTo(null))));
        Author result = manager.findAuthorById(authorId);
        assertThat("loaded author differs from the saved one", result, is(equalTo(author)));
        
        assertThat("loaded author is the same instance", result, is(not(sameInstance(author))));
        
        
    }

    /**
     * Test of updateAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testUpdateAuthor() {
        System.out.println("updateAuthor");
        
        Author author = new Author();
        author.setFirstname("Oldrich");
        author.setSurname("Faldik");
        author.setNationality("Czech");
        author.setDescription("Novodoby autor");
        author.setDateOfBirth(LocalDate.of(1990, 10, 20));
        
        manager.createAuthor(author);
        
        Long authorId = author.getId();
        
        //change surname to "Novak"
        
        author.setSurname("Novak");
        manager.updateAuthor(author);
        //load from database
        author = manager.findAuthorById(authorId);
        
        
        assertThat("surname was not changed", author.getSurname(), is(equalTo("Novak")));
        assertThat("firstname was changed when changing surname", author.getFirstname(), is(equalTo("Oldrich")));
        assertThat("description was changed when changing surname", author.getDescription(), is(equalTo("Novodoby autor")));
        assertThat("nationality was changed when changing surname", author.getNationality(), is(equalTo("Czech")));
        assertThat("nationality was changed when changing surname", author.getDateOfBirth(), is(equalTo(LocalDate.of(1990, 10, 20))));
        
        
        
        
    }

    /**
     * Test of deleteAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testDeleteAuthor() {
        System.out.println("deleteAuthor");
        Author a1 = new Author();
        a1.setFirstname("Oldrich");
        a1.setSurname("Faldik");
        a1.setNationality("Czech");
        a1.setDescription("Novodoby autor");
        a1.setDateOfBirth(LocalDate.of(1990, 10, 20));
        
        Author a2 = new Author();
        a2.setFirstname("Karel");
        a2.setSurname("Soukup");
        a2.setNationality("Czech");
        a2.setDescription("Stredovek");
        a2.setDateOfBirth(LocalDate.of(1450, 11, 12));
        
        manager.createAuthor(a1);
        manager.createAuthor(a2);
       
        
        assertNotNull(manager.findAuthorById(a1.getId()));
        assertNotNull(manager.findAuthorById(a2.getId()));

        manager.deleteAuthor(a1);

        assertNull(manager.findAuthorById(a1.getId()));
        assertNotNull(manager.findAuthorById(a2.getId()));
        
    }

    /**
     * Test of findAllAuthors method, of class AuthorManagerImpl.
     */
    @Test
    public void testFindAllAuthors() {
        System.out.println("findAllAuthors");
        Author a1 = new Author();
        a1.setFirstname("Oldrich");
        a1.setSurname("Faldik");
        a1.setNationality("Czech");
        a1.setDescription("Novodoby autor");
        a1.setDateOfBirth(LocalDate.of(1990, 10, 20));
        
        Author a2 = new Author();
        a2.setFirstname("Karel");
        a2.setSurname("Soukup");
        a2.setNationality("Czech");
        a2.setDescription("Stredovek");
        a2.setDateOfBirth(LocalDate.of(1450, 11, 12));
        
        manager.createAuthor(a1);
        manager.createAuthor(a2);
        
        
        
        List<Author> expResult;
        expResult = new ArrayList<>();
        expResult.add(a2);
        expResult.add(a1);
        List<Author> result = manager.findAllAuthors();
        
        assertEquals("saved and retrieved list of authors differ",expResult, result);
       
    }

    /**
     * Test of findAuthorById method, of class AuthorManagerImpl.
     */
    @Test
    public void testFindAuthorById() {
        System.out.println("findAuthorById");
        Author a1 = new Author();
        a1.setFirstname("Oldrich");
        a1.setSurname("Faldik");
        a1.setNationality("Czech");
        a1.setDescription("Novodoby autor");
        a1.setDateOfBirth(LocalDate.of(1990, 10, 20));
        
        manager.createAuthor(a1);
        
        Long authorId = a1.getId();
        
        Author r1 = manager.findAuthorById(authorId);
        
        
        assertEquals("saved and retrieved authors differ",a1, r1);
    }
    
}
