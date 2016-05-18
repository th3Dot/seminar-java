/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui.actions;

import cz.muni.fi.javaseminar.kafa.bookregister.gui.MainWindow;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.NewAuthorWindow;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

/**
 *
 * @author Martin
 */
public class SpawnNewAuthorWindow extends AbstractSpawnWindowAction {

    public SpawnNewAuthorWindow(String name, MainWindow spawningWindow) {
        super(name, spawningWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame newAuthorWindow = new NewAuthorWindow();
        newAuthorWindow.addWindowListener(focusPassingListener);
        newAuthorWindow.setVisible(true);
        spawningWindow.setEnabled(false);
    }

}
