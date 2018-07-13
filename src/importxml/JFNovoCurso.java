/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importxml;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Container;
import java.awt.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Gustavo
 */
public class JFNovoCurso extends javax.swing.JFrame {

    JTextField jTxt1, jTFV;
    JComboBox<String> jCbx1;
    JButton jButton;
    int id;
    String nomeCurso, vagas;
    String tipoCurso;

    public String getVagas() {
        vagas = jTFVagas.getText();
        return vagas;
    }

    public void setVagas(String vagas) {
        this.vagas = vagas;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Creates new form JFNovoCurso
     */
    public JFNovoCurso() {

        initComponents();
        this.jComboBoxTipoCurso.removeAllItems();

        this.jComboBoxTipoCurso.addItem("Técnico");
        this.jComboBoxTipoCurso.addItem("Superior");
        this.jComboBoxTipoCurso.addItem("Pós-Graduação");
        this.jComboBoxTipoCurso.addItem("Mestrado");

        this.jTxt1 = jTFNomeCurso;
        this.jCbx1 = jComboBoxTipoCurso;
        this.jButton = jButtonSalvar;
        this.jTFV = jTFVagas;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTFNomeCurso = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxTipoCurso = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTFVagas = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCurso = new javax.swing.JMenu();
        jMenuPrincipal = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuNovaDisiciplina = new javax.swing.JMenuItem();
        jMenuPesquisarDisciplina = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuNovaMatriz = new javax.swing.JMenuItem();
        jMenuPesquisarMatriz = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome do curso: ");

        jTFNomeCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNomeCursoActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo: ");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jLabel3.setText("--  Novo Curso --");

        jComboBoxTipoCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxTipoCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoCursoActionPerformed(evt);
            }
        });

        jLabel4.setText("Vagas: ");

        jMenuCurso.setText("Importar");

        jMenuPrincipal.setText("Importar...");
        jMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPrincipalActionPerformed(evt);
            }
        });
        jMenuCurso.add(jMenuPrincipal);

        jMenuBar1.add(jMenuCurso);

        jMenu1.setText("Disciplina");

        jMenuNovaDisiciplina.setText("Novo...");
        jMenuNovaDisiciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNovaDisiciplinaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuNovaDisiciplina);

        jMenuPesquisarDisciplina.setText("Pesquisar...");
        jMenuPesquisarDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPesquisarDisciplinaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuPesquisarDisciplina);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Matriz");

        jMenuNovaMatriz.setText("Novo...");
        jMenuNovaMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNovaMatrizActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuNovaMatriz);

        jMenuPesquisarMatriz.setText("Pesquisar");
        jMenuPesquisarMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPesquisarMatrizActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuPesquisarMatriz);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonSalvar)
                .addGap(198, 198, 198))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(208, 208, 208))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFVagas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTFVagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jButtonSalvar)
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPrincipalActionPerformed
        JFrameMain obj = new JFrameMain();
        obj.setVisible(true);

    }//GEN-LAST:event_jMenuPrincipalActionPerformed

    private void jMenuNovaDisiciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovaDisiciplinaActionPerformed
        JFNovaDisciplina obj = null;
        obj = new JFNovaDisciplina();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovaDisiciplinaActionPerformed

    private void jMenuPesquisarDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarDisciplinaActionPerformed
        JFPesquisarDisciplina obj = new JFPesquisarDisciplina();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarDisciplinaActionPerformed

    private void jMenuNovaMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovaMatrizActionPerformed
        JFNovaMatriz obj = null;
        obj = new JFNovaMatriz();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovaMatrizActionPerformed

    private void jMenuPesquisarMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarMatrizActionPerformed
        JFPesquisarMatriz obj = new JFPesquisarMatriz();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarMatrizActionPerformed

    private void jTFNomeCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNomeCursoActionPerformed

    }//GEN-LAST:event_jTFNomeCursoActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        inOrUp();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    void inOrUp() {

        if (getId() == 0) {
            inserirDados();
        } else {
            updateTable();
        }

    }

    void inserirDados() {
        Connection con = new ConnectionFactory().getConnection();
        String nomeCurso = jTFNomeCurso.getText();
        String tipoCurso = jComboBoxTipoCurso.getSelectedItem().toString();

        try {
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement("INSERT INTO curso (nomeCurso, tipoCurso, vagas) VALUES( "
                    + "'" + nomeCurso + "', "
                    + "'" + tipoCurso + "', "
                    + "'" + getVagas() + "'"
                    + ")");
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro");
            ex.printStackTrace();
        } finally {
            try {
                JOptionPane.showMessageDialog(this, "Registro salvo");
                this.dispose();
                con.close();

            } catch (SQLException ex) {
                Logger.getLogger(JFNovoCurso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    void updateTable() {

        Connection conn = new ConnectionFactory().getConnection();

        setNomeCurso(jTxt1.getText());
        setTipoCurso(jComboBoxTipoCurso.getSelectedItem().toString());
        setVagas(jTFVagas.getText());
        jTFVagas.setText(getVagas());

        String queryUp = "UPDATE curso SET nomeCurso = '" + nomeCurso + "'"
                + ", tipoCurso = '" + tipoCurso + ",' vagas = '" + getVagas() + "' WHERE idCurso = "
                + "'" + getId() + "'";

        try {
            java.sql.PreparedStatement stmt1 = conn.prepareStatement(queryUp);
            stmt1.executeUpdate();
            stmt1.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                JOptionPane.showMessageDialog(this, "Atualizado");
                this.dispose();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(JFNovoCurso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void jComboBoxTipoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoCursoActionPerformed

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
            java.util.logging.Logger.getLogger(JFNovoCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFNovoCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFNovoCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFNovoCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFNovoCurso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jComboBoxTipoCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCurso;
    private javax.swing.JMenuItem jMenuNovaDisiciplina;
    private javax.swing.JMenuItem jMenuNovaMatriz;
    private javax.swing.JMenuItem jMenuPesquisarDisciplina;
    private javax.swing.JMenuItem jMenuPesquisarMatriz;
    private javax.swing.JMenuItem jMenuPrincipal;
    private javax.swing.JTextField jTFNomeCurso;
    private javax.swing.JTextField jTFVagas;
    // End of variables declaration//GEN-END:variables
}
