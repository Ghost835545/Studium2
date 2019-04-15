package com.example.ivan.loginapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ivan.loginapp.R;
import com.example.ivan.loginapp.SingletUsers;
import com.example.ivan.loginapp.entity.User;
import com.example.ivan.loginapp.rest.Connection;

import java.util.ArrayList;
import java.util.List;

public class FragmentUsers extends Fragment {

    private RecyclerView mUserRecyclerView;
    private UserAdapter mAdapter;
    public ProgressBar progressBar;
    public ArrayList<User> mUsers;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        getActivity().setTitle("Список пользователей");
        progressBar = (ProgressBar)  view.findViewById(R.id.fragment_progress_users);
        mUserRecyclerView = (RecyclerView) view.findViewById(R.id.user_recycler_view);
        mUserRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        OutputTestsTask task = new OutputTestsTask();
        task.execute();
        return view;
    }


    private class OutputTestsTask extends AsyncTask<Void, Void, User[]> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected User[] doInBackground(Void... params) {
            try {
                User[] users = new Connection().getUsers( );
                return users;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(User[] users) {

                if (mUsers == null) {
                    mUsers = new ArrayList<>();
                }


                for (int i = 0; i < users.length; i++) {
                    mUsers.add(users[i]);
                }
            SingletUsers singletUsers = SingletUsers.get(getActivity());
            List <User> user = singletUsers.getUsers(mUsers);
            mAdapter = new FragmentUsers.UserAdapter(user);
            mUserRecyclerView.setAdapter(mAdapter);
            progressBar.setVisibility(View.GONE);

        }
    }

    private class UserHolder extends RecyclerView.ViewHolder{
        private TextView mTitleTextView;
        private TextView mEmailTextView;
        private TextView mGroupTextView;

        private User mUser;

        public UserHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_user, parent, false));

            mTitleTextView = (TextView) itemView.findViewById(R.id.user_title);
            mGroupTextView = (TextView) itemView.findViewById(R.id.user_group);
            mEmailTextView = (TextView) itemView.findViewById(R.id.user_email);

        }

        public void bind(User user) {
            mUser = user;
            mTitleTextView.setText(mUser.getFio());
            mGroupTextView.setText("Группа: " + mUser.getGroup());
            mEmailTextView.setText("Email: " + mUser.getEmail());

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
