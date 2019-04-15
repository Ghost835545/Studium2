package com.example.ivan.loginapp.entity;

import java.util.Objects;

public class Role {

    private Integer idRole;
    private String roleName;
    private Integer access;

    public Role(){

    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(idRole, role.idRole) &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(access, role.access);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, roleName, access);
    }
}
