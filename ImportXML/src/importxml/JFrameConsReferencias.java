/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importxml;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gustavo
 */
public class JFrameConsReferencias extends javax.swing.JFrame {

    //Declaração de variávies
    Connection con = new ConnectionFactory().getConnection();
    private JTable table = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();
    String codDisciplina, titulo, nomeDis;
    int idReferencia, idCurso;
    long idPHL;
    JLabel jLD;

    //Métodos Getters e Setters
    public String getcodDisciplina() {
        return codDisciplina;
    }

    public void setcodDisciplina(String codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public int getIdCurso() {
        String query = "SELECT matrizCurso FROM matriz_has_disciplina "
                + "WHERE disciplina = '" + getcodDisciplina() + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idCurso = rs.getInt("matrizCurso");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public long getIdPHL() {
        return idPHL;
    }

    public void setIdPHL(long idPHL) {
        this.idPHL = idPHL;
    }

    public String getNomeDis() {
        return nomeDis;
    }

    public void setNomeDis(String nomeDis) {
        this.nomeDis = nomeDis;
    }

    //Método Construtor
    public JFrameConsReferencias() {
        initComponents();
        this.jLD = jLabelD;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButtonExcluir = new javax.swing.JButton();
        jScrollPaneReferencias = new javax.swing.JScrollPane();
        jButtonAdicionar = new javax.swing.JButton();
        jLabelD = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonAdicionar.setText("Adicionar");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        jLabelD.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelD.setText("jLabel2");
        jLabelD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPaneReferencias, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(310, 310, 310))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabelD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(345, 345, 345))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabelD)
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdicionar)
                    .addComponent(jButtonExcluir))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneReferencias, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Botão adicionar Referencias; Adiciona Referências Bibliográficas
     * referentes a disciplina selecionada no Frame JFPesquisarDisciplina
     * através do Frame Referencias, que é aberta quando esse botão é
     * selecionado.
     */
    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        Referencias referencias = new Referencias();
        referencias.setCodDisciplina(getcodDisciplina());
        referencias.setNomeDisciplina(getNomeDis());
        referencias.nomeDis.setText(jLabelD.getText());
        referencias.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    //Botão excluir: Chama a função excluirReferencias()
    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        excluirReferencias();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameConsReferencias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JLabel jLabelD;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneReferencias;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    /**
     * Função para popular a tabela com as referências bibliográficas referentes
     * a disciplina selecionada no frame JFPesquisarDisciplina
     */
    void popularJtable() {
        modelo = (DefaultTableModel) table.getModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Título");
        modelo.addColumn("Autor");
        modelo.addColumn("Nº de exemplares");

        String query = "select d.codDisciplina, d.nome, t.descricao, c.titulo, c.autor, c.qtdEx, c.idPHL "
                + "from disciplina as d, matriz_has_disciplina as m, disciplina_livros as dl, catalogo as c, tiporeferencia as t "
                + "where matrizcurso = " + getIdCurso() + " and m.disciplina = d.codDisciplina and d.codDisciplina = dl.disciplina "
                + "and  t.idTipoRef = dl.tipoReferencia and dl.livro = c.idPHL and d.codDisciplina = '"
                + "" + getcodDisciplina() + "' order by nome, tipoReferencia";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("d.codDisciplina"),
                    rs.getString("c.titulo"),
                    rs.getString("c.autor"),
                    rs.getInt("c.qtdEx")
                });
                setIdPHL(rs.getLong("c.idPHL"));
                setNomeDis(rs.getString("d.nome"));
            }
            table.setModel(modelo);
            jScrollPaneReferencias.setViewportView(table);
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    void excluirReferencias() {

        long livro = 0;
        int linha = table.getSelectedRow();
        String titulo = table.getValueAt(linha, 3).toString();
        String query1 = "SELECT livro, idPHL FROM disciplina_livros, catalogo WHERE disciplina "
                + "= '" + getcodDisciplina() + "' AND titulo = '" + titulo + "' "
                + "AND disciplina_livros.livro = catalogo.idPHL;";
        try {
            PreparedStatement stmt = con.prepareStatement(query1);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                livro = rs.getLong("livro");
            }
            stmt.close();
            String query2 = "DELETE FROM disciplina_livros WHERE disciplina = '" + getcodDisciplina() + "' "
                    + "AND livro = " + livro + "";

            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja "
                    + "exlcuir a referência?", "Atenção", JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.OK_OPTION) {
                PreparedStatement stmt1 = con.prepareStatement(query2);
                int conseguiu_excluir = stmt1.executeUpdate();
                if (conseguiu_excluir == 1) {
                    modelo.removeRow(linha);
                    table.setModel(modelo);
                    stmt1.close();

                    JOptionPane.showMessageDialog(this, "Registro excluído com sucesso");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Impossível excluir");
            ex.printStackTrace();
        }

    }
}
