
package importxml;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gustavo
 */
public class JFPesquisarMatriz extends javax.swing.JFrame {
    
    Connection con = new ConnectionFactory().getConnection();
    JTable table = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();
    JComboBox<String> jCTP, jCNC;
    int idCurso;
    List<Object> idMatriz = new ArrayList<>();
    String nomeCurso, tipoCurso, anoInicio, anoFim, status;
    
    public String getStatus() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            status = (String) table.getValueAt(i, 3);
        }
        
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getAnoInicio() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            anoInicio = (String) table.getValueAt(i, 1);
        }
        return anoInicio;
    }
    
    public void setAnoInicio(String anoInicio) {
        this.anoInicio = anoInicio;
    }
    
    public String getAnoFim() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            anoFim = (String) table.getValueAt(i, 2);
        }
        return anoFim;
    }
    
    public void setAnoFim(String anoFim) {
        this.anoFim = anoFim;
    }
    
    public String getTipoCurso() {
        tipoCurso = jComboBoxTipoCurso.getSelectedItem().toString();
        return tipoCurso;
    }
    
    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }
    
    public String getNomeCurso() {
        nomeCurso = jComboBoxNomeCurso.getSelectedItem().toString();
        return nomeCurso;
    }
    
    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
    
    public List<Object> getIdMatriz() {
        Connection conn = new ConnectionFactory().getConnection();
        String query = "SELECT idMatriz FROM Matriz WHERE idCurso = '" + getIdCurso() + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idMatriz = (List<Object>) rs.getObject("idMatriz");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idMatriz;
    }
    
    public void setIdMatriz(List<Object> idMatriz) {
        this.idMatriz = idMatriz;
    }
    
    public int getIdCurso() {
        Connection conn = new ConnectionFactory().getConnection();
        String query = "SELECT idCurso FROM Curso WHERE nomeCurso = '" + getNomeCurso() + "'";
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
    
    public JFPesquisarMatriz() {
        initComponents();
        this.jCTP = jComboBoxTipoCurso;
        this.jCNC = jComboBoxNomeCurso;
        
        this.jComboBoxTipoCurso.removeAllItems();
        this.jComboBoxTipoCurso.addItem("Selecione");
        this.jComboBoxTipoCurso.addItem("Técnico");
        this.jComboBoxTipoCurso.addItem("Superior");
        this.jComboBoxTipoCurso.addItem("Pós-Graduação");
        this.jComboBoxTipoCurso.addItem("Mestrado");
        
        updateCombo();
    }
    
    void updateCombo() {
        
        jComboBoxTipoCurso.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                jComboBoxNomeCurso.removeAllItems();
                List<Object> idCurso = new ArrayList<>();
                List<Object> nomeCurso = new ArrayList<>();
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    String sqlId = "SELECT idCurso FROM Curso WHERE tipoCurso = "
                            + "'" + ie.getItem().toString() + "'";
                    try {
                        PreparedStatement stmt = con.prepareStatement(sqlId);
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                            idCurso.add(rs.getInt("idCurso"));
                        }
                        stmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    for (Object objId : idCurso) {
                        String sqlNC = "SELECT nomeCurso FROM curso WHERE idCurso ="
                                + "'" + objId + "'";
                        try {
                            PreparedStatement stmtNC = con.prepareStatement(sqlNC);
                            ResultSet rsNC = stmtNC.executeQuery();
                            while (rsNC.next()) {
                                nomeCurso.add(rsNC.getString("nomeCurso"));
                            }
                            DefaultComboBoxModel model = new DefaultComboBoxModel();
                            model.removeAllElements();
                            for (Object obj : nomeCurso) {
                                model.addElement(obj);
                            }
                            jComboBoxNomeCurso.setModel(model);
                            stmtNC.close();
                            con.close();
                            
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                
            }
        });
    }
    
    void popularJtable() {
        Connection conn = new ConnectionFactory().getConnection();
        modelo = (DefaultTableModel) table.getModel();
        modelo.addColumn("Nome do Curso");
        modelo.addColumn("Ano Inicio");
        modelo.addColumn("Ano Final");
        modelo.addColumn("Status");
        
        String st = null;
        
        String query = "SELECT anoInicio, anoFim, status FROM Matriz "
                + "WHERE Curso_idCurso = '" + getIdCurso() + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("Status") == 0) {
                    st = "Ativa";
                } else {
                    st = "Inativa";
                }
                modelo.addRow(new Object[]{
                    getNomeCurso(),
                    rs.getString("anoInicio"),
                    rs.getString("anoFim"),
                    st
                });
            }
            table.setModel(modelo);
            jScrollPaneMatrizes.setViewportView(table);
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    void excluirMatriz() {
        Connection conn = new ConnectionFactory().getConnection();
        int idM = 0;
        int linha = table.getSelectedRow();
        String anoInicio = (String) table.getValueAt(linha, 1);
        String query = "SELECT idMatriz FROM Matriz WHERE Curso_idCurso = " + getIdCurso() + ""
             + " AND anoInicio = " + anoInicio + "";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idM = rs.getInt("idMatriz");
            }
            stmt.close();
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja "
                    + "excluir a matriz?", "Atenção ", JOptionPane.YES_NO_OPTION);
            String query2 = "DELETE FROM Matriz WHERE idMatriz = "
                    + "'" + idM + "'";
            if (opcao == JOptionPane.OK_OPTION) {
                
                PreparedStatement stmt2 = conn.prepareStatement(query2);
                int conseguiu_excluir = stmt2.executeUpdate();
                if (conseguiu_excluir == 1) {
                    modelo.removeRow(linha);
                    table.setModel(modelo);
                    stmt2.close();
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBoxTipoCurso = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxNomeCurso = new javax.swing.JComboBox<>();
        jScrollPaneMatrizes = new javax.swing.JScrollPane();
        jButtonPesquisar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuImportar = new javax.swing.JMenu();
        jMenuItemImportar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemNovoCurso = new javax.swing.JMenuItem();
        jMenuItemPesquisar = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItemNovaDisciplina = new javax.swing.JMenuItem();
        jMenuItemPesquisarDisciplina = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tipo de curso:");

        jComboBoxTipoCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Curso:");

        jComboBoxNomeCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jLabel3.setText("Pesquisar Matriz");

        jMenuImportar.setText("Importar");

        jMenuItemImportar.setText("Importar");
        jMenuItemImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemImportarActionPerformed(evt);
            }
        });
        jMenuImportar.add(jMenuItemImportar);

        jMenuBar1.add(jMenuImportar);

        jMenu2.setText("Curso");

        jMenuItemNovoCurso.setText("Novo Curso");
        jMenuItemNovoCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNovoCursoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemNovoCurso);

        jMenuItemPesquisar.setText("Pesquisar...");
        jMenuItemPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPesquisarActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemPesquisar);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Disciplina");

        jMenuItemNovaDisciplina.setText("Nova Disciplina");
        jMenuItemNovaDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNovaDisciplinaActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemNovaDisciplina);

        jMenuItemPesquisarDisciplina.setText("Pesquisar...");
        jMenuItemPesquisarDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPesquisarDisciplinaActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemPesquisarDisciplina);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneMatrizes, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonPesquisar)
                                        .addGap(50, 50, 50)
                                        .addComponent(jButtonAlterar)))
                                .addGap(62, 62, 62)
                                .addComponent(jButtonExcluir))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPesquisar)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonExcluir))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneMatrizes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        popularJtable();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        Connection conn = new ConnectionFactory().getConnection();
        JFNovaMatriz janela = new JFNovaMatriz();
        janela.jCTP.setSelectedItem(getTipoCurso());
        janela.jCNC.setSelectedItem(getNomeCurso());
        janela.anoI.setText(getAnoInicio());
        janela.anoF.setText(getAnoFim());
        janela.jCSt.setSelectedItem(getStatus());
        
        int idMat = 0;
        String query = "SELECT idMatriz FROM matriz WHERE Curso_idCurso = '" + getIdCurso() + "'"
                + "AND anoInicio = '" + getAnoInicio() + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idMat = rs.getInt("idMatriz");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        janela.setIdMatriz(idMat);
        janela.setVisible(true);

    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        excluirMatriz();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jMenuItemImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImportarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemImportarActionPerformed

    private void jMenuItemNovoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovoCursoActionPerformed
        JFNovoCurso obj = new JFNovoCurso();
        obj.setVisible(true);

    }//GEN-LAST:event_jMenuItemNovoCursoActionPerformed

    private void jMenuItemPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPesquisarActionPerformed
        JFPesquisarCurso obj = new JFPesquisarCurso();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItemPesquisarActionPerformed

    private void jMenuItemNovaDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovaDisciplinaActionPerformed
        JFNovaDisciplina obj = new JFNovaDisciplina();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItemNovaDisciplinaActionPerformed

    private void jMenuItemPesquisarDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPesquisarDisciplinaActionPerformed
        JFPesquisarDisciplina obj = new JFPesquisarDisciplina();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItemPesquisarDisciplinaActionPerformed

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
            java.util.logging.Logger.getLogger(JFPesquisarMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPesquisarMatriz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JComboBox<String> jComboBoxNomeCurso;
    private javax.swing.JComboBox<String> jComboBoxTipoCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuImportar;
    private javax.swing.JMenuItem jMenuItemImportar;
    private javax.swing.JMenuItem jMenuItemNovaDisciplina;
    private javax.swing.JMenuItem jMenuItemNovoCurso;
    private javax.swing.JMenuItem jMenuItemPesquisar;
    private javax.swing.JMenuItem jMenuItemPesquisarDisciplina;
    private javax.swing.JScrollPane jScrollPaneMatrizes;
    // End of variables declaration//GEN-END:variables
}
