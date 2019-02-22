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

public class SingletUsers {
    private static SingletUsers sSingletUsers;
    private List<User> mUsers;

    public static SingletUsers get(Context context) {
        if (sSingletUsers == null) {
            sSingletUsers = new SingletUsers(context);
        }
        return sSingletUsers;

    }

    private SingletUsers(Context context) {
        mUsers = new ArrayList<>();

    }

    public List<User> getUsers(ProgressBar progressBar)
    {
         OutputUsersTask task = new OutputUsersTask();
         task.execute();
         try {
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return mUsers;
    }

    private class OutputUsersTask extends AsyncTask<Void, Void, User[]> {

        @Override
        protected User[] doInBackground(Void... params) {
            try {
                User[] user = new Connection().getUsers();
                return user;

            } catch (Exception e) {

            }
            return null;
        }

        protected void onPostExecute(User[] users) {

            if (mUsers.size()>0) {
                mUsers = new ArrayList<>();
            }

            for (int i = 0; i < users.length; i++) {
                mUsers.add(users[i]);
            }

        }
    }
}
