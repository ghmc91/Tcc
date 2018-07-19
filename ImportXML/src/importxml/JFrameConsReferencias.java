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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gustavo
 */
public class JFrameConsReferencias extends javax.swing.JFrame {

    Connection con = new ConnectionFactory().getConnection();
    private JTable table = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();
    String codDisciplina, titulo;
    int idReferencia, idCurso;
    long idPHL;

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

    public JFrameConsReferencias() {
        initComponents();

    }

    void popularJtable() {
        modelo = (DefaultTableModel) table.getModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Disciplina");
        modelo.addColumn("Descrição");
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
                    rs.getString("d.nome"),
                    rs.getString("t.descricao"),
                    rs.getString("c.titulo"),
                    rs.getString("c.autor"),
                    rs.getInt("c.qtdEx")
                });
                setIdPHL(rs.getLong("c.idPHL"));
            }
            table.setModel(modelo);
            jScrollPaneReferencias.setViewportView(table);
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButtonExcluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPaneReferencias = new javax.swing.JScrollPane();
        jButtonAdicionar = new javax.swing.JButton();

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

        jLabel1.setText("Referencias");

        jButtonAdicionar.setText("Adicionar");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneReferencias, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jButtonAdicionar)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(jButtonExcluir)))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonAdicionar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneReferencias, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        Referencias referencias = new Referencias();
        referencias.setCodDisciplina(getcodDisciplina());
        referencias.setVisible(true);
    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        Connection conn = new ConnectionFactory().getConnection();
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
                PreparedStatement stmt1 = conn.prepareStatement(query2);
                int conseguiu_excluir = stmt1.executeUpdate();
                if (conseguiu_excluir == 1) {
                    modelo.removeRow(linha);
                    table.setModel(modelo);
                    stmt1.close();
                    conn.close();
                    JOptionPane.showMessageDialog(this, "Registro excluído com sucesso");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Impossível excluir");
            ex.printStackTrace();
        }
    
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneReferencias;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
