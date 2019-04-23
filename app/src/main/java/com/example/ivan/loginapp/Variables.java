package com.example.ivan.loginapp;

public class Variables {
    private static String USER_LOGIN;
    private static String FIO;
    private static String TEST_NAME;
    private static Float MARK;
    private static Integer ID_TEST;
    private static Integer ID_RESULT;

    public static Integer getIdResult() {
        return ID_RESULT;
    }

    public static void setIdResult(Integer idResult) {
        ID_RESULT = idResult;
    }

    public static Float getMARK() {
        return MARK;
    }

    public static void setMARK(Float MARK) {
        Variables.MARK = MARK;
    }

    public static Integer getIdTest() {
        return ID_TEST;
    }

    public static void setIdTest(Integer idTest) {
        ID_TEST = idTest;
    }

    public static String getFIO() {
        return FIO;
    }

    public static void setFIO(String FIO) {
        Variables.FIO = FIO;
    }

    public static String getTestName() {
        return TEST_NAME;
    }

    public static void setTestName(String testName) {
        TEST_NAME = testName;
    }

    public static String getUserLogin() {
        return USER_LOGIN;
    }

    public static void setUserLogin(String userLogin) {
        USER_LOGIN = userLogin;
    }
}
