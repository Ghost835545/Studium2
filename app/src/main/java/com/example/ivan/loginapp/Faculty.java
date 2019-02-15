package com.example.ivan.loginapp;

public class Faculty {
    private Integer idFaculty;
    private String facultyName;

    public Faculty(){

    }

    public Integer getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(Integer idFaculty) {
        this.idFaculty = idFaculty;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public String toString() {
        return facultyName;
    }
}
