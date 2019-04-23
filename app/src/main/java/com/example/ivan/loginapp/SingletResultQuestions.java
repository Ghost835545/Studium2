package com.example.ivan.loginapp;

import android.content.Context;

import com.example.ivan.loginapp.entity.Question;
import com.example.ivan.loginapp.entity.ResultQuestion;
import com.example.ivan.loginapp.entity.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SingletResultQuestions {
    private static SingletResultQuestions sSingletResultQuestions;

    private List<Question> mResultQuestions;

    public static SingletResultQuestions get(Context context) {
        if (sSingletResultQuestions == null) {
            sSingletResultQuestions = new SingletResultQuestions(context);
        }
        return sSingletResultQuestions;

    }

    private SingletResultQuestions(Context context) {
        mResultQuestions = new ArrayList<>();

    }

    public List<Question> getTests(List<Question> resultQuestion) {
        mResultQuestions=resultQuestion;
        return mResultQuestions;
    }

}
