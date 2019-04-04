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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        //Initialization
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);


        buttonLogin.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

        }
    }
    private void Userlogin(){
        //getting email and password from edittext
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //if email address is empty
            Toast.makeText(this,"Please enter your email address", Toast.LENGTH_LONG).show();
            //stop function from executing further
            return;
        }

        if (TextUtils.isEmpty(password)) {

            //if pwd is empty
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
        progressDialog.setMessage("Keep calm authenticating...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //Start Main Application
                            finish();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                        }else {
                            progressDialog.setMessage("User not found or incorrect email, retry");
                            progressDialog.show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogin){
            Userlogin();
        }
        if(v == textViewSignIn){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
