package com.example.ivan.loginapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ivan.loginapp.rest.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
