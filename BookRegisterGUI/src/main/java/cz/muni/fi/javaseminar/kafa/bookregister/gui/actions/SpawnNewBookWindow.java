/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui.actions;

import cz.muni.fi.javaseminar.kafa.bookregister.gui.MainWindow;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.NewBookWindow;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Martin
 */
public class SpawnNewBookWindow extends AbstractSpawnWindowAction {

    public SpawnNewBookWindow(String name, MainWindow spawningWindow) {
        super(name, spawningWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (spawningWindow.getAuthorsTableModel().getRowCount() == 0) {
            JOptionPane.showMessageDialog(spawningWindow, "You have to create some author first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFrame newBookWindow = new NewBookWindow();
        newBookWindow.addWindowListener(focusPassingListener);
        newBookWindow.setVisible(true);
        spawningWindow.setEnabled(false);
    }
}
