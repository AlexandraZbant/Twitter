package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {

    int id;
    String message;
    int userId;
    LocalDateTime createdAt;
    ArrayList<Like> likes;

    public Post(int id, String message, int userId, LocalDateTime createdAt) {
        this.id = id;
        this.message = message;
        this.userId = userId;
        this.createdAt = createdAt;
        this.likes = new ArrayList<>();
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ArrayList<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
         this.likes = likes;
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                ", likes=" + likes +
                '}';
    }
}
