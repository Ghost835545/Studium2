package com.example.ivan.loginapp.entity;

import java.util.Date;
import java.util.Objects;

public class Answer {
    private Integer idAnswer;
    private String answerText;
    private Date dateEdit;
    private String dirImage;
    private String dirAudio;
    private String dirVideo;
    private Boolean correct;
    private User user;
    private Question question;

    public Answer() {

    }

    public Integer getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(Integer idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Date getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(Date dateEdit) {
        this.dateEdit = dateEdit;
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

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(idAnswer, answer.idAnswer) &&
                Objects.equals(answerText, answer.answerText) &&
                Objects.equals(dateEdit, answer.dateEdit) &&
                Objects.equals(dirImage, answer.dirImage) &&
                Objects.equals(dirAudio, answer.dirAudio) &&
                Objects.equals(dirVideo, answer.dirVideo) &&
                Objects.equals(correct, answer.correct) &&
                Objects.equals(user, answer.user
                );
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnswer, answerText, dateEdit, dirImage, dirAudio, dirVideo, correct, user);
    }
}
