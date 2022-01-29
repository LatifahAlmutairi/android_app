package com.example.schoolmedicalobservation;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class reportType2 extends AppCompatActivity {
    ImageView img;

    long timestamp;

    EditText Note;
    EditText txtTime;
    EditText txtDate;
    EditText editText1;


    private StudentsInformations student;
    private ReportDiabeticType2 reportDiabeticType2;
    private Button save;
    private DatabaseReference databaseReference;
    private FirebaseDatabase mDatabase;

    String medicine;
    String Unitdose;
    Spinner planets_spinner1;
    Spinner numOfDose_spinner;
    private String[] array;
    private String[] array1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_type2);

        mDatabase = FirebaseDatabase.getInstance();
        student = (StudentsInformations) getIntent().getSerializableExtra("student");

        Note =(EditText) findViewById(R.id.editText5);
        editText1=(EditText) findViewById(R.id.editText1);
        img =(ImageView)findViewById(R.id.imageView);

        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute1 = c.get(Calendar.MINUTE);
        txtTime = (EditText) findViewById(R.id.editText);
        txtDate = (EditText)findViewById(R.id.editDate);


        array = getResources().getStringArray(R.array.MedicineforType2);

        planets_spinner1 = (Spinner) findViewById(R.id.planets_spinner);

        planets_spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medicine = array[position];
//                databaseReference = FirebaseDatabase.getInstance().getReference().child("Report_Type2").child(medicine);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //the second spinner for number of dose

        array1 = getResources().getStringArray(R.array.numOfDoseArray);

        numOfDose_spinner = (Spinner) findViewById(R.id.numOfDose_spinner);

        numOfDose_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Unitdose = array1[position];


//                databaseReference = FirebaseDatabase.getInstance().getReference().child("Report_Type2").child(Unitdose);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //database
        DatabaseReference databaseReference = mDatabase.getReference("Report_Type2");

        Query query = databaseReference.orderByChild("user_key");//.equalTo(student.getKey());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        ReportDiabeticType2 reportDiabeticType22 = user.getValue(ReportDiabeticType2.class);
                        reportDiabeticType22.setKey(user.getKey());
                        if(reportDiabeticType22.getUser_key().equals(student.getUser_key())) {
                            reportDiabeticType2 = reportDiabeticType22 ;
                            viewData();
                            break;
                        }
                    }if(reportDiabeticType2 == null){
                        reportDiabeticType2 = new ReportDiabeticType2();
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
                updateReport();
            }
        });


        txtTime.setOnClickListener(new View.OnClickListener() { // هينا حطيت الوقت
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                TimePickerDialog timepick = new TimePickerDialog(reportType2.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {//  هنا يصير عندي حدث لما امرر التايم
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
//                        String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                        txtTime.setText(hourOfDay + ":" + minute);
                        reportDiabeticType2.setMeasruementTime(calendar.getTimeInMillis());
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

                DatePickerDialog datepicker = new DatePickerDialog(reportType2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date netDate = (new Date(calendar.getTimeInMillis()));
                        String format = formatter.format(netDate);
                        txtDate.setText(format);
                        reportDiabeticType2.setTimestamp(calendar.getTimeInMillis());
                    }
                }, year, month, day);
                datepicker.setTitle("Select data ");
                datepicker.show();

                saveDate();//latifah added

            }
        });


        //__________________________بداية ان فلات للاشياء حقت الاجراء __________________

        EditText editText1 = (EditText) findViewById(R.id.editText1);
        Button btn = (Button) findViewById(R.id.buttonC);
        //____________________________________________________________________


    }

    public  void btn_camera(View view){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,100);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            img.setImageBitmap((Bitmap)data.getExtras().get("data"));

        }
    }

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


            } else if (DeditText1 <=80 && DeditText1>50) {

                AlertDialog.Builder build = new AlertDialog.Builder(this);
                build.setMessage(" You should drink half cup of juice, or a spoon of honey, or a spoon of sugar, or 250 ml of skim milk.").setTitle(" Blood sugar is low ").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //NO THING
                    }
                }).show();

            } else if (DeditText1 >80 && DeditText1<=160 ) {
                AlertDialog.Builder build = new AlertDialog.Builder(this);
                build.setMessage("Normal").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //NO THING
                    }
                }).show();
            } else if (DeditText1 <=200 && DeditText1 >160) {
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
            } else if (DeditText1 <250 && DeditText1 >200) {
                AlertDialog.Builder build = new AlertDialog.Builder(this);
                build.setMessage("Should drink plenty of water and prefer to go to the bathroom").setTitle("Blood sugar is high").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //NO THING
                    }
                }).show();
                //  اطلع له زر يوديه للجرعة التصحيحيه
            } else if (DeditText1 >=250) {
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
            Toast.makeText(this, " you should  put integer num ", Toast.LENGTH_LONG).show();
            editText1.setError("\" you should  put integer num \"");


        } catch (Exception r) {
            Toast.makeText(this,r.getMessage(), Toast.LENGTH_LONG).show();


        }
    }
    //____________________________________________نهاية الاجراءات  __________________


    private void viewData() {

        txtDate.setText(reportDiabeticType2.getTimeDate());
        txtTime.setText(reportDiabeticType2.getTime());
        editText1.setText(reportDiabeticType2.getBlood_sugar());
        Note.setText(reportDiabeticType2.getNote());
        ArrayList<String> c = new ArrayList<>();
        Collections.addAll(c, array);
        planets_spinner1.setSelection(c.indexOf(reportDiabeticType2.getTypeOfMedicien()));
        ArrayList<String> a = new ArrayList<>();
        Collections.addAll(a,array1);
        numOfDose_spinner.setSelection(a.indexOf(reportDiabeticType2.getUnitOfMedicien()));

    }



    private void updateReport() {

        String Date=txtDate.getText().toString();

        String BloodSugar=editText1.getText().toString();

        // String needCorrective=

        String not= Note.getText().toString();
        String sipnner= planets_spinner1.getSelectedItem().toString();
        String sipnner1=numOfDose_spinner.getSelectedItem().toString();
        reportDiabeticType2.setUnitOfMedicien(sipnner);
        reportDiabeticType2.setTypeOfMedicien(sipnner1);
        reportDiabeticType2.setBlood_sugar(BloodSugar);
        reportDiabeticType2.setTimeDate(Date);

        reportDiabeticType2.setNote(not);


        reportDiabeticType2.setUser_key(student.getUser_key());

        Map<String, Object> postValues = reportDiabeticType2.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put( this.reportDiabeticType2.getKey() , postValues);
        if(reportDiabeticType2.getKey() == null){
            DatabaseReference d = mDatabase.getReference("Report_Type2").push();
            reportDiabeticType2.setKey(d.getKey());
            d.updateChildren(postValues);
        }else
        mDatabase.getReference("Report_Type2").updateChildren(childUpdates);
    }



    private void saveDate() {
      /*  DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Map map = new HashMap();
        map.put("timestamp", ServerValue.TIMESTAMP);
        ref.child("Report_Type2").updateChildren(map);*/
    }
}

