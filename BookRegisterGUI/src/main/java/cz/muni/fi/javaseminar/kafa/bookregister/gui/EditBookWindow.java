/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui;

/**
 *
 * @author Martin
 */
public class EditBookWindow extends javax.swing.JFrame {

    /**
     * Creates new form NewBookWindow
     */
    public EditBookWindow() {
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

        buttonPanel = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        attributesPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        namePanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        isbnPanel = new javax.swing.JPanel();
        isbnLabel = new javax.swing.JLabel();
        isbnTextField = new javax.swing.JTextField();
        publishDatePanel = new javax.swing.JPanel();
        publishDateLabel = new javax.swing.JLabel();
        publishDateTextField = new javax.swing.JTextField();
        authorPanel = new javax.swing.JPanel();
        authorLabel = new javax.swing.JLabel();
        authorComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Book");
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(400, 250));
        setResizable(false);

        buttonPanel.setLayout(new java.awt.GridLayout(1, 2));

        saveButton.setText("Save");
        buttonPanel.add(saveButton);

        cancelButton.setText("Cancel");
        buttonPanel.add(cancelButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        attributesPanel.setMinimumSize(new java.awt.Dimension(400, 500));
        attributesPanel.setPreferredSize(new java.awt.Dimension(400, 250));
        attributesPanel.setLayout(new java.awt.GridLayout(6, 1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Edit existing book:");
        attributesPanel.add(jLabel1);

        namePanel.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        namePanel.setPreferredSize(new java.awt.Dimension(127, 12));
        namePanel.setLayout(new javax.swing.BoxLayout(namePanel, javax.swing.BoxLayout.LINE_AXIS));

        nameLabel.setText("Name:");
        nameLabel.setMinimumSize(new java.awt.Dimension(100, 16));
        nameLabel.setName(""); // NOI18N
        nameLabel.setPreferredSize(new java.awt.Dimension(80, 16));
        namePanel.add(nameLabel);

        nameTextField.setText("Pigmalion");
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });
        namePanel.add(nameTextField);

        attributesPanel.add(namePanel);

        isbnPanel.setLayout(new javax.swing.BoxLayout(isbnPanel, javax.swing.BoxLayout.LINE_AXIS));

        isbnLabel.setText("ISBN:");
        isbnLabel.setMinimumSize(new java.awt.Dimension(100, 16));
        isbnLabel.setName(""); // NOI18N
        isbnLabel.setPreferredSize(new java.awt.Dimension(80, 16));
        isbnPanel.add(isbnLabel);

        isbnTextField.setText("456128741");
        isbnTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isbnTextFieldActionPerformed(evt);
            }
        });
        isbnPanel.add(isbnTextField);

        attributesPanel.add(isbnPanel);

        publishDatePanel.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        publishDatePanel.setPreferredSize(new java.awt.Dimension(127, 12));
        publishDatePanel.setLayout(new javax.swing.BoxLayout(publishDatePanel, javax.swing.BoxLayout.LINE_AXIS));

        publishDateLabel.setText("Publish Date:");
        publishDateLabel.setMinimumSize(new java.awt.Dimension(100, 16));
        publishDateLabel.setName(""); // NOI18N
        publishDateLabel.setPreferredSize(new java.awt.Dimension(80, 16));
        publishDatePanel.add(publishDateLabel);

        publishDateTextField.setText("10-20-1840");
        publishDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publishDateTextFieldActionPerformed(evt);
            }
        });
        publishDatePanel.add(publishDateTextField);

        attributesPanel.add(publishDatePanel);

        authorPanel.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        authorPanel.setPreferredSize(new java.awt.Dimension(127, 12));
        authorPanel.setLayout(new javax.swing.BoxLayout(authorPanel, javax.swing.BoxLayout.LINE_AXIS));

        authorLabel.setText("Author:");
        authorLabel.setMinimumSize(new java.awt.Dimension(100, 16));
        authorLabel.setName(""); // NOI18N
        authorLabel.setPreferredSize(new java.awt.Dimension(80, 16));
        authorPanel.add(authorLabel);

        authorComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        authorPanel.add(authorComboBox);

        attributesPanel.add(authorPanel);

        getContentPane().add(attributesPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void isbnTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isbnTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isbnTextFieldActionPerformed

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void publishDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publishDateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_publishDateTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(EditBookWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditBookWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditBookWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditBookWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditBookWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel attributesPanel;
    private javax.swing.JComboBox authorComboBox;
    private javax.swing.JLabel authorLabel;
    private javax.swing.JPanel authorPanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel isbnLabel;
    private javax.swing.JPanel isbnPanel;
    private javax.swing.JTextField isbnTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JPanel namePanel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel publishDateLabel;
    private javax.swing.JPanel publishDatePanel;
    private javax.swing.JTextField publishDateTextField;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
