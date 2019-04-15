package com.example.ivan.loginapp.entity;

import java.util.Objects;

public class Group {
    private Integer idGroup;
    private String groupName;
    private Role role;
    private Direction direction;


    public Group() {
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    @Override
    public String toString() {
        return groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(idGroup, group.idGroup) &&
                Objects.equals(groupName, group.groupName) &&
                Objects.equals(role, group.role) &&
                Objects.equals(direction, group.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGroup, groupName, role, direction);
    }
}

