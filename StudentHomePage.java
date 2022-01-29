package com.example.schoolmedicalobservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StudentHomePage extends AppCompatActivity {

    private Button fillBut;
    private Button viewReportBut;
    private Student student;
    private FirebaseDatabase mDatabase;
    private ReportDiabeticType1 reportDiabeticType11;
    private ReportDiabeticType2 reportDiabeticType22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        mDatabase = FirebaseDatabase.getInstance();
        fillBut=findViewById(R.id.fill_medical_report);
        viewReportBut=findViewById(R.id.ViewReport);
        student = (Student) getIntent().getSerializableExtra("student");

        fillBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),StudentActivity.class);
                intent.putExtra("student",student);
                startActivity(intent);

            }
        });


        DatabaseReference databaseReference = mDatabase.getReference("Report_Type1");
        //database

        Query query = databaseReference.orderByChild("user_key");//.equalTo(student.getKey());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        ReportDiabeticType1 reportDiabeticType1 = user.getValue(ReportDiabeticType1.class);
                        reportDiabeticType1.setKey(user.getKey());
                        if(reportDiabeticType1.getUser_key().equals(student.getKey())) {
                            reportDiabeticType11 = reportDiabeticType1 ;
                            break;
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database error", Toast.LENGTH_LONG).show();
            }
        });

        //database
        databaseReference = mDatabase.getReference("Report_Type2");

        query = databaseReference.orderByChild("user_key");//.equalTo(student.getKey());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        ReportDiabeticType2 reportDiabeticType2 = user.getValue(ReportDiabeticType2.class);
                        reportDiabeticType2.setKey(user.getKey());

                        if(reportDiabeticType2.getUser_key().equals(student.getKey())) {
                            reportDiabeticType22 = reportDiabeticType2 ;
                            break;
                        }

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database error", Toast.LENGTH_LONG).show();
            }
        });


        viewReportBut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                if(reportDiabeticType11!=null){
                    Intent intent = new Intent(StudentHomePage.this,ViewReport1.class);
                    intent.putExtra("report",reportDiabeticType11);
                    startActivity(intent);
                }else if(reportDiabeticType22 !=null){
                    Intent intent = new Intent(StudentHomePage.this,ViewReport2.class);
                    intent.putExtra("report",reportDiabeticType22);
                    startActivity(intent);
                }else{
                    Toast.makeText(StudentHomePage.this,"Student have no report!",Toast.LENGTH_LONG).show();
                }
           }
       });


    }
}
