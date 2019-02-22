package com.example.ivan.loginapp;

import android.content.Context;
import android.os.AsyncTask;
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

    public List<Test> getTests() {
        OutputUsersTask task = new OutputUsersTask();
        task.execute();
        return mTests;
    }

    private class OutputUsersTask extends AsyncTask<Void, Void, Test[]> {

        @Override
        protected Test[] doInBackground(Void... params) {
            try {
                Test[] tests = new Connection().getTests();
                return tests;

            } catch (Exception e) {

            }
            return null;
        }

        protected void onPostExecute(Test[] tests) {
            if (mTests.size() > 0){
                mTests = new ArrayList<>();
            }
                for (int i = 0; i < tests.length; i++) {
                    mTests.add(tests[i]);
                }
        }
    }
}
