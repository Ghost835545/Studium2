package com.example.ivan.loginapp.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ivan.loginapp.Answer;
import com.example.ivan.loginapp.Question;
import com.example.ivan.loginapp.R;
import com.example.ivan.loginapp.Test;
import com.example.ivan.loginapp.rest.Connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    public ProgressBar progressBar;
    public int position;
    public List<Question> mQuestions;
    public Bundle extras;
    public TextView textquestion;
    public TextView countquestions;
    public ArrayList<Answer> answersList;
    public MyArrayAdapter mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_of_test);
        textquestion = findViewById(R.id.test_question);
        countquestions = findViewById(R.id.countQ);
        extras = getIntent().getExtras();
        setTitle(extras.getString("TestName"));
        OutputTestsTask task = new OutputTestsTask();
        task.execute();
        final ImageButton buttonRight = findViewById(R.id.button_right);
        final ImageButton buttonLeft = findViewById(R.id.button_left);
        buttonLeft.setEnabled(false);


        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialAnwers();
                buttonLeft.setEnabled(true);
                position++;
                countquestions.setText((position + 1) + "/" + mQuestions.size());
                Question question = mQuestions.get(position);
                textquestion.setText(question.getQuestionText());
                if ((mQuestions.size() - 1) == position) {
                    buttonRight.setEnabled(false);
                }
            }
        });
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonRight.setEnabled(true);
                position--;
                System.out.println(position);
                countquestions.setText((position + 1) + "/" + mQuestions.size());
                Question question = mQuestions.get(position);
                textquestion.setText(question.getQuestionText());
                if (position == 0) {
                    buttonLeft.setEnabled(false);
                }
            }
        });


    }
    public void initialAnwers(){
        mQuestions.size();
        ListView listView = (ListView) findViewById(R.id.list_answers);
        mArrayAdapter = new MyArrayAdapter(this, R.layout.list_item_test_answers,
        android.R.id.text1, Collections.singletonList(mQuestions.get(position).getAnswers()[position].getAnswerText()));
        listView.setAdapter(mArrayAdapter);
        listView.setOnItemClickListener(myOnItemClickListener);
    }



   private class OutputTestsTask extends AsyncTask<Void, Void, Test[]> {

        @Override
        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
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
                Integer pos = extras.getInt("positionT");
                mQuestions = new ArrayList<>(tests[pos].getQuestions());
                OutputAnswersTask task = new OutputAnswersTask();
                task.execute();
                textquestion.setText(mQuestions.get(position).getQuestionText());
                countquestions.setText((position + 1) + "/" + mQuestions.size());
        }
    }



    private class OutputAnswersTask extends AsyncTask<Void, Void, Question[]> {

        @Override
        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Question[] doInBackground(Void... params) {
            try {
                for (Question question : mQuestions) {
                    question.initAnswers();
                }
            } catch (Exception e) {

            }
            return null;
        }

    }
    AdapterView.OnItemClickListener myOnItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            mArrayAdapter.toggleChecked(position);
        }
    };
    private class MyArrayAdapter extends ArrayAdapter<String> {

        private SparseBooleanArray mCheckedMap = new SparseBooleanArray();

        MyArrayAdapter(Context context, int resource,
                       int textViewResourceId, List<String> objects) {
            super(context, resource, textViewResourceId, objects);

            for (int i = 0; i < objects.size(); i++) {
                mCheckedMap.put(i, false);
            }
        }

        void toggleChecked(int position) {
            if (mCheckedMap.get(position)) {
                mCheckedMap.put(position, false);
            } else {
                mCheckedMap.put(position, true);
            }

            notifyDataSetChanged();
        }

        public List<Integer> getCheckedItemPositions() {
            List<Integer> checkedItemPositions = new ArrayList<>();

            for (int i = 0; i < mCheckedMap.size(); i++) {
                if (mCheckedMap.get(i)) {
                    (checkedItemPositions).add(i);
                }
            }

            return checkedItemPositions;
        }

        List<String> getCheckedItems() {
            List<String> checkedItems = new ArrayList<>();

            for (int i = 0; i < mCheckedMap.size(); i++) {
                if (mCheckedMap.get(i)) {
                    (checkedItems).add(mQuestions.get(i).getAnswers()[i].getAnswerText());
                }
            }

            return checkedItems;
        }

        @NonNull
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View row = convertView;

            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.list_item_test_answers, parent, false);
            }

            CheckedTextView checkedTextView = (CheckedTextView) row
                    .findViewById(R.id.checkedTextView_answers);
            checkedTextView.setText(mQuestions.get(position).getAnswers()[position].getAnswerText());

            Boolean checked = mCheckedMap.get(position);
            if (checked != null) {
                checkedTextView.setChecked(checked);
            }

            return row;
        }

    }


}