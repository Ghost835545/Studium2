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
    /*private static SingletTests sSingletTests;

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
        try {
            //TimeUnit.MILLISECONDS.sleep(500);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return mTests;
    }

    //public User getUser(UUID id){
    //for (User user : mUsers){
    //if (user.getIdUser().equals(id)) {
    //return user;
    //}
    //}
    ;
    //}

    private class OutputUsersTask extends AsyncTask<Void, Void, Test[]> {

        @Override
        protected Test[] doInBackground(Void... params) {
            try {
                Test[] tests = new Connection().();
                return tests;

            } catch (Exception e) {

            }
            return null;
        }

        protected void onPostExecute(Test[] tests) {

            for (int i = 0; i < tests.length; i++) {
                mTests.add(tests[i]);
            }
        }
    }*/
}
