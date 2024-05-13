package com.autismdetectionapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.autismdetectionapp.databinding.ActivitySecondTestBinding;
import com.autismdetectionapp.model.GoodBadData;
import com.autismdetectionapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class SecondTestActivity extends AppCompatActivity {

    ActivitySecondTestBinding binding;
    List<GoodBadData> goodBadDataList = new ArrayList<>();
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fillDataInList();
        binding.testtwoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExitDialog();
            }
        });
        initViews();
    }

    private void initViews() {
        displayQuestion(currentIndex);
        binding.btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((binding.rdFirstBad.isChecked() || binding.rdFirstGood.isChecked())
                        && (binding.rdSecondGood.isChecked() || binding.rdSecondBad.isChecked())) {
                    answerCounting(currentIndex);
                    currentIndex++;
                    if (currentIndex >= goodBadDataList.size()) {
                        Constants.isSecondActivityComplete = true;
                        startActivity(new Intent(getApplicationContext(), TestCategoryActivity.class));
                        finishAffinity();
                    } else {
                        displayQuestion(currentIndex);
                    }
                } else {
                    Toast.makeText(SecondTestActivity.this, "Choose Option to Continue", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void answerCounting(int cIndex) {
        if (cIndex == 0) {
            if (binding.rdFirstBad.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
            if (binding.rdSecondGood.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
        } else if (cIndex == 1) {
            if (binding.rdFirstGood.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
            if (binding.rdSecondBad.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
        } else if (cIndex == 2) {
            if (binding.rdFirstBad.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
            if (binding.rdSecondGood.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
        } else if (cIndex == 3) {
            if (binding.rdFirstGood.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
            if (binding.rdSecondBad.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
        } else if (cIndex == 4) {
            if (binding.rdFirstBad.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
            if (binding.rdSecondGood.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
        } else if (cIndex == 5) {
            if (binding.rdFirstGood.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
            if (binding.rdSecondBad.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
        } else if (cIndex == 6) {
            if (binding.rdFirstBad.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
            if (binding.rdSecondGood.isChecked()) {
                Constants.activityTwoCount = Constants.activityTwoCount + 1;
            }
        }
    }

    private void displayQuestion(int c_Index) {
        binding.rdFirstGood.setChecked(false);
        binding.rdFirstBad.setChecked(false);
        binding.rdSecondGood.setChecked(false);
        binding.rdSecondBad.setChecked(false);
        binding.tvQuestionNumber.setText("Question " + (c_Index + 1) + " of " + goodBadDataList.size());
        binding.iV1.setImageResource(goodBadDataList.get(c_Index).getImageOne());
        binding.iV2.setImageResource(goodBadDataList.get(c_Index).getImageTwo());
    }


    private void fillDataInList() {
        goodBadDataList.clear();
        goodBadDataList.add(new GoodBadData(R.drawable.cutting_tress, R.drawable.watering_tress));
        goodBadDataList.add(new GoodBadData(R.drawable.helping_others, R.drawable.push_others));
        goodBadDataList.add(new GoodBadData(R.drawable.throw_toys, R.drawable.play_toys));
        goodBadDataList.add(new GoodBadData(R.drawable.use_dustbin, R.drawable.not_use_dustbin));
        goodBadDataList.add(new GoodBadData(R.drawable.run_in_classs, R.drawable.sit_in_class));
        goodBadDataList.add(new GoodBadData(R.drawable.save_food, R.drawable.waste_food));
        goodBadDataList.add(new GoodBadData(R.drawable.yelling, R.drawable.speak_nicely));
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Activity");
        builder.setMessage("Are you sure you want to proceed? You'll lose your Data");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Constants.activityTwoCount = 0;
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