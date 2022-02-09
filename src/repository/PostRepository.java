package repository;

import model.Post;
import service.DatabaseConnector;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class PostRepository {

    Statement myUpdateStatement = DatabaseConnector.getUpdateStatement();
    Statement myReadStatement = DatabaseConnector.getReadStatement();
    Scanner sc = new Scanner(System.in);

    public static final String INVALID_QUERY = "Query invalid";

    ResultSet rs = null;
    int userId = 0;
    int an = 0;
    int luna = 0;
    int zi = 0;
    int ora = 0;
    int minut = 0;

    public void createPost(String message, int userId, int year, int month, int day, int hour, int minute) {
        String query = "INSERT INTO posts (message, u_id, p_year, p_month, p_day, p_hour, p_minute) VALUES" +
                "('%s', %d, %d, %d, %d, %d, %d)";

        if (myUpdateStatement != null) {
            try {
                int affectedColumns = myUpdateStatement.executeUpdate(String.format(query, message, userId, year, month, day, hour, minute));
                System.out.println(affectedColumns == 0 ? "Mesajul nu a putut fi salvat." : "Mesajul a fost salvat cu succes.");
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
        }
    }

    public ArrayList<Post> readUserPost(int id) {
        ArrayList<Post> post = new ArrayList<>();

        if (myReadStatement != null) {
            try {
                rs = myReadStatement.executeQuery("SELECT * FROM posts WHERE u_id = " + id);
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
                return null;
            }
            try {
                while (rs.next()) {
                    int postId = rs.getInt("id");
                    String message = rs.getString("message");
                    userId = rs.getInt("u_id");
                    an = rs.getInt("p_year");
                    luna = rs.getInt("p_month");
                    zi = rs.getInt("p_day");
                    ora = rs.getInt("p_hour");
                    minut = rs.getInt("p_minute");
                    post.add(new Post(postId, message, userId, LocalDateTime.of(an, luna, zi, ora, minut)));
                }
            } catch (SQLException e) {
            }
        }
        return post;
    }

    public Post readPostById(int postId) {

        if (myReadStatement != null) {
            try {
                rs = myReadStatement.executeQuery("SELECT * from posts WHERE id = " + postId);
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
            try {
                rs.next();
                String message = rs.getString("message");
                userId = rs.getInt("u_id");
                an = rs.getInt("p_year");
                luna = rs.getInt("p_month");
                zi = rs.getInt("p_day");
                ora = rs.getInt("p_hour");
                minut = rs.getInt("p_minute");
                return new Post(postId, message, userId, LocalDateTime.of(an, luna, zi, ora, minut));
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    public void updatePost(int id) {
        System.out.println("Introduceti noul mesaj");
        String message = sc.nextLine();

        if (myUpdateStatement != null) {
            try {
                String query = "UPDATE posts SET message = '%s' WHERE id = %d";
                myUpdateStatement.executeUpdate(String.format(query, message, id));
                System.out.println("Postarea a fost modificata cu succes.");
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
        }
    }

    public void deleteAllUserPost(int id) {
        if (myUpdateStatement != null) {
            try {
                int affectedRows = myUpdateStatement.executeUpdate("DELETE FROM posts WHERE u_id = " + id);
                System.out.println((affectedRows == 1 ? "Postarea" : "Postarile ") + " user-ului cu id-ul " + id + " " +
                        (affectedRows == 1 ? " a fost stearsa." : "au fost sterse."));
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
        }
    }

    public void deletePostById(int id) {
        if (myUpdateStatement != null) {
            try {
                myUpdateStatement.executeUpdate("DELETE FROM posts WHERE id = " + id);
                System.out.println("Postarea cu id-ul " + id + " a fost stearsa.");
            } catch (SQLException e) {
                System.out.println(INVALID_QUERY);
            }
        }
    }
}

