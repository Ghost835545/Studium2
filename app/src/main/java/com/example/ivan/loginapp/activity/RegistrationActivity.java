package com.example.ivan.loginapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.example.ivan.loginapp.Direction;
import com.example.ivan.loginapp.Faculty;
import com.example.ivan.loginapp.Group;
import com.example.ivan.loginapp.R;
import com.example.ivan.loginapp.Role;
import com.example.ivan.loginapp.User;
import com.example.ivan.loginapp.editSpinner.NothingSelectedSpinnerAdapter;
import com.example.ivan.loginapp.rest.Connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RegistrationActivity extends AppCompatActivity {
    public Role Role;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final EditText text = (EditText) findViewById(R.id.text_spinner_groups);
        text.setVisibility(View.INVISIBLE);
        initCheckBoxGroup();
        initSpinnerSelectFaculty();
        initSpinnerSelectDirection();
        HttpRequestTaskFaculty taskFaculty = new HttpRequestTaskFaculty(this);
        taskFaculty.execute();
        initReg();
    }



    private void initCheckBoxGroup() {
        final CheckBox checkBox = findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final Spinner spinner = (Spinner) findViewById(R.id.groups);
                    final EditText text = (EditText) findViewById(R.id.text_spinner_groups);
                    spinner.setEnabled(false);
                    spinner.setAdapter(null);
                    text.setVisibility(View.VISIBLE);
                }
                else
                {
                    final Spinner spinner = (Spinner) findViewById(R.id.groups);
                    final EditText text = (EditText) findViewById(R.id.text_spinner_groups);
                    spinner.setEnabled(true);
                    text.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    private void initSpinnerSelectFaculty() {
        final Spinner spfaculty = findViewById(R.id.faculties);
        spfaculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FacultyAdapter adapter = (FacultyAdapter) spfaculty.getAdapter();
                Faculty faculty = adapter.getItem(position);
                HttpRequestTaskDirection taskFaculty = new HttpRequestTaskDirection(faculty.getIdFaculty());
                taskFaculty.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerSelectDirection() {
        final Spinner spdirections = findViewById(R.id.directions);
        spdirections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DirectionAdapter adapter = (DirectionAdapter) spdirections.getAdapter();
                Direction directions = adapter.getItem(position);
                HttpRequestTaskGroups taskGroups = new HttpRequestTaskGroups(directions.getIdDirection());
                taskGroups.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void startActivity() {
        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void initReg() {

        final EditText fioE = findViewById(R.id.fio);
        final EditText loginE = findViewById(R.id.login_r);
        final EditText passwordE = findViewById(R.id.password_r);
        final Spinner spinnerDr = findViewById(R.id.directions);
        final Spinner spinnerGr = findViewById(R.id.groups);
        final EditText phoneE = findViewById(R.id.phone);
        final EditText emailE = findViewById(R.id.email);
        final Button button = findViewById(R.id.buttonReg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputRegistr(fioE.getText().toString(), loginE.getText().toString(), Security.encryptPass(passwordE.getText().toString()), (Direction) spinnerDr.getSelectedItem(),
                        (Group) spinnerGr.getSelectedItem(), phoneE.getText().toString(), emailE.getText().toString());

            }
        });

    }

    private void inputRegistr(String fio, String login, String password, Direction direction, Group group, String phone, String email) {
        User user = new User();
        user.setFio(fio);
        user.setLogin(login);
        user.setPassword(password);
        user.setDirection(direction);
        user.setGroup(group);
        user.setPhone(phone);
        user.setEmail(email);
        user.setDateReg(new Date());
        user.setDateAuth(new Date());
        user.setStatus((byte) 1);
        user.setRole(Role);
        RegistUserTask task = new RegistUserTask(user);
        task.execute();

    }

    private class HttpRequestTaskFaculty extends AsyncTask<Void, Void, Faculty[]> {

        HttpRequestTaskFaculty(RegistrationActivity activity) {
            this.activity = activity;
        }

        RegistrationActivity activity;

        @Override
        protected Faculty[] doInBackground(Void... params) {
            try {

                Faculty[] faculties = new Connection().getFaculty();
                return faculties;


            } catch (Exception e) {

            }
            return null;
        }

        protected void onPostExecute(Faculty[] faculties) {
            if (faculties == null)
                return;

            final Spinner spinner = findViewById(R.id.faculties);
            FacultyAdapter grad = new FacultyAdapter(RegistrationActivity.this, faculties);
            spinner.setAdapter(grad);

        }


    }

    private class HttpRequestTaskDirection extends AsyncTask<Void, Void, Direction[]> {

        private int idfaculty;

        HttpRequestTaskDirection(int id) {
            idfaculty = id;

        }

        @Override
        protected Direction[] doInBackground(Void... params) {
            try {

                Direction[] directions = new Connection().getDirections(idfaculty);
                return directions;


            } catch (Exception e) {

            }
            return null;
        }

        protected void onPostExecute(Direction[] directions) {
            if (directions == null)
                return;
            Spinner spinner = findViewById(R.id.directions);
            DirectionAdapter grad = new DirectionAdapter(RegistrationActivity.this, directions);
            spinner.setAdapter(grad);
        }


    }


    private class HttpRequestTaskGroups extends AsyncTask<Void, Void, Group[]> {

        private int idDirection;

        HttpRequestTaskGroups(int id) {
            idDirection = id;
        }

        @Override
        protected Group[] doInBackground(Void... params) {
            try {

                Group[] groups = new Connection().getGroups(idDirection);
                return groups;


            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Group[] groups) {
            if (groups == null)
                return;
            Spinner spinner = findViewById(R.id.groups);
            GroupAdapter grad = new GroupAdapter(RegistrationActivity.this, groups);
            Role = groups[0].getRole();
            spinner.setAdapter(grad);
        }


    }


    private class RegistUserTask extends AsyncTask<Void, Void, User> {
        User user = null;

        RegistUserTask(User newuser) {
            this.user = newuser;
        }

        @Override
        protected User doInBackground(Void... params) {
            try {
                User user = new Connection().registUser(this.user);
                return user;

            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(User newuser) {

            // for(Group g:groups)
            //Log.i("t1", newuser.getFio());

            Toast.makeText(getApplicationContext(), "Вы зарегистрировались!", Toast.LENGTH_SHORT).show();
            startActivity();
        }
    }

    private class FacultyAdapter extends ArrayAdapter<Faculty> {

        FacultyAdapter(Context ob, Faculty[] faculties) {
            super(ob, R.layout.list_item_facultes, faculties);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Faculty faculty = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_facultes, null);
            }
            ((TextView) convertView.findViewById(R.id.text_faculties)).setText(faculty.getFacultyName());

            return convertView;
        }
    }


    private class DirectionAdapter extends ArrayAdapter<Direction> {

        DirectionAdapter(Context ob, Direction[] directions) {
            super(ob, R.layout.list_item_directions, directions);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Direction direction = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_directions, null);
            }

            ((TextView) convertView.findViewById(R.id.text_directions)).setText(direction.getDirectionName());
            return convertView;
        }
    }

    private class GroupAdapter extends ArrayAdapter<Group> {

        GroupAdapter(Context ob, Group[] group) {
            super(ob, R.layout.list_item_groups, group);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Group group = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_groups, null);
            }
            ((TextView) convertView.findViewById(R.id.text_groups)).setText(group.getGroupName());
            return convertView;
        }


    }
}

