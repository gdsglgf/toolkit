package com.howtodoinjava.demo.model;

public class User {
    public User() { }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return String.format("User [id:%d, username:%s]", id, username);
    }

    private int id;
    private String username;
    private String password;
}
