package com.example.ivan.loginapp.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ivan.loginapp.Group;
import com.example.ivan.loginapp.R;
import com.example.ivan.loginapp.SingletUsers;
import com.example.ivan.loginapp.User;
import com.example.ivan.loginapp.rest.Connection;

import java.util.List;

public class FragmentUsers extends Fragment {

    private RecyclerView mUserRecyclerView;
    private UserAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
       View view = inflater.inflate(R.layout.fragment_users, container, false);
        getActivity().setTitle("Список пользователей");
        mUserRecyclerView = (RecyclerView) view.findViewById(R.id.user_recycler_view);
        mUserRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }



    private void updateUI() {
        SingletUsers singletUser = SingletUsers.get(getActivity());
        List<User> users = singletUser.getUsers();
        mAdapter = new UserAdapter(users);
        mUserRecyclerView.setAdapter(mAdapter);
    }

    private class UserHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private TextView mEmailTextView;
        private TextView mGroupTextView;

        private User mUser;

        public UserHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_user, parent, false));

            mTitleTextView = (TextView) itemView.findViewById(R.id.user_title);
            mEmailTextView = (TextView) itemView.findViewById(R.id.user_email);
            mGroupTextView = (TextView) itemView.findViewById(R.id.user_group);
        }

        public void bind(User user) {
            mUser = user;
            mTitleTextView.setText(mUser.getFio());
            mEmailTextView.setText("Email: " + mUser.getEmail());
            mGroupTextView.setText("Группа: " + mUser.getGroup().toString());
        }

    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder> {

        private List<User> mUser;

        public UserAdapter(List<User> user) {
            mUser = user;
        }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new UserHolder(layoutInflater, parent);

        }

        @Override
        public void onBindViewHolder(@NonNull UserHolder holder, int position) {
            User user = mUser.get(position);
            holder.bind(user);
        }

        @Override
        public int getItemCount() {
            return mUser.size();
        }
    }


}
