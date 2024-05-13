package com.autismdetectionapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.autismdetectionapp.databinding.ActivityOneTestBinding;
import com.autismdetectionapp.model.AssessmentQuiz;
import com.autismdetectionapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ActivityOneTestActivity extends AppCompatActivity {

    ActivityOneTestBinding binding;
    List<AssessmentQuiz> assessmentQuizList = new ArrayList<>();
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOneTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fillDataInList();
        binding.testoneBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExitDialog();
            }
        });
        initViews();

    }

    private void initViews() {
        displayQuestion(currentIndex);

        binding.tvAnswerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex++;
                if (currentIndex >= assessmentQuizList.size()) {
                    Constants.isFirstActivityComplete = true;
                    startActivity(new Intent(getApplicationContext(), TestCategoryActivity.class));
                    finishAffinity();
                } else {
                    displayQuestion(currentIndex);
                }
            }
        });

        binding.tvAnswerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.activityOneCount = Constants.activityOneCount + 1;
                currentIndex++;
                if (currentIndex >= assessmentQuizList.size()) {
                    Constants.isFirstActivityComplete = true;
                    startActivity(new Intent(getApplicationContext(), TestCategoryActivity.class));
                    finishAffinity();
                } else {
                    displayQuestion(currentIndex);
                }
            }
        });

        binding.tvAnswerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.activityOneCount = Constants.activityOneCount + 2;
                currentIndex++;
                if (currentIndex >= assessmentQuizList.size()) {
                    Constants.isFirstActivityComplete = true;
                    startActivity(new Intent(getApplicationContext(), TestCategoryActivity.class));
                    finishAffinity();
                } else {
                    displayQuestion(currentIndex);
                }

            }
        });

    }

    private void displayQuestion(int c_Index) {
        binding.tvQuestionNumber.setText("Question " + (c_Index+1) + " of " + assessmentQuizList.size());
        binding.tvQuestion.setText(assessmentQuizList.get(c_Index).getQuestion());
        binding.tvAnswerOne.setText(assessmentQuizList.get(c_Index).getAnswerA());
        binding.tvAnswerTwo.setText(assessmentQuizList.get(c_Index).getAnswerB());
        binding.tvAnswerThree.setText(assessmentQuizList.get(c_Index).getAnswerC());
    }

    private void fillDataInList() {
        //list size 12
        assessmentQuizList.clear();
        assessmentQuizList.add(new AssessmentQuiz("I like to do things the same way.",
                "Never", "Some of the time", "Most of the time"));
        assessmentQuizList.add(new AssessmentQuiz("I tend to notice small details that others don’t.",
                "Never", "Some of the time", "Most of the time"));
        assessmentQuizList.add(new AssessmentQuiz("I prefer reading books about non-fiction subjects like dinosaurs.",
                "Never", "Some of the time", "Most of the time"));
        assessmentQuizList.add(new AssessmentQuiz("I frequently find it hard to keep a conversation going.",
                "Never", "Some of the time", "Most of the time"));
        assessmentQuizList.add(new AssessmentQuiz("Numbers, dates, or strings of information fascinate me.",
                "Never", "Some of the time", "Most of the time"));
        assessmentQuizList.add(new AssessmentQuiz("New situations make me anxious and nervous.",
                "Never", "Some of the time", "Most of the time"));
        assessmentQuizList.add(new AssessmentQuiz("I can easily recognize patterns in things all the time.",
                "Never", "Some of the time", "Most of the time"));
        assessmentQuizList.add(new AssessmentQuiz("I focus more on the whole picture rather than the small details.",
                "Never", "Some of the time", "Most of the time"));
        assessmentQuizList.add(new AssessmentQuiz("I find it hard to figure out people’s intentions.",
                "Never", "Some of the time", "Most of the time"));
        assessmentQuizList.add(new AssessmentQuiz("I find it easy to talk in groups of people.",
                "Never", "Some of the time", "Most of the time"));
        assessmentQuizList.add(new AssessmentQuiz("When I talk, it isn’t always easy for others to get a word in.",
                "Never", "Some of the time", "Most of the time"));
        assessmentQuizList.add(new AssessmentQuiz("I often notice small sounds when others don’t.",
                "Never", "Some of the time", "Most of the time"));
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Activity");
        builder.setMessage("Are you sure you want to proceed? You'll lose your Data");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Constants.activityOneCount = 0;
                startActivity(new Intent(getApplicationContext(), TestCategoryActivity.class));
                finishAffinity();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }
}