package com.example.ivan.loginapp.entity;

import com.example.ivan.loginapp.entity.Direction;
import com.example.ivan.loginapp.entity.Group;
import com.example.ivan.loginapp.entity.Role;

import java.util.Date;
import java.util.Objects;

public class User {
    private Integer idUser;
    private String fio;
    private String login;
    private String password;
    private Date dateReg;
    private Date dateAuth;
    private String phone;
    private String email;
    private Byte status;
    private Role role;
    private Direction direction;
    private Group group;

    public User() {

    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    public Date getDateAuth() {
        return dateAuth;
    }

    public void setDateAuth(Date dateAuth) {
        this.dateAuth = dateAuth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Привет, " + getFio();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(idUser, user.idUser) &&
                Objects.equals(fio, user.fio) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(dateReg, user.dateReg) &&
                Objects.equals(dateAuth, user.dateAuth) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(email, user.email) &&
                Objects.equals(status, user.status) &&
                Objects.equals(role, user.role) &&
                Objects.equals(group, user.group) &&
                Objects.equals(direction, user.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, fio, login, password, dateReg, dateAuth, phone, email, status, role, group, direction);
    }
}
