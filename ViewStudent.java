package com.example.schoolmedicalobservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class ViewStudent extends AppCompatActivity {


    private Button viewButton ;
    private Button report1But;
    private Button report2But;
    private Button alarmClock;

    private StudentsInformations student ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
/*        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        student = (StudentsInformations)getIntent().getSerializableExtra("student");
        viewButton = findViewById(R.id.view_student);
//        ImageView button = findViewById(R.id.action_bar_back);

       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , StudentDetailsActivity.class);
                intent.putExtra("StudentsInformations",student);
                startActivity(intent);
            }
        });
        report1But=findViewById(R.id.report1);
        report1But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), reportType1.class);
                intent.putExtra("student",student);
                startActivity(intent);
            }
        });

        report2But=findViewById(R.id.report2);
        report2But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),reportType2.class);
                intent.putExtra("student",student);
                startActivity(intent);
            }
        });

        alarmClock=findViewById(R.id.buttonAlarm);
        alarmClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AlarmClock.class);
                startActivity(intent);
            }
        });

    }

}

