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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin
 */
public class AuthorBackendWorker extends AbstractBackendWorker {
    
    private final static Logger log = LoggerFactory.getLogger(AuthorBackendWorker.class);

    private Author author;
    private Method method;

    public AuthorBackendWorker(Author author, Method method) {
        if (author == null) {
            log.error("Author is null for method");
            throw new IllegalArgumentException("Argument author is null.");
        }
        this.author = author;
        this.method = method;
    }

    @Override
    protected Void doInBackground() throws Exception {
        switch (method) {
            case CREATE:
                log.debug("Creating new author: "+author.getFirstname()+" "+author.getSurname());
                authorManager.createAuthor(author);
                break;
            case DELETE:
                try {
                    log.debug("Deleting author: "+author.getFirstname()+" "+author.getSurname());
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
                            log.error("Couldn't delete author. Probably there are some books assigned to him.");
                        }
                    });
                }
                break;
            case UPDATE:
                log.debug("Updating author on: "+author.getFirstname()+" "+author.getSurname());
                authorManager.updateAuthor(author);
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
