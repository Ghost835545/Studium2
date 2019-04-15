package com.example.ivan.loginapp;

import android.content.Context;

import com.example.ivan.loginapp.entity.Test;

import java.util.ArrayList;
import java.util.List;

public class SingletTests {
    private static SingletTests sSingletTests;

    private List<Test> mTests;

    public static SingletTests get(Context context) {
        if (sSingletTests == null) {
            sSingletTests = new SingletTests(context);
        }
        return sSingletTests;

    }

    private SingletTests(Context context) {
        mTests = new ArrayList<>();

    }

    public List<Test> getTests(List<Test> tests) {
        mTests=tests;
        return mTests;
    }

}
