package com.example.ivan.loginapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Direction {
    private Integer idDirection;
    private Faculty faculty;
    private String directionName;
    @JsonIgnore
    private Boolean inSubject;

    public Direction() {

    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Integer getIdDirection() {
        return idDirection;
    }

    public void setIdDirection(Integer idDirection) {
        this.idDirection = idDirection;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public Boolean getInSubject() {
        return inSubject;
    }

    public void setInSubject(Boolean inSubject) {
        this.inSubject = inSubject;
    }

    @Override
    public String toString() {
        return directionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return Objects.equals(idDirection, direction.idDirection) &&
                Objects.equals(directionName, direction.directionName) &&
                Objects.equals(inSubject, direction.inSubject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDirection, directionName, inSubject);
    }
}
