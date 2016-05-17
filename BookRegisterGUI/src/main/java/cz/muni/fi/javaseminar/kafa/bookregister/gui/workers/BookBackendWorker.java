/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui.workers;

import cz.muni.fi.javaseminar.kafa.bookregister.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin
 */
public class BookBackendWorker extends AbstractBackendWorker {
    
    private final static Logger log = LoggerFactory.getLogger(BookBackendWorker.class);

    private Book book;
    private Method method;

    public BookBackendWorker(Book book, Method method) {
        if (book == null) {
            log.error("Book is null for method BookBackendWorker");
            throw new IllegalArgumentException("Argument author is null.");
        }
        this.book = book;
        this.method = method;
    }

    @Override
    protected Void doInBackground() throws Exception {
        switch (method) {
            case CREATE:
                log.debug("Creating new book: "+book.getName());
                bookManager.createBook(book);
                break;
            case DELETE:
                log.debug("Deleting book: "+book.getName());
                bookManager.deleteBook(book);
                break;
            case UPDATE:
                log.debug("Updating book on: "+book.getName());
                bookManager.updateBook(book);
                break;
            default:
                log.error("Unkown method for author");
                throw new IllegalArgumentException("Unkown method.");
        }
        return null;
    }

    public enum Method {

        CREATE, DELETE, UPDATE

    }

}
