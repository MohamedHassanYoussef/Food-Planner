package com.example.myfoodplaner.login;

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

import com.example.myfoodplaner.R;
import com.example.myfoodplaner.SignUpScreen1;
import com.example.myfoodplaner.home.view.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen1 extends AppCompatActivity {

    TextView editTextEmail;
    TextView editTextPassoward;
    Button btnLogin;
    FirebaseAuth mAuth;
    TextView singup;




    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent in = new Intent(LoginScreen1.this , HomeActivity.class );
            startActivity(in);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen1);
        mAuth=FirebaseAuth.getInstance();
         editTextEmail=findViewById(R.id.emailInput);

         editTextPassoward=findViewById(R.id.passwordInput);

         btnLogin=findViewById(R.id.loginButton);
         singup=findViewById(R.id.signUpText);




        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginScreen1.this , SignUpScreen1.class );
                startActivity(in);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email= String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTextPassoward.getText());
                       if(TextUtils.isEmpty(email)){

                           Toast.makeText(LoginScreen1.this ,"Enter Email",Toast.LENGTH_SHORT).show();
                           return;
                                  }
                if(TextUtils.isEmpty(password)){

                    Toast.makeText(LoginScreen1.this ,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(LoginScreen1.this, "Login Sucsseful.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(LoginScreen1.this , HomeActivity.class );
                                    startActivity(in);
                                    finish();
                                } else {


                                    Toast.makeText(LoginScreen1.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}