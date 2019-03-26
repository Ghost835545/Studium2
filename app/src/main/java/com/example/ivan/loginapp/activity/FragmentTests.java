package com.example.ivan.loginapp.activity;

import android.content.Intent;
import android.graphics.Color;

import android.os.AsyncTask;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;

import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ivan.loginapp.R;
import com.example.ivan.loginapp.SingletTests;
import com.example.ivan.loginapp.SingletUsers;
import com.example.ivan.loginapp.Test;
import com.example.ivan.loginapp.User;
import com.example.ivan.loginapp.rest.Connection;

import java.util.ArrayList;
import java.util.List;

public class FragmentTests extends Fragment {

    private RecyclerView mTestRecyclerView;
    private TestAdapter mAdapter;
    public ProgressBar progressBar;
    public ArrayList<Test> mTests;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_tests, container, false);
        getActivity().setTitle("Список Тестов");
        progressBar = (ProgressBar) view.findViewById(R.id.fragment_progress_tests);
        mTestRecyclerView = (RecyclerView) view.findViewById(R.id.user_recycler_view_tests);
        mTestRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        OutputTestsTask task = new OutputTestsTask();
        task.execute();
        return view;
    }

    private class OutputTestsTask extends AsyncTask<Void, Void, Test[]> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

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

            if (mTests == null) {
                mTests = new ArrayList<>();
            }

            for (int i = 0; i < tests.length; i++) {
                mTests.add(tests[i]);
            }
            SingletTests singletTest = SingletTests.get(getActivity());
            List<Test> test = singletTest.getTests(mTests);
            mAdapter = new FragmentTests.TestAdapter(test);
            mTestRecyclerView.setAdapter(mAdapter);
            progressBar.setVisibility(View.GONE);

        }
    }

    private class TestHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mSubjectTestTextView;
        private TextView mTitleTestTextView;


        private Test mTest;

        public TestHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_tests, parent, false));
            mSubjectTestTextView = (TextView) itemView.findViewById(R.id.test_subject);
            mTitleTestTextView = (TextView) itemView.findViewById(R.id.test);
            itemView.setOnClickListener(this);
        }

        public void bind(Test test) {
            mTest = test;
            mSubjectTestTextView.setText(mTest.getSubject().getSubjectName());
            mTitleTestTextView.setText(mTest.getTestName());

        }

        @Override
        public void onClick(View view) {
            String TestName = this.mTest.getTestName();
            Integer positionT = getAdapterPosition();
            Intent intent = new Intent(getActivity(), TestActivity.class);
            intent.putExtra("TestName", TestName);
            intent.putExtra("positionT",positionT);
            startActivity(intent);

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
