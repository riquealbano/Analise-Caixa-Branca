package CódigoFonte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * CLASSE USER - Contém falhas graves de segurança e de design.
 * Não utiliza Javadoc para documentação completa.
 */
public class User {
    
    /**
     * Tenta conectar ao BD, mas está propenso a falhas e esconde exceções.
     * @return Connection (Pode retornar 'null' se a conexão falhar, causando NullPointerException no chamador).
     */
    public Connection conectarBD(){
        // Variável local 'conn' é declarada como null.
        Connection conn = null;
        try{
            // FALHA: Nome do driver incorreto (com.mysql.Driver.Manager) – Isso causará um ClassNotFoundException.
            // O .newInstance() é obsoleto e desnecessário.
            Class.forName("com.mysql.Driver.Manager").newInstance(); 
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
            
        // FALHA CRÍTICA: Bloco catch vazio (engole a exceção!).
        // Se a conexão falhar (pelo erro do driver, por exemplo), a exceção é ignorada,
        // e 'conn' será retornado como 'null' (Linha 16).
        }catch (Exception e) { } 
        return conn; // Retorna null em caso de falha silenciosa.
    }
    
    // FALHA DE DESIGN: Variáveis de estado da classe, usadas incorretamente como locais.
    // Isso torna a classe não segura para múltiplos usuários simultâneos.
    public String nome="";
    public boolean result = false;
    
    /**
     * Verifica o login do usuário. Contém falha de segurança (SQL Injection) e bug fatal.
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return true se o usuário for válido, false caso contrário.
     */
    public boolean verificarUsuario(String login, String senha){
        String sql = "";
        // Variável local 'conn' recebe 'null' se conectarBD() falhar (quase garantido pelo erro do driver).
        Connection conn = conectarBD(); 
        //INSTRUÇÃO SQL
        
        // FALHA GRAVE (SQL Injection): A query é construída por concatenação de strings.
        // Um atacante pode injetar código malicioso aqui.
        sql +="select nome from usuarios ";
        sql +="where login = '" + login + "'";
        sql +=" and senha = '" + senha + "'";
        
        try{
            // FALHA FATAL: Se 'conn' for 'null' (o que é muito provável), esta linha lança um NullPointerException.
            Statement st = conn.createStatement(); 
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                result = true;
                // 'nome' é uma variável de classe modificada aqui.
                nome = rs.getString("nome");}
        
        // FALHA CRÍTICA: Bloco catch vazio (engole a exceção!).
        // Exceções de SQL ou o NullPointerException são ignorados silenciosamente.
        }catch (Exception e) { } 
        
        // FALHA (Vazamento de Recursos): Conexão (conn), Statement (st) e ResultSet (rs)
        // NUNCA são fechados, causando Resource Leak.
        return result;
    } //fim da class
}