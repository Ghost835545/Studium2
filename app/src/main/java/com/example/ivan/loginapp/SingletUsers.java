package com.example.ivan.loginapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
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

    public List<User> getUsers(List<User> users) {
         mUsers = users;
        return mUsers;
    }


}
