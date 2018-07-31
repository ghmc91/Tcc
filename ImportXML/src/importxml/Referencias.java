package importxml;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gustavo
 */
public class Referencias extends javax.swing.JFrame {

    //Declaração de variáveis
    Connection con = new ConnectionFactory().getConnection();
    JTable table = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();
    String titulo, codDisciplina, nomeDisciplina, autor;
    int tipoRef;
    JTextField jTTitulo, jTFAutor;
    JLabel nomeDis;
    long idPHL;
    List<Long> resultList = new ArrayList<>();

    //Métodos Getters e Setters
    public long getIdPHL() {
        int t = table.getSelectedRow();
        idPHL = resultList.get(t);
        return idPHL;
    }

    public void setIdPHL(long idPHL) {
        this.idPHL = idPHL;
    }

    public String getTitulo() {
        titulo = jTFTitulo.getText();
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTipoRef() {
        if (jRadioBasica.isSelected()) {
            tipoRef = 1;
        } else if (jRadioComplementar.isSelected()) {
            tipoRef = 2;
        }
        return tipoRef;
    }

    public void setTipoRef(int tipoRef) {
        this.tipoRef = tipoRef;
    }

    public String getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(String codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getAutor() {
        autor = jTextFieldAutor.getText();
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    //Método Constructor
    public Referencias() {
        initComponents();
        ButtonGroup buttonGroupR = new ButtonGroup();
        buttonGroupR.add(jRadioBasica);
        buttonGroupR.add(jRadioComplementar);
        this.nomeDis = jLabel1;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFTitulo = new javax.swing.JTextField();
        jButtonPesquisar = new javax.swing.JButton();
        jLabelDisc = new javax.swing.JLabel();
        jScrollPaneReferencias = new javax.swing.JScrollPane();
        jButtonAdicionar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jRadioBasica = new javax.swing.JRadioButton();
        jRadioComplementar = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldAutor = new javax.swing.JTextField();
        jLabelNome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setText("Título:");

        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jButtonAdicionar.setText("Adicionar");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo de Referência:");

        jRadioBasica.setText("Básica");

        jRadioComplementar.setText("Complementar");

        jLabel4.setText("Autor");

        jLabelNome.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonAdicionar)
                                .addGap(288, 288, 288))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jButtonPesquisar))
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(407, 407, 407)
                            .addComponent(jLabelDisc))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPaneReferencias, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(jTFTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioBasica)
                        .addGap(93, 93, 93)
                        .addComponent(jRadioComplementar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelDisc)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jButtonPesquisar)
                .addGap(26, 26, 26)
                .addComponent(jScrollPaneReferencias, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioBasica)
                    .addComponent(jRadioComplementar)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jButtonAdicionar)
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Botão para adicionar refereências bibliográficas
     */
    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        insert();
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja inserir outra referencia?",
                "Atenção", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            Referencias janela = new Referencias();
            janela.setCodDisciplina(getCodDisciplina());
            this.dispose();
            janela.setVisible(true);
        }
    }//GEN-LAST:event_jButtonAdicionarActionPerformed
    //Botão pesquisar que chama a função popularJtable
    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        popularJTable();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

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
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelDisc;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioBasica;
    private javax.swing.JRadioButton jRadioComplementar;
    private javax.swing.JScrollPane jScrollPaneReferencias;
    private javax.swing.JTextField jTFTitulo;
    private javax.swing.JTextField jTextFieldAutor;
    // End of variables declaration//GEN-END:variables

    //Função para popular a JTable de acordo com os resultados dos filtros
    void popularJTable() {

        modelo = (DefaultTableModel) table.getModel();
        modelo.addColumn("Título");
        modelo.addColumn("Autor");
        modelo.addColumn("Editora");
        modelo.addColumn("Ano publicação");
        modelo.addColumn("Quantidade de exemplares");

        String query = "SELECT DISTINCT idPHL, titulo, autor, editora, anoPublicacao, qtdEx FROM catalogo "
                + "WHERE titulo LIKE '%" + getTitulo() + "%' AND autor LIKE '%" + getAutor() + "%'";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("editora"),
                    rs.getString("anoPublicacao"),
                    rs.getInt("qtdEx")
                });
                resultList.add(rs.getLong("idPHL"));

            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        table.setModel(modelo);
        jScrollPaneReferencias.setViewportView(table);

    }

    /**
     * Função para inserir dados na tabela disciplina_livros Com valores obtidos
     * nos métodos Getters e Setters
     */
    void insert() {
        String ins2 = "INSERT INTO disciplina_livros(livro, disciplina, tipoReferencia) VALUES ("
                + "" + getIdPHL() + ", '" + getCodDisciplina() + "', " + getTipoRef() + ")";
        try {
            PreparedStatement stmt2 = con.prepareStatement(ins2);
            stmt2.executeUpdate();
            JOptionPane.showMessageDialog(this, "Inserido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Impossível Inserir");
            ex.printStackTrace();
        }

    }

}
