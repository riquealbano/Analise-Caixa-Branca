package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    
    public Connection conectarBD(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.Driver.Manager").newInstance();
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        }catch (Exception e) { } 
        return conn;
    }
    
    public String nome="";
    public boolean result = false;
    
    // Nó 1
    public boolean verificarUsuario(String login, String senha){
        String sql = "";
        
        // Nó 2
        Connection conn = conectarBD(); 
        
        // Nó 3
        sql +="select nome from usuarios ";
        sql +="where login = '" + login + "'";
        sql +=" and senha = '" + senha + "'";
        
        // Nó 4 (Decisão 1: Início do Try)
        try{
            // Nó 5
            Statement st = conn.createStatement(); 
            
            // Nó 6
            ResultSet rs = st.executeQuery(sql);
            
            // Nó 7 (Decisão 2: Início do If)
            if(rs.next()){
                // Nó 8
                result = true;
                // Nó 9 (Fim do If)
                nome = rs.getString("nome");}
        
        // Nó 10 (Bloco Catch)
        }catch (Exception e) { } 
        
        // Nó 12 (Fim do Método/Exit Node)
        return result;
    } //fim da class
}
