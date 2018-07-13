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

    private JTable table = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();
    String titulo;
    int idMatriz, idReferencia;

    public int getIdMatriz() {
        return idMatriz;
    }

    public void setIdMatriz(int idMatriz) {
        this.idMatriz = idMatriz;
    }

    public JFrameConsReferencias() {
        initComponents();
        
        
        
    }
         

    void popularJtable() {
        Connection conn = new ConnectionFactory().getConnection();

        modelo = (DefaultTableModel) table.getModel();
        modelo.addColumn("Matriz_idMatriz");
        modelo.addColumn("tipoReferencia");
        modelo.addColumn("Titulo");
        modelo.addColumn("Autor");
        modelo.addColumn("Edicao");
        modelo.addColumn("anoPublicacao");

        String sql = "SELECT Matriz_idMatriz, tipoReferencia, Titulo, Autor, "
                + "Edicao, anoPublicacao FROM referencias_adotadas WHERE Matriz_idMatriz = '"
                + "" + idMatriz + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("Matriz_idMatriz"),
                    rs.getString("tipoReferencia"),
                    rs.getString("Titulo"),
                    rs.getString("Autor"),
                    rs.getString("Edicao"),
                    rs.getString("anoPublicacao")
                });
            }
            table.setModel(modelo);
            jScrollPaneReferencias.setViewportView(table);
            stmt.close();
            conn.close();
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
        jButtonAlterar = new javax.swing.JButton();
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonAdicionar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonExcluir)
                                .addGap(9, 9, 9))
                            .addComponent(jScrollPaneReferencias, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAlterar)
                            .addComponent(jLabel1))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonAdicionar))
                .addGap(35, 35, 35)
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
        referencias.setIdMatriz(idMatriz);
        referencias.setVisible(true);
    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        Connection conn = new ConnectionFactory().getConnection();
        int linha = table.getSelectedRow();
        String tipoRef = table.getValueAt(linha, 1).toString();
        titulo = table.getValueAt(linha, 2).toString();
        String autor = table.getValueAt(linha, 3).toString();
        String edicao = table.getValueAt(linha, 4).toString();
        String anoPulicacao = table.getValueAt(linha, 5).toString();
        
        String query = "SELECT idReferencia FROM referencias_adotadas "
                + "WHERE Matriz_idMatriz = " + getIdMatriz() + " "
                + "AND Titulo = '" + titulo + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idReferencia = rs.getInt("idReferencia");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFrameConsReferencias.class.getName()).log(Level.SEVERE, null, ex);
        }

        Referencias referencias = new Referencias();
        referencias.setIdMatriz(getIdMatriz());
        referencias.setIdRef(idReferencia);
        referencias.jCBtipoRef.setSelectedItem(tipoRef);
        referencias.jTTitulo.setText(titulo);
        referencias.jTAutor.setText(autor);
        referencias.jTAnoPublicacao.setText(anoPulicacao);
        referencias.setVisible(true);


    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        Connection conn = new ConnectionFactory().getConnection();
        int linha = table.getSelectedRow();
        titulo = table.getValueAt(linha, 2).toString();

        String query = "SELECT idReferencia FROM referencias_adotadas "
                + "WHERE Matriz_idMatriz = " + getIdMatriz() + " "
                + "AND Titulo = '" + titulo + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idReferencia = rs.getInt("idReferencia");
            }
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja "
                    + "exluir a referência?", "Atenção", JOptionPane.YES_NO_OPTION);
            String query2 = "DELETE FROM referencias_adotadas WHERE idReferencia = '" + idReferencia + "'";
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
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneReferencias;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
