package repository;

import model.Follow;
import service.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FollowRepository {

    Statement myUpdateStatement = DatabaseConnector.getUpdateStatement();
    Statement myReadStatement = DatabaseConnector.getReadStatement();

    public static final String INVALID_QUERY = "Query invalid";

    ResultSet rs = null;
    int userId1 = 0;
    int userId2 = 0;

    public void follow(int userId1, int userId2) {
        if (myUpdateStatement != null) {
            try {
                String query = "INSERT INTO follow_table (u_id1, u_id2) VALUES ('%d', '%d')";
                myUpdateStatement.executeUpdate(String.format(query, userId1, userId2));
                System.out.println("Follow-ul a fost creat");
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
        }
    }

    public ArrayList<Follow> readUserFollowers(int id) {
        ArrayList<Follow> follows = new ArrayList<>();

        if (myReadStatement != null) {
            try {
                rs = myReadStatement.executeQuery("SELECT * FROM follow_table WHERE u_id1 = " + id);
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
                return null;
            }
            try {
                while (rs.next()) {
                    userId1 = rs.getInt("u_id1");
                    userId2 = rs.getInt("u_id2");
                    follows.add(new Follow(id, userId1, userId2));
                }
            } catch (SQLException e) {
            }
        }
        return follows;
    }

    public Follow readByUserId(int id) {
        ArrayList<Follow> follows = new ArrayList<>();

        if (myReadStatement != null) {
            try {
                rs = myReadStatement.executeQuery("SELECT * FROM follow_table WHERE u_id1 = " + id);
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
                return null;
            }
            try {
                while (rs.next()) {
                    userId1 = rs.getInt("u_id1");
                    userId2 = rs.getInt("u_id2");
                    return new Follow(id, userId1, userId2);
                }
            } catch (SQLException e) {
            }
        }
        return null;
    }


    public void unfollow(int id) {
        String query = "DELETE FROM follow_table WHERE u_id1 = %d";
        if (myUpdateStatement != null) {
            try {
                int affectedRows = myUpdateStatement.executeUpdate(String.format(query, id));
                System.out.println("Follow-ul a fost sters");
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
        }
    }
}
