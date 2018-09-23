package com.doodlz.husain.theultimatememorytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private EditText mEmailField;
    private EditText mPasswordField;

    private Button mLoginButton;


    private FirebaseAuth mAuth;
    private DatabaseReference userDB;

    private String userId;
    private Users user;

    private FirebaseAuth.AuthStateListener mAuthListener;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        mEmailField=(EditText)findViewById(R.id.emailET);
        mPasswordField=(EditText)findViewById(R.id.passwordET);

        mLoginButton=(Button)findViewById(R.id.loginButton);
        mAuth = FirebaseAuth.getInstance();

        userDB=FirebaseDatabase.getInstance().getReference().child("UserPoints");



        Log.d("version1"," "+userId);



        mAuthListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    userId = mAuth.getCurrentUser().getUid();

                    userDB.child(userId).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Users.setEmailID(dataSnapshot.child("emailId").getValue().toString());
                            Users.setUserName(dataSnapshot.child("userName").getValue().toString());

                            Toast.makeText(Login.this, "Good to go"+Users.getEmailID()+" "+Users.getUserName(), Toast.LENGTH_SHORT).show();



                           Log.d("version1"," "+Users.getUserName());
                            Log.d("version1"," "+Users.getEmailID());




                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                    startActivity(new Intent(Login.this,LevelSelect.class));
                    finish();
                }



            }
        };



        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);


    }

    private void startSignIn() {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();


        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){

            Toast.makeText(Login.this, "Fields are empty", Toast.LENGTH_LONG);

        }else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        userId = mAuth.getCurrentUser().getUid();

                        userDB.child(userId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Users.setUserName(dataSnapshot.child("userName").getValue().toString());
                                Users.setEmailID(dataSnapshot.child("emailId").getValue().toString());
                                Toast.makeText(Login.this, "Good to go"+Users.getEmailID()+" "+Users.getUserName(), Toast.LENGTH_SHORT).show();


                                Log.d("Loginss"," "+Users.getUserName());
                                Log.d("Loginss"," "+Users.getEmailID());



                                finish();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        Toast.makeText(Login.this, "Good to go", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(Login.this,LevelSelect.class));}
                    else{

                        Toast.makeText(Login.this, "Please check your email and password!!", Toast.LENGTH_SHORT).show();


                    }

                }
            });


        }


    }
    public void gotoRegister(View view) {
        Intent intent = new Intent(Login.this,Register.class);
        startActivity(intent);
        finish();
    }
}
