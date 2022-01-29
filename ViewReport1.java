package com.example.schoolmedicalobservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewReport1 extends AppCompatActivity {


    private ReportDiabeticType1 reportDiabeticType1 ;

    private TextView textView1,textView2,textView3,textView4,textView5,textView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report1);

        reportDiabeticType1 = (ReportDiabeticType1) getIntent().getSerializableExtra("report");

        textView1 = findViewById(R.id.date);
        textView2 = findViewById(R.id.time);
        textView3 = findViewById(R.id.blood_sugar);
        textView4 = findViewById(R.id.medicin);
        textView5 = findViewById(R.id.medicin_unit);
        textView6 = findViewById(R.id.note);
        textView1.setText(reportDiabeticType1.getTimeDate());
        textView2.setText(reportDiabeticType1.getTime());
        textView3.setText(reportDiabeticType1.getBlood_sugar());
        textView4.setText(reportDiabeticType1.getTypeOfdailyDose());
        textView5.setText(reportDiabeticType1.getUnitOfDose());
        textView6.setText(reportDiabeticType1.getNote());
    }


}
