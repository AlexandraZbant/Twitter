package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {

    int id;
    String message;
    int userId;
    LocalDateTime createdAt;
    ArrayList<Like> likes;
    int count;


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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
         this.likes = likes;
        if(likes.size() != 0){
            setCount(likes.size());
        }else{
            setCount(0);
        }
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                ", likes=" + likes +
                ", count=" + count +
                '}';
    }
}
