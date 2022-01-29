package com.example.schoolmedicalobservation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class StudentDetailsActivity extends AppCompatActivity {

    private TextView studentName ;
    private TextView studentAge ;
    private TextView studentLength ;
    private TextView studentWeight ;
    private TextView diabetesType ;
    private TextView numberOfDose ;
    private TextView bloodType ;
    private TextView phone1 ;
    private TextView phone2 ;

    private StudentsInformations student ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);




        student = (StudentsInformations) getIntent().getSerializableExtra("StudentsInformations");

        studentName = findViewById(R.id.student_name);
        studentAge = findViewById(R.id.student_age);
        studentLength = findViewById(R.id.length);
        studentWeight = findViewById(R.id.weight);
        diabetesType = findViewById(R.id.diabetes_type);
        numberOfDose = findViewById(R.id.number_og_dose);
        bloodType = findViewById(R.id.blood_type_text);

        phone1 = findViewById(R.id.phone_1);
        phone2 = findViewById(R.id.phone_2);


        studentName.setText(student.getName());
        studentAge.setText(student.getAge());
        studentLength.setText(student.getLength());
        studentWeight.setText(student.getWeight());
        diabetesType.setText(student.getDiabetic_type());
        numberOfDose.setText(student.getNumber_of_dose());
        bloodType.setText(student.getBlood_type());
        phone2.setText(student.getPhone2());
        phone1.setText(student.getPhone1());

    }

}
