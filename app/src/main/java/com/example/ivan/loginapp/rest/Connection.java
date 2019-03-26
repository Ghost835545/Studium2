package com.example.ivan.loginapp.rest;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ivan.loginapp.Answer;
import com.example.ivan.loginapp.Direction;
import com.example.ivan.loginapp.Faculty;
import com.example.ivan.loginapp.Group;
import com.example.ivan.loginapp.Test;
import com.example.ivan.loginapp.User;
import com.example.ivan.loginapp.activity.Security;
import com.example.ivan.loginapp.rest.url.URLAnswerService;
import com.example.ivan.loginapp.rest.url.URLDirectionService;
import com.example.ivan.loginapp.rest.url.URLFacultyService;
import com.example.ivan.loginapp.rest.url.URLGroupService;
import com.example.ivan.loginapp.rest.url.URLTestService;
import com.example.ivan.loginapp.rest.url.URLUserService;
import com.example.ivan.loginapp.rest.url.URLWebService;


import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;


public class Connection {
    private HttpHeaders headers;
    private RestTemplate rest;

    public Connection() {
        headers = createHeaders("administrator", "hardpassword");
        rest = new RestTemplate(getClientRequestFactory());
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    private ClientHttpRequestFactory getClientRequestFactory() {
        int timeout = 4000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setReadTimeout(timeout);
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }

    private HttpHeaders createHeaders(String username, String password) {

        HttpAuthentication httpAuthentication =
                new HttpBasicAuthentication(username, password);
        HttpHeaders headers = new HttpHeaders();
        headers.setAuthorization(httpAuthentication);
        return headers;
    }

    public Group[] getGroups(int id) {

        HttpEntity<Group[]> request = new HttpEntity<>(headers);
        Group[] groups = rest.exchange(URLWebService.URL + URLGroupService.URL_GROUPS_BY_DIRECTION + "?id=" + id, HttpMethod.GET, request, Group[].class).getBody();
        return groups;
    }

    public Faculty[] getFaculty() {
        HttpEntity<Faculty> request = new HttpEntity<>(headers);
        Faculty[] faculties = rest.exchange(URLWebService.URL + URLFacultyService.URL_FACULTIES, HttpMethod.GET, request, Faculty[].class).getBody();
        return faculties;
    }

    public Direction[] getDirections(int id) {
        HttpEntity<Direction> request = new HttpEntity<>(headers);
        Direction[] directions = rest.exchange(URLWebService.URL + URLDirectionService.URL_DIRECTIONS_BY_FACULTY + "?id=" + id, HttpMethod.GET, request, Direction[].class).getBody();
        return directions;
    }

    public Answer[] getAnswersByQuestion(Integer id) {
        Answer[] answers = null;
        try {
            HttpEntity<Answer> request = new HttpEntity<>(headers);
            answers = rest.exchange(URLWebService.URL + URLAnswerService.URL_ANSWER_BY_QUESTION + "?id=" + id, HttpMethod.GET, request, Answer[].class).getBody();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return answers;
    }

    public User[] getUsers() {

        HttpEntity<User[]> request = new HttpEntity<>(headers);
        User[] users = rest.exchange(URLWebService.URL + URLUserService.URL_USERS, HttpMethod.GET, request, User[].class).getBody();
        return users;
    }

    public User registUser(User user) {
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        User newuser = rest.exchange(URLWebService.URL + URLUserService.URL_ADD, HttpMethod.POST, request, User.class).getBody();
        return newuser;

    }

    public Test[] getTests() {
        HttpEntity<Test[]> request = new HttpEntity<>(headers);
        Test[] tests = rest.exchange(URLWebService.URL + URLTestService.URL_TESTS, HttpMethod.GET, request, Test[].class).getBody();
        return tests;
    }

    public User signIn(String login, String password) {
        User user = null;
        try {
            HttpEntity<User> request = new HttpEntity<>(headers);
            user = rest.exchange(URLWebService.URL + URLUserService.URL_SIGN_IN + "?login=" + login, HttpMethod.GET, request, User.class).getBody();
            if (user != null) {
                String passUser = user.getPassword();
                passUser = Security.decryptPass(passUser);
                if (!passUser.equals(password))
                    user = null;
            }
        } catch (Exception e) {
            Log.e("errorC", e.getMessage());
            e.printStackTrace();
        }
        return user;

    }

}
