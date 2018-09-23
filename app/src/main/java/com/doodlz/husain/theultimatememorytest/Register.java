package com.doodlz.husain.theultimatememorytest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {


    EditText emailRegistrationET,passwordregistrationET,userNameET;
    private DatabaseReference userCreate;
    private ProgressDialog progressDialog;

    Button registerButton;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailRegistrationET=(EditText)findViewById(R.id.emailRegistrationET);
        passwordregistrationET=(EditText)findViewById(R.id.passwordRegistrationET);
        userNameET=(EditText)findViewById(R.id.usernameET);

        registerButton=(Button)findViewById(R.id.registerButton);
        progressDialog = new ProgressDialog(this);
        firebaseAuth= FirebaseAuth.getInstance();

       userCreate=FirebaseDatabase.getInstance().getReference().child("UserPoints");




    }

    public void registerButtonClicked(View view) {

        registerUser();

    }

    private void registerUser() {
        String email=emailRegistrationET.getText().toString().trim();
        String password=passwordregistrationET.getText().toString().trim();
        if(TextUtils.isEmpty(email)) {
            return;}

        if(TextUtils.isEmpty(password)) {

            return;}

        progressDialog.setMessage("Registering User");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                    Log.d("Registrationss","Working");

                    String userName=userNameET.getText().toString();
                    String userID=firebaseAuth.getCurrentUser().getUid();
                    String emailId=emailRegistrationET.getText().toString();


                    userCreate.child(userID).child("userName").setValue(userName);
                    userCreate.child(userID).child("emailId").setValue(emailId);

                    userCreate.child(userID).child("points").setValue("0");



                    progressDialog.dismiss();

                    Intent intent = new Intent(Register.this,Login.class);
                    startActivity(intent);
                    finish();


                }else{ Toast.makeText(Register.this,"Could not Register",Toast.LENGTH_SHORT).show();}
            }
        });


    }


    public void gotoLogin(View view) {
        startActivity(new Intent(Register.this,Login.class));
    }
}

