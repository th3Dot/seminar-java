/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui.actions;

import cz.muni.fi.javaseminar.kafa.bookregister.gui.NewBookWindow;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

/**
 *
 * @author Martin
 */
public class SpawnNewBookWindow extends AbstractSpawnWindowAction {

    public SpawnNewBookWindow(String name, JFrame spawningWindow) {
        super(name, spawningWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame newBookWindow = new NewBookWindow();
        newBookWindow.addWindowListener(focusPassingListener);
        newBookWindow.setVisible(true);
        spawningWindow.setEnabled(false);
    }
}
