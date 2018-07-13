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
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Vector;
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
    JComboBox<String> jCTC, jCNC;
    JTextField jNomeDisc, jCodDisc;
    String nomeCurso, nome, codDisciplina;
    int idDisciplina, idCurso;

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(String codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public int getIdCurso() {
        Connection conn = new ConnectionFactory().getConnection();
        String query = "SELECT idCurso FROM Curso WHERE nomeCurso = "
                + "'" + getNomeCurso() + "'";
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                idCurso = rs.getInt("idCurso");
            }
            stmt.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public JFNovaDisciplina() throws SQLException {
        initComponents();
        jComboTipoCurso.removeAllItems();
        jComboTipoCurso.addItem("Técnico");
        jComboTipoCurso.addItem("Superior");
        jComboTipoCurso.addItem("Pós-Graduação");
        jComboTipoCurso.addItem("Mestrado");

        updateCombo();
        
        this.jCNC = jComboBoxNomeCurso;
        this.jCTC = jComboTipoCurso;
        this.jNomeDisc = jTextFieldNomeDisciplina;
        this.jCodDisc = jTFCodDisc;

    }
    
    public void insert(String nome, String codDisciplina, String nomeCurso){
        

        try {

            String queryId = "SELECT idCurso FROM curso "
                    + "WHERE nomeCurso = '" + nomeCurso + "'";
            PreparedStatement returnId = con.prepareStatement(queryId);
            ResultSet rs = returnId.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("idCurso");
            }
            returnId.close();
            

            PreparedStatement stmt = con.prepareStatement("INSERT INTO disciplina (codDisciplina, "
                    + "nome, Curso_idCurso) VALUES ("
                    + "'" + getCodDisciplina()+ "',"
                    + "'" + getNome() + "',"
                    + "'" + id + "'"
                    + ")");

            stmt.executeUpdate();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(this, "Dados gravados com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro");
            ex.printStackTrace();
        }

    }
    
    public void update(String nome, String codDisciplina){
        setNome(jTextFieldNomeDisciplina.getText());
        setCodDisciplina(jTFCodDisc.getText());
        setNomeCurso(jCNC.getSelectedItem().toString());
        
        
        
        String queryUp = "UPDATE Disciplina SET Curso_idCurso = "
                + "'" + getIdCurso() + "', nome = '" + nome + "', "
                + "codDisciplina = '" + codDisciplina + "' WHERE idDisciplina = "
                + "'"  + getIdDisciplina() + "'";
        try{
            PreparedStatement stmt = con.prepareStatement(queryUp);
            stmt.executeUpdate();
            stmt.close();
            con.close();
            JOptionPane.showMessageDialog(this, "Atualizado");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Erro ao atualizar");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNomeDisciplina = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxNomeCurso = new javax.swing.JComboBox<>();
        jButtonSalvar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboTipoCurso = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTFCodDisc = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCurso = new javax.swing.JMenu();
        jMenuPrincipal = new javax.swing.JMenuItem();
        jMenuMatriz = new javax.swing.JMenu();
        jMenuNovaMatriz = new javax.swing.JMenuItem();
        jMenuPesquisarMatriz = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuNovoCurso = new javax.swing.JMenuItem();
        jMenuPesquisarCurso = new javax.swing.JMenuItem();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("-- Nova Disciplina --");

        jLabel2.setText("Nome da Disciplina: ");

        jTextFieldNomeDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeDisciplinaActionPerformed(evt);
            }
        });

        jLabel4.setText("Curso:");

        jComboBoxNomeCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo de Curso: ");

        jComboTipoCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel6.setText("Código da disciplina: ");

        jTFCodDisc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFCodDiscActionPerformed(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTFCodDisc, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNomeDisciplina, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                        .addContainerGap(186, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonSalvar)
                .addGap(203, 203, 203))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(206, 206, 206))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTFCodDisc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jButtonSalvar)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPrincipalActionPerformed
        JFNovoCurso obj = new JFNovoCurso();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPrincipalActionPerformed

    private void jMenuNovaMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovaMatrizActionPerformed
        JFNovaMatriz obj = null;
        try {
            obj = new JFNovaMatriz();
        } catch (SQLException ex) {
            Logger.getLogger(JFNovaDisciplina.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(JFNovaDisciplina.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovoCursoActionPerformed

    private void jMenuPesquisarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarCursoActionPerformed
        JFPesquisarMatriz obj = new JFPesquisarMatriz();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarCursoActionPerformed

    private void jTextFieldNomeDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeDisciplinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeDisciplinaActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        
        nome = jTextFieldNomeDisciplina.getText();
        codDisciplina = jTFCodDisc.getText();
        nomeCurso = jComboBoxNomeCurso.getSelectedItem().toString();
        
        if(getIdDisciplina()==0){
            insert(nome, codDisciplina, nomeCurso);
        }else{
            update(nome, codDisciplina);
        }
        
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTFCodDiscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFCodDiscActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFCodDiscActionPerformed

    private void updateCombo() {

        jComboTipoCurso.addItemListener(new ItemListener() {
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

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            }
        });
        
    }

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
                try {
                    new JFNovaDisciplina().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFNovaDisciplina.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jComboBoxNomeCurso;
    private javax.swing.JComboBox<String> jComboTipoCurso;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCurso;
    private javax.swing.JMenu jMenuMatriz;
    private javax.swing.JMenuItem jMenuNovaMatriz;
    private javax.swing.JMenuItem jMenuNovoCurso;
    private javax.swing.JMenuItem jMenuPesquisarCurso;
    private javax.swing.JMenuItem jMenuPesquisarMatriz;
    private javax.swing.JMenuItem jMenuPrincipal;
    private javax.swing.JTextField jTFCodDisc;
    private javax.swing.JTextField jTextFieldNomeDisciplina;
    // End of variables declaration//GEN-END:variables
}
