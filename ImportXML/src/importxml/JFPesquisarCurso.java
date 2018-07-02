/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importxml;

import com.mysql.jdbc.Connection;
import java.awt.Frame;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Gustavo
 */
public class JFPesquisarCurso extends javax.swing.JFrame {

    Connection con = new ConnectionFactory().getConnection();
    JTable table = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();
    JButton atualizar;
    String tipoCurso = "";
    String nomeCurso = "";
    int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        String query = "SELECT idCurso FROM Curso WHERE nomeCurso = "
                + "' " + getNomeCurso() + " ' AND tipoCurso = "
                + "' " + getTipoCurso() + " '";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idCurso");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.id = id;
    }

    public String getTipoCurso() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            tipoCurso = (String) table.getValueAt(i, 0);
        }
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public String getNomeCurso() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            nomeCurso = (String) table.getValueAt(i, 1);
        }
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public JFPesquisarCurso() {
        initComponents();
        this.jComboBoxTipoCurso.removeAllItems();

        this.jComboBoxTipoCurso.addItem("Técnico");
        this.jComboBoxTipoCurso.addItem("Superior");
        this.jComboBoxTipoCurso.addItem("Pós-Graduação");
        this.jComboBoxTipoCurso.addItem("Mestrado");

    }

    public void popularJtable(String sql) {

        try {

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            modelo = (DefaultTableModel) table.getModel();
            modelo.addColumn("tipoCurso");
            modelo.addColumn("nomeCurso");

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("tipoCurso"),
                    rs.getString("nomeCurso")
                });
            }
            table.setModel(modelo);
            jScrollPaneCursos.setViewportView(table);
            stmt.close();
            //con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void excluirCurso() {
        Connection conn = new ConnectionFactory().getConnection();
        int linha = table.getSelectedRow();
        tipoCurso = table.getValueAt(linha, 0).toString();
        nomeCurso = table.getValueAt(linha, 1).toString();

        String query = "SELECT idCurso FROM Curso WHERE nomeCurso = "
                + "'" + nomeCurso + "' AND tipoCurso = "
                + "'" + tipoCurso + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idCurso");
            }
            stmt.close();
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja "
                    + "exluir o curso?", "Atenção ", JOptionPane.YES_NO_OPTION);
            
            String sql = "DELETE FROM Curso WHERE idCurso = "
                        + "'" + id + "'";
            if (opcao == JOptionPane.OK_OPTION) {
              
                PreparedStatement stmt1 = conn.prepareStatement(sql);
                int conseguiu_excluir =  stmt1.executeUpdate();
                if (conseguiu_excluir == 1) {
                    modelo.removeRow(linha);
                    table.setModel(modelo);
                    stmt1.close();
                    conn.close();
                    JOptionPane.showMessageDialog(this, "Registro exluído com sucesso");
                } else {
                    JOptionPane.showMessageDialog(this, "Impossível excluir");
                }

            } else {
                this.dispose();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tccPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("tccPU").createEntityManager();
        cursoQuery = java.beans.Beans.isDesignTime() ? null : tccPUEntityManager.createQuery("SELECT c FROM Curso c");
        cursoList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : cursoQuery.getResultList();
        jButtonAlterarCurso = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxTipoCurso = new javax.swing.JComboBox<>();
        jButtonExcluirCurso = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();
        jScrollPaneCursos = new javax.swing.JScrollPane();
        jButtonAlterar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCurso = new javax.swing.JMenu();
        jMenuPrincipal = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuNovaDisiciplina = new javax.swing.JMenuItem();
        jMenuPesquisarDisciplina = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuNovaMatriz = new javax.swing.JMenuItem();
        jMenuPesquisarMatriz = new javax.swing.JMenuItem();

        jButtonAlterarCurso.setText("Alterar");
        jButtonAlterarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarCursoActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("-- Cursos -- ");

        jLabel2.setText("Tipo de Curso:");

        jComboBoxTipoCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonExcluirCurso.setText("Excluir");
        jButtonExcluirCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirCursoActionPerformed(evt);
            }
        });

        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonPesquisar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jScrollPaneCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jButtonAlterar)
                        .addGap(48, 48, 48)
                        .addComponent(jButtonExcluirCurso)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisar))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluirCurso)
                    .addComponent(jButtonAlterar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPrincipalActionPerformed
        JFNovoCurso obj = new JFNovoCurso();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPrincipalActionPerformed

    private void jMenuNovaDisiciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovaDisiciplinaActionPerformed
        JFNovaDisciplina obj = null;
        try {
            obj = new JFNovaDisciplina();
        } catch (SQLException ex) {
            Logger.getLogger(JFPesquisarCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovaDisiciplinaActionPerformed

    private void jMenuPesquisarDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarDisciplinaActionPerformed
        JFPesquisarDisciplina obj = new JFPesquisarDisciplina();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarDisciplinaActionPerformed

    private void jMenuNovaMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovaMatrizActionPerformed
        JFNovaMatriz obj = null;
        try {
            obj = new JFNovaMatriz();
        } catch (SQLException ex) {
            Logger.getLogger(JFPesquisarCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovaMatrizActionPerformed

    private void jMenuPesquisarMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarMatrizActionPerformed

        JFPesquisarMatriz obj = new JFPesquisarMatriz();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarMatrizActionPerformed

    private void jButtonAlterarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarCursoActionPerformed

    }//GEN-LAST:event_jButtonAlterarCursoActionPerformed

    private void jButtonExcluirCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirCursoActionPerformed

        excluirCurso();
    }//GEN-LAST:event_jButtonExcluirCursoActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed

        String sql = "SELECT tipoCurso, nomeCurso FROM Curso WHERE tipoCurso = '"
                + jComboBoxTipoCurso.getSelectedItem().toString() + "'";
        popularJtable(sql);

    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        Connection conn = new ConnectionFactory().getConnection();
        JFNovoCurso janela = new JFNovoCurso();

        janela.jTxt1.setText(getNomeCurso());
        janela.jCbx1.setSelectedItem(getTipoCurso());
        String query = "SELECT idCurso FROM Curso WHERE nomeCurso = "
                + "'" + getNomeCurso() + "' AND tipoCurso = "
                + "'" + getTipoCurso() + "'";
        int idx = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idx = rs.getInt("idCurso");
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        janela.setId(idx);
        janela.setVisible(true);
    }//GEN-LAST:event_jButtonAlterarActionPerformed

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
            java.util.logging.Logger.getLogger(JFPesquisarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPesquisarCurso().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.util.List<importxml.Curso> cursoList;
    private javax.persistence.Query cursoQuery;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonAlterarCurso;
    private javax.swing.JButton jButtonExcluirCurso;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JComboBox<String> jComboBoxTipoCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCurso;
    private javax.swing.JMenuItem jMenuNovaDisiciplina;
    private javax.swing.JMenuItem jMenuNovaMatriz;
    private javax.swing.JMenuItem jMenuPesquisarDisciplina;
    private javax.swing.JMenuItem jMenuPesquisarMatriz;
    private javax.swing.JMenuItem jMenuPrincipal;
    private javax.swing.JScrollPane jScrollPaneCursos;
    private javax.persistence.EntityManager tccPUEntityManager;
    // End of variables declaration//GEN-END:variables
}
