package com.example.collo.kinemax;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView TextViewSignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//hiding title bar

        //Initialization
        firebaseAuth=FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

        }

        progressDialog=new ProgressDialog(this);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
       editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        TextViewSignUp=(TextView) findViewById(R.id.textViewSignUp);


        buttonRegister.setOnClickListener(this);
        TextViewSignUp.setOnClickListener(this);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
             //if email address is empty
            Toast.makeText(this,"Please enter email address", Toast.LENGTH_LONG).show();
            //stop function from executing further
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //if phone is empty
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            //stop function from executing further
            return;
        }

        if (TextUtils.isEmpty(password) || password.length() < 6 ){

            //if pwd is less than 6 characters
            editTextPassword.setError("You must have a minimum of six characters as your password");
            //stop function from executing further
            return;
        }
        //if validations are ok we will display progress bar
        progressDialog.setMessage("Keep calm registeration in progress");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            //user registered successfully

                                finish();
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));


                            Toast.makeText(MainActivity.this, "Registration successful",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Enter user email correctly",Toast.LENGTH_LONG).show();
                    }}
                });
    }

    @Override
    public void onClick(View v) {

        if(v == buttonRegister){
            registerUser();
        }

        if(v == TextViewSignUp){

            //open login activity
            startActivity(new Intent(this, LoginActivity.class ));
        }

    }
}
