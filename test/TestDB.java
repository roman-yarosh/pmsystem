package net.proselyte.pmsystem.test;

import org.junit.Test;

import java.sql.*;
/**
 * Test DB connection. Cloud-Based Relational Database.
 *
 * @author Kyryl Potapenko
 */
public class TestDB {
    private static String DB_URL = "jdbc:mysql://pmsystem.cxguqrodfhgl.us-west-2.rds.amazonaws.com:3306/pmsystem";
    private static String USER = "pmsystem";
    private static String PASS = "systemin";

    @Test
    public void readTest() {
        Statement statement = null;
        Connection connection = null;
        try  {
            connection = getConnection();
            System.out.println("Connected to the database....");
            statement = connection.createStatement();
            String sql = "Select * from TEST_TABLE";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("test_id");
                String name = rs.getString("test_name");
                System.out.print("  ID: " + id);
                System.out.print(", Status: " + name);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("sql exception" + e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Goodbye!");
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }
}
