/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui.model;

import cz.muni.fi.javaseminar.kafa.bookregister.Author;
import cz.muni.fi.javaseminar.kafa.bookregister.AuthorManager;
import cz.muni.fi.javaseminar.kafa.bookregister.Book;
import cz.muni.fi.javaseminar.kafa.bookregister.BookManager;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.backend.BackendService;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.workers.BookBackendWorker;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.SwingWorker.StateValue;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Martin
 */
public class BooksTableModel extends DefaultTableModel {

    private final AuthorManager am;
    private final BookManager bm;
    private int rowCount;
    private List<Book> books;
    
    private final static Logger log = LoggerFactory.getLogger(BooksTableModel.class);

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    private Author currentSelectedAuthor;

    public Author getCurrentSelectedAuthor() {
        return currentSelectedAuthor;
    }

    public void setCurrentSelectedAuthor(Author currentSelectedAuthor) {
        this.currentSelectedAuthor = currentSelectedAuthor;
    }

    public BooksTableModel() {
        am = BackendService.getAuthorManager();
        bm = BackendService.getBookManager();
        rowCount = 0;
        log.debug("inicializing BookTableModel");
    }

    public void setAuthorIndex(int index) {
        if (index >= 0) {
            currentSelectedAuthor = am.findAllAuthors().get(index);
            books = bm.findBooksByAuthor(currentSelectedAuthor);
            rowCount = books.size();
            
        } else {
            currentSelectedAuthor = null;
            books = null;
            rowCount = 0;
            
        }

        fireTableDataChanged();
    }

    public void updateData() {
        if (currentSelectedAuthor != null) {
            books = bm.findBooksByAuthor(am.findAuthorById(currentSelectedAuthor.getId()));
            rowCount = books.size();
            this.fireTableDataChanged();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        switch (columnIndex) {
            case 0:
                book.setName((String) aValue);
                break;
            case 1:
                book.setIsbn((String) aValue);
                break;
            case 2:
                Date date = (Date) aValue;
                if (date == null) {
                    book.setPublished(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toLocalDate());
                    break;
                }
                book.setPublished(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
                break;
            default:
                log.error("Unknown columnIndex in method setValueAt (BookTableModel)");
                throw new IllegalArgumentException("columnIndex");
        }

        BookBackendWorker worker = new BookBackendWorker(book, BookBackendWorker.Method.UPDATE);
        worker.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (((StateValue) evt.getNewValue()).equals(StateValue.DONE)) {
                    fireTableCellUpdated(rowCount, rowCount);
                }
            }

        });
        worker.execute();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return java.util.ResourceBundle.getBundle("cz/muni/fi/javaseminar/kafa/bookregister/gui/Bundle").getString("Table.books.name");
            case 1:
                return java.util.ResourceBundle.getBundle("cz/muni/fi/javaseminar/kafa/bookregister/gui/Bundle").getString("Table.books.ISBN");
            case 2:
                return java.util.ResourceBundle.getBundle("cz/muni/fi/javaseminar/kafa/bookregister/gui/Bundle").getString("Table.books.publishDate");
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return book.getName();
            case 1:
                return book.getIsbn();
            case 2:
                return book.getPublished();
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
}
