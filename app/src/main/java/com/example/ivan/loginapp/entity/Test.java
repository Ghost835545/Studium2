package com.example.ivan.loginapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class Test {
    private Integer idTest;
    private String testName;
    private Date dateEdit;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timer;
    private User user;
    private Subject subject;
    private Set<Question> questions;

    public Test(){

    }

    public Integer getIdTest() {
        return idTest;
    }

    public void setIdTest(Integer idTest) {
        this.idTest = idTest;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Date getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(Date dateEdit) {
        this.dateEdit = dateEdit;
    }

    public Date getTimer() {
        return timer;
    }

    public void setTimer(Date timer) {
        this.timer = timer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(idTest, test.idTest) &&
                Objects.equals(testName, test.testName) &&
                Objects.equals(dateEdit, test.dateEdit) &&
                Objects.equals(timer, test.timer) &&
                Objects.equals(user, test.user) &&
                Objects.equals(subject, test.subject) &&
                Objects.equals(questions, test.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTest, testName, dateEdit, timer, user, subject);
    }
}
