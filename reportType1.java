package com.example.schoolmedicalobservation;



import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class reportType1 extends AppCompatActivity {


    EditText ed1;//
    EditText ed2;
    EditText ed3; //1 /a
    Button cal1;
    TextView ss;
    Spinner planets_spinner1;
    Spinner numOfDose_spinner;
    EditText note;

    long timestamp;
     EditText editText1;
     EditText txtTime ;
    EditText txtDate;

    private RadioGroup radioGroup;
    private RadioButton radioButton1;




    String medicine;
    String Unitdose;
    private StudentsInformations student;
    private ReportDiabeticType1 reportDiabeticType1;
    private Button save;
    private DatabaseReference databaseReference;
    private FirebaseDatabase mDatabase;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_type1);

        note=(EditText) findViewById(R.id.note);
        mDatabase = FirebaseDatabase.getInstance();
        student = (StudentsInformations) getIntent().getSerializableExtra("student");

/*trrrrrrrrrrrrrrrrrrrry-----just try--------
        radioGroup =(RadioGroup)findViewById(R.id.radioGroip);
         int selectedId = radioGroup.getCheckedRadioButtonId();
         radioButton=(RadioButton)findViewById(selectedId);
         String selected =radioButton.getText().toString();
         databaseReference=FirebaseDatabase.getInstance().getReference();
*/

        radioGroup =(RadioGroup)findViewById(R.id.radioGroip);



       /* final Spinner planets_spinner1=(Spinner)findViewById(R.id.planets_spinner);
        final List<String> spinnerArrayList=new ArrayList<String>();
        spinnerArrayList.add("name1");
        spinnerArrayList.add("name2");
        spinnerArrayList.add("name3");
        spinnerArrayList.add("name4");
        spinnerArrayList.add("name5");

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerArrayList );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        planets_spinner1.setAdapter(adapter);*/
///the first spinner for medicine
        final String[] array = getResources().getStringArray(R.array.Medicine);

         planets_spinner1 = (Spinner) findViewById(R.id.planets_spinner);

        planets_spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medicine = array[position];
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Report_Type1").child(medicine);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //the second spinner for number of dose

        final String[] array1 = getResources().getStringArray(R.array.numOfDose_array);

        numOfDose_spinner = (Spinner) findViewById(R.id.numOfDose_spinner);

        numOfDose_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Unitdose = array1[position];
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Report_Type1").child(Unitdose);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute1 = c.get(Calendar.MINUTE);
         txtTime = (EditText) findViewById(R.id.editText);
        txtDate = (EditText) findViewById(R.id.editDate);


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

                        ReportDiabeticType1 reportDiabeticType11 = user.getValue(ReportDiabeticType1.class);
                        reportDiabeticType11.setKey(user.getKey());
                        if(reportDiabeticType11.getUser_key().equals(student.getUser_key())) {
                            reportDiabeticType1 = reportDiabeticType11;
                            viewData();
                            break;
                        }
                    }
                    if(reportDiabeticType1 == null){

                            reportDiabeticType1 = new ReportDiabeticType1();

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database error", Toast.LENGTH_LONG).show();
            }
        });


        save = findViewById(R.id.Save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editText1.getText().toString())){

                    editText1.setError(" you must fill it  ");

                } else{
                      updateReport();
                }
                //------------------------------
                if(TextUtils.isEmpty(txtDate.getText().toString())){

                    txtDate.setError(" you must fill it  ");

                } else{
                    updateReport();
                }
                if(TextUtils.isEmpty(txtTime.getText().toString())){

                    txtTime.setError(" you must fill it  ");

                } else{
                    updateReport();
                }

            }
        });


        txtTime.setOnClickListener(new View.OnClickListener() { // هينا حطيت الوقت
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                TimePickerDialog timepick = new TimePickerDialog(reportType1.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {//  هنا يصير عندي حدث لما امرر التايم
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
//                        String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                        txtTime.setText(hourOfDay + ":" + minute);
                        reportDiabeticType1.setMeasruementTime(calendar.getTimeInMillis());
                    }
                }, hour, minute1, true
                );
                timepick.setTitle("Select time ");
                timepick.show();
                saveDate();//latifah added



            }
        }); //  هينا خلص الوقت

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                DatePickerDialog datepicker = new DatePickerDialog(reportType1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date netDate = (new Date(calendar.getTimeInMillis()));
                        String format = formatter.format(netDate);
                        txtDate.setText(format);
                        reportDiabeticType1.setTimestamp(calendar.getTimeInMillis());
                    }
                }, year, month, day);
                datepicker.setTitle("Select data ");
                datepicker.show();

                saveDate();//latifah added

            }
        });


        //____________________________________________بداية ان فلات للاشياء حقت الاجراء __________________

        editText1 = (EditText) findViewById(R.id.editText1);
        btn = (Button) findViewById(R.id.buttonC);
        //____________________________________________بداية الراديو بوتن __________________
        ed1 = (EditText) findViewById(R.id.editText2);
        ed2 = (EditText) findViewById(R.id.editText3);
        ed3 = (EditText) findViewById(R.id.editText4);
        cal1 = (Button) findViewById(R.id.cal1);
        ss = (TextView) findViewById(R.id.textView1);
        ed1.setEnabled(false);
        ed2.setEnabled(false);
        ed3.setEnabled(false);
        cal1.setEnabled(false);
        ss.setEnabled(false);
        //_______________________________for radiobtn نهاية  _______________________________

        cal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1, n2, n3;

                int senOP = 1700;

                n1 = Integer.parseInt(ed3.getText().toString());
                n2 = Integer.parseInt(ed2.getText().toString());
                n3 = Integer.parseInt(ed1.getText().toString());
                double n4 = senOP / n1;
                double n5 = n2 - n3;
                double n6 = n5 / n4;
                double round  = Math.round(n6);

                ss.setText((String.valueOf(round)));
            }
        });
        //____________________________نهاية  الحساب__________________________________


    }
    //________________________ بداية راديو بوتن ______________________________________

    public void onRadioGroup1Click(View V) {
        switch (V.getId()) {

            case R.id.radio1:
                ed1.setEnabled(true);
                ed2.setEnabled(true);
                ed3.setEnabled(true);
                cal1.setEnabled(true);
                ss.setEnabled(true);


                break;
            case R.id.radio2:
                ed1.setEnabled(false);
                ed2.setEnabled(false);
                ed3.setEnabled(false);
                cal1.setEnabled(false);
                ss.setEnabled(false);


                break;

        }

    }
    //______________________ نهاية راديو بوتن ________________________________________
    //____________________________________________بداية الراديو بوتن __________________

    public void predureOfSuger(View view) {

         editText1 = (EditText) findViewById(R.id.editText1);


        int DeditText1;
        try {
            String str = editText1.getText().toString();
            DeditText1 = Integer.parseInt(str);
            final EditText editText = (EditText) findViewById(R.id.editText1);


            if (DeditText1 <= 50) {

                AlertDialog.Builder build = new AlertDialog.Builder(this);
                build.setMessage("Blood sugar is too low. Drink juice which has sugar first and then eats a meal ").setTitle("Very Dangerous").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //NO THING
                    }
                }).show();


            } else if (DeditText1 <= 80 && DeditText1 > 50) {

                AlertDialog.Builder build = new AlertDialog.Builder(this);
                build.setMessage(" You should drink half cup of juice, or a spoon of honey, or a spoon of sugar, or 250 ml of skim milk.").setTitle(" Blood sugar is low ").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //NO THING
                    }
                }).show();

            } else if (DeditText1 > 80 && DeditText1 <= 160) {
                AlertDialog.Builder build = new AlertDialog.Builder(this);
                build.setMessage("Normal").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //NO THING
                    }
                }).show();
            } else if (DeditText1 <= 200 && DeditText1 > 160) {
                AlertDialog.Builder build = new AlertDialog.Builder(this);
                build.setMessage("If the arrow of sensor tend to down it's fine,\n" +
                        " otherwise, that mean sugar blood little high  now\n" +
                        " and will  became more highest \n" +
                        " if the diabetic doesn't do any simple kinetic behavior as (walk) or eat the meal ").setTitle("Blood sugar is a little high ").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //NO THING
                    }
                }).show();
                // هينا تصرفي غبي لاني حاطه ١٠٠ فوق البرنامج بيقراه قبل
            } else if (DeditText1 < 250 && DeditText1 > 200) {
                AlertDialog.Builder build = new AlertDialog.Builder(this);
                build.setMessage("Should drink plenty of water and prefer to go to the bathroom").setTitle("Blood sugar is high").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //NO THING
                    }
                }).show();

            } else if (DeditText1 >= 250) {
                AlertDialog.Builder build = new AlertDialog.Builder(this);
                build.setMessage("you should give a corrective dose to diabetic's student \n" +
                        "and she/he should not do Strenuous kinetic activity.").setTitle("Blood sugar is too high ").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //NO THING
                    }
                }).show();


            } else {
                AlertDialog.Builder build = new AlertDialog.Builder(this);
                build.setMessage("try agin").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //NO THING
                    }
                }).show();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, " you must put integer number ", Toast.LENGTH_LONG).show();
            editText1.setError("");


        } catch (Exception r) {
            Toast.makeText(this, r.getMessage(), Toast.LENGTH_LONG).show();


        }
    }
    //____________________________________________نهاية الاجراءات  __________________





    private void viewData() {

        txtDate.setText(reportDiabeticType1.getTimeDate());
        txtTime.setText(reportDiabeticType1.getTime());
        ed1.setText(reportDiabeticType1.getNormalRate());
        ed2.setText(reportDiabeticType1.getBlood_sugarIfhigh_CorrectiveDose());
        ed3.setText(reportDiabeticType1.getNumOfdose());
        ss.setText(reportDiabeticType1.getResultOfCorrectiveDose());
        editText1.setText(reportDiabeticType1.getBlood_sugar());
        note.setText(reportDiabeticType1.getNote());

        radioButton1=(RadioButton)findViewById(reportDiabeticType1.getNeedOrDontNeedCorrectiveDose());
//        radioButton1.setSelected(true);



    }


    private void updateReport() {

        String Date=txtDate.getText().toString();
        String BloodSugar=editText1.getText().toString();
        String timeforEachSugarMeasurement=txtTime.getText().toString();

        String normalRtae=ed1.getText().toString();
        String BloodSugarifCorrective=ed2.getText().toString();
        String NumofDose=ed3.getText().toString();
        String result=ss.getText().toString();

        String sipnner= planets_spinner1.getSelectedItem().toString();
        String sipnner1=numOfDose_spinner.getSelectedItem().toString();
        String not=note.getText().toString();

        reportDiabeticType1.setTimeDate(Date);
        reportDiabeticType1.setBlood_sugar(BloodSugar);
        reportDiabeticType1.setTime_of_blood_sugar(timeforEachSugarMeasurement);
     reportDiabeticType1.setNormalRate(normalRtae);
     reportDiabeticType1.setBlood_sugarIfhigh_CorrectiveDose(BloodSugarifCorrective);
     reportDiabeticType1.setNumOfdose(NumofDose);
     reportDiabeticType1.setResultOfCorrectiveDose(result);
     reportDiabeticType1.setNote(not);
     reportDiabeticType1.setTypeOfdailyDose(sipnner);
     reportDiabeticType1.setUnitOfDose(sipnner1);


        reportDiabeticType1.setUser_key(student.getUser_key());

        Map<String, Object> postValues = reportDiabeticType1.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put( reportDiabeticType1.getKey() , postValues);

        if(reportDiabeticType1.getKey() == null){
            DatabaseReference d = mDatabase.getReference("Report_Type1").push();
            reportDiabeticType1.setKey(d.getKey());
            d.updateChildren(postValues);
        }else
        mDatabase.getReference("Report_Type1").updateChildren(childUpdates);
    }


private void saveDate() {
//    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//    Map map = new HashMap();
//    map.put("timestamp", ServerValue.TIMESTAMP);
//    ref.child("Report_Type1").updateChildren(map);
}

}

