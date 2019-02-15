package com.example.ivan.loginapp.activity;

import android.graphics.Color;

import android.view.View.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;

import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ivan.loginapp.R;
import com.example.ivan.loginapp.SingletTests;
import com.example.ivan.loginapp.SingletUsers;
import com.example.ivan.loginapp.User;

import java.util.List;

public class FragmentTests extends Fragment {

    /*private RecyclerView mTestRecyclerView;
    private UserAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_tests, container, false);
        getActivity().setTitle("Тесты");
        mTestRecyclerView = (RecyclerView) view.findViewById(R.id.user_recycler_view_tests);
        mTestRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }


    private void updateUI() {
        SingletTests singletTests = SingletTests.get(getActivity());
        List<Test> tests = singletTests.getTests();
        mAdapter = new UserAdapter(tests);
        mTestRecyclerView.setAdapter(mAdapter);
    }

    private class TestHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{

        private TextView mSubject;
        private TextView mTest;
        private Test mTest;

        public TestHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_tests, parent, false));

            mSubject = (TextView) itemView.findViewById(R.id.test_subject);
            mTest= (TextView) itemView.findViewById(R.id.test);
            mTest.setOnClickListener(this);
        }
        public void bind(Test test) {
            mTest = test;
            mSubject.setText(mTest.getFio());
            mEmailTextView.setText("Email: " + mUser.getEmail());
            mGroupTextView.setText("Группа: " + mUser.getGroup().toString());
        }

     @Override
        public void onClick(View view){

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
    }*/
}
