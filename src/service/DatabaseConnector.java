package service;

import java.sql.*;

public class DatabaseConnector {

    public static final String CONNECTION_ERROR = "Conexiunea nu a putut fi stabilita";

    public static Statement getReadStatement() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitterDatabase", "root", "MySQL3085");
            return connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            System.out.println(CONNECTION_ERROR);
            return null;
        }
    }

    public static Statement getUpdateStatement() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitterDatabase", "root", "MySQL3085");
            return connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            System.out.println(CONNECTION_ERROR);
            return null;
        }
    }
}

