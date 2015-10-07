package com.example.yhuang.scavengerhunt.Database;

/**
 * Created by siyer on 10/6/2015.
 */
import java.sql.*; //For DB Connection

public class ClueDBConnection {
    private static final String URL = "jdbc:mysql://45.55.65.113/mobproto";
    private static final String USER = "student";
    private static final String PASS = "MobProto";

    public static void main(String[] argv) {
        try {
            getDataFromTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getDataFromTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT VIDEO_ID FROM SCAVENGER_INFO WHERE VIDEO_ID = ?";

        try {
            dbConnection = getConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, 1001);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String videoid = rs.getString("VIDEO_ID");

                System.out.println("videoid : " + videoid);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }
    }

    public static Connection getConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

        try {
            dbConnection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}