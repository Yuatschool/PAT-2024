/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User-Pc
 */
public class DB {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
     private static final String url = "jdbc:mysql://127.0.0.1:3306/stocklist";
    private static final String user = "root";
    private static final String pass = "Reddam2021";
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    private static Connection conn;

    public static void connect() throws ClassNotFoundException, SQLException {
        if(conn == null){
            Class.forName(driver);
            System.out.println("Driver found");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connected");
        }
    }

    //INSERT, UPDATE or DELETE
    public static void update(String query) throws SQLException {
        statement = conn.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    //SELECT
    public static ResultSet query(String query) throws SQLException {
        statement = conn.prepareStatement(query);
        resultSet = (ResultSet) statement.executeQuery();
        return resultSet;
    }
}




