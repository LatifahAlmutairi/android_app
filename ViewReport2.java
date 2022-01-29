package com.example.schoolmedicalobservation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewReport2 extends AppCompatActivity {


    private ReportDiabeticType2 reportDiabeticType2 ;
    private TextView textView1,textView2,textView3,textView4,textView5,textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report2);

        reportDiabeticType2 = (ReportDiabeticType2) getIntent().getSerializableExtra("report");

        textView1 = findViewById(R.id.date);
        textView2 = findViewById(R.id.time);
        textView3 = findViewById(R.id.blood_sugar);
        textView4 = findViewById(R.id.medicin);
        textView5 = findViewById(R.id.medicin_unit);
        textView6 = findViewById(R.id.note);

        textView1.setText(reportDiabeticType2.getTimeDate());
        textView2.setText(reportDiabeticType2.getTime());
        textView3.setText(reportDiabeticType2.getBlood_sugar());
        textView4.setText(reportDiabeticType2.getTypeOfMedicien());
        textView5.setText(reportDiabeticType2.getUnitOfMedicien());
        textView6.setText(reportDiabeticType2.getNote());
    }

}
