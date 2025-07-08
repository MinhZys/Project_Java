package com.example.projectjava.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    // Hàm dùng để lấy kết nối khi gọi từ controller
    public static Connection getConnection() throws SQLException {
        String serverName = "localhost";
        String portNumber = "1433";
        String databaseName = "FastFood";
        String username = "sa";
        String password = "tranminhquy120";

        String connectionUrl = "jdbc:sqlserver://" + serverName + ":" + portNumber
                + ";databaseName=" + databaseName + ";encrypt=true;trustServerCertificate=true";

        return DriverManager.getConnection(connectionUrl, username, password);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Kết nối SQL Server thành công!");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi kết nối SQL Server:");
            e.printStackTrace();
        }
    }
}
