/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui;

import cz.muni.fi.javaseminar.kafa.bookregister.Author;
import cz.muni.fi.javaseminar.kafa.bookregister.AuthorManager;
import cz.muni.fi.javaseminar.kafa.bookregister.BookManager;
import cz.muni.fi.javaseminar.kafa.bookregister.gui.backend.BackendService;
import java.awt.event.WindowEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import no.tornado.databinding.model.ListComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author olda
 */
public class NewAuthorWindow extends javax.swing.JFrame {

    private AuthorManager am = BackendService.getAuthorManager();
    private BookManager bm = BackendService.getBookManager();

    /**
     * Creates new form NewAuthorWindow
     */
    public NewAuthorWindow() {
        initComponents();
        namePanel4.add(datePicker);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        windowLabel = new javax.swing.JLabel();
        namePanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        namePanel1 = new javax.swing.JPanel();
        nameLabel1 = new javax.swing.JLabel();
        nameTextField1 = new javax.swing.JTextField();
        namePanel2 = new javax.swing.JPanel();
        nameLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        namePanel3 = new javax.swing.JPanel();
        nameLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        namePanel4 = new javax.swing.JPanel();
        nameLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 250));
        jPanel1.setLayout(new java.awt.GridLayout(6, 1));

        windowLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        windowLabel.setText("Create new author:");
        jPanel1.add(windowLabel);

        namePanel.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        namePanel.setPreferredSize(new java.awt.Dimension(127, 12));
        namePanel.setLayout(new javax.swing.BoxLayout(namePanel, javax.swing.BoxLayout.LINE_AXIS));

        nameLabel.setText("Firstname:");
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

        jPanel1.add(namePanel);

        namePanel1.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        namePanel1.setPreferredSize(new java.awt.Dimension(127, 12));
        namePanel1.setLayout(new javax.swing.BoxLayout(namePanel1, javax.swing.BoxLayout.LINE_AXIS));

        nameLabel1.setText("Surname:");
        nameLabel1.setMinimumSize(new java.awt.Dimension(100, 16));
        nameLabel1.setName(""); // NOI18N
        nameLabel1.setPreferredSize(new java.awt.Dimension(80, 16));
        namePanel1.add(nameLabel1);

        nameTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextField1ActionPerformed(evt);
            }
        });
        namePanel1.add(nameTextField1);

        jPanel1.add(namePanel1);

        namePanel2.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        namePanel2.setPreferredSize(new java.awt.Dimension(127, 12));
        namePanel2.setLayout(new javax.swing.BoxLayout(namePanel2, javax.swing.BoxLayout.LINE_AXIS));

        nameLabel2.setText("Nationality:");
        nameLabel2.setMinimumSize(new java.awt.Dimension(100, 16));
        nameLabel2.setName(""); // NOI18N
        nameLabel2.setPreferredSize(new java.awt.Dimension(80, 16));
        namePanel2.add(nameLabel2);

        List<String> countries = new ArrayList<>();
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            countries.add(obj.getDisplayCountry());
        }
        jComboBox1.setModel(new ListComboBoxModel(countries));
        jComboBox1.setSelectedIndex(0);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        namePanel2.add(jComboBox1);

        jPanel1.add(namePanel2);

        namePanel3.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        namePanel3.setPreferredSize(new java.awt.Dimension(127, 12));
        namePanel3.setLayout(new javax.swing.BoxLayout(namePanel3, javax.swing.BoxLayout.LINE_AXIS));

        nameLabel3.setText("Description:");
        nameLabel3.setMinimumSize(new java.awt.Dimension(100, 16));
        nameLabel3.setName(""); // NOI18N
        nameLabel3.setPreferredSize(new java.awt.Dimension(80, 16));
        namePanel3.add(nameLabel3);
        namePanel3.add(jTextField1);

        jPanel1.add(namePanel3);

        namePanel4.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        namePanel4.setPreferredSize(new java.awt.Dimension(127, 12));
        namePanel4.setLayout(new javax.swing.BoxLayout(namePanel4, javax.swing.BoxLayout.LINE_AXIS));

        nameLabel4.setText("Date of Birth:");
        nameLabel4.setMinimumSize(new java.awt.Dimension(100, 16));
        nameLabel4.setName(""); // NOI18N
        nameLabel4.setPreferredSize(new java.awt.Dimension(80, 16));
        namePanel4.add(nameLabel4);

        jPanel1.add(namePanel4);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setText("Create");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void nameTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Instant instant;
        LocalDate date;
        if (datePicker.getDate() != null) {
            instant = Instant.ofEpochMilli(datePicker.getDate().getTime());
            date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        } else {
            date = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toLocalDate();
        }

        Author newAuthor = new Author(null, nameTextField.getText(), nameTextField1.getText(), jTextField1.getText(), (String) jComboBox1.getSelectedItem(), date);
        am.createAuthor(newAuthor);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(NewAuthorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewAuthorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewAuthorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewAuthorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewAuthorWindow().setVisible(true);
            }
        });
    }
    private JXDatePicker datePicker = new JXDatePicker();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JLabel nameLabel2;
    private javax.swing.JLabel nameLabel3;
    private javax.swing.JLabel nameLabel4;
    private javax.swing.JPanel namePanel;
    private javax.swing.JPanel namePanel1;
    private javax.swing.JPanel namePanel2;
    private javax.swing.JPanel namePanel3;
    private javax.swing.JPanel namePanel4;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField nameTextField1;
    private javax.swing.JLabel windowLabel;
    // End of variables declaration//GEN-END:variables
}
