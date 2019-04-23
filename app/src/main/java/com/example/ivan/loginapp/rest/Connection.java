package com.example.ivan.loginapp.rest;

import android.database.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ObservableShort;
import android.util.Log;

import com.example.ivan.loginapp.entity.Answer;
import com.example.ivan.loginapp.entity.Direction;
import com.example.ivan.loginapp.entity.Faculty;
import com.example.ivan.loginapp.entity.Group;
import com.example.ivan.loginapp.entity.Question;
import com.example.ivan.loginapp.entity.ResultQuestion;
import com.example.ivan.loginapp.entity.ResultTest;
import com.example.ivan.loginapp.entity.Test;
import com.example.ivan.loginapp.entity.User;
import com.example.ivan.loginapp.activity.Security;
import com.example.ivan.loginapp.rest.url.URLAnswerService;
import com.example.ivan.loginapp.rest.url.URLDirectionService;
import com.example.ivan.loginapp.rest.url.URLFacultyService;
import com.example.ivan.loginapp.rest.url.URLGroupService;
import com.example.ivan.loginapp.rest.url.URLQuestionService;
import com.example.ivan.loginapp.rest.url.URLResultQuestionService;
import com.example.ivan.loginapp.rest.url.URLResultTestService;
import com.example.ivan.loginapp.rest.url.URLTestService;
import com.example.ivan.loginapp.rest.url.URLUserService;
import com.example.ivan.loginapp.rest.url.URLWebService;


import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.transform.Result;


public class Connection {
    private HttpHeaders headers;
    private RestTemplate rest;

    public Connection() {
        try {
            headers = createHeaders("administrator", "hardpassword");
            rest = new RestTemplate(getClientRequestFactory());
            rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private ClientHttpRequestFactory getClientRequestFactory() {
        try {
            int timeout = 4000;
            HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            clientHttpRequestFactory.setReadTimeout(timeout);
            clientHttpRequestFactory.setConnectTimeout(timeout);
            return clientHttpRequestFactory;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    private HttpHeaders createHeaders(String username, String password) {
        try {
            HttpAuthentication httpAuthentication =
                    new HttpBasicAuthentication(username, password);
            HttpHeaders headers = new HttpHeaders();
            headers.setAuthorization(httpAuthentication);
            return headers;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public Group[] getGroups(int id) {
        try {
            HttpEntity<Group[]> request = new HttpEntity<>(headers);
            Group[] groups = rest.exchange(URLWebService.URL + URLGroupService.URL_GROUPS_BY_DIRECTION + "?id=" + id, HttpMethod.GET, request, Group[].class).getBody();
            return groups;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public Faculty[] getFaculty() {
        try {
            HttpEntity<Faculty> request = new HttpEntity<>(headers);
            Faculty[] faculties = rest.exchange(URLWebService.URL + URLFacultyService.URL_FACULTIES, HttpMethod.GET, request, Faculty[].class).getBody();
            return faculties;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public Direction[] getDirections(int id) {
        try {
            HttpEntity<Direction> request = new HttpEntity<>(headers);
            Direction[] directions = rest.exchange(URLWebService.URL + URLDirectionService.URL_DIRECTIONS_BY_FACULTY + "?id=" + id, HttpMethod.GET, request, Direction[].class).getBody();
            return directions;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public List<Answer> getAnswersByQuestion(Integer id) {
        Answer[] answers = null;
        try {
            HttpEntity<Answer> request = new HttpEntity<>(headers);
            answers = rest.exchange(URLWebService.URL + URLAnswerService.URL_ANSWER_BY_QUESTION + "?id=" + id, HttpMethod.GET, request, Answer[].class).getBody();
            if (answers.length > 0) {
                List<Answer> answerSet = Arrays.asList(answers);
                return answerSet;
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public User[] getUsers() {
        try {
            HttpEntity<User[]> request = new HttpEntity<>(headers);
            User[] users = rest.exchange(URLWebService.URL + URLUserService.URL_USERS, HttpMethod.GET, request, User[].class).getBody();
            return users;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public User getUsers_By_login(String USER_LOGIN) {
        try {
            HttpEntity<User> request = new HttpEntity<>(headers);
            User users = rest.exchange(URLWebService.URL + URLUserService.URL_USER_BY_LOGIN + "?login=" + USER_LOGIN, HttpMethod.GET, request, User.class).getBody();
            return users;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;

    }

    public User registUser(User user) {
        try {
            HttpEntity<User> request = new HttpEntity<>(user, headers);
            User newuser = rest.exchange(URLWebService.URL + URLUserService.URL_ADD, HttpMethod.POST, request, User.class).getBody();
            return newuser;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public Test[] getTests() {
        try {
            HttpEntity<Test[]> request = new HttpEntity<>(headers);
            Test[] tests = rest.exchange(URLWebService.URL + URLTestService.URL_TESTS, HttpMethod.GET, request, Test[].class).getBody();
            return tests;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }
    public Test getTestsById(int id_test) {
        try {
            HttpEntity<Test> request = new HttpEntity<>(headers);
            Test tests = rest.exchange(URLWebService.URL + URLTestService.URL_TEST+"?id="+id_test, HttpMethod.GET, request, Test.class).getBody();
            return tests;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }
    public ResultTest getResultTest(ResultTest result) {
        try {
            HttpEntity<ResultTest> request = new HttpEntity<>(result, headers);
            ResultTest resultTest = rest.exchange(URLWebService.URL + URLResultTestService.URL_ADD, HttpMethod.POST, request, ResultTest.class).getBody();
            return resultTest;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public Group CreateGroup(Group group) {
        Group createGroup = null;
        try {
            HttpEntity<Group> request = new HttpEntity<>(group, headers);
            createGroup = rest.exchange(URLWebService.URL + URLGroupService.URL_ADD, HttpMethod.POST, request, Group.class).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return createGroup;
    }


    public Question createQuestion(Question question) {
        Question createQuestion = null;
        try {
            HttpEntity<Question> request = new HttpEntity<>(question, headers);
            createQuestion = rest.exchange(URLWebService.URL + URLQuestionService.URL_ADD, HttpMethod.POST, request, Question.class).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return createQuestion;
    }

    public Answer createAnswer(Answer answer) {
        Answer createAnswer = null;
        try {
            HttpEntity<Answer> request = new HttpEntity<>(answer, headers);
            createAnswer = rest.exchange(URLWebService.URL + URLAnswerService.URL_ADD, HttpMethod.POST, request, Answer.class).getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return createAnswer;
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

    public ResultQuestion removeResultQuestion(ResultQuestion resultQuestion) {
        ResultQuestion deleteResultQuestion = null;
        try {
            HttpEntity<ResultQuestion> request = new HttpEntity<>(resultQuestion, headers);
            deleteResultQuestion = rest.exchange(URLWebService.URL + URLResultQuestionService.URL_DELETE, HttpMethod.DELETE, request, ResultQuestion.class).getBody();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return deleteResultQuestion;
    }

    public ResultQuestion removeResultQuestionById(Integer idResultQuestion) {
        ResultQuestion deleteResultQuestion = null;
        try {
            HttpEntity<ResultQuestion> request = new HttpEntity<>(headers);
            deleteResultQuestion = rest.exchange(URLWebService.URL + URLResultQuestionService.URL_DELETE_BY_ID + "?id=" + idResultQuestion, HttpMethod.GET, request, ResultQuestion.class).getBody();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public ResultQuestion addResultQuestion(ResultQuestion resultQuestion) {
        ResultQuestion newResultQuestion = null;
        try {
            HttpEntity<ResultQuestion> request = new HttpEntity<>(resultQuestion, headers);
            newResultQuestion = rest.exchange(URLWebService.URL + URLResultQuestionService.URL_ADD, HttpMethod.POST, request, ResultQuestion.class).getBody();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return newResultQuestion;
    }

    public List<ResultQuestion> getResultQuestionByQuestionAndResultTest(Integer idQuestion, Integer idResult) {
        List<ResultQuestion> resultQuestions = null;
        try {
            HttpEntity<Question> request = new HttpEntity<>(headers);
            ResponseEntity<ResultQuestion[]> responseEntity = rest.exchange(URLWebService.URL + URLResultQuestionService.URL_RESULT_QUESTIONS_BY_QUESTION_AND_RESULT_TEST + "?id_q=" + idQuestion + "&&id_r=" + idResult, HttpMethod.GET, request, ResultQuestion[].class);
            ResultQuestion[] resultArray = responseEntity.getBody();
            if (resultArray != null) {
                resultQuestions = new ArrayList<ResultQuestion>();
                resultQuestions.addAll(Arrays.asList(resultArray));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return resultQuestions;
    }

    public ResultTest fixResult(ResultTest resultTest) {
        ResultTest fixResultTest = null;
        try {
            HttpEntity<ResultTest> request = new HttpEntity<>(resultTest, headers);
            fixResultTest = rest.exchange(URLWebService.URL + URLResultTestService.URL_FIX_RESULT, HttpMethod.PUT, request, ResultTest.class).getBody();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return fixResultTest;
    }

    public ResultQuestion[] getResultQuestions(int idResultTest) {
        try {
            HttpEntity<ResultQuestion[]> request = new HttpEntity<>(headers);
            ResultQuestion[] resultQuestions = rest.exchange(URLWebService.URL + URLResultQuestionService.URL_RESULT_QUESTIONS_BY_RESULT_TEST + "?id=" + idResultTest, HttpMethod.GET, request, ResultQuestion[].class).getBody();
            return resultQuestions;
            /*ResultQuestion[] resultArray = responseEntity.getBody();
            if (resultArray != null) {
                resultQuestions = new ArrayList<ResultQuestion>();
                resultQuestions.addAll(Arrays.asList(resultArray));
            }*/
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }


}
