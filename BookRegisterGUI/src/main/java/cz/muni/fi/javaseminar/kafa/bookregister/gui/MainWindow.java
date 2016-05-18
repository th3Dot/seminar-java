/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui;

import cz.muni.fi.javaseminar.kafa.bookregister.Author;
import cz.muni.fi.javaseminar.kafa.bookregister.AuthorManager;
import cz.muni.fi.javaseminar.kafa.bookregister.BookManager;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.actions.SpawnNewAuthorWindow;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.actions.SpawnNewBookWindow;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.backend.BackendService;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.model.AuthorsTableModel;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.model.BooksTableModel;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.workers.BookBackendWorker;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableCellRenderer;
import org.jdesktop.swingx.table.DatePickerCellEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin
 */
public class MainWindow extends javax.swing.JFrame {

    private final SpawnNewAuthorWindow spawnNewAuthorWindowAction = new SpawnNewAuthorWindow(java.util.ResourceBundle.getBundle("cz/muni/fi/javaseminar/kafa/bookregister/gui/Bundle").getString("Menu.file.newAuthor"), this);
    private final SpawnNewBookWindow spawnNewBookWindowAction = new SpawnNewBookWindow(java.util.ResourceBundle.getBundle("cz/muni/fi/javaseminar/kafa/bookregister/gui/Bundle").getString("Menu.file.newBook"), this);
    private AuthorsTableModel authorsTableModel;
    private BooksTableModel booksTableModel;

    public BookManager getBookManager() {
        return bookManager;
    }

    public AuthorManager getAuthorManager() {
        return authorManager;
    }
    private final BookManager bookManager;
    private final AuthorManager authorManager;
    private final static Logger log = LoggerFactory.getLogger(MainWindow.class);

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        authorManager = BackendService.getAuthorManager();
        bookManager = BackendService.getBookManager();
        initTableModels();
        initComponents();
        initAuthorsTable();
        initBooksTable();
        initMainWindow();
    }

    private void initMainWindow() {
        this.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                updateModel();
            }
        });

        this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK), "NEW_AUTHOR");
        this.getRootPane().getActionMap().put("NEW_AUTHOR", spawnNewAuthorWindowAction);
    }

    private void initTableModels() {
        authorsTableModel = new AuthorsTableModel(authorManager, bookManager);
        booksTableModel = new BooksTableModel();
    }

    private void initBooksTable() {
        booksTable.getColumnModel().getColumn(2).setCellEditor(new DatePickerCellEditor(new SimpleDateFormat("dd. MM. yyyy")));
        booksTable.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object value, boolean selected, boolean hasFocus, int row, int column) {

                if (value instanceof Date) {

                    // You could use SimpleDateFormatter instead
                    value = new SimpleDateFormat("dd. MM. yyyy").format(value);

                }

                return super.getTableCellRendererComponent(jtable, value, selected, hasFocus, row, column);

            }
        });
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
                    JOptionPane.showMessageDialog(MainWindow.this, "You haven't selected any book.");
                    return;
                }
                BookBackendWorker worker = new BookBackendWorker(booksTableModel.getBooks().get(booksTable.getSelectedRow()), BookBackendWorker.Method.DELETE);
                worker.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if (((SwingWorker.StateValue) evt.getNewValue()).equals(SwingWorker.StateValue.DONE)) {
                            updateModel();
                        }
                    }

                });
                worker.execute();

            }
        });
        booksPopupMenu.add(deleteBook);
        booksTable.setComponentPopupMenu(booksPopupMenu);
    }

    private void initAuthorsTable() {
        authorsTable.getColumnModel().getColumn(2).setCellEditor(new DatePickerCellEditor(new SimpleDateFormat("dd. MM. yyyy")));
        authorsTable.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object value, boolean selected, boolean hasFocus, int row, int column) {

                if (value instanceof Date) {

                    // You could use SimpleDateFormatter instead
                    value = new SimpleDateFormat("dd. MM. yyyy").format(value);

                }

                return super.getTableCellRendererComponent(jtable, value, selected, hasFocus, row, column);

            }
        });

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

        authorsTable.getColumnModel().getColumn(2).setCellEditor(new DatePickerCellEditor(new SimpleDateFormat("dd. MM. yyyy")));
        authorsTable.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object value, boolean selected, boolean hasFocus, int row, int column) {

                if (value instanceof Date) {

                    // You could use SimpleDateFormatter instead
                    value = new SimpleDateFormat("dd. MM. yyyy").format(value);

                }

                return super.getTableCellRendererComponent(jtable, value, selected, hasFocus, row, column);

            }
        });

        authorsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel = authorsTable.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                DefaultListSelectionModel source = (DefaultListSelectionModel) e.getSource();
                if (source.getMinSelectionIndex() >= 0) {
                    authorsTableModel.setCurrentSlectedIndex(source.getMinSelectionIndex());
                }

                booksTableModel.setAuthorIndex(source.getMinSelectionIndex());
            }
        });

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
                new SwingWorker<Void, Void>() {
                    private Author author;

                    @Override
                    protected Void doInBackground() throws Exception {
                        author = authorsTableModel.getAuthors().get(authorsTable.getSelectedRow());
                        log.debug("Deleting author: " + author.getFirstname() + " " + author.getSurname());
                        authorManager.deleteAuthor(author);

                        return null;
                    }

                    @Override
                    protected void done() {
                        try {
                            get();
                        } catch (Exception e) {
                            log.error("There was an exception thrown during deletion author: " + author.getFirstname() + " " + author.getSurname(), e);
                            JOptionPane.showMessageDialog(MainWindow.this, "Couldn't delete author. Reason: " + e.getMessage());
                            return;
                        }

                        updateModel();
                    }
                }.execute();
            }
        });
        authorsPopupMenu.add(deleteItem);
        authorsTable.setComponentPopupMenu(authorsPopupMenu);
    }

    public void updateModel() {
        authorsTableModel.updateData();

        if (authorsTableModel.getRowCount() > 0) {
            authorsTable.setRowSelectionInterval(authorsTableModel.getCurrentSlectedIndex(), authorsTableModel.getCurrentSlectedIndex());
            spawnNewBookWindowAction.setEnabled(true);
        } else {
            spawnNewBookWindowAction.setEnabled(false);
        }

        booksTableModel.updateData();
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

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("cz/muni/fi/javaseminar/kafa/bookregister/gui/Bundle"); // NOI18N
        authorsLabel.setText(bundle.getString("Table.authors.title")); // NOI18N
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

        booksLabel.setText(bundle.getString("Table.books.title")); // NOI18N
        booksPanel.add(booksLabel, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(booksPanel);

        fileMenu.setText(bundle.getString("Menu.file")); // NOI18N

        newAuthorMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        newAuthorMenuItem.setText(bundle.getString("Menu.file.newAuthor")); // NOI18N
        newAuthorMenuItem.setMnemonic(KeyEvent.VK_N);
        newAuthorMenuItem.setAction(spawnNewAuthorWindowAction);
        newAuthorMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        fileMenu.add(newAuthorMenuItem);

        newBookMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        newBookMenuItem.setText(bundle.getString("Menu.file.newBook")); // NOI18N
        newBookMenuItem.setMnemonic(KeyEvent.VK_B);
        newBookMenuItem.setAction(spawnNewBookWindowAction);
        newBookMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));

        newBookMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JMenuItem m = (JMenuItem) e.getSource();
                if (!m.isEnabled())
                JOptionPane.showMessageDialog(MainWindow.this, "You have to create an author first.");
            }
        });
        fileMenu.add(newBookMenuItem);

        fileMenu.setMnemonic(KeyEvent.VK_F);

        mainMenuBar.add(fileMenu);

        setJMenuBar(mainMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

}
