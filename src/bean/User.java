package bean;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private long user_id;
    private String created;
    private String status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", user_id=" + user_id +
                ", created='" + created + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public User(String username, long user_id, String created, String status) {
        this.username = username;
        this.user_id = user_id;
        this.created = created;
        this.status = status;
    }

    public User() {
    }
}
