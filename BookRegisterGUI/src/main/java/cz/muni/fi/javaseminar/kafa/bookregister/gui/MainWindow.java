/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui;

import cz.muni.fi.javaseminar.kafa.bookregister.gui.backend.BackendService;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.model.AuthorsTableModel;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.model.BooksTableModel;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableCellRenderer;
import org.jdesktop.swingx.table.DatePickerCellEditor;

/**
 *
 * @author Martin
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        authorsTableModel = new AuthorsTableModel();
        booksTableModel = new BooksTableModel();
        initComponents();
        booksTable.getColumnModel().getColumn(2).setCellEditor(new DatePickerCellEditor(new SimpleDateFormat("dd. MM. yyyy")));
        booksTable.getColumnModel().getColumn(2).setCellRenderer(new DateCellRenderer());
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        authorsTable.getColumnModel().getColumn(2).setCellEditor(new DatePickerCellEditor(new SimpleDateFormat("dd. MM. yyyy")));
        authorsTable.getColumnModel().getColumn(2).setCellRenderer(new DateCellRenderer());
        authorsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel selectionModel = authorsTable.getSelectionModel();

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                DefaultListSelectionModel source = (DefaultListSelectionModel) e.getSource();
                if (source.getMinSelectionIndex() >= 0) {
                    authorsTableModel.setCurrentSlectedIndex(source.getMinSelectionIndex());
                }

                booksTableModel.setAuthorIndex(source.getMinSelectionIndex());
            }
        });

        JFrame t = this;

        JPopupMenu authorsPopupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");

        authorsPopupMenu.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = authorsTable.rowAtPoint(SwingUtilities.convertPoint(authorsPopupMenu, new Point(0, 0), authorsTable));
                        if (rowAtPoint > -1) {
                            authorsTable.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                            authorsTableModel.setCurrentSlectedIndex(rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }
        });
        deleteItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    authorsTableModel.deleteAuthorAtIndex(authorsTable.getSelectedRow());

                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(t, "Couldn't delete author. Reason: " + ex.getMessage());
                }

            }
        });
        authorsPopupMenu.add(deleteItem);
        authorsTable.setComponentPopupMenu(authorsPopupMenu);

        JPopupMenu booksPopupMenu = new JPopupMenu();
        JMenuItem deleteBook = new JMenuItem("Delete");
        booksPopupMenu.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = booksTable.rowAtPoint(SwingUtilities.convertPoint(booksPopupMenu, new Point(0, 0), booksTable));
                        if (rowAtPoint > -1) {
                            booksTable.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }
        });
        deleteBook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (booksTable.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(t, "You haven't selected any book.");
                    return;
                }
                booksTableModel.deleteBookAtIndex(booksTable.getSelectedRow());

            }
        });
        booksPopupMenu.add(deleteBook);
        booksTable.setComponentPopupMenu(booksPopupMenu);
    }

    private void getFocusBack() {
        this.setEnabled(true);
        this.toFront();
        booksTableModel.updateData();
        authorsTableModel.updateData();
        if (authorsTableModel.getRowCount() != 0) {
            authorsTable.setRowSelectionInterval(authorsTableModel.getCurrentSlectedIndex(), authorsTableModel.getCurrentSlectedIndex());
        }
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Book Register 1.0");
        setMinimumSize(new java.awt.Dimension(1024, 480));
        setPreferredSize(new java.awt.Dimension(1024, 640));
        getContentPane().setLayout(new java.awt.GridLayout(2, 1));

        authorsPanel.setLayout(new java.awt.BorderLayout());

        authorsLabel.setText("Authors");
        authorsPanel.add(authorsLabel, java.awt.BorderLayout.PAGE_START);

        authorsTable.setModel(authorsTableModel);
        authorsTable.setMinimumSize(new java.awt.Dimension(480, 640));
        authorsScrollPane.setViewportView(authorsTable);

        authorsPanel.add(authorsScrollPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(authorsPanel);

        booksPanel.setLayout(new java.awt.BorderLayout());

        booksTable.setModel(booksTableModel);
        booksScrollPane.setViewportView(booksTable);

        booksPanel.add(booksScrollPane, java.awt.BorderLayout.CENTER);

        booksLabel.setText("Books");
        booksPanel.add(booksLabel, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(booksPanel);

        fileMenu.setText("File");

        newAuthorMenuItem.setText("New author...");
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
            }

            @Override
            public void windowClosing(WindowEvent e) {
                getFocusBack();
                e.getWindow().dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }//GEN-LAST:event_newAuthorMenuItemActionPerformed

    private void newBookMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBookMenuItemActionPerformed
        if (BackendService.getAuthorManager().findAllAuthors().size() == 0) {
            JOptionPane.showMessageDialog(this, "First create some authors.");
            return;
        }
        JFrame newBookWindow = new NewBookWindow();
        newBookWindow.setVisible(true);
        this.setEnabled(false);
        newBookWindow.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                getFocusBack();
                e.getWindow().dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }//GEN-LAST:event_newBookMenuItemActionPerformed

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
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            }
        });
    }

    private AuthorsTableModel authorsTableModel;
    private BooksTableModel booksTableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorsLabel;
    private javax.swing.JPanel authorsPanel;
    private javax.swing.JScrollPane authorsScrollPane;
    private javax.swing.JTable authorsTable;
    private javax.swing.JLabel booksLabel;
    private javax.swing.JPanel booksPanel;
    private javax.swing.JScrollPane booksScrollPane;
    private javax.swing.JTable booksTable;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenuItem newAuthorMenuItem;
    private javax.swing.JMenuItem newBookMenuItem;
    // End of variables declaration//GEN-END:variables

    public class DateCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean selected, boolean hasFocus, int row, int column) {

            if (value instanceof Date) {

                // You could use SimpleDateFormatter instead
                value = new SimpleDateFormat("dd. MM. yyyy").format(value);

            }

            return super.getTableCellRendererComponent(jtable, value, selected, hasFocus, row, column);

        }
    }
}
