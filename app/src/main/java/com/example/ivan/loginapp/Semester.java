package com.example.ivan.loginapp;

public class Semester {

    private Integer idSemester;
    private String semesterName;
    private Course course;

    public Semester(){

    }

    public Integer getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(Integer idSemester) {
        this.idSemester = idSemester;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
