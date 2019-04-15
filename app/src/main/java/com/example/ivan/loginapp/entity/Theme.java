package com.example.ivan.loginapp.entity;

import com.example.ivan.loginapp.entity.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.Set;

public class Theme {
    private Integer idTheme;
    private String themeText;
    @JsonIgnore
    private Set<Question> questions;

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
        Theme theme = (Theme) o;
        return Objects.equals(idTheme, theme.idTheme) &&
                Objects.equals(themeText, theme.themeText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTheme, themeText);
    }
}
