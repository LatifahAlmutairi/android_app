package com.example.schoolmedicalobservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText userNameText, inputPassword;
    private ProgressBar progressBar;
    private Button btnLogin;
    private DatabaseReference databaseReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Advisors");



        userNameText = (EditText) findViewById(R.id.user_name);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login_button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = LoginActivity.this.userNameText.getText().toString();
                final String password = inputPassword.getText().toString();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(getApplicationContext(), "Enter civil registry!", Toast.LENGTH_SHORT).show();
                    return;}
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;}

                progressBar.setVisibility(View.VISIBLE);
                Query query = databaseReference.orderByChild("user_name").equalTo(Long.parseLong(userName));
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot user : dataSnapshot.getChildren()) {
                                Advisors usersBean = user.getValue(Advisors.class);
                                assert usersBean != null;
                                if (usersBean.getPassword().equals(password.trim())) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtra("Advisors",usersBean);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            DatabaseReference studentsRef = database.getReference("Student");
                            Query query = studentsRef.orderByChild("user_name").equalTo(Long.parseLong(userName));
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {


                                        for (DataSnapshot user : dataSnapshot.getChildren()) {

                                            Student usersBean = user.getValue(Student.class);
                                            usersBean.setKey(user.getKey());
                                            assert usersBean != null;
                                            if (usersBean.getPassword().equals(password.trim())) {
                                                Intent intent = new Intent(getApplicationContext(),StudentHomePage.class);
                                                intent.putExtra("student",usersBean);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Toast.makeText(getApplicationContext(), "Database error", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Database error", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}