/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 *
 * @author Martin
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        authorsPanel = new javax.swing.JPanel();
        authorsLabel = new javax.swing.JLabel();
        authorsScrollPane = new javax.swing.JScrollPane();
        authorsTable = new javax.swing.JTable();
        booksPanel = new javax.swing.JPanel();
        booksScrollPane = new javax.swing.JScrollPane();
        booksTable = new javax.swing.JTable();
        booksLabel = new javax.swing.JLabel();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newAuthorMenuItem = new javax.swing.JMenuItem();
        newBookMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        editAuthorMenuItem = new javax.swing.JMenuItem();
        editBookMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Book Register 1.0");
        setMinimumSize(new java.awt.Dimension(1024, 480));
        setPreferredSize(new java.awt.Dimension(1024, 640));
        getContentPane().setLayout(new java.awt.GridLayout(2, 1));

        authorsPanel.setLayout(new java.awt.BorderLayout());

        authorsLabel.setText("Authors");
        authorsPanel.add(authorsLabel, java.awt.BorderLayout.PAGE_START);

        authorsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"George", "Shaw", "08-20-1861", "Best of best"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Firstname", "Surname", "Date of Birth", "Description"
            }
        ));
        authorsTable.setColumnSelectionAllowed(true);
        authorsTable.setMinimumSize(new java.awt.Dimension(480, 640));
        authorsScrollPane.setViewportView(authorsTable);
        authorsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        authorsPanel.add(authorsScrollPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(authorsPanel);

        booksPanel.setLayout(new java.awt.BorderLayout());

        booksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Pigmalion", "456128741", "10-20-1840"},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "ISBN", "Published Date"
            }
        ));
        booksScrollPane.setViewportView(booksTable);

        booksPanel.add(booksScrollPane, java.awt.BorderLayout.CENTER);

        booksLabel.setText("Books");
        booksPanel.add(booksLabel, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(booksPanel);

        fileMenu.setText("File");

        newAuthorMenuItem.setText("New author");
        newAuthorMenuItem.setActionCommand("New author...");
        newAuthorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAuthorMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newAuthorMenuItem);

        newBookMenuItem.setText("New book...");
        newBookMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBookMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newBookMenuItem);

        mainMenuBar.add(fileMenu);

        editMenu.setText("Edit");

        editAuthorMenuItem.setText("Edit existing author...");
        editAuthorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editAuthorMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(editAuthorMenuItem);

        editBookMenuItem.setText("Edit existing book...");
        editBookMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBookMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(editBookMenuItem);

        mainMenuBar.add(editMenu);

        setJMenuBar(mainMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newAuthorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAuthorMenuItemActionPerformed
        JFrame newAuthorWindow = new NewAuthorWindow();
        newAuthorWindow.setVisible(true);
        this.setEnabled(false);
        newAuthorWindow.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent e) {
                mainWindow.setEnabled(true);
                e.getWindow().dispose();
                mainWindow.toFront();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }//GEN-LAST:event_newAuthorMenuItemActionPerformed

    private void newBookMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBookMenuItemActionPerformed
        JFrame newBookWindow = new NewBookWindow();
        newBookWindow.setVisible(true);
        this.setEnabled(false);
        newBookWindow.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent e) {
                mainWindow.setEnabled(true);
                e.getWindow().dispose();
                mainWindow.toFront();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }//GEN-LAST:event_newBookMenuItemActionPerformed

    private void editBookMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBookMenuItemActionPerformed
        JFrame editBookWindow = new EditBookWindow();
        editBookWindow.setVisible(true);
        this.setEnabled(false);
        editBookWindow.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent e) {
                mainWindow.setEnabled(true);
                e.getWindow().dispose();
                mainWindow.toFront();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }//GEN-LAST:event_editBookMenuItemActionPerformed

    private void editAuthorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAuthorMenuItemActionPerformed
       JFrame editAuthorWindow = new EditAuthorWindow();
        editAuthorWindow.setVisible(true);
        this.setEnabled(false);
        editAuthorWindow.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent e) {
                mainWindow.setEnabled(true);
                e.getWindow().dispose();
                mainWindow.toFront();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }//GEN-LAST:event_editAuthorMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            }
        });
    }

    private static MainWindow mainWindow;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorsLabel;
    private javax.swing.JPanel authorsPanel;
    private javax.swing.JScrollPane authorsScrollPane;
    private javax.swing.JTable authorsTable;
    private javax.swing.JLabel booksLabel;
    private javax.swing.JPanel booksPanel;
    private javax.swing.JScrollPane booksScrollPane;
    private javax.swing.JTable booksTable;
    private javax.swing.JMenuItem editAuthorMenuItem;
    private javax.swing.JMenuItem editBookMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenuItem newAuthorMenuItem;
    private javax.swing.JMenuItem newBookMenuItem;
    // End of variables declaration//GEN-END:variables
}
