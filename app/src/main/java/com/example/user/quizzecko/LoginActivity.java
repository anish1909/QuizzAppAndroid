package com.example.user.quizzecko;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    AppCompatButton notamembersigninhere;
    AppCompatButton login_button;
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseauth;
   TextInputLayout mEmailTextINputLayout,mPasswordTextInputLayout;
   EditText mEmailEditText,mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        notamembersigninhere=(AppCompatButton)findViewById(R.id.notamembersignup);
        login_button=(AppCompatButton)findViewById(R.id.loginbutton);
        mFirebaseauth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseauth.getCurrentUser();
        mEmailTextINputLayout=(TextInputLayout)findViewById(R.id.emailTextInputLayout);
        mPasswordTextInputLayout=(TextInputLayout)findViewById(R.id.passwordTextInputLayout);
        mEmailEditText=(EditText)findViewById(R.id.emailEditTextView);
        mPasswordEditText=(EditText)findViewById(R.id.passwordEditTextView);

        notamembersigninhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmailEditText.getText().toString().trim();
                String password=mPasswordEditText.getText().toString().trim();
                mFirebaseauth.signInWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }
                }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                           startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "sign in failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                //startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });

    }

}
