package importxml;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Gustavo
 */
public class JFNovaMatriz extends javax.swing.JFrame {

    Connection con = new ConnectionFactory().getConnection();
    JComboBox<String> jCTP, jCNC, jCSt;
    String nomeCurso, anoInicio, anoFim;
    JTextField anoI, anoF;
    int status, idCurso, idMatriz;

    public int getIdMatriz() {
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

    public String getAnoInicio() {
        anoInicio = jTFAnoInicio.getText();
        return anoInicio;
    }

    public void setAnoInicio(String anoInicio) {
        this.anoInicio = anoInicio;
    }

    public String getAnoFim() {
        anoFim = jTFAnoFim.getText();
        return anoFim;
    }

    public void setAnoFim(String anoFim) {
        this.anoFim = anoFim;
    }

    public int getStatus() {
        String st = jComboBoxStatus.getSelectedItem().toString();
        if (st.equals("Ativa")) {
            status = 0;
        } else {
            status = 1;
        }
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdCurso() {
        Connection con = new ConnectionFactory().getConnection();
        String query = "SELECT idCurso FROM curso WHERE nomeCurso = '" + getNomeCurso() + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                idCurso = rs.getInt("idCurso");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public JFNovaMatriz() {
        initComponents();

        this.jCTP = jComboBoxTipoCurso;
        this.jCNC = jComboBoxNomeCurso;
        this.anoI = jTFAnoInicio;
        this.anoF = jTFAnoFim;
        this.jCSt = jComboBoxStatus;

        this.jComboBoxTipoCurso.removeAllItems();
        this.jComboBoxTipoCurso.addItem("Selecione");
        this.jComboBoxTipoCurso.addItem("Técnico");
        this.jComboBoxTipoCurso.addItem("Superior");
        this.jComboBoxTipoCurso.addItem("Pós-Graduação");
        this.jComboBoxTipoCurso.addItem("Mestrado");

        updateCombo();

        this.jComboBoxNomeCurso.addItem("Selecione");

        this.jComboBoxStatus.addItem("Ativa");
        this.jComboBoxStatus.addItem("Inativa");

    }

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

    void insert(int idCurso, String anoInicio, String anoFim, int status) {
        Connection con = new ConnectionFactory().getConnection();
        String query = "INSERT INTO matriz (anoInicio, anoFim, status, idCurso) VALUES("
                + "'" + anoInicio + "', '" + anoFim + "', '" + status + "', '" + idCurso + "')";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(this, "Registro salvo");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro");
        }
    }

    void update(int idCurso, String anoInicio, String anoFim, int status) {
        Connection conn = new ConnectionFactory().getConnection();
        String query = "UPDATE matriz SET anoFim = '" + anoFim + "', "
                + "status = '" + status + "',idCurso = '" + idCurso + "' WHERE anoInicio = '"
                + "" + getAnoInicio() + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Atualizado");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro");
        }
    }

    public int countForUp() {
        int cont = 0;
        String query = "SELECT count(*) AS cont FROM matriz WHERE idCurso = '" + getIdCurso() + "' "
                + "AND anoInicio = '" + getAnoInicio() + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cont = rs.getInt("cont");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cont;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBoxTipoCurso = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxNomeCurso = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTFAnoInicio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFAnoFim = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemImportar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemNovoCurso = new javax.swing.JMenuItem();
        jMenuItemPesquisarCurso = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItemNovaMatriz = new javax.swing.JMenuItem();
        jMenuItemPesquisarMatriz = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tipo de curso: ");

        jLabel2.setText("Curso:");

        jComboBoxNomeCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel3.setText("Ano inicio:");

        jTFAnoInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFAnoInicioActionPerformed(evt);
            }
        });

        jLabel4.setText("Ano fim:");

        jLabel5.setText("Status:");

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Nova Matriz");

        jMenu1.setText("Importar");

        jMenuItemImportar.setText("Importar");
        jMenuItemImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemImportarActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemImportar);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Curso");

        jMenuItemNovoCurso.setText("Novo Curso");
        jMenu2.add(jMenuItemNovoCurso);

        jMenuItemPesquisarCurso.setText("Pesquisar...");
        jMenu2.add(jMenuItemPesquisarCurso);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Matriz");

        jMenuItemNovaMatriz.setText("Nova Disciplina");
        jMenu5.add(jMenuItemNovaMatriz);

        jMenuItemPesquisarMatriz.setText("Pesquisar...");
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
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxTipoCurso, 0, 100, Short.MAX_VALUE)
                            .addComponent(jTFAnoInicio)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxNomeCurso, 0, 100, Short.MAX_VALUE)
                            .addComponent(jTFAnoFim)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(204, 204, 204))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(179, 179, 179))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTipoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTFAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTFAnoFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (countForUp() == 0) {
            insert(getIdCurso(), getAnoInicio(), getAnoFim(), getStatus());

        } else {
            update(getIdCurso(), getAnoInicio(), getAnoFim(), getStatus());
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTFAnoInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFAnoInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFAnoInicioActionPerformed

    private void jMenuItemImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImportarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemImportarActionPerformed

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
                new JFNovaMatriz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxNomeCurso;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JComboBox<String> jComboBoxTipoCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemImportar;
    private javax.swing.JMenuItem jMenuItemNovaMatriz;
    private javax.swing.JMenuItem jMenuItemNovoCurso;
    private javax.swing.JMenuItem jMenuItemPesquisarCurso;
    private javax.swing.JMenuItem jMenuItemPesquisarMatriz;
    private javax.swing.JTextField jTFAnoFim;
    private javax.swing.JTextField jTFAnoInicio;
    // End of variables declaration//GEN-END:variables
}
