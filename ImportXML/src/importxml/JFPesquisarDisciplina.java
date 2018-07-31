package importxml;

import java.awt.FontMetrics;
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
import javax.swing.table.TableColumn;

/**
 *
 * @author Gustavo
 */
public class JFPesquisarDisciplina extends javax.swing.JFrame {

    //Declaração variáveis
    Connection con = new ConnectionFactory().getConnection();
    JTable table = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();
    JComboBox<String> jCTC, jCNC;
    int idCurso;
    String nomeCurso, anoInicio, nomeDisciplina, codigo, tipoCurso, periodo;

    //Funções Getters e Setters
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

    //Método Construtor
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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Tipo de curso:");

        jComboBoxTipoCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Curso:");

        jComboBoxNomeCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Ano início da matriz:");

        jComboBoxAnoInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

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

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(214, 214, 214))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPaneDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(28, 28, 28)
                                    .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(44, 44, 44)
                                    .addComponent(jLabel2))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(103, 103, 103)
                                            .addComponent(jLabel3))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(80, 80, 80)
                                            .addComponent(jButtonAlterar)))
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonExcluir)
                                        .addComponent(jComboBoxAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonPesquisar)
                                        .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(75, 75, 75)
                                    .addComponent(jButtonReferencias))))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonPesquisar))
                    .addComponent(jLabel3))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonReferencias))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Botão pesquisar que chama a função popularJTable para popular a tabela
     * com os resultados dos filtros
     */
    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        popularJTable();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed
    /**
     * Botão alterar; direciona para tela de cadastro de disciplinas
     * Com o código já definido e impossibilitado de ser alterado
     */
    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        Connection conn = new ConnectionFactory().getConnection();
        JFNovaDisciplina janela = new JFNovaDisciplina();
        janela.jCTC.setSelectedItem(getTipoCurso());
        janela.jCNC.setSelectedItem(getNomeCurso());
        janela.jTFND.setText(getNomeDisciplina());
        janela.jL7.setText("Atualizar Disciplina");
        janela.jBAt.setText("Atualizar");
        janela.jTFCod.setText(getCodigo());
        janela.jTFCod.setEditable(false);
        janela.jCNC.setEnabled(false);
        janela.jCTC.setEnabled(false);
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

    /**
     * Botão para excluir disciplina 
     */
    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        excluirDisciplina();
    }//GEN-LAST:event_jButtonExcluirActionPerformed
    /**
     * Botão que direciona o usuário para o Frame JFrameConsReferencias
     * onde é possível ver as referências bibliográficas referentes à
     * Disciplina selecionada na Jtable
     */
    
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
        jFcons.jLD.setText("Referências: " + nomeD);
        this.dispose();

    }//GEN-LAST:event_jButtonReferenciasActionPerformed

    /**
     * Opções da barra de menus: Importar(Importar) Curso(Novo Curso e Pesquisar
     * Curso) Matriz(Nova Matriz e Pesquisar)
     */
    
    private void jMenuItemImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImportarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItemImportarActionPerformed

    private void jMenuNovoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovoCursoActionPerformed
        JFNovoCurso obj = new JFNovoCurso();
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuNovoCursoActionPerformed

    private void jMenuItemPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPesquisarActionPerformed
        JFPesquisarCurso obj = new JFPesquisarCurso();
        obj.setVisible(true);
        this.dispose();             
    }//GEN-LAST:event_jMenuItemPesquisarActionPerformed

    private void jMenuItemNovaMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovaMatrizActionPerformed
        JFNovaMatriz obj = null;
        obj = new JFNovaMatriz();
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemNovaMatrizActionPerformed

    private void jMenuItemPesquisarMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPesquisarMatrizActionPerformed
        JFPesquisarMatriz obj = new JFPesquisarMatriz();
        obj.setVisible(true);
        this.dispose();
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
    
    /**
     * Atualiza os JComboBox jComboBoxNomeCurso e jComboBoxAnoInicio; O
     * jComboBoxNome Curso é atualizado a partir do que é selecionado no
     * JComboBoxTipoCurso; O jComboBoxAnoInicio é atualizado a partir do que é
     * selecionado no jComboBoxNomeCurso
     */
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

    /**
     * Popula a JTable com o retorno da consulta descrita na variável query
     */
    void popularJTable() {

        modelo = (DefaultTableModel) table.getModel();
        modelo.addColumn("Curso");
        modelo.addColumn("Matriz");
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
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.getColumnModel().getColumn(0).setPreferredWidth(225);
            table.getColumnModel().getColumn(1).setPreferredWidth(68);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setPreferredWidth(68);

            jScrollPaneDisciplina.setViewportView(table);
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Exclui disciplina através do código da mesma e do ano inicio da matriz
     * obtido pela linha selecionada na JTable
     */
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

}
