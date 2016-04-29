/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui;

import cz.muni.fi.javaseminar.kafa.bookregister.Author;
import cz.muni.fi.javaseminar.kafa.bookregister.AuthorManager;
import cz.muni.fi.javaseminar.kafa.bookregister.Book;
import cz.muni.fi.javaseminar.kafa.bookregister.BookManager;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.backend.BackendService;
import java.awt.event.WindowEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import no.tornado.databinding.model.ListComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Martin
 */
public class NewBookWindow extends javax.swing.JFrame {

    private AuthorManager am = BackendService.getAuthorManager();
    private BookManager bm = BackendService.getBookManager();

    /**
     * Creates new form NewBookWindow
     */
    public NewBookWindow() {
        initComponents();
        publishDatePanel.add(datePicker);
        List<String> authors = am.findAllAuthors()
                .stream().map((Author x) -> x.getFirstname() + " " + x.getSurname())
                .collect(Collectors.toList());
        authorComboBox.setModel(new ListComboBoxModel(authors));
        try {
            authorComboBox.setSelectedIndex(0);
        } catch (IllegalArgumentException e) {
            authorComboBox.setSelectedIndex(-1);
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

        buttonPanel = new javax.swing.JPanel();
        createButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        atributesPanel = new javax.swing.JPanel();
        windowLabel = new javax.swing.JLabel();
        namePanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        isbnPanel = new javax.swing.JPanel();
        isbnLabel = new javax.swing.JLabel();
        isbnTextField = new javax.swing.JTextField();
        publishDatePanel = new javax.swing.JPanel();
        publishDateLabel = new javax.swing.JLabel();
        authorPanel = new javax.swing.JPanel();
        authorLabel = new javax.swing.JLabel();
        authorComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Book");
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(400, 250));
        setResizable(false);

        buttonPanel.setLayout(new java.awt.GridLayout(1, 2));

        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(createButton);

        cancelButton.setText("Cancel");
        buttonPanel.add(cancelButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        atributesPanel.setMinimumSize(new java.awt.Dimension(400, 500));
        atributesPanel.setPreferredSize(new java.awt.Dimension(400, 500));
        atributesPanel.setLayout(new java.awt.GridLayout(6, 1));

        windowLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        windowLabel.setText("Create new book:");
        atributesPanel.add(windowLabel);

        namePanel.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        namePanel.setPreferredSize(new java.awt.Dimension(127, 12));
        namePanel.setLayout(new javax.swing.BoxLayout(namePanel, javax.swing.BoxLayout.LINE_AXIS));

        nameLabel.setText("Name:");
        nameLabel.setMinimumSize(new java.awt.Dimension(100, 16));
        nameLabel.setName(""); // NOI18N
        nameLabel.setPreferredSize(new java.awt.Dimension(80, 16));
        namePanel.add(nameLabel);

        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });
        namePanel.add(nameTextField);

        atributesPanel.add(namePanel);

        isbnPanel.setLayout(new javax.swing.BoxLayout(isbnPanel, javax.swing.BoxLayout.LINE_AXIS));

        isbnLabel.setText("ISBN:");
        isbnLabel.setMinimumSize(new java.awt.Dimension(100, 16));
        isbnLabel.setName(""); // NOI18N
        isbnLabel.setPreferredSize(new java.awt.Dimension(80, 16));
        isbnPanel.add(isbnLabel);

        isbnTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isbnTextFieldActionPerformed(evt);
            }
        });
        isbnPanel.add(isbnTextField);

        atributesPanel.add(isbnPanel);

        publishDatePanel.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        publishDatePanel.setPreferredSize(new java.awt.Dimension(127, 12));
        publishDatePanel.setLayout(new java.awt.GridLayout(1, 0));

        publishDateLabel.setText("Publish Date:");
        publishDateLabel.setMinimumSize(new java.awt.Dimension(100, 16));
        publishDateLabel.setName(""); // NOI18N
        publishDateLabel.setPreferredSize(new java.awt.Dimension(80, 16));
        publishDateLabel.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                publishDateLabelAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        publishDatePanel.add(publishDateLabel);

        atributesPanel.add(publishDatePanel);

        authorPanel.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        authorPanel.setPreferredSize(new java.awt.Dimension(127, 12));
        authorPanel.setLayout(new javax.swing.BoxLayout(authorPanel, javax.swing.BoxLayout.LINE_AXIS));

        authorLabel.setText("Author:");
        authorLabel.setMinimumSize(new java.awt.Dimension(100, 16));
        authorLabel.setName(""); // NOI18N
        authorLabel.setPreferredSize(new java.awt.Dimension(80, 16));
        authorPanel.add(authorLabel);

        authorComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        authorComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorComboBoxActionPerformed(evt);
            }
        });
        authorPanel.add(authorComboBox);

        atributesPanel.add(authorPanel);

        getContentPane().add(atributesPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void isbnTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isbnTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isbnTextFieldActionPerformed

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        Instant instant = Instant.ofEpochMilli(datePicker.getDate().getTime());
        LocalDate date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        Book newBook = new Book(null, nameTextField.getText(), isbnTextField.getText(), date, am.findAllAuthors().get(authorComboBox.getSelectedIndex()).getId());
        bm.createBook(newBook);
        MainWindow.updateBookTable(authorComboBox.getSelectedIndex());
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_createButtonActionPerformed

    private void publishDateLabelAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_publishDateLabelAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_publishDateLabelAncestorAdded

    private void authorComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_authorComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(NewBookWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewBookWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewBookWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewBookWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewBookWindow().setVisible(true);
            }
        });
    }

    private JXDatePicker datePicker = new JXDatePicker();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel atributesPanel;
    private javax.swing.JComboBox authorComboBox;
    private javax.swing.JLabel authorLabel;
    private javax.swing.JPanel authorPanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel isbnLabel;
    private javax.swing.JPanel isbnPanel;
    private javax.swing.JTextField isbnTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JPanel namePanel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel publishDateLabel;
    private javax.swing.JPanel publishDatePanel;
    private javax.swing.JLabel windowLabel;
    // End of variables declaration//GEN-END:variables
}
