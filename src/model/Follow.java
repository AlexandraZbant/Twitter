package model;

public class Follow {
    int id;
    int userId1;
    int userId2;

    public Follow(int id, int userId1, int userId2) {
        this.id = id;
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getUserId2() {
        return userId2;
    }

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", userId1=" + userId1 +
                ", userId2=" + userId2 +
                '}';
    }
}
