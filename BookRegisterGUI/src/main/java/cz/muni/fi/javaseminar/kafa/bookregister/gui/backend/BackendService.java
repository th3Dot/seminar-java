/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui.backend;

import cz.muni.fi.javaseminar.kafa.bookregister.AuthorManager;
import cz.muni.fi.javaseminar.kafa.bookregister.AuthorManagerImpl;
import cz.muni.fi.javaseminar.kafa.bookregister.BookManager;
import cz.muni.fi.javaseminar.kafa.bookregister.BookManagerImpl;
import java.time.Clock;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Martin
 */
public final class BackendService {

    public static DataSource dataSource;

    private static BookManagerImpl bookManager;
    private static AuthorManagerImpl authorManager;

    private static final ApplicationContext CTX = new ClassPathXmlApplicationContext("spring/spring-context.xml");

    private BackendService() {
    }

    public static BookManager getBookManager() {
        if (bookManager == null) {
            dataSource = CTX.getBean(DataSource.class);
            bookManager = new BookManagerImpl();
            bookManager.setDataSource(dataSource);
        }
        return bookManager;
    }

    public static AuthorManager getAuthorManager() {
        if (bookManager == null) {
            dataSource = CTX.getBean(DataSource.class);
            authorManager = new AuthorManagerImpl();
            authorManager.setClock(Clock.systemDefaultZone());
            authorManager.setDataSource(dataSource);
        }
        return authorManager;
    }
}
