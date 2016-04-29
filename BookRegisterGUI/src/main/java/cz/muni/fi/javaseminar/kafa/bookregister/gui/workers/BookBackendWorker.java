/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui.workers;

import cz.muni.fi.javaseminar.kafa.bookregister.Book;

/**
 *
 * @author Martin
 */
public class BookBackendWorker extends AbstractBackendWorker {

    private Book book;
    private Method method;

    public BookBackendWorker(Book book, Method method) {
        if (book == null) {
            throw new IllegalArgumentException("Argument author is null.");
        }
        this.book = book;
        this.method = method;
    }

    @Override
    protected Void doInBackground() throws Exception {
        switch (method) {
            case CREATE:
                bookManager.createBook(book);
                break;
            case DELETE:
                bookManager.deleteBook(book);
                break;
            case UPDATE:
                bookManager.updateBook(book);
                break;
            default:
                throw new IllegalArgumentException("Unkown method.");
        }
        return null;
    }

    public enum Method {

        CREATE, DELETE, UPDATE

    }

}
