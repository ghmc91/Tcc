/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importxml;

import com.mysql.jdbc.Connection;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Table;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gustavo
 */
public class JFPesquisarMatriz extends javax.swing.JFrame {

    private JTable table = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();
    Connection con = new ConnectionFactory().getConnection();
    int idCurso = 0, idDisciplina = 0, idMatriz = 0;
    String nomeCurso, nomeDisciplina, tipoCurso, status, anoInicio, anoFim;

    public String getTipoCurso() {
        Connection conn = new ConnectionFactory().getConnection();
        String query = "SELECT tipoCurso FROM Curso WHERE nomeCurso = "
                + "'" + getNomeCurso() + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tipoCurso = rs.getString("tipoCurso");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = jComboBoxTipoCurso.getSelectedItem().toString();
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

    public String getNomeDisciplina() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            nomeDisciplina = (String) table.getValueAt(i, 1);
        }
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getAnoInicio() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            anoInicio = (String) table.getValueAt(i, 2);
        }
        return anoInicio;
    }

    public void setAnoInicio(String anoInicio) {
        this.anoInicio = anoInicio;
    }

    public String getAnoFim() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            anoFim = (String) table.getValueAt(i, 3);
        }
        return anoFim;
    }

    public void setAnoFim(String anoFim) {
        this.anoFim = anoFim;
    }

    public String getStatus() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            status = (String) table.getValueAt(i, 4);
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdCurso() {
        Connection conn = new ConnectionFactory().getConnection();
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            nomeCurso = (String) table.getValueAt(i, 0);
        }
        String sql = "SELECT idCurso FROM Curso WHERE nomeCurso = "
                + "'" + nomeCurso + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idCurso = rs.getInt("idCurso");
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdDisciplina() {
        Connection conn = new ConnectionFactory().getConnection();
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            nomeDisciplina = (String) table.getValueAt(i, 1);
        }
        String sql = "SELECT idDisciplina FROM Disciplina WHERE nome= "
                + "'" + nomeDisciplina + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
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

    public int getIdMatriz() {
        Connection conn = new ConnectionFactory().getConnection();
        String query = "SELECT idMatriz FROM matriz WHERE Disciplina_idDisciplina = "
                + "'" + getIdDisciplina() + "' AND Disciplina_Curso_idCurso = "
                + "'" + getIdCurso() + "' AND anoInicio = '" + getAnoInicio() + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idMatriz = rs.getInt("idMatriz");
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idMatriz;
    }

    public void setIdMatriz(int idMatriz) {
        this.idMatriz = idMatriz;
    }

    public JFPesquisarMatriz() {
        initComponents();

        updateCombo();

        jComboBoxTipoCurso.addItem("Técnico");
        jComboBoxTipoCurso.addItem("Superior");
        jComboBoxTipoCurso.addItem("Pós-Graduação");
        jComboBoxTipoCurso.addItem("Mestrado");
        
        jButtonReferencias.setEnabled(false);
             

    }

    

    private void updateCombo() {

        jComboBoxTipoCurso.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                jComboBoxCurso.removeAllItems();
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
                            model.addElement("Selecione");
                            jComboBoxCurso.setModel(model);
                            stmtNC.close();

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            }
        });
        jComboBoxCurso.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                jComboBoxDisciplina.removeAll();
                List<Object> nomeDisciplina = new ArrayList<>();
                Connection conn = new ConnectionFactory().getConnection();
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    int id = 0;
                    String nC = jComboBoxCurso.getSelectedItem().toString();
                    PreparedStatement stmt1 = null;
                    PreparedStatement stmtAI = null;
                    String query = "SELECT idCurso FROM Curso WHERE "
                            + "nomeCurso = '" + nC + "'";
                    try {
                        stmt1 = conn.prepareStatement(query);
                        ResultSet rs = stmt1.executeQuery();
                        while (rs.next()) {
                            id = rs.getInt("idCurso");
                        }
                        stmt1.close();
                        String queryAI = "SELECT nome FROM Disciplina WHERE Curso_idCurso = "
                                + "'" + id + "'";

                        com.mysql.jdbc.PreparedStatement stmtNC = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(queryAI);
                        ResultSet rsNC = stmtNC.executeQuery();
                        while (rsNC.next()) {
                            nomeDisciplina.add(rsNC.getString("nome"));
                        }
                        stmtNC.close();
                        DefaultComboBoxModel modelD = new DefaultComboBoxModel();
                        modelD.removeAllElements();
                        modelD.addElement("Selecione");
                        for (Object obj : nomeDisciplina){
                             modelD.addElement(obj);
                            }
                        modelD.addElement("Selecione");
                        jComboBoxDisciplina.setModel(modelD);
                        conn.close();

                    
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }

    public void popularJtable(String sql) {

        nomeCurso = jComboBoxCurso.getSelectedItem().toString();
        nomeDisciplina = jComboBoxDisciplina.getSelectedItem().toString();
        modelo = (DefaultTableModel) table.getModel();
        modelo.addColumn("nomeCurso");
        modelo.addColumn("disciplina");
        modelo.addColumn("anoInicio");
        modelo.addColumn("anoFim");
        modelo.addColumn("status");

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("status") == 0) {
                    status = "Inativa";
                } else if (rs.getInt("status") == 1) {
                    status = "Ativa";
                }
                modelo.addRow(new Object[]{
                    nomeCurso,
                    nomeDisciplina,
                    rs.getString("anoInicio"),
                    rs.getString("anoFim"),
                    status});
            }

            table.setModel(modelo);
            jScrollPaneMatrizes.setViewportView(table);
            stmt.close();
            con.close();
            
            table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaMouseClicked(evt);
            }
        });
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void jTabelaMouseClicked(java.awt.event.MouseEvent evt) {
        if (table.getSelectedRow() != -1) {
            jButtonReferencias.setEnabled(true);
        } else {
            jButtonReferencias.setEnabled(false);
        }
    }

    void excluirMatriz() {
        Connection conn = new ConnectionFactory().getConnection();
        int linha = table.getSelectedRow();
        nomeCurso = table.getValueAt(linha, 0).toString();
        nomeDisciplina = table.getValueAt(linha, 1).toString();
        anoInicio = table.getValueAt(linha, 2).toString();
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
                    + "'" + nomeDisciplina + "' AND Curso_idCurso = '" + idCurso + "'";
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                idDisciplina = rs2.getInt("idDisciplina");
            }
            stmt2.close();

            String query3 = "SELECT idMatriz FROM Matriz WHERE Disciplina_idDisciplina = "
                    + "'" + idDisciplina + "' AND Disciplina_Curso_idCurso = "
                    + "'" + idCurso + "' AND anoInicio = '" + anoInicio + "'";
            PreparedStatement stmt3 = conn.prepareStatement(query3);
            ResultSet rs3 = stmt3.executeQuery();
            while (rs3.next()) {
                idMatriz = rs3.getInt("idMatriz");
            }
            stmt3.close();
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja "
                    + "exluir a Disciplina?", "Atenção ", JOptionPane.YES_NO_OPTION);
            String query4 = "DELETE FROM Matriz WHERE idMatriz = "
                    + "'" + idMatriz + "'";
            if (opcao == JOptionPane.OK_OPTION) {

                PreparedStatement stmt4 = conn.prepareStatement(query4);
                int conseguiu_excluir = stmt4.executeUpdate();
                if (conseguiu_excluir == 1) {
                    modelo.removeRow(linha);
                    table.setModel(modelo);
                    stmt4.close();
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
        jLabel2 = new javax.swing.JLabel();
        jComboBoxTipoCurso = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxCurso = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxDisciplina = new javax.swing.JComboBox<>();
        jButtonPesquisar = new javax.swing.JButton();
        jButtonAlterarCurso = new javax.swing.JButton();
        jButtonExcluirCurso = new javax.swing.JButton();
        jScrollPaneMatrizes = new javax.swing.JScrollPane();
        jButtonReferencias = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCurso = new javax.swing.JMenu();
        jMenuPrincipal = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuNovaDisiciplina = new javax.swing.JMenuItem();
        jMenuPesquisarDisciplina = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuNovoCurso = new javax.swing.JMenuItem();
        jMenuPesquisarCurso = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Matriz");

        jLabel2.setText("Tipo de Curso");

        jComboBoxTipoCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", " " }));

        jLabel3.setText("Curso");

        jComboBoxCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxCurso.setSelectedIndex(-1);

        jLabel4.setText("Disciplina");

        jComboBoxDisciplina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", " " }));

        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jButtonAlterarCurso.setText("Alterar");
        jButtonAlterarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarCursoActionPerformed(evt);
            }
        });

        jButtonExcluirCurso.setText("Excluir");
        jButtonExcluirCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirCursoActionPerformed(evt);
            }
        });

        jButtonReferencias.setText("Referencias");
        jButtonReferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReferenciasActionPerformed(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(jButtonAlterarCurso))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonExcluirCurso)
                                        .addGap(45, 45, 45)
                                        .addComponent(jButtonReferencias))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jComboBoxDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(83, Short.MAX_VALUE))
                    .addComponent(jScrollPaneMatrizes, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addGap(189, 189, 189)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlterarCurso)
                    .addComponent(jButtonPesquisar)
                    .addComponent(jButtonExcluirCurso)
                    .addComponent(jButtonReferencias))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneMatrizes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
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
            Logger.getLogger(JFPesquisarMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovaDisiciplinaActionPerformed

    private void jMenuPesquisarDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarDisciplinaActionPerformed
        JFPesquisarDisciplina obj = new JFPesquisarDisciplina();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarDisciplinaActionPerformed

    private void jMenuNovoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovoCursoActionPerformed
        JFNovaMatriz obj = null;
        try {
            obj = new JFNovaMatriz();
        } catch (SQLException ex) {
            Logger.getLogger(JFPesquisarMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovoCursoActionPerformed

    private void jMenuPesquisarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarCursoActionPerformed
        JFPesquisarMatriz obj = new JFPesquisarMatriz();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarCursoActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed

        String nomeCurso = jComboBoxCurso.getSelectedItem().toString();
        String nomeDis = jComboBoxDisciplina.getSelectedItem().toString();
        String query = "SELECT idCurso FROM Curso WHERE nomeCurso = "
                + "'" + nomeCurso + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idCurso = rs.getInt("idCurso");
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String sql = "SELECT idDisciplina FROM disciplina WHERE nome = "
                + "'" + nomeDis + "'";
        try {
            PreparedStatement stmt1 = con.prepareStatement(sql);
            ResultSet rsD = stmt1.executeQuery();
            while (rsD.next()) {
                idDisciplina = rsD.getInt("idDisciplina");
            }
            stmt1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String sql1 = "SELECT anoInicio, anoFim, status FROM Matriz WHERE"
                + " Disciplina_Curso_idCurso = " + idCurso + " AND Disciplina_idDisciplina = "
                + "" + idDisciplina + "";

        popularJtable(sql1);

    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonAlterarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarCursoActionPerformed
        

        Connection conn = new ConnectionFactory().getConnection();
        JFNovaMatriz janela = null;
        try {
            janela = new JFNovaMatriz();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //System.out.println(getTipoCurso() + getNomeCurso() + getNomeDisciplina());
        janela.jCTP.setSelectedItem(getTipoCurso());
        janela.jCNC.setSelectedItem(getNomeCurso());
        janela.jDisc.setSelectedItem(getNomeDisciplina());
        janela.jS.setSelectedItem(getStatus());
        janela.jTAI.setText(getAnoInicio());
        janela.jTAF.setText(getAnoFim());
        janela.setId(getIdMatriz());

        janela.setVisible(true);


    }//GEN-LAST:event_jButtonAlterarCursoActionPerformed

    private void jButtonExcluirCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirCursoActionPerformed
        excluirMatriz();
    }//GEN-LAST:event_jButtonExcluirCursoActionPerformed

    private void jButtonReferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReferenciasActionPerformed
        
        Connection conn = new ConnectionFactory().getConnection();

        int linha = table.getSelectedRow();
        nomeCurso = table.getValueAt(linha, 0).toString();
        nomeDisciplina = table.getValueAt(linha, 1).toString();
        anoInicio = table.getValueAt(linha, 2).toString();
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
                    + "'" + nomeDisciplina + "' AND Curso_idCurso = '" + idCurso + "'";
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                idDisciplina = rs2.getInt("idDisciplina");
            }
            stmt2.close();

            String query3 = "SELECT idMatriz FROM Matriz WHERE Disciplina_idDisciplina = "
                    + "'" + idDisciplina + "' AND Disciplina_Curso_idCurso = "
                    + "'" + idCurso + "' AND anoInicio = '" + anoInicio + "'";
            PreparedStatement stmt3 = conn.prepareStatement(query3);
            ResultSet rs3 = stmt3.executeQuery();
            while (rs3.next()) {
                idMatriz = rs3.getInt("idMatriz");
            }
            stmt3.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        JFrameConsReferencias jFcons = new JFrameConsReferencias();
        jFcons.setIdMatriz(idMatriz);
        jFcons.popularJtable();
        jFcons.setVisible(true);

    }//GEN-LAST:event_jButtonReferenciasActionPerformed

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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPesquisarMatriz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterarCurso;
    private javax.swing.JButton jButtonExcluirCurso;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonReferencias;
    private javax.swing.JComboBox<String> jComboBoxCurso;
    private javax.swing.JComboBox<String> jComboBoxDisciplina;
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
    private javax.swing.JMenuItem jMenuNovoCurso;
    private javax.swing.JMenuItem jMenuPesquisarCurso;
    private javax.swing.JMenuItem jMenuPesquisarDisciplina;
    private javax.swing.JMenuItem jMenuPrincipal;
    private javax.swing.JScrollPane jScrollPaneMatrizes;
    // End of variables declaration//GEN-END:variables
}
