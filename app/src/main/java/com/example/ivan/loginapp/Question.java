package com.example.ivan.loginapp;

import com.example.ivan.loginapp.rest.Connection;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;

public class Question {
    private Integer idQuestion;
    private String questionText;
    private Theme theme;
    private Date dateReg;
    private Integer questionType;
    private String dirImage;
    private String dirAudio;
    private String dirVideo;
    private User user;
    @JsonIgnore
    private Answer[] answers;
    @JsonIgnore
    private Boolean inTest;

    public Question(){

    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getDirImage() {
        return dirImage;
    }

    public void setDirImage(String dirImage) {
        this.dirImage = dirImage;
    }

    public String getDirAudio() {
        return dirAudio;
    }

    public void setDirAudio(String dirAudio) {
        this.dirAudio = dirAudio;
    }

    public String getDirVideo() {
        return dirVideo;
    }

    public void setDirVideo(String dirVideo) {
        this.dirVideo = dirVideo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public Boolean getInTest() {
        return inTest;
    }

    public void setInTest(Boolean inTest) {
        this.inTest = inTest;
    }

    public void initAnswers() {
        Connection rest = new Connection();
        answers = rest.getAnswersByQuestion(getIdQuestion());
    }
}
