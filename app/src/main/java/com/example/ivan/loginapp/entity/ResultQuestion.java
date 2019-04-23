package com.example.ivan.loginapp.entity;

import com.example.ivan.loginapp.rest.Connection;

import java.util.Objects;
import java.util.Set;

public class ResultQuestion {
    private Integer idResultQuestion;
    private Question question;
    private Answer answer;
    private ResultTest resultTest;
    private User user;

    public ResultQuestion() {

    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Integer getIdResultQuestion() {
        return idResultQuestion;
    }

    public void setIdResultQuestion(Integer idResultQuestion) {
        this.idResultQuestion = idResultQuestion;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ResultTest getResultTest() {
        return resultTest;
    }

    public void setResultTest(ResultTest resultTest) {
        this.resultTest = resultTest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultQuestion that = (ResultQuestion) o;
        return Objects.equals(answer, that.answer) &&
                Objects.equals(user, that.user) &&
                Objects.equals(question, that.question) &&
                Objects.equals(resultTest, that.resultTest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer.getAnswerText(), user.getLogin(), question.getQuestionText(), resultTest.getIdResult());
    }


}
