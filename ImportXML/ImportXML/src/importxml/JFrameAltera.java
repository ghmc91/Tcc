/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importxml;

import com.mysql.jdbc.Connection;
import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.WindowConstants;

/**
 *
 * @author Gustavo
 */
public class JFrameAltera extends javax.swing.JDialog {

        
    public JFrameAltera(Frame parent, boolean isModal) {
        super(parent,isModal);
        initComponents();
        
    }

    JFrameAltera() {
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${defaultCloseOperation}"), this, org.jdesktop.beansbinding.BeanProperty.create("defaultCloseOperation"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        bindingGroup.bind();

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
            java.util.logging.Logger.getLogger(JFrameAltera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameAltera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameAltera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameAltera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                          }
        });
    }

    void updateTable() {
        
        String tipoCurso = "";
        String nomeCurso = "";
        JFPesquisarCurso jFPc = new JFPesquisarCurso();

        Connection conn = new ConnectionFactory().getConnection();

        for (int i = 0; i <= jFPc.table.getSelectedRow(); i++) {
            tipoCurso = (String) jFPc.table.getValueAt(i, 0);
            nomeCurso = (String) jFPc.table.getValueAt(i, 1);
        }

        JFNovoCurso janela = new JFNovoCurso();

        janela.jTxt1.setText(nomeCurso);
        janela.jCbx1.setSelectedItem(tipoCurso);
        janela.setVisible(true);

        String queryId = "SELECT idCurso FROM curso Where nomeCurso = "
                + "'" + janela.jTxt1.getText() + "'";

        try {

            java.sql.PreparedStatement stmt = conn.prepareStatement(queryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //janela.id = rs.getString("idCurso");
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String nC = janela.jTxt1.getText();
        String jC = janela.jCbx1.getSelectedItem().toString();

        String queryUp = "UPDATE curso SET nomeCurso = '" + nC + ""
                + "', tipoCurso = '" + jC + "' WHERE idCurso = "
                + "'" + janela.id + "'";

        try {
            java.sql.PreparedStatement stmt1 = conn.prepareStatement(queryUp);
            stmt1.executeUpdate();
            stmt1.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(JFNovoCurso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
