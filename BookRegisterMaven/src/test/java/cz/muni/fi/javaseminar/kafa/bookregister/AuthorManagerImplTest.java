/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister;

import cz.muni.fi.javaseminar.kafa.common.DBUtils;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Oldrich Faldik, martin.kalenda
 */
public class AuthorManagerImplTest {

    private AuthorManagerImpl manager;
    private DataSource dataSource;
    private Author authorOlda;
    private Author authorKarel;
    private Clock clock;
    private static final String SQL_SCRIPT_NAME = "scriptDB.sql";
    private static final ApplicationContext CTX = new ClassPathXmlApplicationContext("spring/spring-test-context.xml");

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
        manager = (AuthorManagerImpl) CTX.getBean("authorManager");
        dataSource = manager.getDataSource();
        DBUtils.executeSqlScript(dataSource,BookManager.class.getResource(SQL_SCRIPT_NAME));

        authorOlda = Author.builder()
                .firstname("Oldrich")
                .surname("Faldik")
                .nationality("Czech")
                .description("Novodoby autor")
                .dateOfBirth(LocalDate.of(1990, Month.JANUARY, 20))
                .build();
        
        authorKarel = Author.builder()
                .firstname("Karel")
                .surname("Soukup")
                .nationality("Czech")
                .description("Stredovek")
                .dateOfBirth(LocalDate.of(1450, Month.AUGUST, 12))
                .build();
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
        assertThat(authorOlda.getDateOfBirth(), is(LocalDate.of(1990, Month.JANUARY, 20)));

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
