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

    //Declaração de variáveis
    Connection con = new ConnectionFactory().getConnection();
    JTable table = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();
    JComboBox<String> jCTP, jCNC;
    int idCurso;
    String nomeCurso, tipoCurso, anoInicio, anoFim, status;

    //Funções Getters e Setters
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

    public int getIdCurso() {
        Connection conn = new ConnectionFactory().getConnection();
        String query = "SELECT idCurso FROM curso WHERE nomeCurso = '" + getNomeCurso() + "'";
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

    //Método Construtor
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxTipoCurso = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxNomeCurso = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();
        jScrollPaneMatrizes = new javax.swing.JScrollPane();
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

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Pesquisar Matriz");

        jComboBoxTipoCurso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jComboBoxTipoCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Tipo de curso:");

        jComboBoxNomeCurso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jComboBoxNomeCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Curso:");

        jButtonAlterar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonExcluir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonPesquisar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneMatrizes, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneMatrizes, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBoxNomeCurso, jComboBoxTipoCurso});

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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Botão para popular a JTable a partir da pesquisa feita; Chama a função
     * popularJtable(), que popula a tabela a partir dos filtros da pesquisa
     */
    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        popularJtable();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    /**
     * Botão para alterar a Matriz;Direciona para o JFNovaMtariz com o ano
     * início, o nome do curso e o tipo do mesmo já configurados e indisponíveis
     * para alterações
     */
    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        Connection conn = new ConnectionFactory().getConnection();
        JFNovaMatriz janela = new JFNovaMatriz();
        janela.jCTP.setSelectedItem(getTipoCurso());
        janela.jCNC.setSelectedItem(getNomeCurso());
        janela.anoI.setText(getAnoInicio());
        janela.jL6.setText("Atualizar Matriz");
        janela.jBAt.setText("Atualizar");
        janela.anoI.setEditable(false);
        janela.jCNC.setEnabled(false);
        janela.jCTP.setEnabled(false);
        janela.anoF.setText(getAnoFim());
        janela.jCSt.setSelectedItem(getStatus());
        janela.setAnoInicio(getAnoInicio());
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    /**
     * Botão para excluir matrizes; Chama a função excluirMatriz()
     */
    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        excluirMatriz();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    /**
     * Opções da barra de menus: Importar(Importar) Curso(Novo Curso e Pesquisar
     * Curso) Disciplina(Nova Disciplina e Pesquisar)
     */
    private void jMenuItemImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImportarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItemImportarActionPerformed

    private void jMenuItemNovoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovoCursoActionPerformed
        JFNovoCurso obj = new JFNovoCurso();
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemNovoCursoActionPerformed

    private void jMenuItemPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPesquisarActionPerformed
        JFPesquisarCurso obj = new JFPesquisarCurso();
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemPesquisarActionPerformed

    private void jMenuItemNovaDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovaDisciplinaActionPerformed
        JFNovaDisciplina obj = new JFNovaDisciplina();
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemNovaDisciplinaActionPerformed

    private void jMenuItemPesquisarDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPesquisarDisciplinaActionPerformed
        JFPesquisarDisciplina obj = new JFPesquisarDisciplina();
        obj.setVisible(true);
        this.dispose();
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPaneMatrizes;
    // End of variables declaration//GEN-END:variables

    /**
     * Função que atualiza o jComboBoxNomeCurso a partir do que é selecionado no
     * jComboBoxTipoCurso, retornando os cursos pelo tipo selecionado
     */
    void updateCombo() {

        jComboBoxTipoCurso.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                jComboBoxNomeCurso.removeAllItems();
                List<Object> idCurso = new ArrayList<>();
                List<Object> nomeCurso = new ArrayList<>();
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    String sqlId = "SELECT idCurso FROM curso WHERE tipoCurso = "
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

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            }
        });
    }

    /**
     * Popula a JTable com o retorno da consulta descrita na variável query
     */
    void popularJtable() {
        Connection conn = new ConnectionFactory().getConnection();
        modelo = (DefaultTableModel) table.getModel();
        modelo.addColumn("Nome do Curso");
        modelo.addColumn("Ano Inicio");
        modelo.addColumn("Ano Final");
        modelo.addColumn("Status");

        String st = null;

        String query = "SELECT anoInicio, anoFim, status FROM matriz "
                + "WHERE idCurso = '" + getIdCurso() + "'";
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
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.getColumnModel().getColumn(0).setPreferredWidth(225);
            table.getColumnModel().getColumn(1).setPreferredWidth(70);
            table.getColumnModel().getColumn(2).setPreferredWidth(70);
            table.getColumnModel().getColumn(3).setPreferredWidth(70);
            jScrollPaneMatrizes.setViewportView(table);
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Função para excluir dados da tabela matriz; seleciona o ano inicio da
     * linha selecionada e pega o idCurso com a função getIdCurso(); com isso
     * apaga o registro na tabela e na JTable
     */
    void excluirMatriz() {
        Connection conn = new ConnectionFactory().getConnection();
        int linha = table.getSelectedRow();
        String anoInicio = (String) table.getValueAt(linha, 1);

        try {
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja "
                    + "excluir a matriz?", "Atenção ", JOptionPane.YES_NO_OPTION);
            String query2 = "DELETE FROM matriz WHERE anoInicio = " + anoInicio + " "
                    + "AND idCurso = " + getIdCurso() + "";
            if (opcao == JOptionPane.OK_OPTION) {

                PreparedStatement stmt2 = conn.prepareStatement(query2);
                int conseguiu_excluir = stmt2.executeUpdate();
                if (conseguiu_excluir == 1) {
                    modelo.removeRow(linha);
                    table.setModel(modelo);
                    stmt2.close();
                    conn.close();
                    JOptionPane.showMessageDialog(this, "Registro exlcuído com sucesso");
                } else {
                    JOptionPane.showMessageDialog(this, "Impossível excluir");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Impossível excluir!"
                    + "\nCertifique-se que não há disciplinas vinculadas a essa matriz.");
            ex.printStackTrace();
        }
    }

}
