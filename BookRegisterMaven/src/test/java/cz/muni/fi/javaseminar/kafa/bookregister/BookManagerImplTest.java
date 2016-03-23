package cz.muni.fi.javaseminar.kafa.bookregister;

import cz.muni.fi.javaseminar.kafa.common.DBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author martin.kalenda, Oldrich Faldik
 */
public class BookManagerImplTest {

    private BookManagerImpl bookManager;
    private String testBookName;
    private String testBookISBN;
    private LocalDate testBookPublishDate;
    private Book.Builder testBook;
    private DataSource dataSource;

    private static final String SQL_SCRIPT_NAME = "scriptDB.sql";
    private static final ApplicationContext CTX = new ClassPathXmlApplicationContext("spring/spring-test-context.xml");

    public BookManagerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException {
        bookManager = (BookManagerImpl) CTX.getBean("bookManager");
        dataSource = bookManager.getDataSource();
        DBUtils.executeSqlScript(dataSource, BookManager.class.getResource(SQL_SCRIPT_NAME));

        testBookName = "Test Book";
        testBookISBN = "Test-ISBN";
        testBookPublishDate = LocalDate.of(2003, Month.MARCH, 1);
        //clock namockovat na localdate
        testBook = Book.builder()
                .isbn(testBookISBN)
                .name(testBookName)
                .published(testBookPublishDate);
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
        Book testBook = this.testBook.build();
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
        Book testBook = this.testBook.build();
        String newBookName = "newBookName";
        String newBookISBN = "newISBN";
        LocalDate newBookPublished = LocalDate.of(2004, Month.AUGUST, 2);
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
        Book testBook = this.testBook.build();
        bookManager.createBook(testBook);
        Book retrievedBook = bookManager.findBookById(testBook.getId());
        assertNotNull(retrievedBook);
        bookManager.deleteBook(testBook);
        retrievedBook = bookManager.findBookById(testBook.getId());
        assertNull(retrievedBook);
    }

    @Test
    public void testFindAllBooks() {
        Book testBook = this.testBook.build();
        Book secondTestBook = this.testBook.name("Trala").isbn("123").build();
        assertThat(bookManager.findAllBooks()).isEmpty();
        bookManager.createBook(testBook);
        bookManager.createBook(secondTestBook);

        assertThat(bookManager.findAllBooks())
                .usingFieldByFieldElementComparator()
                .containsOnly(testBook, secondTestBook);
    }

    @Test
    public void testFindBooksByAuthor() {
        Author author = Author.builder()
                .firstname("Franta")
                .surname("Novak")
                .dateOfBirth(testBookPublishDate)
                .nationality("Czech")
                .description("Nothing").build();

        AuthorManager authorManager = (AuthorManager) CTX.getBean("authorManager");
        authorManager.createAuthor(author);
        
        Book authorTestBook = testBook
                .authorId(author.getId())
                .build();
        
        bookManager.createBook(authorTestBook);
        
        assertThat(bookManager.findBooksByAuthor(author))
                .containsExactly(authorTestBook);
    }

    @Test
    public void testFindBookById() {
        Book testBook = this.testBook.build();
        bookManager.createBook(testBook);
        Book retrievedBook = bookManager.findBookById(testBook.getId());
        assertThat(retrievedBook.getId()).isEqualTo(testBook.getId());
    }

}
