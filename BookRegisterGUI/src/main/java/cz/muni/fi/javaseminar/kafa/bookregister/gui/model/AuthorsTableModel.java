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
import cz.muni.fi.javaseminar.kafa.bookregister.gui.workers.AuthorBackendWorker;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.SwingWorker;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

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
        if (currentSlectedIndex > rowCount - 1) {
            currentSlectedIndex = 0;
        }
        this.fireTableDataChanged();

    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return java.util.ResourceBundle.getBundle("cz/muni/fi/javaseminar/kafa/bookregister/gui/Bundle").getString("Table.authors.firstname");
            case 1:
                return java.util.ResourceBundle.getBundle("cz/muni/fi/javaseminar/kafa/bookregister/gui/Bundle").getString("Table.authors.surname");
            case 2:
                return java.util.ResourceBundle.getBundle("cz/muni/fi/javaseminar/kafa/bookregister/gui/Bundle").getString("Table.authors.dateOfBirth");
            case 3:
                return java.util.ResourceBundle.getBundle("cz/muni/fi/javaseminar/kafa/bookregister/gui/Bundle").getString("Table.authors.description");
            case 4:
                return java.util.ResourceBundle.getBundle("cz/muni/fi/javaseminar/kafa/bookregister/gui/Bundle").getString("Table.authors.nationality");
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
                Date date = (Date) aValue;
                if (date == null) {
                    author.setDateOfBirth(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toLocalDate());
                    break;
                }
                author.setDateOfBirth(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
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
        AuthorBackendWorker worker = new AuthorBackendWorker(author, AuthorBackendWorker.Method.UPDATE);
        worker.execute();
        worker.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (((SwingWorker.StateValue) evt.getNewValue()).equals(SwingWorker.StateValue.DONE)) {
                    fireTableCellUpdated(rowCount, rowCount);
                }
            }

        });
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
