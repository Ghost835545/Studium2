package com.example.ivan.loginapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ivan.loginapp.R;
import com.example.ivan.loginapp.SingletResultQuestions;
import com.example.ivan.loginapp.SingletTests;
import com.example.ivan.loginapp.Variables;
import com.example.ivan.loginapp.entity.Answer;
import com.example.ivan.loginapp.entity.Question;
import com.example.ivan.loginapp.entity.ResultQuestion;
import com.example.ivan.loginapp.entity.Test;
import com.example.ivan.loginapp.rest.Connection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ResultsOfSelectedTestActivity extends AppCompatActivity {
    private TextView titleTest;
    private TextView markOfTest;
    public TextView mistakes;
    public HashMap<Integer, List<ResultQuestion>> mResultQuestions;
    public List<Question> mTestQuestions;
    public ResultQuestionsAdapter mResultQuestionsAdapter;
    public HashMap<Question, Boolean> mColorQuestions;
    public RecyclerView mQuestionsRecyclerView;
    public Integer mistake;
    public ProgressBar progressBar;
    public Integer row;
    private Boolean clockEnd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_of_selected_test);
        mColorQuestions = new HashMap<>();
        row = 0;
        progressBar = (ProgressBar) findViewById(R.id.results_tests);
        progressBar.setVisibility(View.VISIBLE);
        Bundle extras = getIntent().getExtras();
        clockEnd = extras.getBoolean("clockEnd");
        if (clockEnd)
            alertDialog();
        mistakes = findViewById(R.id.errors_of_test);
        mQuestionsRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_result_questions);
        mQuestionsRecyclerView.setLayoutManager(new LinearLayoutManager(ResultsOfSelectedTestActivity.this));
        titleTest = (TextView) findViewById(R.id.title_of_selected_test);
        titleTest.setText(Variables.getTestName());
        markOfTest = (TextView) findViewById(R.id.points_of_test);
        markOfTest.setText(String.format("%.2f", Variables.getMARK()));
        OutputTestsQuestionTask testsQuestionTask = new OutputTestsQuestionTask();
        testsQuestionTask.execute();

    }

    public void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ResultsOfSelectedTestActivity.this);
        builder.setTitle("Тест завершен.")
                .setMessage("Время теста вышло!")
                .setIcon(R.drawable.ic_info_outline_black_24dp)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /*public void onBackPressed()
    {
        /*finish();
        Intent intent = new Intent (this,FragmentTests.class);
        startActivity(intent);
    }*/
    private class OutputResultsQuestionTask extends AsyncTask<Void, Void, List<ResultQuestion>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<ResultQuestion> doInBackground(Void... params) {
            try {
                mResultQuestions = new HashMap<Integer, List<ResultQuestion>>();
                int i = 0;
                for (Question question : mTestQuestions) {
                    List<ResultQuestion> resultQuestions = new Connection().getResultQuestionByQuestionAndResultTest(question.getIdQuestion(), Variables.getIdResult());
                    question.setNumber(++i);
                    mResultQuestions.put(question.getIdQuestion(), resultQuestions);
                }
                return null;
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(List<ResultQuestion> resultQuestions) {
            analizeR();
            initialListView();
            progressBar.setVisibility(View.GONE);
        }
    }

    private class OutputTestsQuestionTask extends AsyncTask<Void, Void, Test> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Test doInBackground(Void... params) {
            try {
                Test questionsTest = new Connection().getTestsById(Variables.getIdTest());
                mTestQuestions = new ArrayList<>(questionsTest.getQuestions());
                for (Question question : mTestQuestions)
                    question.initAnswers();
                return questionsTest;
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Test questionsTest) {
            OutputResultsQuestionTask resultsQuestionTask = new OutputResultsQuestionTask();
            resultsQuestionTask.execute();
            mistake = mTestQuestions.size();

        }
    }

    public void initialListView() {
        SingletResultQuestions singletResultQuestions = SingletResultQuestions.get(ResultsOfSelectedTestActivity.this);
        List<Question> resultQuestionsTests = singletResultQuestions.getTests(mTestQuestions);
        mResultQuestionsAdapter = new ResultsOfSelectedTestActivity.ResultQuestionsAdapter(resultQuestionsTests);
        mQuestionsRecyclerView.setAdapter(mResultQuestionsAdapter);
    }

    public void analizeR() {
        for (Question question : mTestQuestions) {
            List<ResultQuestion> resultQuestions = mResultQuestions.get(question.getIdQuestion());
            List<Answer> answers = question.getAnswers();
            Boolean isRight = false;
            int countRight = 0;
            if ((resultQuestions != null) && (resultQuestions.size() != 0)) {
                for (Answer answer : answers)
                    if (answer.getCorrect())
                        countRight++;
                if ((resultQuestions.size() != countRight)) {
                    isRight = false;
                } else {
                    for (ResultQuestion resultQuestion : resultQuestions)
                        if (resultQuestion.getAnswer().getCorrect()) {
                            isRight = true;
                        } else {
                            isRight = false;
                            break;
                        }
                }
                if (isRight) {
                    mColorQuestions.put(question, true);
                    mistake--;
                } else {
                    mColorQuestions.put(question, false);
                }
            }
        }
    }

    public class TestResultQuestionsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mResultQuestionsTextView;
        private TextView mRow;
        private Question mResultQuestion;

        public TestResultQuestionsHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_result_questions, parent, false));
            mResultQuestionsTextView = (TextView) itemView.findViewById(R.id.test_result_question);
            mRow = (TextView) itemView.findViewById(R.id.row_questions);
            itemView.setOnClickListener(this);
        }

        public void bind(Question question) {
            mResultQuestionsTextView.setBackgroundColor(Color.parseColor("#FFB3B3"));

                if (mColorQuestions.get(question)!=null && mColorQuestions.get(question)){
                    mResultQuestionsTextView.setBackgroundColor(Color.parseColor("#04E41F"));
                }
            mResultQuestion = question;
            mRow.setText(mResultQuestion.getNumber() + ".");
            mResultQuestionsTextView.setText(mResultQuestion.getQuestionText());
            mistakes.setText(mistake.toString());
        }

        @Override
        public void onClick(View v) {

        }


    }


    private class ResultQuestionsAdapter extends RecyclerView.Adapter<ResultsOfSelectedTestActivity.TestResultQuestionsHolder> {

        private List<Question> mResultQuestion;

        public ResultQuestionsAdapter(List<Question> result) {
            mResultQuestion = result;
        }

        @NonNull
        @Override
        public ResultsOfSelectedTestActivity.TestResultQuestionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ResultsOfSelectedTestActivity.this);
            return new ResultsOfSelectedTestActivity.TestResultQuestionsHolder(layoutInflater, parent);

        }

        @Override
        public void onBindViewHolder(@NonNull ResultsOfSelectedTestActivity.TestResultQuestionsHolder holder, int position) {
            Question question = mResultQuestion.get(position);
            holder.bind(question);

        }

        @Override
        public int getItemCount() {
            return mResultQuestion.size();
        }
    }
}


