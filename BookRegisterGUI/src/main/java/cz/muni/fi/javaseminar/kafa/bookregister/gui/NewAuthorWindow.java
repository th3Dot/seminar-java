/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookregister.gui;

/**
 *
 * @author olda
 */
public class NewAuthorWindow extends javax.swing.JFrame {

    /**
     * Creates new form NewAuthorWindow
     */
    public NewAuthorWindow() {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        namePanel4 = new javax.swing.JPanel();
        nameLabel4 = new javax.swing.JLabel();
        nameTextField4 = new javax.swing.JTextField();
        createButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setResizable(false);

        windowLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        windowLabel.setText("Create new author:");

        namePanel.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        namePanel.setPreferredSize(new java.awt.Dimension(127, 12));
        namePanel.setLayout(new javax.swing.BoxLayout(namePanel, javax.swing.BoxLayout.LINE_AXIS));

        nameLabel.setText("Firstname:");
        nameLabel.setMinimumSize(new java.awt.Dimension(100, 16));
        nameLabel.setName(""); // NOI18N
        nameLabel.setPreferredSize(new java.awt.Dimension(80, 16));
        namePanel.add(nameLabel);

        nameTextField.setText("Joshua ");
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });
        namePanel.add(nameTextField);

        namePanel1.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        namePanel1.setPreferredSize(new java.awt.Dimension(127, 12));
        namePanel1.setLayout(new javax.swing.BoxLayout(namePanel1, javax.swing.BoxLayout.LINE_AXIS));

        nameLabel1.setText("Surname:");
        nameLabel1.setMinimumSize(new java.awt.Dimension(100, 16));
        nameLabel1.setName(""); // NOI18N
        nameLabel1.setPreferredSize(new java.awt.Dimension(80, 16));
        namePanel1.add(nameLabel1);

        nameTextField1.setText("Boch");
        nameTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextField1ActionPerformed(evt);
            }
        });
        namePanel1.add(nameTextField1);

        namePanel2.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        namePanel2.setPreferredSize(new java.awt.Dimension(127, 12));
        namePanel2.setLayout(new javax.swing.BoxLayout(namePanel2, javax.swing.BoxLayout.LINE_AXIS));

        nameLabel2.setText("Nationality:");
        nameLabel2.setMinimumSize(new java.awt.Dimension(100, 16));
        nameLabel2.setName(""); // NOI18N
        nameLabel2.setPreferredSize(new java.awt.Dimension(80, 16));
        namePanel2.add(nameLabel2);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        namePanel2.add(jComboBox1);

        namePanel3.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        namePanel3.setPreferredSize(new java.awt.Dimension(127, 12));
        namePanel3.setLayout(new javax.swing.BoxLayout(namePanel3, javax.swing.BoxLayout.LINE_AXIS));

        nameLabel3.setText("Description:");
        nameLabel3.setMinimumSize(new java.awt.Dimension(100, 16));
        nameLabel3.setName(""); // NOI18N
        nameLabel3.setPreferredSize(new java.awt.Dimension(80, 16));
        namePanel3.add(nameLabel3);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Java expert");
        jScrollPane1.setViewportView(jTextArea1);

        namePanel3.add(jScrollPane1);

        namePanel4.setMaximumSize(new java.awt.Dimension(2147483647, 12));
        namePanel4.setPreferredSize(new java.awt.Dimension(127, 12));
        namePanel4.setLayout(new javax.swing.BoxLayout(namePanel4, javax.swing.BoxLayout.LINE_AXIS));

        nameLabel4.setText("Date of Birth:");
        nameLabel4.setMinimumSize(new java.awt.Dimension(100, 16));
        nameLabel4.setName(""); // NOI18N
        nameLabel4.setPreferredSize(new java.awt.Dimension(80, 16));
        namePanel4.add(nameLabel4);

        nameTextField4.setText("08-28-1961");
        nameTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextField4ActionPerformed(evt);
            }
        });
        namePanel4.add(nameTextField4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(windowLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namePanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(windowLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namePanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namePanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        createButton.setText("Create");

        jButton1.setText("Create");

        jButton2.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 221, Short.MAX_VALUE)
                    .addComponent(createButton)
                    .addGap(0, 221, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void nameTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextField1ActionPerformed

    private void nameTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextField4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
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
    private javax.swing.JTextField nameTextField4;
    private javax.swing.JLabel windowLabel;
    // End of variables declaration//GEN-END:variables
}