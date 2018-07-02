/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importxml;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Gustavo
 */
public class JFNovaMatriz extends javax.swing.JFrame {

    Connection con = new ConnectionFactory().getConnection();
    String curso = null;
    String anoInicio = null;
    String anoFim = null;
    String status = null;
    JComboBox<String> jCTP, jCNC, jDisc, jS;
    JTextField jTAI, jTAF;
    int id = 0, idDisciplina = 0;
    String disciplina;

    public String getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(String anoInicio) {
        this.anoInicio = anoInicio;
    }

    public String getAnoFim() {
        return anoFim;
    }

    public void setAnoFim(String anoFim) {
        this.anoFim = anoFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JFNovaMatriz() throws SQLException {
        initComponents();

        updateCombo();

        jComboBoxStatus.addItem("Ativa");
        jComboBoxStatus.addItem("Inativa");

        jComboBoxTipoCurso.removeAllItems();
        jComboBoxTipoCurso.addItem("Técnico");
        jComboBoxTipoCurso.addItem("Superior");
        jComboBoxTipoCurso.addItem("Pós-Graduação");
        jComboBoxTipoCurso.addItem("Mestrado");

        jComboBoxCurso.addItem("Selecione");
        jComboBoxDisciplina.removeAllItems();
        jComboBoxDisciplina.addItem("Selecione");

        this.jCTP = jComboBoxTipoCurso;
        this.jCNC = jComboBoxCurso;
        this.jDisc = jComboBoxDisciplina;
        this.jS = jComboBoxStatus;
        this.jTAI = jTFAnoInicio;
        this.jTAF = jTFAnoFinal;

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
                        java.sql.PreparedStatement stmt = con.prepareStatement(sqlId);
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
                            java.sql.PreparedStatement stmtNC = con.prepareStatement(sqlNC);
                            ResultSet rsNC = stmtNC.executeQuery();
                            while (rsNC.next()) {
                                nomeCurso.add(rsNC.getString("nomeCurso"));
                            }
                            DefaultComboBoxModel model = new DefaultComboBoxModel();
                            model.removeAllElements();
                            model.addElement("Selecione");
                            for (Object obj : nomeCurso) {
                                model.addElement(obj);
                            }

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
                Connection conn = new ConnectionFactory().getConnection();
                List<Object> nomeDisciplina = new ArrayList<>();
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    int id = 0;
                    String nC = jComboBoxCurso.getSelectedItem().toString();
                    java.sql.PreparedStatement stmt1 = null;
                    java.sql.PreparedStatement stmtAI = null;
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

                        PreparedStatement stmtNC = (PreparedStatement) conn.prepareStatement(queryAI);
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

    void updateMatriz() {
        Connection conn = new ConnectionFactory().getConnection();
        setAnoInicio(jTFAnoInicio.getText());
        setAnoFim(jTFAnoFinal.getText());
        setStatus(jComboBoxStatus.getSelectedItem().toString());
        int idCurso = 0;
        int status = 0;
        String sql = "SELECT idCurso FROM Curso WHERE nomeCurso = "
                + "'" + jComboBoxCurso.getSelectedItem().toString() + "'";
        try {
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idCurso = rs.getInt("idCurso");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String sql1 = "SELECT idDisciplina FROM Disciplina WHERE nome = "
                + "'" + jComboBoxDisciplina.getSelectedItem().toString() + "'";
        try {
            java.sql.PreparedStatement stmt = con.prepareStatement(sql1);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idDisciplina = rs.getInt("idDisciplina");
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (jComboBoxStatus.getSelectedItem().equals("Ativa")) {
            status = 1;
        } else if (jComboBoxStatus.getSelectedItem().equals("Inativa")) {
            status = 0;
        }

        String query = "UPDATE matriz SET Disciplina_Curso_idCurso = "
                + "" + idCurso + ", Disciplina_idDisciplina = "
                + "" + idDisciplina + ", anoInicio = " + getAnoInicio() + ", "
                + "anoFim = " + getAnoFim() + ", status = " + status + " WHERE "
                + "idMatriz = " + getId() + "";
        try {
            PreparedStatement stmt2 = (PreparedStatement) con.prepareStatement(query);
            stmt2.executeUpdate();
            stmt2.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Atualizado");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jButtonSalvar = new javax.swing.JButton();
        jTFAnoInicio = new javax.swing.JTextField();
        jTFAnoFinal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxTipoCurso = new javax.swing.JComboBox<>();
        jComboBoxCurso = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxDisciplina = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCurso = new javax.swing.JMenu();
        jMenuPrincipal = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuNovaDisiciplina = new javax.swing.JMenuItem();
        jMenuPesquisarDisciplina = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuNovoCurso = new javax.swing.JMenuItem();
        jMenuPesquisarCurso = new javax.swing.JMenuItem();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("-- Nova Matriz --");

        jLabel3.setText("Curso:");

        jLabel5.setText("Ano de início: ");

        jLabel6.setText("Ano final:");

        jLabel7.setText("Status");

        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStatusActionPerformed(evt);
            }
        });

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jTFAnoFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFAnoFinalActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo de curso:");

        jComboBoxTipoCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jComboBoxCurso.setSelectedItem(-1);

        jLabel4.setText("Disciplina:");

        jComboBoxDisciplina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", " " }));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(223, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxTipoCurso, 0, 80, Short.MAX_VALUE)
                                    .addComponent(jComboBoxDisciplina, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jTFAnoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxCurso, 0, 91, Short.MAX_VALUE)
                            .addComponent(jComboBoxStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(115, 115, 115))
            .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTFAnoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jButtonSalvar)
                .addContainerGap(36, Short.MAX_VALUE))
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
            Logger.getLogger(JFNovaMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovaDisiciplinaActionPerformed

    private void jMenuPesquisarDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarDisciplinaActionPerformed
        JFPesquisarDisciplina obj = new JFPesquisarDisciplina();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarDisciplinaActionPerformed

    private void jMenuNovoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovoCursoActionPerformed
        JFNovoCurso obj = new JFNovoCurso();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovoCursoActionPerformed

    private void jMenuPesquisarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarCursoActionPerformed
        JFPesquisarMatriz obj = new JFPesquisarMatriz();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarCursoActionPerformed

    private void jTFAnoFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFAnoFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFAnoFinalActionPerformed

    private void jComboBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxStatusActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        curso = jComboBoxCurso.getSelectedItem().toString();
        anoInicio = jTFAnoInicio.getText();
        anoFim = jTFAnoFinal.getText();
        disciplina = jComboBoxDisciplina.getSelectedItem().toString();
        int status = 0;
        if (jComboBoxStatus.getSelectedItem().toString().equals("Inativa")) {
            status = 0;
        } else if (jComboBoxStatus.getSelectedItem().toString().equalsIgnoreCase("Ativa")) {
            status = 1;
        }
        if (getId() == 0) {

            insert(anoInicio, anoFim, status, curso, disciplina);
            Connection conn = new ConnectionFactory().getConnection();
            int opcao = JOptionPane.showConfirmDialog(this, "Deseja inserir a referencia bibliográfica?",
                    "Atenção", JOptionPane.YES_NO_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                int idMatriz = 0;
                Referencias referencias = new Referencias();
                String sql1 = "SELECT idDisciplina FROM Disciplina WHERE nome = "
                        + "'" + jComboBoxDisciplina.getSelectedItem().toString() + "'";
                try {
                    java.sql.PreparedStatement stmt = conn.prepareStatement(sql1);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        idDisciplina = rs.getInt("idDisciplina");
                    }
                    stmt.close();
                    String sql2 = "SELECT idMatriz FROM matriz WHERE Disciplina_idDisciplina = "
                            + "'" + idDisciplina + "' AND anoInicio = '" + anoInicio + "'";
                    PreparedStatement stmt1 = (PreparedStatement) conn.prepareStatement(sql2);
                    ResultSet rs2 = stmt1.executeQuery();
                    while (rs2.next()) {
                        idMatriz = rs2.getInt("idMatriz");
                    }
                    referencias.setIdMatriz(idMatriz);
                    referencias.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            } else if (opcao == JOptionPane.NO_OPTION) {
                this.dispose();
            }
        } else {
            updateMatriz();
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void insert(String anoInicio, String anoFim, int status, String curso, String disciplina) {
        int id = 0;
        int idDisciplina = 0;
        String query = "SELECT idCurso FROM Curso "
                + "WHERE nomeCurso = '" + curso + "'";
        try {
            PreparedStatement stmtID = (PreparedStatement) con.prepareStatement(query);
            ResultSet rsID = stmtID.executeQuery();
            while (rsID.next()) {
                id = rsID.getInt("idCurso");
            }
            stmtID.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String sql = "SELECT idDisciplina FROM Disciplina WHERE nome = "
                + "'" + disciplina + "'";
        try {
            PreparedStatement stmtD = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rsD = stmtD.executeQuery();
            while (rsD.next()) {
                idDisciplina = rsD.getInt("idDisciplina");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String queryIn = "INSERT INTO matriz(anoInicio, anoFim, Status, "
                + "Disciplina_idDisciplina, Disciplina_Curso_idCurso)"
                + "VALUES ('" + anoInicio + "', '" + anoFim + "', '" + status + "', "
                + "'" + idDisciplina + "', '" + id + "')";
        try {
            PreparedStatement stmtIn = (PreparedStatement) con.prepareStatement(queryIn);
            stmtIn.executeUpdate();
            stmtIn.close();
            con.close();
            JOptionPane.showMessageDialog(this, "Gravado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Impossível Inserir");
            ex.printStackTrace();
        }
    }

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
            java.util.logging.Logger.getLogger(JFNovaMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFNovaMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFNovaMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFNovaMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFNovaMatriz().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFNovaMatriz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jComboBoxCurso;
    private javax.swing.JComboBox<String> jComboBoxDisciplina;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JComboBox<String> jComboBoxTipoCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCurso;
    private javax.swing.JMenuItem jMenuNovaDisiciplina;
    private javax.swing.JMenuItem jMenuNovoCurso;
    private javax.swing.JMenuItem jMenuPesquisarCurso;
    private javax.swing.JMenuItem jMenuPesquisarDisciplina;
    private javax.swing.JMenuItem jMenuPrincipal;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField jTFAnoFinal;
    private javax.swing.JTextField jTFAnoInicio;
    // End of variables declaration//GEN-END:variables
}
