/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui.workers;

import cz.muni.fi.javaseminar.kafa.bookregister.AuthorManager;
import cz.muni.fi.javaseminar.kafa.bookregister.BookManager;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.backend.BackendService;
import javax.swing.SwingWorker;

/**
 *
 * @author Martin
 */
public abstract class AbstractBackendWorker extends SwingWorker<Void, Void> {

    protected AuthorManager authorManager = BackendService.getAuthorManager();
    protected BookManager bookManager = BackendService.getBookManager();

}
