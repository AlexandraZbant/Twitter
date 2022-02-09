package repository;

import model.Like;
import service.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class LikeRepository {

    Statement myUpdateStatement = DatabaseConnector.getUpdateStatement();
    Statement myReadStatement = DatabaseConnector.getReadStatement();

    public static final String INVALID_QUERY = "Query invalid";

    ResultSet rs = null;
    int postId = 0;
    int userId = 0;
    int id = 0;


    public void createLike(int postId, int userId) {
        if (myUpdateStatement != null) {
            try {
                String query = "INSERT INTO like_table (p_id, u_id) VALUES ('%d', '%d')";
                myUpdateStatement.executeUpdate(String.format(query, postId, userId));
                System.out.println("Like-ul a fost creat");
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
        }
    }

    public ArrayList<Like> readLikes(int id) {
        ArrayList<Like> likes = new ArrayList<>();

        if (myReadStatement != null) {
            try {
                rs = myReadStatement.executeQuery("SELECT * FROM like_table WHERE p_id = " + id);
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
                return null;
            }
            try {
                while (rs.next()) {
                    postId = rs.getInt("p_id");
                    userId = rs.getInt("u_id");
                    likes.add(new Like(id, postId, userId));
                }
            } catch (SQLException e) {

            }
        }
        return likes;
    }

    public Like readLike(int userId, int postId) {

        if (myReadStatement != null) {
            try {
                rs = myReadStatement.executeQuery("SELECT * FROM like_table WHERE u_id = " + userId + " AND p_id = " + postId);
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
            try {
                rs.next();
                id = rs.getInt("id");
                userId = rs.getInt("u_id");
                postId = rs.getInt("p_id");
                return new Like(id, userId, postId);
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    public void deleteLikes(int id) {
        String query = "DELETE FROM like_table WHERE id = '%d'";

        if (myUpdateStatement != null) {
            try {
                int affectedRow = myUpdateStatement.executeUpdate(String.format(query, id));
                System.out.println("Like-ul " + (affectedRow != 0 ? "" : "nu") + "a fost sters");
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
        }
    }
}
