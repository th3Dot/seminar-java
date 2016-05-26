/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui.model;

import cz.muni.fi.javaseminar.kafa.bookregister.Author;
import cz.muni.fi.javaseminar.kafa.bookregister.AuthorManager;
import cz.muni.fi.javaseminar.kafa.bookregister.BookManager;
import java.awt.Frame;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final static Logger log = LoggerFactory.getLogger(AuthorsTableModel.class);

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public AuthorsTableModel(AuthorManager authorManager, BookManager bookManager) {
        am = authorManager;
        bm = bookManager;
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

    public void updateAuthors() {
        new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                log.debug("Fetching new data for AuthorsTableModel from database.");
                authors = am.findAllAuthors();
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                } catch (Exception e) {
                    log.error("There was an exception thrown during update of AuthorsTableModel.", e);
                    return;
                }

                rowCount = authors.size();
                if (currentSlectedIndex > rowCount - 1) {
                    currentSlectedIndex = 0;
                }

                log.debug("Updating authors table in GUI based on newly fetched data.");
                fireTableDataChanged();
            }

        }.execute();
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
                log.error("Unknown columnIndex in method getColumnName (AuthorsTableModel)");
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
                log.error("Unknown columnIndex in method setValueAt (AuthorsTableModel)");
                throw new IllegalArgumentException("columnIndex");
        }

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                log.debug("Updating author: " + author.getFirstname() + " " + author.getSurname() + ". Writing the update to database.");
                am.updateAuthor(author);

                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(Frame.getFrames()[0], "Can't update author: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    log.error("There was an exception thrown during update of author: " + author.getFirstname() + " " + author.getSurname(), e);
                }
                log.debug("Updating authors table in GUI based on newly fetched data.");
                fireTableDataChanged();
            }
        }.execute();
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
                log.error("Unknown columnIndex in method getValueAt (AuthorsTableModel)");
                throw new IllegalArgumentException("columnIndex");
        }
    }
}
