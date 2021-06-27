package com.example.firehotel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends Activity implements View.OnClickListener{
    private TextView register,forgotPassword;
    private EditText editTextEmail,editTextPassword;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        mAuth=FirebaseAuth.getInstance();

        register=findViewById(R.id.register1);
        register.setOnClickListener(this);

        signIn=findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);

        progressBar=findViewById(R.id.progressBar);

        forgotPassword=findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register1:
                startActivity(new Intent(this,RegisterUser.class));
                break;

            case R.id.signIn:
                userLogin();
                editTextEmail.getText().clear();
                editTextPassword.getText().clear();
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(LoginActivity.this,ForgotPassword.class));
        }
    }

    private void userLogin() {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<6){
            editTextPassword.setError("Min password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()){
                        //redirect to user profile
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this," Check your email to verify your account!",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this," Failed to Login! Please check your credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
