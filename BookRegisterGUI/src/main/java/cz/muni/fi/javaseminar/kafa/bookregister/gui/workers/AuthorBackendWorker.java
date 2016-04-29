/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui.workers;

import cz.muni.fi.javaseminar.kafa.bookregister.Author;
import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Martin
 */
public class AuthorBackendWorker extends AbstractBackendWorker {

    private Author author;
    private Method method;

    public AuthorBackendWorker(Author author, Method method) {
        if (author == null) {
            throw new IllegalArgumentException("Argument author is null.");
        }
        this.author = author;
        this.method = method;
    }

    @Override
    protected Void doInBackground() throws Exception {
        switch (method) {
            case CREATE:
                authorManager.createAuthor(author);
                break;
            case DELETE:
                try {
                    authorManager.deleteAuthor(author);
                } catch (DataAccessException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(
                                    Frame.getFrames()[0],
                                    "Couldn't delete author. Probably there are some books assigned to him. Please delete them first.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    });
                }
                break;
            case UPDATE:
                authorManager.updateAuthor(author);
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
