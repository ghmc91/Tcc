/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importxml;

import com.mysql.jdbc.Connection;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JFPesquisarDisciplina extends javax.swing.JFrame {

    private JTable table = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();
    Connection con = new ConnectionFactory().getConnection();
    String nomeCurso, nome, codDisciplina, tipoCurso;
    int idDisciplina, idCurso;

    public String getTipoCurso() {
        return jComboBoxTipoCurso.getSelectedItem().toString();
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public String getNomeCurso() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            nomeCurso = (String) table.getValueAt(i, 0);
        }
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getNome() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            nome = (String) table.getValueAt(i, 1);
        }
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodDisciplina() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            codDisciplina = (String) table.getValueAt(i, 2);
        }
        return codDisciplina;
    }

    public void setCodDisciplina(String codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public int getIdDisciplina() {
        Connection conn = new ConnectionFactory().getConnection();
        String query = "SELECT idDisciplina FROM Disciplina WHERE Curso_idCurso = "
                + "'" + getIdCurso() + "'AND nome = '" + getNome() + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idDisciplina = rs.getInt("idDisciplina");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public int getIdCurso() {
        Connection conn = new ConnectionFactory().getConnection();
        String query = "SELECT idCurso FROM Curso where nomeCurso = "
                + "'" + getNomeCurso() + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idCurso = rs.getInt("idCurso");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public JFPesquisarDisciplina() {
        initComponents();

        jComboBoxTipoCurso.addItem("Técnico");
        jComboBoxTipoCurso.addItem("Superior");
        jComboBoxTipoCurso.addItem("Pós-Graduação");
        jComboBoxTipoCurso.addItem("Mestrado");

        updateCombo();

    }

    private void updateCombo() {

        jComboBoxTipoCurso.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                jComboBoxCurso.removeAllItems();
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    String sqlId = "SELECT idCurso FROM Curso WHERE tipoCurso = "
                            + "'" + ie.getItem().toString() + "'";
                    try {
                        PreparedStatement stmt = con.prepareStatement(sqlId);
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                            String id = rs.getString("idCurso");
                            String sqlNC = "SELECT nomeCurso FROM curso WHERE idCurso ="
                                    + "'" + id + "'";
                            PreparedStatement stmtNC = con.prepareStatement(sqlNC);
                            ResultSet rsNC = stmtNC.executeQuery();
                            while (rsNC.next()) {
                                jComboBoxCurso.addItem(rsNC.getString("nomeCurso"));

                            }

                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });

    }

    public void popularJtable() {

        modelo.addColumn("Nome do curso");
        modelo.addColumn("Disciplina");
        modelo.addColumn("Código");
        int id = 0;
        String nomeCurso = jComboBoxCurso.getSelectedItem().toString();

        String sql1 = "SELECT idCurso FROM Curso "
                + "WHERE nomeCurso = '" + nomeCurso + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql1);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idCurso");
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String query = "SELECT nome, codDisciplina FROM Disciplina "
                + "WHERE Curso_idCurso = '" + id + "'";

        try {
            PreparedStatement stmt2 = con.prepareStatement(query);
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                modelo.addRow(new Object[]{
                    nomeCurso,
                    rs2.getString("nome"),
                    rs2.getString("CodDisciplina")
                });
            }
            stmt2.close();
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        table.setModel(modelo);
        jScrollPaneDisciplinas.setViewportView(table);

    }

    void excluirDisciplina() {
        Connection conn = new ConnectionFactory().getConnection();
        int linha = table.getSelectedRow();
        nomeCurso = table.getValueAt(linha, 0).toString();
        nome = table.getValueAt(linha, 1).toString();
        String query = "SELECT idCurso FROM Curso WHERE nomeCurso = "
                + "'" + nomeCurso + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idCurso = rs.getInt("idCurso");
            }
            stmt.close();
            String query2 = "SELECT idDisciplina FROM Disciplina WHERE nome = "
                    + "'" + nome + "' AND Curso_idCurso = '" + idCurso + "'";
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                idDisciplina = rs2.getInt("idDisciplina");
            }
            stmt2.close();
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja "
                    + "exluir a Disciplina?", "Atenção ", JOptionPane.YES_NO_OPTION);
            String query3 = "DELETE FROM Disciplina WHERE idDisciplina = "
                    + "'" + idDisciplina + "' AND Curso_idCurso = "
                    + "'" + idCurso + "'";
            if (opcao == JOptionPane.OK_OPTION) {

                PreparedStatement stmt3 = conn.prepareStatement(query3);
                int conseguiu_excluir = stmt3.executeUpdate();
                if (conseguiu_excluir == 1) {
                    modelo.removeRow(linha);
                    table.setModel(modelo);
                    stmt3.close();
                    conn.close();
                    JOptionPane.showMessageDialog(this, "Registro exluído com sucesso");
                } else {
                    JOptionPane.showMessageDialog(this, "Impossível excluir");
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxTipoCurso = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxCurso = new javax.swing.JComboBox<>();
        jButtonAlterarCurso = new javax.swing.JButton();
        jButtonExcluirDisciplina = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();
        jScrollPaneDisciplinas = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCurso = new javax.swing.JMenu();
        jMenuPrincipal = new javax.swing.JMenuItem();
        jMenuMatriz = new javax.swing.JMenu();
        jMenuNovaMatriz = new javax.swing.JMenuItem();
        jMenuPesquisarMatriz = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuNovoCurso = new javax.swing.JMenuItem();
        jMenuPesquisarCurso = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Disciplinas");

        jLabel2.setText("Tipo de Curso: ");

        jComboBoxTipoCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", " " }));

        jLabel3.setText("Curso: ");

        jComboBoxCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jButtonAlterarCurso.setText("Alterar");
        jButtonAlterarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarCursoActionPerformed(evt);
            }
        });

        jButtonExcluirDisciplina.setText("Excluir");
        jButtonExcluirDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirDisciplinaActionPerformed(evt);
            }
        });

        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
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

        jMenuMatriz.setText("Matriz");

        jMenuNovaMatriz.setText("Novo...");
        jMenuNovaMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNovaMatrizActionPerformed(evt);
            }
        });
        jMenuMatriz.add(jMenuNovaMatriz);

        jMenuPesquisarMatriz.setText("Pesquisar...");
        jMenuPesquisarMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPesquisarMatrizActionPerformed(evt);
            }
        });
        jMenuMatriz.add(jMenuPesquisarMatriz);

        jMenuBar1.add(jMenuMatriz);

        jMenu2.setText("Curso");

        jMenuNovoCurso.setText("Novo...");
        jMenuNovoCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNovoCursoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuNovoCurso);

        jMenuPesquisarCurso.setText("Pesquisar");
        jMenuPesquisarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPesquisarCursoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuPesquisarCurso);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jScrollPaneDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(jButtonPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonAlterarCurso))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(287, 287, 287)
                                .addComponent(jLabel1)))
                        .addGap(37, 37, 37)
                        .addComponent(jButtonExcluirDisciplina)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(43, 43, 43)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jComboBoxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(3, 3, 3)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonPesquisar)
                        .addComponent(jButtonAlterarCurso))
                    .addComponent(jButtonExcluirDisciplina))
                .addGap(29, 29, 29)
                .addComponent(jScrollPaneDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPrincipalActionPerformed
        JFNovoCurso obj = new JFNovoCurso();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPrincipalActionPerformed

    private void jMenuNovaMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovaMatrizActionPerformed
        JFNovaDisciplina obj = null;
        try {
            obj = new JFNovaDisciplina();
        } catch (SQLException ex) {
            Logger.getLogger(JFPesquisarDisciplina.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovaMatrizActionPerformed

    private void jMenuPesquisarMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarMatrizActionPerformed
        JFPesquisarDisciplina obj = new JFPesquisarDisciplina();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarMatrizActionPerformed

    private void jMenuNovoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovoCursoActionPerformed
        JFNovaMatriz obj = null;
        try {
            obj = new JFNovaMatriz();
        } catch (SQLException ex) {
            Logger.getLogger(JFPesquisarDisciplina.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovoCursoActionPerformed

    private void jMenuPesquisarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarCursoActionPerformed
        JFPesquisarMatriz obj = new JFPesquisarMatriz();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarCursoActionPerformed

    private void jButtonAlterarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarCursoActionPerformed
        Connection conn = new ConnectionFactory().getConnection();

        try {
            JFNovaDisciplina janela = new JFNovaDisciplina();
            janela.jCTC.setSelectedItem(getTipoCurso());
            janela.jCNC.setSelectedItem(getNomeCurso());
            janela.jNomeDisc.setText(getNome());
            janela.jCodDisc.setText(getCodDisciplina());
            janela.setIdDisciplina(getIdDisciplina());
            janela.setVisible(true);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }//GEN-LAST:event_jButtonAlterarCursoActionPerformed

    private void jButtonExcluirDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirDisciplinaActionPerformed
        
        excluirDisciplina();
    }//GEN-LAST:event_jButtonExcluirDisciplinaActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed

        popularJtable();

    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    /**
     * @param args the command line argumjComboBoxTipoCurso
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
            java.util.logging.Logger.getLogger(JFPesquisarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPesquisarDisciplina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterarCurso;
    private javax.swing.JButton jButtonExcluirDisciplina;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JComboBox<String> jComboBoxCurso;
    private javax.swing.JComboBox<String> jComboBoxTipoCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCurso;
    private javax.swing.JMenu jMenuMatriz;
    private javax.swing.JMenuItem jMenuNovaMatriz;
    private javax.swing.JMenuItem jMenuNovoCurso;
    private javax.swing.JMenuItem jMenuPesquisarCurso;
    private javax.swing.JMenuItem jMenuPesquisarMatriz;
    private javax.swing.JMenuItem jMenuPrincipal;
    private javax.swing.JScrollPane jScrollPaneDisciplinas;
    // End of variables declaration//GEN-END:variables
}
