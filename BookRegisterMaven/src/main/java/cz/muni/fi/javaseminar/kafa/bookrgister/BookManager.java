/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookrgister;

import java.util.List;

/**
 *
 * @author martin.kalenda
 */
public interface BookManager {
    public void createBook(Book book);
    public void updateBook(Book book);
    public void deleteBook(Book book);
    public Book findBookById(Long id);
    public List<Book> findBooksByAuthor(Author author);
    public List<Book> findAllBooks();
}
