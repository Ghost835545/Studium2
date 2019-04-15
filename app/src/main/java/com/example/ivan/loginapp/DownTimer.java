package com.example.ivan.loginapp;


import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class DownTimer {
    private static final long START_TIME_IN_MILLIS = 2000;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private CountDownTimer mCountDownTimer;
    private TextView mTextView;
    private Long test_time;

    public DownTimer(TextView mTextViewCountDown, Long time) {
        mTextView = mTextViewCountDown;
        test_time = time;
        mTimeLeftInMillis+=test_time;
        startTimer();
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis -= 1000;
                //System.out.println(mTimeLeftInMillis);
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDownText() {
        int hours = (int) TimeUnit.MILLISECONDS.toHours(mTimeLeftInMillis - 1000);
        int minutes = (int) (TimeUnit.MILLISECONDS.toMinutes(mTimeLeftInMillis - 1000) % TimeUnit.HOURS.toMinutes(1));
        int seconds = (int) (TimeUnit.MILLISECONDS.toSeconds(mTimeLeftInMillis - 1000) % TimeUnit.MINUTES.toSeconds(1));

        if ((hours != 0) && (minutes != 0) && (seconds != 0)) {
            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
            mTextView.setText(timeLeftFormatted);
        } else if ((hours == 0) && (minutes != 0) && (seconds != 0)) {
            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
            mTextView.setText(timeLeftFormatted);
        } else if ((hours == 0) && (minutes != 0) && (seconds == 0)) {
            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
            mTextView.setText(timeLeftFormatted);
        } else if ((hours == 0) && (minutes == 0) && (seconds != 0)) {
            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
            mTextView.setText(timeLeftFormatted);
        } else if ((hours != 0) && (minutes != 0) && (seconds == 0)) {
            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
            mTextView.setText(timeLeftFormatted);
        } else if ((hours != 0) && (minutes == 0) && (seconds != 0)) {
            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
            mTextView.setText(timeLeftFormatted);
        } else if ((hours != 0) && (minutes == 0) && (seconds == 0)) {
            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
            mTextView.setText(timeLeftFormatted);
        }
    }
}