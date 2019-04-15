package com.example.ivan.loginapp;

import android.content.Context;

import com.example.ivan.loginapp.entity.User;

import java.util.ArrayList;
import java.util.List;

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
