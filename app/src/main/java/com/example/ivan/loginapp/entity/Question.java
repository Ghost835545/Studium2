package com.example.ivan.loginapp.entity;

import com.example.ivan.loginapp.rest.Connection;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Question {
    private Integer idQuestion;
    private String questionText;
    private Theme theme;
    private Date dateEdit;
    private Integer questionType;
    private String dirImage;
    private String dirAudio;
    private String dirVideo;
    private User user;
    @JsonIgnore
    private List <Answer> answers;
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

    public Date getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(Date dateEdit) {
        this.dateEdit = dateEdit;
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

    public List <Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List <Answer> answers) {
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
         shuffleArray(answers);

    }
    public void shuffleArray(List ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.size() - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Object a = ar.get(index);
            ar.set(index,ar.get(i));
            ar.set(i,a);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(idQuestion, question.idQuestion) &&
                Objects.equals(questionText, question.questionText) &&
                Objects.equals(theme, question.theme) &&
                Objects.equals(dateEdit, question.dateEdit) &&
                Objects.equals(questionType, question.questionType) &&
                Objects.equals(dirImage, question.dirImage) &&
                Objects.equals(dirAudio, question.dirAudio) &&
                Objects.equals(dirVideo, question.dirVideo) &&
                Objects.equals(user, question.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuestion, questionText, theme, dateEdit, questionType, dirImage, dirAudio, dirVideo, user);
    }
}
