package com.autismdetectionapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.autismdetectionapp.databinding.ActivitySignupBinding;
import com.autismdetectionapp.model.Parent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setTitle("please wait...");
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        binding.btnSignupSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    createAccount();
                }
            }
        });

    }

    private boolean validation() {
        if (binding.edtRegName.getText().toString().isEmpty()) {
            binding.edtRegName.setError("fill this field");
            return false;
        } else if (binding.edtRegEmail.getText().toString().isEmpty()) {
            binding.edtRegEmail.setError("fill this field");
            return false;
        } else if (binding.edtRegCnic.getText().toString().isEmpty()) {
            binding.edtRegCnic.setError("fill this field");
            return false;
        } else if (binding.edtRegCnic.getText().toString().length() != 13) {
            binding.edtRegCnic.setError("Required 13 Digits");
            return false;
        } else if (binding.edtRegContact.getText().toString().isEmpty()) {
            binding.edtRegContact.setError("fill this field");
            return false;
        } else if (binding.edtRegContact.getText().toString().length() != 11) {
            binding.edtRegContact.setError("Required 11 Digits");
            return false;
        } else if (binding.edtRegPassword.getText().toString().isEmpty()) {
            binding.edtRegPassword.setError("fill this field");
            return false;
        } else if (binding.edtRegContact.getText().toString().length() < 6) {
            binding.edtRegContact.setError("password too short");
            return false;
        }
        return true;
    }

    private void createAccount() {
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(binding.edtRegEmail.getText().toString(),
                        binding.edtRegPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            saveUserData(user.getUid());
                        } else {
                            Toast.makeText(SignupActivity.this, "Sign up failed. Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void saveUserData(String userId) {
        Parent parent = new Parent(binding.edtRegName.getText().toString(),
                binding.edtRegEmail.getText().toString(),
                binding.edtRegPassword.getText().toString(),
                binding.edtRegContact.getText().toString(),
                binding.edtRegCnic.getText().toString());
        mDatabase.child("parent").child(userId).setValue(parent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SignupActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finishAffinity();
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finishAffinity();
    }
}