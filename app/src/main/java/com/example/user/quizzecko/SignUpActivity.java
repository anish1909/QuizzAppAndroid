package com.example.user.quizzecko;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout nameTextInputTextLayout, EmailTextInputTextLayout,PasswordTextInputTextLayout;
           EditText nameEditTextView,EmailEditTextView, PasswordEditTextView;
           AppCompatButton registerappCompatButton;
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameTextInputTextLayout=(TextInputLayout)findViewById(R.id.nameTextInputTextLayout);
        EmailTextInputTextLayout=(TextInputLayout)findViewById(R.id.EmailTextInputTextLayout);
        PasswordTextInputTextLayout=(TextInputLayout)findViewById(R.id.PasswordTextInputTextLayout);
        nameEditTextView = (EditText)findViewById(R.id.nameEditTextView);
        EmailEditTextView=(EditText)findViewById(R.id.EmailEditTextView);
        PasswordEditTextView=(EditText)findViewById(R.id.PasswordEditTextView);
        registerappCompatButton=(AppCompatButton)findViewById(R.id.registerAppcompatButton);
        mFirebaseauth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseauth.getCurrentUser();
        registerappCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=EmailEditTextView.getText().toString().trim();
                String password=PasswordEditTextView.getText().toString().trim();
                mFirebaseauth.createUserWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }
                }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this, "sign in failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
