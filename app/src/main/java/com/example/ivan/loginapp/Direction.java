package com.example.ivan.loginapp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
}
