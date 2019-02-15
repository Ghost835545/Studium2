package com.example.ivan.loginapp;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Subject {

    private Integer idSubject;
    private String nameSubject;
    private Direction directions;
    @JsonIgnore
    private Test tests;

    public Subject(){

    }

    public Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Direction getDirections() {
        return directions;
    }

    public void setDirections(Direction directions) {
        this.directions = directions;
    }

    public Test getTests() {
        return tests;
    }

    public void setTests(Test tests) {
        this.tests = tests;
    }
}
