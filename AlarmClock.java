package com.example.schoolmedicalobservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlarmClock extends AppCompatActivity {
    EditText mHourEditText ;
    EditText mMinuteEditText ;
    Button mSetAlarmButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock);
        mHourEditText =(EditText)findViewById(R.id.hour);
        mMinuteEditText=(EditText)findViewById(R.id.mint);
        mSetAlarmButton=(Button)findViewById(R.id.buttalarm);

       mSetAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour =Integer.parseInt(mHourEditText.getText().toString());
                int minute =Integer.parseInt(mMinuteEditText.getText().toString());
                Intent intent =new Intent(android.provider.AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(android.provider.AlarmClock.EXTRA_HOUR,hour);
                intent.putExtra(android.provider.AlarmClock.EXTRA_MINUTES,minute);
                if(hour<=24 &&minute<=60) {
                    startActivity(intent);
                }
            }
        });




    }
}
