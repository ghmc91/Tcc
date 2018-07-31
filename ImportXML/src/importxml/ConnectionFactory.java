/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importxml;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
public class ConnectionFactory {
    
    Connection con;
    
    public Connection getConnection(){
        //URL da conexão
        String url = "jdbc:mysql://localhost/tcc2";
        try {
            return (Connection) DriverManager.getConnection(url,"root", "GUstavo08!");
        }catch(SQLException ex){
            System.out.println("Problemas na conexão " + ex.getMessage());
            return null;
            }
    }
 
}