package com.example.ivan.loginapp;

public class Semester {

    private Integer idSemester;
    private String semestrName;
    private Course course;

    public Semester(){

    }

    public Integer getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(Integer idSemester) {
        this.idSemester = idSemester;
    }

    public String getSemestrName() {
        return semestrName;
    }

    public void setSemestrName(String semestrName) {
        this.semestrName = semestrName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
