package com.epam.bolotin.periodicals.model.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: Viacheslav Bolotin
 * @date: 30.12.2022
 */
public class User extends Entity {
    private static final long serialVersionUID = 1L;
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private transient String password;
    private boolean blocked = false;
    private int userRoleId;
    private Timestamp createTime;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getCreateTime() {
        return createTime.toString().substring(0, createTime.toString().length()-2);
    }

    public void setCreateTime(Timestamp create_time) {
        this.createTime = create_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
