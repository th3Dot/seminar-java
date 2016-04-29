/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui.model;

import cz.muni.fi.javaseminar.kafa.bookregister.AuthorManager;
import cz.muni.fi.javaseminar.kafa.bookregister.Book;
import cz.muni.fi.javaseminar.kafa.bookregister.BookManager;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.backend.BackendService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Martin
 */
public class BooksTableModel extends DefaultTableModel {

    private final AuthorManager am;
    private final BookManager bm;
    private int rowCount;
    final private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private List<Book> books;

    public BooksTableModel() {
        am = BackendService.getAuthorManager();
        bm = BackendService.getBookManager();
        rowCount = 0;
    }

    public void setAuthorIndex(int index) {
        books = bm.findBooksByAuthor(am.findAllAuthors().get(index));
        rowCount = books.size();
        fireTableDataChanged();
    }

    public void updateData(int index) {
        books = bm.findBooksByAuthor(am.findAllAuthors().get(index));
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
                book.setPublished(LocalDate.parse((String) aValue, formatter));
                break;
            default:
                throw new IllegalArgumentException("columnIndex");
        }

        bm.updateBook(book);
        fireTableCellUpdated(rowIndex, columnIndex);
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
                return "Name";
            case 1:
                return "ISBN";
            case 2:
                return "Publish Date";
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
