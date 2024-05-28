package model.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final DBConnector INSTANCE = new DBConnector();
    private static Connection connection = null;

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        return INSTANCE;
    }

    public static Connection connection(String db, String username, String password) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (connection == null || connection.isClosed()) {
                connection = doConnection(db, username, password);
            }
        } catch (SQLException e) {
            System.err.println("Error al comprobar si está cerrada la conexión: " + e);
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e);
        }
    }

    private static Connection doConnection(String db, String username, String password) throws ClassNotFoundException {
        Connection conn;
        try {
            conn = DriverManager.getConnection(URL + db, username, password);
        } catch (SQLException e) {
            System.err.println("Error al crear la conexión: " + e);
            throw new RuntimeException(e);
        }
        System.out.println("Conexión creada: " + conn);
        return conn;
    }
}

