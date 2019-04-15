package com.example.ivan.loginapp.entity;

import java.util.Date;
import java.util.Objects;

public class ResultTest {
    private Integer idResult;
    private Date dateBegin;
    private Date dateEnd;
    private Float mark;
    private User user;
    private Test test;

    public ResultTest(){

    }

    public Integer getIdResult() {
        return idResult;
    }

    public void setIdResult(Integer idResult) {
        this.idResult = idResult;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultTest that = (ResultTest) o;
        return Objects.equals(idResult, that.idResult) &&
                Objects.equals(user, that.user) &&
                Objects.equals(test, that.test) &&
                Objects.equals(dateBegin, that.dateBegin) &&
                Objects.equals(dateEnd, that.dateEnd) &&
                Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idResult, user, test, dateBegin, dateEnd, mark);
    }
}
