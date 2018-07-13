package importxml;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Gustavo
 */
public class JFrameMain extends javax.swing.JFrame {

    //Declaração das variaveis de classe
    private File xmlFile;
    private File arquivoEscolhido;

    public JFrameMain() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        jButtonProcurar = new javax.swing.JButton();
        jButtonImportar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaInfo = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCurso = new javax.swing.JMenu();
        jMenuNovoCurso = new javax.swing.JMenuItem();
        jMenPesquisarCurso = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuNovaDisiciplina = new javax.swing.JMenuItem();
        jMenuPesquisarDisciplina = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuNovaMatriz = new javax.swing.JMenuItem();
        jMenuPesquisarMatriz = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenuItem2.setText("jMenuItem2");

        jMenu5.setText("jMenu5");

        jMenu6.setText("jMenu6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ImportXML");

        jLabel1.setText("Selecionar arquivo");

        jButtonProcurar.setText("Procurar");
        jButtonProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProcurarActionPerformed(evt);
            }
        });

        jButtonImportar.setText("Importar");
        jButtonImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImportarActionPerformed(evt);
            }
        });

        jTextAreaInfo.setColumns(20);
        jTextAreaInfo.setRows(5);
        jScrollPane1.setViewportView(jTextAreaInfo);

        jMenuCurso.setText("Curso");

        jMenuNovoCurso.setText("Novo...");
        jMenuNovoCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNovoCursoActionPerformed(evt);
            }
        });
        jMenuCurso.add(jMenuNovoCurso);

        jMenPesquisarCurso.setText("Pesquisar...");
        jMenPesquisarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenPesquisarCursoActionPerformed(evt);
            }
        });
        jMenuCurso.add(jMenPesquisarCurso);

        jMenuBar1.add(jMenuCurso);

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

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonProcurar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonImportar, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButtonProcurar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonImportar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * O Botão procurar chama a janela para selecionar o arquivo xml
     * através do método buscarJFileChooser armazenado na variável arquivo
     * escolhido
     */
    private void jButtonProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProcurarActionPerformed
        arquivoEscolhido = buscarJFileChooser();

    }//GEN-LAST:event_jButtonProcurarActionPerformed
    
    //    
    private void jButtonImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImportarActionPerformed
        inserirDados(xmlFile);
    }//GEN-LAST:event_jButtonImportarActionPerformed

    private void jMenuNovoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovoCursoActionPerformed
        JFNovoCurso obj = new JFNovoCurso();
        obj.setVisible(true);
        
    }//GEN-LAST:event_jMenuNovoCursoActionPerformed

    private void jMenPesquisarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenPesquisarCursoActionPerformed
        JFPesquisarCurso obj = new JFPesquisarCurso();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenPesquisarCursoActionPerformed

    private void jMenuNovaDisiciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovaDisiciplinaActionPerformed
        JFNovaDisciplina obj=null; 
        try {
            obj = new JFNovaDisciplina();
        } catch (SQLException ex) {
            Logger.getLogger(JFrameMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovaDisiciplinaActionPerformed

    private void jMenuPesquisarDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarDisciplinaActionPerformed
        JFPesquisarDisciplina obj = new JFPesquisarDisciplina();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarDisciplinaActionPerformed

    private void jMenuNovaMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNovaMatrizActionPerformed
        JFNovaMatriz obj = null;
        try {
            obj = new JFNovaMatriz();
        } catch (SQLException ex) {
            Logger.getLogger(JFrameMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuNovaMatrizActionPerformed

    private void jMenuPesquisarMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPesquisarMatrizActionPerformed
        JFPesquisarMatriz obj = new JFPesquisarMatriz();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuPesquisarMatrizActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameMain().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonImportar;
    private javax.swing.JButton jButtonProcurar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenPesquisarCurso;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuCurso;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuNovaDisiciplina;
    private javax.swing.JMenuItem jMenuNovaMatriz;
    private javax.swing.JMenuItem jMenuNovoCurso;
    private javax.swing.JMenuItem jMenuPesquisarDisciplina;
    private javax.swing.JMenuItem jMenuPesquisarMatriz;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaInfo;
    // End of variables declaration//GEN-END:variables

    //Função para selecionar o arquivo xml a ser importado     
    private File buscarJFileChooser() {
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("Selecione o arquivo xml");
            jFileChooser.setFileFilter(new FileTypeFilter(".xml", "XML File"));
            int result = jFileChooser.showSaveDialog(null);
            if (result == jFileChooser.APPROVE_OPTION) {
                xmlFile = jFileChooser.getSelectedFile();
            }

            File arquivo = jFileChooser.getSelectedFile();

            jTextAreaInfo.setText("Arquivo selecionado: " + arquivo.getName()
                    + "\n" + "Caminho: " + arquivo.getPath());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Documento XML não selecionado");
        }
        return null;

    }
    
    //Função para inserir os dados obtidos do arquivo xml
    public void inserirDados(File arquivo) {
        //Testando o carregamento do driver jdbc

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Driver não carregado");
            return;
        }

        //Testando a conexão com o banco de dados
        Connection con;
        con = new ConnectionFactory().getConnection();
        //Convertendo o arquivo xml
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(arquivo);
            
            XPath xpath = XPathFactory.newInstance().newXPath();
            Object res = xpath.evaluate("/db/rec", doc, XPathConstants.NODESET);
            
            // Comando para popular a coluna titulo da tabela catalogo
            PreparedStatement stmt = con
                    .prepareStatement("INSERT INTO catalogo (titulo, autor, edicao, anoPublicacao) "
                            + "VALUES (?,?,?,?)");
            
            // Lista de nós reconhecidos pela tag 'rec'
            NodeList nList = doc.getElementsByTagName("rec");
            
            /**
             * loop para extrair os titulos dos livros que estão contidos no
             * elemento filho especificado no segundo parâmetro da função
             * getTextContent (abaixo da função principal) e inserir os
             * dados na coluna titulo da tabela catalgo
             */
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                List<String> columns;
                columns = Arrays
                        .asList(getTextContent(node, "v018"),
                                getTextContent(node, "v016"),
                                getTextContent(node, "v062"),
                                getTextContent(node, "v064"));
                
                //Loop para ir preenchendo as colunas da tabela catalogo
                for (int n = 0; n < columns.size(); n++) {
                    stmt.setString(n + 1, columns.get(n));
                }
                stmt.execute();
                
            }
            //Mensagem para informar que os dados foram inserirdos com sucesso
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (IOException | SQLException | ParserConfigurationException | XPathExpressionException | SAXException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados");
            e.printStackTrace();
        }
        
        // Capturando exceção SQL
        try {
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexão não estabelecida");
            return;
        }

    }

    // Função para pegar o texto contido na lista de nós
    static private String getTextContent(Node parentNode, String childName) {
        NodeList nList = parentNode.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            Node n = nList.item(i);
            String name = n.getNodeName();
            if (name != null && name.equals(childName)) {
                return n.getTextContent();
            }
        }
        return "";

    }

}
