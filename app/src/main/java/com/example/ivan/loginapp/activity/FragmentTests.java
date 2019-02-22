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
import com.example.ivan.loginapp.Test;
import com.example.ivan.loginapp.User;

import java.util.List;

public class FragmentTests extends Fragment {

    private RecyclerView mTestRecyclerView;
    private FragmentTests.TestAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_tests, container, false);
        getActivity().setTitle("Список Тестов");
        mTestRecyclerView = (RecyclerView) view.findViewById(R.id.user_recycler_view_tests);
        mTestRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        SingletTests singletTest = SingletTests.get(getActivity());
        List <Test> test = singletTest.getTests();
        mAdapter = new FragmentTests.TestAdapter(test);
        mTestRecyclerView.setAdapter(mAdapter);
    }


    private class TestHolder extends RecyclerView.ViewHolder {
        private TextView mSubjectTestTextView;
        private TextView mTitleTextTextView;


        private Test mTest;

        public TestHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_tests, parent, false));

            mSubjectTestTextView = (TextView) itemView.findViewById(R.id.test_subject);
            mTitleTextTextView = (TextView) itemView.findViewById(R.id.test);

        }

        public void bind(Test test) {
            mTest = test;
            mSubjectTestTextView.setText(mTest.getSubject().getSubjectName());
            mTitleTextTextView.setText (mTest.getTestName());


        }

    }

    private class TestAdapter extends RecyclerView.Adapter<FragmentTests.TestHolder> {

        private List<Test> mTest;

        public TestAdapter(List<Test> test) {
            mTest = test;
        }

        @NonNull
        @Override
        public FragmentTests.TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new FragmentTests.TestHolder(layoutInflater, parent);

        }

        @Override
        public void onBindViewHolder(@NonNull FragmentTests.TestHolder holder, int position) {
            Test test = mTest.get(position);
            holder.bind(test);
        }

        @Override
        public int getItemCount() {
            return mTest.size();
        }
    }
}
