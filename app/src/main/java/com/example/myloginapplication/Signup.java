package com.example.myloginapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class Signup extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView email = (TextView) findViewById(R.id.email);
        TextView username =(TextView) findViewById(R.id.username);
        TextView login = (TextView) findViewById(R.id.login);
        TextView password =(TextView) findViewById(R.id.password);
        TextView confirmpass =(TextView) findViewById(R.id.confirmpass);
        MaterialButton signupbtn =(MaterialButton) findViewById(R.id.signupbtn);
        FirebaseAuth mAuth = FirebaseAuth.getInstance() ;
        FirebaseUser mUser = mAuth.getCurrentUser();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inpemail = email.getText().toString();
                String inppass = password.getText().toString();
                String inpconfpass = confirmpass.getText().toString();
                String inpusername = username.getText().toString();
                String regex = "^(.+)@(.+)$";
                if (!inpemail.matches(regex))
                {
                    email.setError("Enter valid e mail id");
                    email.requestFocus();
                }
                else if(inppass.isEmpty() || inppass.length()<6)
                {
                    password.setError("Enter valis password");
                    password.requestFocus();
                }
                else if(!inppass.equals(inpconfpass))
                {
                    confirmpass.setError("Passwords do not match");
                    confirmpass.requestFocus();
                }
                else
                {
                    mAuth.createUserWithEmailAndPassword(inpemail,inppass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(Signup.this, Login.class));
                                Toast.makeText(Signup.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Signup.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                /*if (password.getText().toString().equals(confirmpass.getText().toString())) {
                        //correct
                        Toast.makeText(Signup.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Signup.this, Login.class));
                    }
                else
                {
                    //incorrect
                    Toast.makeText(Signup.this,"PASSWORD DOES NOT MATCH",Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Signup.this,Login.class));
            }
        });
    }
}