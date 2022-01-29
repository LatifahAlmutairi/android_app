package com.example.schoolmedicalobservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class StudentActivity extends AppCompatActivity {

    private EditText studentName ;
    private EditText studentAge ;
    private EditText studentLength ;
    private EditText studentWeight ;
    private EditText diabetesType ;
    private EditText numberOfDose ;
    private EditText bloodType ;

    private EditText phone1 ;
    private EditText phone2 ;

    private Student student ;


    private StudentsInformations studentsInformations ;



    private Button doneButton ;
    private DatabaseReference databaseReference;
    private FirebaseDatabase mDatabase ;
    private  boolean newUser = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        mDatabase = FirebaseDatabase.getInstance();
        student = (Student) getIntent().getSerializableExtra("student");



        studentName = findViewById(R.id.student_name);
        studentAge = findViewById(R.id.student_age);
        studentLength = findViewById(R.id.length);
        studentWeight = findViewById(R.id.weight);
        diabetesType = findViewById(R.id.diabetes_type);
        numberOfDose = findViewById(R.id.number_og_dose);
        bloodType = findViewById(R.id.blood_type_text);

        phone1 = findViewById(R.id.phone_1);
        phone2 = findViewById(R.id.phone_2);

        databaseReference = mDatabase.getReference("studentsInformations");

        Query query = databaseReference.orderByChild("user_key");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        studentsInformations = user.getValue(StudentsInformations.class);
                        newUser = false;
                        if(student.getKey().equals(studentsInformations.getUser_key())) {
                            viewData();
                            break;
                        }
                    }

                }else{
                    studentsInformations = new StudentsInformations();
                    newUser = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database error", Toast.LENGTH_LONG).show();
            }
        });


        doneButton = findViewById(R.id.done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Data have been saved ", Toast.LENGTH_LONG).show();

                updateStudent();
            }
        });

    }

    private void viewData() {
        studentName.setText(studentsInformations.getName());
        studentAge.setText(studentsInformations.getAge());
        studentLength.setText(studentsInformations.getLength());
        studentWeight.setText(studentsInformations.getWeight());
        diabetesType.setText(studentsInformations.getDiabetic_type());
        numberOfDose.setText(studentsInformations.getNumber_of_dose());
        bloodType.setText(studentsInformations.getBlood_type());

        phone2.setText(studentsInformations.getPhone2());
        phone1.setText(studentsInformations.getPhone1());

    }

    private void updateStudent() {

        String name = studentName.getText().toString();
        String age = studentAge.getText().toString();
        String length = studentLength.getText().toString();
        String weight = studentWeight.getText().toString();
        String diabetestype = diabetesType.getText().toString();
        String numberofDoase = numberOfDose.getText().toString();
        String blood = bloodType.getText().toString();

        String phone11 = phone1.getText().toString();
        String phone22 = phone2.getText().toString();

        studentsInformations.setName(name);
        studentsInformations.setAge(age);
        studentsInformations.setLength(length);
        studentsInformations.setWeight(weight);
        studentsInformations.setDiabetic_type(diabetestype);
        studentsInformations.setBlood_type(blood);
        studentsInformations.setNumber_of_dose(numberofDoase);

        studentsInformations.setPhone1(phone11);
        studentsInformations.setPhone2(phone22);
        studentsInformations.setUser_key(student.getKey());

        Map<String, Object> postValues = studentsInformations.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put( this.student.getKey() , postValues);

        if(newUser)
            mDatabase.getReference("studentsInformations").getRef().updateChildren(childUpdates);//.setValue(childUpdates);
        else
            mDatabase.getReference("studentsInformations").updateChildren(childUpdates);

        newUser = false ;

    }

}
