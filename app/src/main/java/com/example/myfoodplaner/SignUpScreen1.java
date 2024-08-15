package com.example.myfoodplaner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfoodplaner.login.LoginScreen1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpScreen1 extends AppCompatActivity {

    TextView editTextEmail;
    TextView editTextPassoward;
    Button btnSignUp;
    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_screen1);

        mAuth=FirebaseAuth.getInstance();
        editTextEmail=findViewById(R.id.et_email);

        editTextPassoward=findViewById(R.id.et_password);

        btnSignUp=findViewById(R.id.btn_sign_up);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email= String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTextPassoward.getText());
                if(TextUtils.isEmpty(email)){

                    Toast.makeText(SignUpScreen1.this ,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){

                    Toast.makeText(SignUpScreen1.this ,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(SignUpScreen1.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(SignUpScreen1.this , LoginScreen1.class );
                                    startActivity(in);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(SignUpScreen1.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }

    }
