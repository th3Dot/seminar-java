/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui.model;

import cz.muni.fi.javaseminar.kafa.bookregister.Author;
import cz.muni.fi.javaseminar.kafa.bookregister.AuthorManager;
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
public class AuthorsTableModel extends DefaultTableModel {

    private final AuthorManager am;
    private final BookManager bm;

    private List<Author> authors;
    private int currentSlectedIndex;
    private int rowCount;

    final private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public AuthorsTableModel() {
        am = BackendService.getAuthorManager();
        bm = BackendService.getBookManager();
        authors = am.findAllAuthors();
        rowCount = authors.size();
    }

    public int getCurrentSlectedIndex() {
        return currentSlectedIndex;
    }

    public void setCurrentSlectedIndex(int index) {
        currentSlectedIndex = index;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public void updateData() {
        authors = am.findAllAuthors();
        rowCount = authors.size();
        this.fireTableDataChanged();

    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Fistname";
            case 1:
                return "Surname";
            case 2:
                return "Date of Birth";
            case 3:
                return "Description";
            case 4:
                return "Nationality";
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Author author = authors.get(rowIndex);
        switch (columnIndex) {
            case 0:
                author.setFirstname((String) aValue);
                break;
            case 1:
                author.setSurname((String) aValue);
                break;
            case 2:
                author.setDateOfBirth(LocalDate.parse((String) aValue, formatter));
                break;
            case 3:
                author.setDescription((String) aValue);
                break;
            case 4:
                author.setNationality((String) aValue);
                break;
            default:
                throw new IllegalArgumentException("columnIndex");
        }

        am.updateAuthor(author);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Author author = authors.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return author.getFirstname();
            case 1:
                return author.getSurname();
            case 2:
                return author.getDateOfBirth();
            case 3:
                return author.getDescription();
            case 4:
                return author.getNationality();
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
}
