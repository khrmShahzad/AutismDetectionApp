package com.autismdetectionapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.autismdetectionapp.databinding.ActivityBreakTheBubbleBinding;
import com.autismdetectionapp.util.Constants;

public class BreakTheBubbleActivity extends AppCompatActivity {

    ActivityBreakTheBubbleBinding binding;
    int score = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBreakTheBubbleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                String timeFormatted = String.format("%02d:%02d", secondsRemaining / 60, secondsRemaining % 60);
                binding.tvBreakBubbleScore.setText("Time remaining: " + timeFormatted);
            }

            public void onFinish() {
                binding.tvBreakBubbleScore.setText("Time finished");
                if (score == 15) {
                    Constants.isBreakTheBubbleActivityComplete = true;
                    Constants.breakTheBubbleActivityCount = score;
                    showResultDialog("Congratulation! You completed this task");
                } else {
                    Constants.isBreakTheBubbleActivityComplete = false;
                    Constants.breakTheBubbleActivityCount = 0;
                    showResultDialog("Bad Luck! Your Total Score is: " + score);
                }
            }
        }.start();

        binding.bubbleOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleOne.setVisibility(View.INVISIBLE);
            }
        });

        binding.bubbleTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleTwo.setVisibility(View.INVISIBLE);
            }
        });

        binding.bubbleThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleThree.setVisibility(View.INVISIBLE);
            }
        });
        binding.bubbleFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleFour.setVisibility(View.INVISIBLE);
            }
        });
        binding.bubbleFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleFive.setVisibility(View.INVISIBLE);
            }
        });
        binding.bubbleSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleSix.setVisibility(View.INVISIBLE);
            }
        });
        binding.bubbleSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleSeven.setVisibility(View.INVISIBLE);
            }
        });

        binding.bubbleEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleEight.setVisibility(View.INVISIBLE);
            }
        });

        binding.bubbleNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleNine.setVisibility(View.INVISIBLE);
            }
        });

        binding.bubbleTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleTen.setVisibility(View.INVISIBLE);
            }
        });

        binding.bubbleEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleEleven.setVisibility(View.INVISIBLE);
            }
        });
        binding.bubbleTwoleve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleTwoleve.setVisibility(View.INVISIBLE);
            }
        });
        binding.bubbleThirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleThirteen.setVisibility(View.INVISIBLE);
            }
        });
        binding.bubbleFourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleFourteen.setVisibility(View.INVISIBLE);
            }
        });
        binding.bubbleFiveteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                binding.bubbleFiveteen.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Activity");
        builder.setMessage("Are you sure you want to proceed? You'll lose your Data");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Constants.breakTheBubbleActivityCount = 0;
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

    private void showResultDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your Result");
        builder.setMessage(message);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Constants.breakTheBubbleActivityCount = 0;
                startActivity(new Intent(getApplicationContext(), TestCategoryActivity.class));
                finishAffinity();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Constants.breakTheBubbleActivityCount = 0;
                startActivity(new Intent(getApplicationContext(), TestCategoryActivity.class));
                finishAffinity();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}