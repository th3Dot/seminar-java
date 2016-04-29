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
    private Author.Builder testAuthor;
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

        testAuthor = Author.builder()
                .firstname("Oldrich")
                .surname("Faldik")
                .nationality("Czech")
                .description("Novodoby autor")
                .dateOfBirth(LocalDate.of(1990, Month.JANUARY, 20));
        //clock namockovat na localdate
        testBook = Book.builder()
                .isbn(testBookISBN)
                .name(testBookName)
                .published(testBookPublishDate);

        Author author = this.testAuthor.build();
        AuthorManagerImpl am = (AuthorManagerImpl) CTX.getBean("authorManager");
        am.createAuthor(author);
        testBook.authorId(author.getId());

    }

    @After
    public void tearDown() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("DROP TABLE BOOK").executeUpdate();
        }
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("DROP TABLE AUTHOR").executeUpdate();

        }
    }

    /**
     * Test of createBook method, of class BookManagerImpl.
     */
    @Test
    public void testCreateBook() {
        Book testBook = this.testBook.build();

        bookManager.createBook(testBook);

        Long testBookId = testBook.getId();
        assertThat(testBookId).isNotNull();

        assertThat(bookManager.findBookById(testBook.getId()))
                .isNotSameAs(testBook)
                .isEqualToComparingFieldByField(testBook);

    }

    /**
     * Test of updateBook method, of class BookManagerImpl.
     */
    @Test
    public void testUpdateBookName() {
        Book testBook = this.testBook.build();
        String newBookName = "newBookName";
        String newBookISBN = "newISBN";
        LocalDate newBookPublished = LocalDate.of(2004, Month.AUGUST, 2);
        bookManager.createBook(testBook);

        testBook.setName(newBookName);
        bookManager.updateBook(testBook);

        assertThat(bookManager.findBookById(testBook.getId()))
                .isEqualToComparingFieldByField(testBook);

    }

    @Test
    public void testUpdateBookISBN() {
        Book testBook = this.testBook.build();
        String newBookName = "newBookName";
        String newBookISBN = "newISBN";
        LocalDate newBookPublished = LocalDate.of(2004, Month.AUGUST, 2);
        bookManager.createBook(testBook);

        testBook.setIsbn(newBookISBN);
        bookManager.updateBook(testBook);

        assertThat(bookManager.findBookById(testBook.getId()))
                .isEqualToComparingFieldByField(testBook);

    }

    @Test
    public void testUpdateBookPublished() {
        Book testBook = this.testBook.build();
        String newBookName = "newBookName";
        String newBookISBN = "newISBN";
        LocalDate newBookPublished = LocalDate.of(2004, Month.AUGUST, 2);
        bookManager.createBook(testBook);

        testBook.setPublished(newBookPublished);
        bookManager.updateBook(testBook);

        assertThat(bookManager.findBookById(testBook.getId()))
                .isEqualToComparingFieldByField(testBook);

    }

    /**
     * Test of deleteBook method, of class BookManagerImpl.
     */
    @Test
    public void testDeleteBook() {
        Book testBook = this.testBook.build();
        bookManager.createBook(testBook);

        assertThat(bookManager.findBookById(testBook.getId())).isNotNull();

        bookManager.deleteBook(testBook);

        assertThat(bookManager.findBookById(testBook.getId())).isNull();

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

        assertThat(bookManager.findBookById(testBook.getId()))
                .isNotSameAs(testBook)
                .isEqualToComparingFieldByField(testBook);

    }

}
