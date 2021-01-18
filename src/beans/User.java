package beans;

import java.util.Date;

public class User {
    private Long User_id;
    private String username;
    private Date created;
    private String status ;

    public User() {
    }

    public User(Long user_id, String username, Date created, String status) {
        User_id = user_id;
        this.username = username;
        this.created = created;
        this.status = status;
    }

    public Long getUser_id() {
        return User_id;
    }

    public void setUser_id(Long user_id) {
        User_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
