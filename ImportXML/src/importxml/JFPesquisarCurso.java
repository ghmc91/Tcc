package importxml;

import com.mysql.jdbc.Connection;
import java.awt.Frame;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Gustavo
 */
public class JFPesquisarCurso extends javax.swing.JFrame {

    //Declaração de variáveis
    Connection con = new ConnectionFactory().getConnection();
    JTable table = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();
    JButton atualizar;
    String tipoCurso = "";
    String nomeCurso = "", vagas = "";
    int id = 0;

    //Métodos Getters e Setters
    public String getVagas() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            vagas = (String) table.getValueAt(i, 2);
        }
        return vagas;
    }

    public void setVagas(String vagas) {
        this.vagas = vagas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        String query = "SELECT idCurso FROM curso WHERE nomeCurso = "
                + "' " + getNomeCurso() + " ' AND tipoCurso = "
                + "' " + getTipoCurso() + " '";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idCurso");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.id = id;
    }

    public String getTipoCurso() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            tipoCurso = (String) table.getValueAt(i, 0);
        }
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public String getNomeCurso() {
        for (int i = 0; i <= table.getSelectedRow(); i++) {
            nomeCurso = (String) table.getValueAt(i, 1);
        }
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    //Método Construtor
    public JFPesquisarCurso() {
        initComponents();
        this.jComboBoxTipoCurso.removeAllItems();

        this.jComboBoxTipoCurso.addItem("Técnico");
        this.jComboBoxTipoCurso.addItem("Superior");
        this.jComboBoxTipoCurso.addItem("Pós-Graduação");
        this.jComboBoxTipoCurso.addItem("Mestrado");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tccPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("tccPU").createEntityManager();
        cursoQuery = java.beans.Beans.isDesignTime() ? null : tccPUEntityManager.createQuery("SELECT c FROM Curso c");
        cursoList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : cursoQuery.getResultList();
        jButtonAlterarCurso = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxTipoCurso = new javax.swing.JComboBox<>();
        jButtonExcluirCurso = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();
        jScrollPaneCursos = new javax.swing.JScrollPane();
        jButtonAlterar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCurso = new javax.swing.JMenu();
        jMenuPrincipal = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuNovaMatriz = new javax.swing.JMenuItem();
        jMenuPesquisarMatriz = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuNovaDisiciplina = new javax.swing.JMenuItem();
        jMenuPesquisarDisciplina = new javax.swing.JMenuItem();

        jButtonAlterarCurso.setText("Alterar");
        jButtonAlterarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarCursoActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("-- Cursos -- ");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Tipo de Curso:");

        jComboBoxTipoCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonExcluirCurso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonExcluirCurso.setText("Excluir");
        jButtonExcluirCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirCursoActionPerformed(evt);
            }
        });

        jButtonPesquisar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jButtonAlterar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
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

        jMenu2.setText("Matriz");

        jMenuNovaMatriz.setText("Novo...");
        jMenuNovaMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNovaMatrizActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuNovaMatriz);

        jMenuPesquisarMatriz.setText("Pesquisar");
        jMenuPesquisarMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPesquisarMatrizActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuPesquisarMatriz);

        jMenuBar1.add(jMenu2);

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

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButtonExcluirCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(25, 25, 25)
                                        .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(36, 36, 36)
                                .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluirCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Opções da barra de menus: Importar(Importar) Matriz(Nova Matriz e
     * Pesquisar Matriz) Disciplina(Nova Disciplina e Pesquisar)
     */
    private void jMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPrincipalActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuPrincipalActionPerformed

    private void jMenuNovaDisiciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovaDisiciplinaActionPerformed
        JFNovaDisciplina obj = null;
        obj = new JFNovaDisciplina();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovaDisiciplinaActionPerformed

    private void jMenuPesquisarDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarDisciplinaActionPerformed
        JFPesquisarDisciplina obj = new JFPesquisarDisciplina();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarDisciplinaActionPerformed

    private void jMenuNovaMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovaMatrizActionPerformed
        JFNovaMatriz obj = null;
        obj = new JFNovaMatriz();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovaMatrizActionPerformed

    private void jMenuPesquisarMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarMatrizActionPerformed
        JFPesquisarMatriz obj = new JFPesquisarMatriz();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarMatrizActionPerformed

    private void jButtonAlterarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarCursoActionPerformed

    }//GEN-LAST:event_jButtonAlterarCursoActionPerformed
    /**
     * Botão excluir; Chama a função excluirCurso 
     */
    private void jButtonExcluirCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirCursoActionPerformed
        excluirCurso();
    }//GEN-LAST:event_jButtonExcluirCursoActionPerformed
    /**
     * Botão pesquisar; cria uma consulta sql e chama
     * a função popularJtable passando como parâmetro essa consulta
     * que servirá para popular a JTable
     */
    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        String sql = "SELECT tipoCurso, nomeCurso, vagas FROM curso WHERE tipoCurso = '"
                + jComboBoxTipoCurso.getSelectedItem().toString() + "'";
        popularJtable(sql);

    }//GEN-LAST:event_jButtonPesquisarActionPerformed
    /**
     * Botão para alterar cursos; Chama a função alterar curso e fecha esse
     * frame
     */
    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        alterarCurso();
        this.dispose();
    }//GEN-LAST:event_jButtonAlterarActionPerformed

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
            java.util.logging.Logger.getLogger(JFPesquisarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPesquisarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPesquisarCurso().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.util.List<importxml.Curso> cursoList;
    private javax.persistence.Query cursoQuery;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonAlterarCurso;
    private javax.swing.JButton jButtonExcluirCurso;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JComboBox<String> jComboBoxTipoCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCurso;
    private javax.swing.JMenuItem jMenuNovaDisiciplina;
    private javax.swing.JMenuItem jMenuNovaMatriz;
    private javax.swing.JMenuItem jMenuPesquisarDisciplina;
    private javax.swing.JMenuItem jMenuPesquisarMatriz;
    private javax.swing.JMenuItem jMenuPrincipal;
    private javax.swing.JScrollPane jScrollPaneCursos;
    private javax.persistence.EntityManager tccPUEntityManager;
    // End of variables declaration//GEN-END:variables

    /**
     * Popula a JTable com o retorno da consulta passada pelo parâmtero sql
     * @param sql = consulta que retorna as colunas tipoCurso, nomeCurso e vagas
     */
    public void popularJtable(String sql) {

        try {

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            modelo = (DefaultTableModel) table.getModel();
            modelo.addColumn("Tipo de Curso");
            modelo.addColumn("Nome do Curso");
            modelo.addColumn("Vagas");

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("tipoCurso"),
                    rs.getString("nomeCurso"),
                    rs.getString("Vagas")
                });
            }
            table.setModel(modelo);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.getColumnModel().getColumn(0).setPreferredWidth(90);
            table.getColumnModel().getColumn(1).setPreferredWidth(215);
            table.getColumnModel().getColumn(2).setPreferredWidth(43);
            jScrollPaneCursos.setViewportView(table);
            stmt.close();
            //con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Função para alterar o curso selecionado na JTable Seleciona o idCurso da
     * linha selecionado e direciona para o frame JFNovoCurso para o usuário
     * modificar o que achar necessário.
     */
    void alterarCurso() {
        Connection conn = new ConnectionFactory().getConnection();
        JFNovoCurso janela = new JFNovoCurso();

        janela.jTxt1.setText(getNomeCurso());
        janela.jCbx1.setSelectedItem(getTipoCurso());
        janela.jTFV.setText(getVagas());

        String query = "SELECT idCurso FROM curso WHERE nomeCurso = "
                + "'" + getNomeCurso() + "' AND tipoCurso = "
                + "'" + getTipoCurso() + "'";
        int idx = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idx = rs.getInt("idCurso");
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        janela.setId(idx);
        janela.setVisible(true);
        janela.jLB.setText("Atualizando Curso");
        janela.jButton.setText("Atualizar");
    }

    /**
     * Função para excluir dados da tabela curso; Seleciona o id do Curso da
     * lina selecionada e apaga o registro na tabela e na JTable
     */
    void excluirCurso() {
        Connection conn = new ConnectionFactory().getConnection();
        int linha = table.getSelectedRow();
        tipoCurso = table.getValueAt(linha, 0).toString();
        nomeCurso = table.getValueAt(linha, 1).toString();

        String query = "SELECT idCurso FROM curso WHERE nomeCurso = "
                + "'" + nomeCurso + "' AND tipoCurso = "
                + "'" + tipoCurso + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idCurso");
            }
            stmt.close();
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja "
                    + "exluir o curso?", "Atenção ", JOptionPane.YES_NO_OPTION);

            String sql = "DELETE FROM curso WHERE idCurso = "
                    + "'" + id + "'";
            if (opcao == JOptionPane.OK_OPTION) {

                PreparedStatement stmt1 = conn.prepareStatement(sql);
                int conseguiu_excluir = stmt1.executeUpdate();
                if (conseguiu_excluir == 1) {
                    modelo.removeRow(linha);
                    table.setModel(modelo);
                    stmt1.close();
                    conn.close();
                    JOptionPane.showMessageDialog(this, "Registro excluído com sucesso");

                }
            } else {
                this.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Impossível excluir!\n Certifique-se que não há"
                    + " matrizes e disciplinas vinculadas a esse curso!");
            this.dispose();
            ex.printStackTrace();
        }
    }

}
