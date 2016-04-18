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
import java.time.LocalDate;
import java.time.Month;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
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
    //private Author authorOlda;
    private Author.Builder authorOlda;
    private Author.Builder authorKarel;
    private Clock clock;
    private static final String SQL_SCRIPT_NAME = "scriptDB.sql";
    private static final ApplicationContext CTX = new ClassPathXmlApplicationContext("spring/spring-test-context.xml");

    @After
    public void tearDown() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {

            connection.prepareStatement("DROP TABLE BOOK").executeUpdate();
        }
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("DROP TABLE AUTHOR").executeUpdate();

        }
    }

    @Before
    public void setUp() throws SQLException, FileNotFoundException {
        manager = (AuthorManagerImpl) CTX.getBean("authorManager");
        dataSource = manager.getDataSource();
        DBUtils.executeSqlScript(dataSource, BookManager.class.getResource(SQL_SCRIPT_NAME));

        authorOlda = Author.builder()
                .firstname("Oldrich")
                .surname("Faldik")
                .nationality("Czech")
                .description("Novodoby autor")
                .dateOfBirth(LocalDate.of(1990, Month.JANUARY, 20));

        authorKarel = Author.builder()
                .firstname("Karel")
                .surname("Soukup")
                .nationality("Czech")
                .description("Stredovek")
                .dateOfBirth(LocalDate.of(1450, Month.AUGUST, 12));
    }

    /**
     * Test of createAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testCreateAuthor() {
        Author authorOlda = this.authorOlda.build();
        manager.createAuthor(authorOlda);

        Long testAuthorId = authorOlda.getId();
        assertThat(testAuthorId).isNotNull();

        assertThat(manager.findAuthorById(authorOlda.getId()))
                .isNotSameAs(authorOlda)
                .isEqualToComparingFieldByField(authorOlda);

    }

    @Test(expected = IllegalArgumentException.class)
    public void createNullAuthor() {
        manager.createAuthor(null);

    }

    /**
     * Test of updateAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testUpdateAuthorSurname() {

        Author authorForUpdate = this.authorOlda.build();
        Author anotherAuthor = this.authorKarel.build();

        manager.createAuthor(authorForUpdate);
        manager.createAuthor(anotherAuthor);

        authorForUpdate.setSurname("Novak");
        manager.updateAuthor(authorForUpdate);

        assertThat(manager.findAuthorById(authorForUpdate.getId()))
                .isEqualToComparingFieldByField(authorForUpdate);
        assertThat(manager.findAuthorById(anotherAuthor.getId()))
                .isEqualToComparingFieldByField(anotherAuthor);

    }

    /**
     * Test of deleteAuthor method, of class AuthorManagerImpl.
     */
    @Test
    public void testDeleteAuthor() {
        Author authorOlda = this.authorOlda.build();
        Author authorKarel = this.authorKarel.build();

        manager.createAuthor(authorOlda);
        manager.createAuthor(authorKarel);

        assertThat(manager.findAuthorById(authorOlda.getId())).isNotNull();
        assertThat(manager.findAuthorById(authorKarel.getId())).isNotNull();

        manager.deleteAuthor(authorOlda);

        assertThat(manager.findAuthorById(authorOlda.getId())).isNull();
        assertThat(manager.findAuthorById(authorKarel.getId())).isNotNull();

    }

    /**
     * Test of findAllAuthors method, of class AuthorManagerImpl.
     */
    @Test
    public void testFindAllAuthors() {
        Author authorOlda = this.authorOlda.build();
        Author authorKarel = this.authorKarel.build();

        manager.createAuthor(authorOlda);
        manager.createAuthor(authorKarel);
        //List<Author> expResult = Arrays.asList(authorOlda, authorKarel);

        //List<Author> result = manager.findAllAuthors();
        assertThat(manager.findAllAuthors()).contains(authorOlda, authorKarel);

    }

    /**
     * Test of findAuthorById method, of class AuthorManagerImpl.
     */
    @Test
    public void testFindAuthorById() {
        Author authorOlda = this.authorOlda.build();
        Author authorKarel = this.authorKarel.build();

        manager.createAuthor(authorOlda);
        manager.createAuthor(authorKarel);

        assertThat(manager.findAuthorById(authorOlda.getId()))
                .isNotSameAs(authorOlda)
                .isEqualToComparingFieldByField(authorOlda);
    }

}
