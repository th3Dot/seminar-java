/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui.actions;

import cz.muni.fi.javaseminar.kafa.bookregister.gui.MainWindow;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.AbstractAction;

/**
 *
 * @author Martin
 */
public abstract class AbstractSpawnWindowAction extends AbstractAction {

    protected MainWindow spawningWindow;
    protected WindowListener focusPassingListener;

    public AbstractSpawnWindowAction(String name, MainWindow spawningWindow) {
        super(name);
        this.spawningWindow = spawningWindow;
        this.focusPassingListener = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                getFocusBack();
                e.getWindow().dispose();
            }
        };
    }

    private void getFocusBack() {
        spawningWindow.setEnabled(true);
        spawningWindow.requestFocus();
        spawningWindow.toFront();
    }
}
