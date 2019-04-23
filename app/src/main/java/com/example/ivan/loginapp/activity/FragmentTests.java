package com.example.ivan.loginapp.activity;

import android.content.Intent;

import android.os.AsyncTask;
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
import com.example.ivan.loginapp.Variables;
import com.example.ivan.loginapp.entity.Answer;
import com.example.ivan.loginapp.entity.Direction;
import com.example.ivan.loginapp.entity.Faculty;
import com.example.ivan.loginapp.entity.Group;
import com.example.ivan.loginapp.entity.Question;
import com.example.ivan.loginapp.entity.Role;
import com.example.ivan.loginapp.entity.Test;
import com.example.ivan.loginapp.entity.Theme;
import com.example.ivan.loginapp.entity.User;
import com.example.ivan.loginapp.rest.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FragmentTests extends Fragment {


    private RecyclerView mTestRecyclerView;
    private TestAdapter mAdapter;
    public ProgressBar progressBar;
    public ArrayList<Test> mTests;
    private static Test selectedTest;
    private Test [] tests;

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
               tests = new Connection().getTests();
            return tests;
        }
            catch(Exception exc)
            {
                exc.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Test[] tests) {

            if (mTests == null) {
                mTests = new ArrayList<>();
            }
            mTests.addAll(Arrays.asList(tests));
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
            Variables.setTestName(this.mTest.getTestName());
            Integer positionT = getAdapterPosition();
            if (tests != null){
                setSelectedTest(tests[positionT]);
            }
            Intent intent = new Intent(getActivity(), TestActivity.class);
            startActivity(intent);
        }
    }

    public static Test getSelectedTest() {
        return selectedTest;
    }

    public static void setSelectedTest(Test selectedTest) {
        FragmentTests.selectedTest = selectedTest;
    }

    public class CreateUsers extends Thread{
        public void run(){
            String fio = "User";
            String login = "User_login";
            String password = Security.encryptPass("user_password");
            Date dateReg = new Date();
            Date dateAuth = new Date();
            String phone = "89141230901";
            String email = "usermail";
            int status = 3;
            Role role = new Role();
            role.setIdRole(3);
            role.setRoleName("Студент");
            role.setAccess(3);
            Direction direction = new Direction();
            direction.setDirectionName("Управление в технических системах");
            direction.setIdDirection(1);
            Faculty faculty = new Faculty();
            faculty.setFacultyName("Факультет информационных технологий");
            faculty.setIdFaculty(1);
            direction.setFaculty(faculty);
            Group group = new Group();
            group.setGroupName("19-УСб");
            group.setDirection(direction);
            group.setRole(role);
            Group group1 = new Connection().CreateGroup(group);
            for (int i =0; i< 10000; i++)
            {
               User user = new User();
               user.setDateAuth(dateAuth);
               user.setDateReg(dateReg);
               user.setDirection(direction);
               user.setEmail(email+i+"@mail.ru");
               user.setFio(fio+"_"+i);
               user.setLogin(login+"_"+i);
               user.setGroup(group1);
               user.setPassword(password);
               user.setPhone(phone+"_"+i);
               user.setRole(role);
               user.setStatus((byte)status);
               User user1 = new Connection().registUser(user);
            }
        }
    }

    public class CreateQuestions extends Thread{
        public void run(){
            String QuestionText = "Сколько будет 2+";
            Date dateEdit = new Date();
            int QuestionType = 2;
            Theme theme = new Theme();
            theme.setIdTheme(26);
            theme.setThemeText("Арифметика");
            User user = new Connection().getUsers_By_login(Variables.getUserLogin());
            for (int i =0; i< 100000; i++)
            {
                Question question = new Question();
                question.setQuestionText(QuestionText+i);
                question.setDateEdit(dateEdit);
                question.setTheme(theme);
                question.setUser(user);
                question.setQuestionType(QuestionType);
                Question createQuestion = new Connection().createQuestion(question);
                for (int j = 0; j<4; j++) {
                    Answer answer = new Answer();
                    answer.setAnswerText(i+2+"");
                    answer.setCorrect((boolean)true);
                    answer.setDateEdit(new Date());
                    answer.setQuestion(createQuestion);
                    answer.setUser(user);
                    Answer createAnswer = new Connection().createAnswer(answer);
                }
            }
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
