package importxml;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gustavo
 */
public class JFPesquisarDisciplina extends javax.swing.JFrame {

    Connection con = new ConnectionFactory().getConnection();
    JTable table = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();
    JComboBox<String> jCTC, jCNC;
    int idCurso;
    String nomeCurso, anoInicio, nomeDisciplina, codigo, tipoCurso, periodo;

    public String getTipoCurso() {
        tipoCurso = jComboBoxTipoCurso.getSelectedItem().toString();
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public String getCodigo() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            codigo = (String) table.getValueAt(i, 3);
        }
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNomeDisciplina() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            nomeDisciplina = (String) table.getValueAt(i, 2);
        }
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getNomeCurso() {
        nomeCurso = jComboBoxNomeCurso.getSelectedItem().toString();
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getAnoInicio() {
        anoInicio = jComboBoxAnoInicio.getSelectedItem().toString();
        return anoInicio;
    }

    public void setAnoInicio(String anoInicio) {
        this.anoInicio = anoInicio;
    }

    public int getIdCurso() {
        try {
            String query = "SELECT idCurso FROM curso WHERE "
                    + "nomeCurso = '" + getNomeCurso() + "'";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idCurso = rs.getInt("idCurso");
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getPeriodo() {
        int per = 0;
        String query = "SELECT periodo FROM matriz_has_disciplina WHERE "
                + "disciplina = '" + getCodigo() + "' AND matrizCurso = " + getIdCurso() + "";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                per = rs.getInt("periodo");
            }
            if (per == 0) {
                periodo = "Selecione";
            } else if (per == 1) {
                periodo = "1º";
            } else if (per == 2) {
                periodo = "2º";
            } else if (per == 3) {
                periodo = "3º";
            } else if (per == 4) {
                periodo = "4º";
            } else if (per == 5) {
                periodo = "5º";
            } else if (per == 6) {
                periodo = "6º";
            } else if (per == 7) {
                periodo = "7º";
            } else if (per == 8) {
                periodo = "8º";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public JFPesquisarDisciplina() {
        initComponents();

        this.jCTC = jComboBoxTipoCurso;
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
                jComboBoxNomeCurso.addItem("Selecione");
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
                            model.addElement("Selecione");
                            jComboBoxNomeCurso.setModel(model);
                            stmtNC.close();

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            }
        });
        jComboBoxNomeCurso.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                jComboBoxAnoInicio.removeAllItems();
                List<Object> anoI = new ArrayList<>();
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    DefaultComboBoxModel model = new DefaultComboBoxModel();
                    String query = "SELECT anoInicio FROM matriz WHERE idCurso = "
                            + "" + getIdCurso() + "";
                    try {
                        PreparedStatement stmt = con.prepareStatement(query);
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                            anoI.add(rs.getInt("anoInicio"));
                        }
                        for (Object obj : anoI) {
                            model.addElement(obj);
                        }
                        stmt.close();
                        jComboBoxAnoInicio.setModel(model);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }

    void popularJTable() {
        
        modelo = (DefaultTableModel) table.getModel();
        modelo.addColumn("Curso");
        modelo.addColumn("anoInicio");
        modelo.addColumn("Nome");
        modelo.addColumn("Codigo");

        String query = "SELECT DISTINCT nome, codDisciplina FROM disciplina, matriz_has_disciplina WHERE matriz_has_disciplina.matrizAno = '"
                + "" + getAnoInicio() + "' AND matriz_has_disciplina.matrizCurso = '" + getIdCurso() + "'";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    getNomeCurso(),
                    getAnoInicio(),
                    rs.getString("nome"),
                    rs.getString("CodDisciplina")
                });
            }
            table.setModel(modelo);
            jScrollPaneDisciplina.setViewportView(table);
            stmt.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void excluirDisciplina() {
        Connection conn = new ConnectionFactory().getConnection();
        String codD = null;
        int linha = table.getSelectedRow();
        String query = "SELECT codDisciplina FROM matriz_has_disciplina, disciplina WHERE matriz_has_disciplina.matrizAno"
                + " = '" + getAnoInicio() + "'"
                + "AND nome = '" + getNomeDisciplina() + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                codD = rs.getString("codDisciplina");
            }
            stmt.close();
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja "
                    + "excluir a disicplina?\n"
                    + "Isso acarretará na exclusão das referências \n"
                    + "relacionadas a mesma.", "Atenção ", JOptionPane.YES_NO_OPTION);
            String query3 = "DELETE FROM matriz_has_disciplina WHERE Disciplina = '" + codD + "'";
            String query4 = "DELETE FROM disciplina_livros WHERE disciplina = '" + codD + "'";
            String query2 = "DELETE FROM disciplina WHERE codDisciplina = "
                    + "'" + codD + "'";
            if (opcao == JOptionPane.OK_OPTION) {
                PreparedStatement stmt3 = conn.prepareStatement(query3);
                stmt3.executeUpdate();
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
            Logger.getLogger(JFPesquisarDisciplina.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxTipoCurso = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxNomeCurso = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxAnoInicio = new javax.swing.JComboBox<>();
        jScrollPaneDisciplina = new javax.swing.JScrollPane();
        jButtonPesquisar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButtonReferencias = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuI = new javax.swing.JMenu();
        jMenuItemImportar = new javax.swing.JMenuItem();
        jMenuNovoCurso = new javax.swing.JMenu();
        jMenuItemNovoCurso = new javax.swing.JMenuItem();
        jMenuItemPesquisar = new javax.swing.JMenuItem();
        jMenuNovaMatriz = new javax.swing.JMenu();
        jMenuItemNovaMatriz = new javax.swing.JMenuItem();
        jMenuItemPesquisarMatriz = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tipo de curso:");

        jLabel2.setText("Curso:");

        jLabel3.setText("Ano início da matriz:");

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

        jLabel4.setText("Pesquisar Disciplina");

        jButtonReferencias.setText("Referências");
        jButtonReferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReferenciasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(173, 173, 173)
                                .addComponent(jButtonReferencias))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jButtonPesquisar)
                                .addGap(86, 86, 86)
                                .addComponent(jButtonAlterar)
                                .addGap(93, 93, 93)
                                .addComponent(jButtonExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(14, 14, 14)))
                        .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jScrollPaneDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel4)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 72, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonPesquisar)
                            .addComponent(jButtonAlterar)
                            .addComponent(jButtonExcluir)
                            .addComponent(jButtonReferencias))
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPaneDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBoxAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jMenuI.setText("Importar");

        jMenuItemImportar.setText("Importar");
        jMenuItemImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemImportarActionPerformed(evt);
            }
        });
        jMenuI.add(jMenuItemImportar);

        jMenuBar1.add(jMenuI);

        jMenuNovoCurso.setText("Curso");
        jMenuNovoCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNovoCursoActionPerformed(evt);
            }
        });

        jMenuItemNovoCurso.setText("Novo Curso");
        jMenuNovoCurso.add(jMenuItemNovoCurso);

        jMenuItemPesquisar.setText("Pesquisar...");
        jMenuItemPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPesquisarActionPerformed(evt);
            }
        });
        jMenuNovoCurso.add(jMenuItemPesquisar);

        jMenuBar1.add(jMenuNovoCurso);

        jMenuNovaMatriz.setText("Matriz");

        jMenuItemNovaMatriz.setText("Nova Matriz");
        jMenuItemNovaMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNovaMatrizActionPerformed(evt);
            }
        });
        jMenuNovaMatriz.add(jMenuItemNovaMatriz);

        jMenuItemPesquisarMatriz.setText("Pesquisar...");
        jMenuItemPesquisarMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPesquisarMatrizActionPerformed(evt);
            }
        });
        jMenuNovaMatriz.add(jMenuItemPesquisarMatriz);

        jMenuBar1.add(jMenuNovaMatriz);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        popularJTable();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        Connection conn = new ConnectionFactory().getConnection();
        JFNovaDisciplina janela = new JFNovaDisciplina();
        janela.jCTC.setSelectedItem(getTipoCurso());
        janela.jCNC.setSelectedItem(getNomeCurso());
        janela.jTFND.setText(getNomeDisciplina());
        janela.jTFCod.setText(getCodigo());
        janela.jTFCod.setEditable(false);
        janela.jCAI.getModel().setSelectedItem(getAnoInicio());
        janela.jCBP.getModel().setSelectedItem(getPeriodo());

        String codD = null;
        int linha = table.getSelectedRow();
        String query = "SELECT codDisciplina FROM matriz_has_disciplina, disciplina "
                + "WHERE matriz_has_disciplina.matrizAno"
                + " = '" + getAnoInicio() + "' "
                + "AND nome = '" + getNomeDisciplina() + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                codD = rs.getString("codDisciplina");
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        janela.setCodigo(codD);
        janela.setVisible(true);
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        excluirDisciplina();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonReferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReferenciasActionPerformed
        JFrameConsReferencias jFcons = new JFrameConsReferencias();
        int linha = table.getSelectedRow();
        String nomeD = (String) table.getValueAt(linha, 2);
        String codD = null;
        String query = "SELECT codDisciplina FROM disciplina, matriz_has_disciplina WHERE nome = '" + nomeD + "'"
                + "AND matriz_has_disciplina.matrizAno = '" + getAnoInicio() + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                codD = rs.getString("codDisciplina");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFPesquisarDisciplina.class.getName()).log(Level.SEVERE, null, ex);
        }

        jFcons.setcodDisciplina(codD);
        jFcons.popularJtable();
        jFcons.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButtonReferenciasActionPerformed

    private void jMenuItemImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImportarActionPerformed
        JFrameMain obj = new JFrameMain();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItemImportarActionPerformed

    private void jMenuNovoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovoCursoActionPerformed
        JFNovoCurso obj = new JFNovoCurso();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovoCursoActionPerformed

    private void jMenuItemPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPesquisarActionPerformed
        JFPesquisarCurso obj = new JFPesquisarCurso();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItemPesquisarActionPerformed

    private void jMenuItemNovaMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovaMatrizActionPerformed
        JFNovaMatriz obj = null;
        obj = new JFNovaMatriz();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItemNovaMatrizActionPerformed

    private void jMenuItemPesquisarMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPesquisarMatrizActionPerformed
        JFPesquisarMatriz obj = new JFPesquisarMatriz();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItemPesquisarMatrizActionPerformed

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
            java.util.logging.Logger.getLogger(JFPesquisarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPesquisarDisciplina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonReferencias;
    private javax.swing.JComboBox<String> jComboBoxAnoInicio;
    private javax.swing.JComboBox<String> jComboBoxNomeCurso;
    private javax.swing.JComboBox<String> jComboBoxTipoCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuI;
    private javax.swing.JMenuItem jMenuItemImportar;
    private javax.swing.JMenuItem jMenuItemNovaMatriz;
    private javax.swing.JMenuItem jMenuItemNovoCurso;
    private javax.swing.JMenuItem jMenuItemPesquisar;
    private javax.swing.JMenuItem jMenuItemPesquisarMatriz;
    private javax.swing.JMenu jMenuNovaMatriz;
    private javax.swing.JMenu jMenuNovoCurso;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPaneDisciplina;
    // End of variables declaration//GEN-END:variables
}
