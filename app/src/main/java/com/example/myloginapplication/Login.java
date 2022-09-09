 package com.example.myloginapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

 public class Login extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);
        TextView signup =(TextView) findViewById(R.id.signup);
        MaterialButton login = (MaterialButton) findViewById(R.id.loginbtn);
        FirebaseAuth mAuth = FirebaseAuth.getInstance() ;
        FirebaseUser mUser = mAuth.getCurrentUser();


        //signup page pe link

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Signup.class));
            }
        });

        //admin checking id= Thakur pass= Sans@8586

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String inpemail = username.getText().toString();
                String inppass = password.getText().toString();
                String regex = "^(.+)@(.+)$";
                if (!inpemail.matches(regex))
                {
                    username.setError("Enter valid e mail id");
                    username.requestFocus();
                }
                else if(inppass.isEmpty() || inppass.length()<6)
                {
                    password.setError("Enter valis password");
                    password.requestFocus();
                }
                else
                {
                    mAuth.signInWithEmailAndPassword(inpemail,inppass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(Login.this, Welcome.class));
                                Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Login.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                /*if(username.getText().toString().equals("Thakur") && password.getText().toString().equals("Thakur"))
                {
                    //correct
                    Toast.makeText(Login.this,"LOGIN SUCCESSFUL !!!!!!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,Welcome.class));
                }
                else
                {
                    //incorrect
                    Toast.makeText(Login.this,"LOGIN FAILED",Toast.LENGTH_SHORT).show();
                }*/
            }
        });

    }
}