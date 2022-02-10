package repository;

import model.User;
import service.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserRepository {
    Statement myReadStatement = DatabaseConnector.getReadStatement();
    Statement myUpdateStatement = DatabaseConnector.getUpdateStatement();

    private static final Scanner sc = new Scanner(System.in);
    public static final String INVALID_QUERY = "Querry invalid";

    ResultSet rs = null;
    String userName = null;
    String firstName = null;
    String lastName = null;
    String email = null;
    String password = null;

    public void createUser(String userName, String firstName, String lastName, String mail, String password) {
        String query = "INSERT INTO user_table (u_userName, u_firstName, u_lastName, u_email, u_password) VALUES" +
                "('%s', '%s', '%s', '%s', '%s')";
        if (DatabaseConnector.getUpdateStatement() != null) {
            try {
                int affectedColumns = myUpdateStatement.executeUpdate(String.format(query, userName, firstName, lastName, mail, password));
                System.out.println(affectedColumns == 0 ? "User-ul nu a putut fi salvat." : "User-ul a fost salvat cu succes.");
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
        }
    }

    public ArrayList<User> readAll() {
        ArrayList<User> users = new ArrayList<>();

        if (myReadStatement != null) {
            try {
                ResultSet rs = myReadStatement.executeQuery("SELECT * FROM user_table");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    userName = rs.getString("u_userName");
                    firstName = rs.getString("u_firstName");
                    lastName = rs.getString("u_lastName");
                    email = rs.getString("u_email");
                    password = rs.getString("u_password");
                    users.add(new User(id, userName, firstName, lastName, email, password));
                }
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
            return users;
        }
        return null;
    }

    public User read_by_id(int id) {
        if (myReadStatement != null) {
            try {
                rs = myReadStatement.executeQuery("SELECT * FROM user_table WHERE id = " + id);
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
                return null;
            }
            try {
                rs.next();
                userName = rs.getString("u_userName");
                firstName = rs.getString("u_firstName");
                lastName = rs.getString("u_lastName");
                email = rs.getString("u_email");
                password = rs.getString("u_password");
            } catch (SQLException e) {
                return null;
            }
            return new User(id, userName, firstName, lastName, email, password);
        }
        return null;
    }

    public void delete(int id) {

        if (myUpdateStatement != null) {
            try {
                int affectedRows = myUpdateStatement.executeUpdate("DELETE FROM user_table WHERE id = " + id);
                System.out.println("User-ul " + (affectedRows != 0 ? "" : "nu") + " a fost sters cu succes.");
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
        }
    }

    public void modifyColumn(int id, String columnName) {
        System.out.println("Introduceti noul " + columnName);
        String newColumnName = sc.nextLine();
        if (myUpdateStatement != null) {
            try {
                String query = "UPDATE user_table SET %s = '%s' WHERE id = %d";
                myUpdateStatement.executeUpdate(String.format(query, columnName, newColumnName, id));
                System.out.println("Userul a fost modificat cu succes.");
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
        }
    }
}