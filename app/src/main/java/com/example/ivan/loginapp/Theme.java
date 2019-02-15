package com.example.ivan.loginapp;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Theme {
    private Integer idTheme;
    private String themeText;
    @JsonIgnore
    private Question questions;

    public Theme() {
    }

    public Integer getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(Integer idTheme) {
        this.idTheme = idTheme;
    }

    public String getThemeText() {
        return themeText;
    }

    public void setThemeText(String themeText) {
        this.themeText = themeText;
    }

    public Question getQuestions() {
        return questions;
    }

    public void setQuestions(Question questions) {
        this.questions = questions;
    }
}
