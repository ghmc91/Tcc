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
import javax.swing.JTextField;

/**
 *
 * @author Gustavo
 */
public class JFNovaDisciplina extends javax.swing.JFrame {

    Connection con = new ConnectionFactory().getConnection();
    JComboBox<String> jCTC, jCNC, jCAI;
    JTextField jTFND, jTFCod;
    String nomeCurso, codigo, anoInicio, nomeDisciplina;
    int idCurso, idDisciplina, idMatriz;

    public String getAnoInicio() {
        anoInicio = jComboBoxAnoInicio.getSelectedItem().toString();
        return anoInicio;
    }

    public void setAnoInicio(String anoInicio) {
        this.anoInicio = anoInicio;
    }

    public int getIdMatriz() {
        Connection con = new ConnectionFactory().getConnection();
        String query = "SELECT idMatriz FROM Matriz WHERE Curso_idCurso = " + getIdCurso() + " "
                + "AND anoInicio = " + getAnoInicio() + "";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idMatriz = rs.getInt("idMatriz");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idMatriz;
    }

    public void setIdMatriz(int idMatriz) {
        this.idMatriz = idMatriz;
    }

    public String getNomeCurso() {
        nomeCurso = jComboBoxNomeCurso.getSelectedItem().toString();
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getCodigo() {
        codigo = jTFCodigo.getText();
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdCurso() {
        String query = "SELECT idCurso FROM Curso WHERE nomeCurso = "
                + "'" + getNomeCurso() + "'";
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
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNomeDisciplina() {
        nomeDisciplina = jTFNomeDisciplina.getText();
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public JFNovaDisciplina() {
        initComponents();
        this.jCTC = jComboBoxTipoCurso;
        this.jCNC = jComboBoxNomeCurso;
        this.jCAI = jComboBoxAnoInicio;
        this.jTFND = jTFNomeDisciplina;
        this.jTFCod = jTFCodigo;

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
                    String query = "SELECT anoInicio FROM matriz WHERE Curso_idCurso = "
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

    void insert() {

        String insert = "INSERT INTO Disciplina(codDisciplina, nome, Matriz) "
                + "VALUES ('" + getCodigo() + "', '" + getNomeDisciplina() + "', '" + getIdMatriz() + "')";
        try {
            PreparedStatement stmt = con.prepareStatement(insert);
            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(this, "Registro salvo");

            int opcao1 = JOptionPane.showConfirmDialog(this, "Deseja inserir outra disciplina?",
                    "Atenção", JOptionPane.YES_NO_OPTION);
            if (opcao1 == JOptionPane.YES_OPTION) {
                this.dispose();
                JFNovaDisciplina nova = new JFNovaDisciplina();
                nova.setVisible(true);
            } else{
            int opcao = JOptionPane.showConfirmDialog(this, "Deseja inserir a referencia bibliográfica?",
                    "Atenção", JOptionPane.YES_NO_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                int idDisciplina = 0;
                Referencias referencias = new Referencias();
                String sql1 = "SELECT idDisciplina FROM Disciplina WHERE nome = "
                        + "'" + getNomeDisciplina() + "' AND Matriz = '" + getIdMatriz() + "'";
                PreparedStatement stmt1 = con.prepareStatement(sql1);
                ResultSet rs = stmt1.executeQuery();
                while (rs.next()) {
                    idDisciplina = rs.getInt("idDisciplina");
                }
                this.dispose();
                referencias.setVisible(true);
                referencias.setIdDisciplina(idDisciplina);
                stmt1.close();

            }}
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    void update() {

        Connection conn = new ConnectionFactory().getConnection();
        String update = "UPDATE Disciplina SET codDisciplina = '" + getCodigo() + "', "
                + "nome = '" + getNomeDisciplina() + "', Matriz = '" + getIdMatriz() + "' "
                + "WHERE idDisciplina = '" + getIdDisciplina() + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(update);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Atualizado com sucesso");
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
        jLabel3 = new javax.swing.JLabel();
        jComboBoxAnoInicio = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTFNomeDisciplina = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jTFCodigo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuImportar = new javax.swing.JMenu();
        jMenuItemImportar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemNovoCurso = new javax.swing.JMenuItem();
        jMenuItemPesquisar = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItemNovaMatriz = new javax.swing.JMenuItem();
        jMenuItemPesquisarMatriz = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tipo de curso: ");

        jComboBoxTipoCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel2.setText("Curso:");

        jLabel3.setText("Ano início da Matriz: ");

        jLabel4.setText("Nome:");

        jTFNomeDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNomeDisciplinaActionPerformed(evt);
            }
        });

        jLabel5.setText("Código: ");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jLabel7.setText("Nova disciplina");

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

        jMenu5.setText("Matriz");

        jMenuItemNovaMatriz.setText("Nova Matriz");
        jMenuItemNovaMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNovaMatrizActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemNovaMatriz);

        jMenuItemPesquisarMatriz.setText("Pesquisar...");
        jMenuItemPesquisarMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPesquisarMatrizActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemPesquisarMatriz);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(162, 162, 162)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)))
                            .addComponent(jComboBoxAnoInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(jTFNomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(113, 113, 113)
                            .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonSalvar)
                        .addGap(194, 194, 194))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(207, 207, 207))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(178, 178, 178))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButtonSalvar)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        if (getIdDisciplina() == 0) {
            insert();
        } else {
            update();
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jMenuItemImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImportarActionPerformed
        JFrameMain obj = new JFrameMain();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItemImportarActionPerformed

    private void jMenuItemNovoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovoCursoActionPerformed
        JFNovoCurso obj = new JFNovoCurso();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItemNovoCursoActionPerformed

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

    private void jTFNomeDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNomeDisciplinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNomeDisciplinaActionPerformed

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
            java.util.logging.Logger.getLogger(JFNovaDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFNovaDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFNovaDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFNovaDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFNovaDisciplina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jComboBoxAnoInicio;
    private javax.swing.JComboBox<String> jComboBoxNomeCurso;
    private javax.swing.JComboBox<String> jComboBoxTipoCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuImportar;
    private javax.swing.JMenuItem jMenuItemImportar;
    private javax.swing.JMenuItem jMenuItemNovaMatriz;
    private javax.swing.JMenuItem jMenuItemNovoCurso;
    private javax.swing.JMenuItem jMenuItemPesquisar;
    private javax.swing.JMenuItem jMenuItemPesquisarMatriz;
    private javax.swing.JTextField jTFCodigo;
    private javax.swing.JTextField jTFNomeDisciplina;
    // End of variables declaration//GEN-END:variables
}
