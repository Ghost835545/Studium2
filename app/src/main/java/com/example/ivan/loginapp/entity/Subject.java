package com.example.ivan.loginapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class Subject {

    private Integer idSubject;
    private String subjectName;
    private Set<Direction> directions;
    @JsonIgnore
    private ArrayList<Test> tests;

    public Subject(){

    }

    public Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Set<Direction> getDirections() {
        return directions;
    }

    public void setDirections(Set<Direction> directions) {
        this.directions = directions;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(idSubject, subject.idSubject) &&
                Objects.equals(subjectName, subject.subjectName) &&
                Objects.equals(directions, subject.directions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubject, subjectName);
    }
}
