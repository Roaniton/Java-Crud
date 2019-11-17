package banco;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class Banco {
    public static Connection getCon(){
        Connection conexao = null;
        // invocando o driver da biblioteca
        String driver = "com.mysql.jdbc.Driver";
        // Armazenar informação ligadas ao banco 
        String url = "jdbc:mysql://localhost:3306/agenda";
        String user = "root";
        String password = "";
        // Conectar o banco de dados 
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
}
}

