package importxml;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Gustavo
 */
public class Referencias extends javax.swing.JFrame {

    Connection con = new ConnectionFactory().getConnection();
    String tipoRef, titulo, edicao, autor, anoPublicacao;
    JComboBox<String> jCBtipoRef;
    JTextField jTTitulo, jTEdicao, jTAutor, jTAnoPublicacao;
    int idDisciplina, tR, idRef;

    public String getTipoRef() {
        tipoRef = jComboBoxTipoRef.getSelectedItem().toString();
        return tipoRef;
    }

    public void setTipoRef(String tipoRef) {
        this.tipoRef = tipoRef;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public int getIdRef() {
        return idRef;
    }

    public void setIdRef(int idRef) {
        this.idRef = idRef;
    }

    public int gettR() {
        if(getTipoRef().equals("Básica")){
            tR = 0;
        }else if(getTipoRef().equals("Complementar")){
            tR = 1;
        }
        return tR;
    }

    public void settR(int tR) {
        this.tR = tR;
    }
    
    

    public Referencias() {
        initComponents();
        jComboBoxTipoRef.addItem("Básica");
        jComboBoxTipoRef.addItem("Complementar");

        this.jCBtipoRef = jComboBoxTipoRef;
        this.jTAutor = jTFAutor;
        this.jTAnoPublicacao = jTFAnoPublicacao;
        this.jTTitulo = jTFTitulo;
        this.jTEdicao = jTFEdicao;
        
        
    }

    void insert(int tR, String titulo, String autor, String edicao, String anoPublicacao) {
        Connection conn = new ConnectionFactory().getConnection();
        String query = "INSERT INTO referencias_adotadas(tipoReferencia, "
                + "Titulo, Autor, Editora, anoPublicacao, Disciplina) VALUES("
                + "'" + tR + "', '" + titulo + "', '" + autor + "', "
                + "'" + edicao + "', '" + anoPublicacao + "', '" + getIdDisciplina()+ "')";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Inserido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Impossível inserir");
            ex.printStackTrace();
        }
    }

    void update(int tR, String titulo, String autor, String edicao, String anoPublicacao) {
                
        Connection conn = new ConnectionFactory().getConnection();
        String query = "UPDATE referencias_adotadas SET tipoReferencia = '" + tR + "',"
                + " Titulo = '" + titulo + "', Autor = '" + autor + "', Editora = '" + edicao + "',"
                + " anoPublicacao = '" + anoPublicacao + "' WHERE idReferencia = '" + getIdRef() + "'";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(this, "Atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Impossível atualizar");
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxTipoRef = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTFTitulo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTFEdicao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTFAutor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTFAnoPublicacao = new javax.swing.JTextField();
        jBSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Referências bibliográficas");

        jLabel5.setText("Tipo");

        jLabel6.setText("Título:");

        jLabel7.setText("Editora");

        jLabel8.setText("Autor");

        jLabel9.setText("Ano da publicação:");

        jTFAnoPublicacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFAnoPublicacaoActionPerformed(evt);
            }
        });

        jBSalvar.setText("Salvar");
        jBSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxTipoRef, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTFTitulo)
                                    .addComponent(jTFAutor)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jBSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTFAnoPublicacao))))))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxTipoRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTFEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTFAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTFAnoPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jBSalvar)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed

        tipoRef = jCBtipoRef.getSelectedItem().toString();
        titulo = jTTitulo.getText();
        autor = jTAutor.getText();
        edicao = jTEdicao.getText();
        anoPublicacao = jTAnoPublicacao.getText();

        
        if (getIdRef() == 0) {
            insert(gettR(), titulo, autor, edicao, anoPublicacao);
            int opcao = JOptionPane.showConfirmDialog(this, "Deseja inserir a referencia bibliográfica?",
                    "Atenção", JOptionPane.YES_NO_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
            this.dispose();
            Referencias rf = new Referencias();
            rf.setIdDisciplina(getIdDisciplina());
            rf.setVisible(true);
            }
        } else {

            update(gettR(), titulo, autor, edicao, anoPublicacao);
        }
    }//GEN-LAST:event_jBSalvarActionPerformed

    private void jTFAnoPublicacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFAnoPublicacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFAnoPublicacaoActionPerformed

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
            java.util.logging.Logger.getLogger(Referencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Referencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Referencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Referencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Referencias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBSalvar;
    private javax.swing.JComboBox<String> jComboBoxTipoRef;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFAnoPublicacao;
    private javax.swing.JTextField jTFAutor;
    private javax.swing.JTextField jTFEdicao;
    private javax.swing.JTextField jTFTitulo;
    // End of variables declaration//GEN-END:variables

    void setAnoPublicacao(int anoPulicacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
