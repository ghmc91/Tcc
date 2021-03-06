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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Gustavo
 */
public class JFNovaDisciplina extends javax.swing.JFrame {

    //Declaração de variáveis
    Connection con = new ConnectionFactory().getConnection();
    JComboBox<String> jCTC, jCNC, jCAI, jCBP;
    JButton jBAt;
    JLabel jL7;
    JTextField jTFND, jTFCod;
    String nomeCurso, codigo, anoInicio, nomeDisciplina;
    int idCurso, periodo;

    //Métodos Getters e Setter
    public String getAnoInicio() {
        anoInicio = jComboBoxAnoInicio.getSelectedItem().toString();
        return anoInicio;
    }

    public void setAnoInicio(String anoInicio) {
        this.anoInicio = anoInicio;
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
        String query = "SELECT idCurso FROM curso WHERE nomeCurso = "
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

    public String getNomeDisciplina() {
        nomeDisciplina = jTFNomeDisciplina.getText();
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getPeriodo() {
        String per = jComboBoxPeriodo.getSelectedItem().toString();
        if (per.equals("Selecione")) {
            periodo = 0;
        } else if (per.equals("1º")) {
            periodo = 1;
        } else if (per.equals("2º")) {
            periodo = 2;
        } else if (per.equals("3º")) {
            periodo = 3;
        } else if (per.equals("4º")) {
            periodo = 4;
        } else if (per.equals("5º")) {
            periodo = 5;
        } else if (per.equals("6º")) {
            periodo = 6;
        } else if (per.equals("7º")) {
            periodo = 7;
        } else {
            periodo = 8;
        }

        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    //Método Construtor
    public JFNovaDisciplina() {
        initComponents();
        this.jCTC = jComboBoxTipoCurso;
        this.jCNC = jComboBoxNomeCurso;
        this.jCAI = jComboBoxAnoInicio;
        this.jTFND = jTFNomeDisciplina;
        this.jTFCod = jTFCodigo;
        this.jCBP = jComboBoxPeriodo;
        this.jL7 = jLabel7;
        this.jBAt = jButtonSalvar;
        
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

        jLabel8 = new javax.swing.JLabel();
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
        jLabel6 = new javax.swing.JLabel();
        jComboBoxPeriodo = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuImportar = new javax.swing.JMenu();
        jMenuItemImportar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemNovoCurso = new javax.swing.JMenuItem();
        jMenuItemPesquisar = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItemNovaMatriz = new javax.swing.JMenuItem();
        jMenuItemPesquisarMatriz = new javax.swing.JMenuItem();

        jLabel8.setText("jLabel8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Tipo de curso: ");

        jComboBoxTipoCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Curso:");

        jComboBoxNomeCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Ano início da Matriz: ");

        jComboBoxAnoInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Nome:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Código: ");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Nova disciplina");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Período: ");

        jComboBoxPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "1º", "2º", "3º", "4º", "5º", "6º", "7º", "8º" }));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFNomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(309, 309, 309))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonSalvar)
                        .addGap(308, 308, 308))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTFNomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBoxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(37, 37, 37)
                .addComponent(jButtonSalvar)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Botão que insere ou atualiza os dados nas tabelas disciplina e
     * matriz_has_disciplina; Primeiro chama a função cod(), se o retorno dessa
     * função for maior que 0, a função chamada é a update(); caso contrário ela
     * chama as funções insert e insertM_H_D;
     */
    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        if (cod() > 0) {
            update();
        } else {
            insert();
            insertM_H_D();
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed
    /**
     * Opções da barra de menus: Importar(Importar) Curso(Novo Curso e Pesquisar
     * Curso) Matriz(Nova Matriz e Pesquisar)
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
    private javax.swing.JComboBox<String> jComboBoxPeriodo;
    private javax.swing.JComboBox<String> jComboBoxTipoCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
     * Função para inserir dados na tabela disciplina Depois de inserida 
     * uma disciplina aparece uma caixa de diálogo perguntando se o 
     * usuário que inserir outra disciplina; Se sim, outro Frame desse é 
     * chamado; Se não, aparece outra caixa de diálogo perguntando
     * se o usuário quer inserir as refrencias bibliográficas referentes a
     * disciplina criada; Se optar por sim, o usuário é direcionado para o Frame
     * que insere as referencias bibliográficas
     */
    void insert() {

        String insert = "INSERT INTO disciplina(codDisciplina, nome) "
                + "VALUES ('" + getCodigo() + "', '" + getNomeDisciplina() + "')";
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
            } else {
                int opcao = JOptionPane.showConfirmDialog(this, "Deseja inserir as Referências Bibliográficas"
                        + "referentes a essa disciplina?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION) {
                    String codDisciplina = null;
                    Referencias referencias = new Referencias();
                    String sql1 = "SELECT codDisciplina FROM disciplina, matriz_has_disciplina  WHERE nome = "
                            + "'" + getNomeDisciplina() + "' AND matrizAno = '" + getAnoInicio() + "'";
                    PreparedStatement stmt1 = con.prepareStatement(sql1);
                    ResultSet rs = stmt1.executeQuery();
                    while (rs.next()) {
                        codDisciplina = rs.getString("codDisciplina");
                    }
                    this.dispose();
                    referencias.setVisible(true);
                    referencias.setCodDisciplina(getCodigo());
                    referencias.setNomeDisciplina(getNomeDisciplina());
                    referencias.nomeDis.setText("Referências - " + getNomeDisciplina());
                    stmt1.close();

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Função para atualizar o nome e o ano início da matriz a partir do código
     * da disciplina
     */
    void update() {

        Connection conn = new ConnectionFactory().getConnection();
        String update = "UPDATE disciplina, matriz_has_disciplina SET nome = '" + getNomeDisciplina() + "'"
                + ", matrizAno = '" + getAnoInicio() + "' "
                + "WHERE codDisciplina = '" + getCodigo() + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(update);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Atualizado com sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Contabiliza quantas disciplina tem com o código fornecido pelo usuário
     *
     * @return o quantidade de registros na tabela com o código do usuário
     */
    public int cod() {
        int cod = 0;
        String query = "SELECT count(*) as Cod FROM disciplina "
                + "WHERE codDisciplina = '" + getCodigo() + "'";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cod = rs.getInt("cod");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cod;
    }

    /**
     * Insere dados na tabela matriz_has_disciplina
     */
    void insertM_H_D() {
        String query = "INSERT INTO matriz_has_disciplina VALUES(" + getIdCurso() + ", "
                + "" + getAnoInicio() + ", '" + getCodigo() + "', " + getPeriodo() + ")";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.executeUpdate();
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

}
