package com.example.ivan.loginapp.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.Toast;

import com.example.ivan.loginapp.DownTimer;
import com.example.ivan.loginapp.Variables;
import com.example.ivan.loginapp.entity.Answer;
import com.example.ivan.loginapp.entity.Question;
import com.example.ivan.loginapp.R;
import com.example.ivan.loginapp.entity.ResultQuestion;
import com.example.ivan.loginapp.entity.ResultTest;
import com.example.ivan.loginapp.entity.Test;
import com.example.ivan.loginapp.entity.User;
import com.example.ivan.loginapp.rest.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ThreadLocalRandom;

public class TestActivity extends AppCompatActivity {
    public ProgressBar progressBar;
    public int index;
    public User user;
    public List<Question> mQuestions;
    public ArrayList<MyArrayAdapter> mArrayAdapterAll;
    public List<Answer> mAnswers;
    public Test selectedTest;
    public ConcurrentHashMap<ResultQuestion, Boolean> resultQuestions;
    public Bundle extras;
    public TextView textquestion;
    public TextView countquestions;
    public MyArrayAdapter mArrayAdapter;
    public ListView listView;
    public Boolean checked;
    public ResultTest resultTest;
    public TextView textTimer;
    public DownTimer countDownTimer;
    public Boolean clockEnd;
    public AlertDialog.Builder ad;
    public ImageButton buttonRight;
    public ImageButton buttonLeft;
    public ImageButton buttonFinished;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_of_test);
        progressBar = (ProgressBar) findViewById(R.id.pr_activity_page_of_test);
        listView = (ListView) findViewById(R.id.list_answers);
        textquestion = findViewById(R.id.test_question);
        countquestions = findViewById(R.id.countQ);
        extras = getIntent().getExtras();
        resultQuestions = new ConcurrentHashMap<>();
        setTitle(Variables.getTestName());
        OutputTestsTask task = new OutputTestsTask();
        task.execute();
        buttonRight = findViewById(R.id.button_right);
        buttonLeft = findViewById(R.id.button_left);
        buttonFinished = findViewById(R.id.button_finished);
        buttonLeft.setEnabled(false);
        buttonRight.setEnabled(false);
        buttonFinished.setEnabled(false);

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLeft.setEnabled(true);
                index++;
                countquestions.setText((index + 1) + "/" + mQuestions.size());
                Question question = mQuestions.get(index);
                initialAnswersRight(index);
                textquestion.setText(question.getQuestionText());
                if ((mQuestions.size() - 1) == index) {
                    buttonRight.setEnabled(false);
                }
            }
        });
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonRight.setEnabled(true);
                index--;
                System.out.println(index);
                countquestions.setText((index + 1) + "/" + mQuestions.size());
                Question question = mQuestions.get(index);
                initialAnswersLeft(index);
                textquestion.setText(question.getQuestionText());
                if (index == 0) {
                    buttonLeft.setEnabled(false);
                }
            }
        });
        buttonFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad = new AlertDialog.Builder(TestActivity.this);
                ad.setTitle("Завершение тестирования.");
                ad.setIcon(R.drawable.ic_error_outline_black_24dp);
                ad.setMessage("Вы действительно хотите завершить тестирование?");
                ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        countDownTimer.stopTimer();
                        FixResultAsync fixResultAsync = new FixResultAsync();
                        fixResultAsync.execute();
                    }
                });
                ad.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ad.show();

            }
        });


    }

    public void onBackPressed() {
        ad = new AlertDialog.Builder(TestActivity.this);
        ad.setTitle("Завершение тестирования.");
        ad.setIcon(R.drawable.ic_error_outline_black_24dp);
        ad.setMessage("Вы действительно хотите завершить тестирование?");
        ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                countDownTimer.stopTimer();
                FixResultAsync fixResultAsync = new FixResultAsync();
                fixResultAsync.execute();
            }
        });
        ad.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ad.show();
    }

    public void runFixResult(Boolean timeEnd) {
        clockEnd = timeEnd;
        FixResultAsync fixResultAsync = new FixResultAsync();
        fixResultAsync.execute();
    }

    public void startactivity() {
        finish();
        Variables.setIdTest(resultTest.getTest().getIdTest());
        Variables.setIdResult(resultTest.getIdResult());
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.putExtra("clockEnd", clockEnd);
        intent.putExtra("flag_results", true);
        startActivity(intent);
    }

    public void shuffleArray(List ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.size() - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Object a = ar.get(index);
            ar.set(index, ar.get(i));
            ar.set(i, a);
        }
    }

    public class FixResultAsync extends AsyncTask<Void, Void, ResultTest> {

        @Override
        protected ResultTest doInBackground(Void... voids) {
            resultTest.setDateEnd(new Date());
            resultTest = new Connection().fixResult(resultTest);
            return resultTest;
        }

        @Override
        protected void onPostExecute(ResultTest resultTest) {
            super.onPostExecute(resultTest);
            Variables.setMARK(resultTest.getMark());
            startactivity();
        }

    }

    public void time(long time) {
        textTimer = (TextView) findViewById(R.id.timer);
        countDownTimer = new DownTimer(textTimer, time, TestActivity.this);
    }

    public void resultTest() {
        MyThread threadUser = new MyThread();
        threadUser.start();
    }

    public class MyThread extends Thread {
        public void run() {
            resultTest = new ResultTest();
            resultTest.setDateBegin(new Date());
            resultTest.setDateEnd(new Date(selectedTest.getTimer().getTime() + resultTest.getDateBegin().getTime()));
            resultTest.setMark(new Float(0.0));
            user = new Connection().getUsers_By_login(Variables.getUserLogin());
            resultTest.setUser(user);
            resultTest.setTest(selectedTest);
            resultTest = new Connection().getResultTest(resultTest);
        }
    }

    public void initialAnswers() {
        mAnswers = new ArrayList<>();
        mAnswers.addAll(mQuestions.get(index).getAnswers());
        mArrayAdapter = new MyArrayAdapter(this, R.layout.list_item_test_answers,
                android.R.id.text1, mAnswers);
        mArrayAdapterAll = new ArrayList<>();
        mArrayAdapterAll.ensureCapacity(mQuestions.size());
        for (int i = 0; i < mQuestions.size(); i++) {
            mArrayAdapterAll.add(i, null);
        }
        mArrayAdapterAll.set(index, mArrayAdapter);
        listView.setAdapter(mArrayAdapterAll.get(index));
        listView.setOnItemClickListener(myOnItemClickListener);
    }

    public void initialAnswersRight(int position) {
        mAnswers.clear();
        mAnswers.addAll(mQuestions.get(position).getAnswers());
        if (mArrayAdapterAll.get(position) != null) {
            mArrayAdapter = new MyArrayAdapter(this, R.layout.list_item_test_answers,
                    android.R.id.text1, mAnswers);
            (mArrayAdapter.mCheckedMap) = (mArrayAdapterAll.get(position).mCheckedMap);
            listView.setAdapter(mArrayAdapter);
            listView.setOnItemClickListener(myOnItemClickListener);
        } else {
            mArrayAdapter = new MyArrayAdapter(this, R.layout.list_item_test_answers,
                    android.R.id.text1, mAnswers);
            mArrayAdapterAll.set(position, mArrayAdapter);
            listView.setAdapter(mArrayAdapterAll.get(position));
            listView.setOnItemClickListener(myOnItemClickListener);
        }


    }

    public void initialAnswersLeft(int position) {
        mAnswers.clear();
        mAnswers.addAll(mQuestions.get(position).getAnswers());
        if (mArrayAdapterAll.get(position) != null) {
            mArrayAdapter = new MyArrayAdapter(this, R.layout.list_item_test_answers,
                    android.R.id.text1, mAnswers);
            (mArrayAdapter.mCheckedMap) = (mArrayAdapterAll.get(position).mCheckedMap);
            listView.setAdapter(mArrayAdapter);
            listView.setOnItemClickListener(myOnItemClickListener);
        } else {
            mArrayAdapter = new MyArrayAdapter(this, R.layout.list_item_test_answers,
                    android.R.id.text1, mAnswers);
            mArrayAdapterAll.set(position, mArrayAdapter);
            listView.setAdapter(mArrayAdapterAll.get(position));
            listView.setOnItemClickListener(myOnItemClickListener);
        }


    }


    private class OutputTestsTask extends AsyncTask<Void, Void, Test> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Test doInBackground(Void... params) {
            try {
                mQuestions = new ArrayList<>(FragmentTests.getSelectedTest().getQuestions());
                shuffleArray(mQuestions);
                selectedTest = FragmentTests.getSelectedTest();
                selectedTest.getTimer().setHours(selectedTest.getTimer().getHours() + 13);
                for (Question question : mQuestions)
                    question.initAnswers();
                return selectedTest;
            } catch (Exception e) {

            }
            return null;
        }

        protected void onPostExecute(Test tests) {
            long time = selectedTest.getTimer().getHours() * 3600 * 1000 + selectedTest.getTimer().getMinutes() * 60 * 1000
                    + selectedTest.getTimer().getSeconds() * 1000;
            time(time);
            initialAnswers();
            resultTest();
            textquestion.setText(mQuestions.get(index).getQuestionText());
            countquestions.setText((index + 1) + "/" + mQuestions.size());
            progressBar.setVisibility(View.GONE);
            buttonRight.setEnabled(true);
            buttonFinished.setEnabled(true);


        }
    }

    AdapterView.OnItemClickListener myOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            mArrayAdapter.toggleChecked(position);

        }
    };

    private class MyArrayAdapter extends ArrayAdapter<Answer> {
        public int i = 0;
        private SparseBooleanArray mCheckedMap = new SparseBooleanArray();
        public boolean checkBox;

        MyArrayAdapter(Context context, int resource,
                       int textViewResourceId, List<Answer> objects) {
            super(context, resource, textViewResourceId, objects);

            for (int i = 0; i < objects.size(); i++) {
                mCheckedMap.put(i, false);
            }
        }

        void toggleChecked(int position) {
            if (mCheckedMap.get(position)) {
                mCheckedMap.put(position, false);
                checkBox = mCheckedMap.valueAt(position);
            } else {
                mCheckedMap.put(position, true);
                checkBox = mCheckedMap.valueAt(position);
            }
            initSelect(position);
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
                if (mCheckedMap.get(i, true)) {
                    (checkedItems).add(mAnswers.get(i).getAnswerText());
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
            checkedTextView.setText(mAnswers.get(position).getAnswerText());

            checked = mCheckedMap.get(position);
            if (checked != null) {
                checkedTextView.setChecked(checked);
            }

            return row;
        }

        private void initSelect(int position) {
            ResultQuestion resultQuestion = new ResultQuestion();
            resultQuestion.setResultTest(resultTest);
            resultQuestion.setUser(user);
            resultQuestion.setAnswer(mAnswers.get(position));
            resultQuestion.setQuestion(mQuestions.get(index));
            if (checkBox) {
                resultQuestions.put(resultQuestion, true);
            } else if (!checkBox) {
                resultQuestions.remove(resultQuestion);
            } /*else if (isSelect) {
                    resultQuestions.add(resultQuestion);
                } else {
                    resultQuestions.remove(resultQuestion);
                }*/
            AnswerQuesTask answerQuesAsync = new AnswerQuesTask(resultQuestion, resultQuestions);
            Thread thread = new Thread(answerQuesAsync);
            thread.start();

        }

        public class AnswerQuesTask implements Runnable {

            private Connection rest;
            private ResultQuestion resultQuestion;
            private ConcurrentHashMap<ResultQuestion, Boolean> resultQuestions;

            public AnswerQuesTask(ResultQuestion resultQuestion, ConcurrentHashMap<ResultQuestion, Boolean> resultQuestions) {
                this.rest = new Connection();
                this.resultQuestions = resultQuestions;
                this.resultQuestion = resultQuestion;
            }

            @Override
            public void run() {
                synchronized (resultQuestions) {
                    List<ResultQuestion> oldResultQuestions = rest.getResultQuestionByQuestionAndResultTest(resultQuestion.getQuestion().getIdQuestion(), resultQuestion.getResultTest().getIdResult());
                    if (oldResultQuestions.size() > 0)
                        for (ResultQuestion action : oldResultQuestions) {
                            rest.removeResultQuestionById(action.getIdResultQuestion());
                        }
                    if (resultQuestions.size() > 0) {
                    }
                    for (Map.Entry<ResultQuestion, Boolean> action : resultQuestions.entrySet()) {
                        rest.addResultQuestion(action.getKey());
                    }
                }
            }
        }
    }

}
