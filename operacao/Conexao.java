package operacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:oracle:thin:@//PEDROBAKONOTE:1521/XE", "NOTEPADM", "poounifesp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}