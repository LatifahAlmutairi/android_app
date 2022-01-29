package com.example.schoolmedicalobservation;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static java.text.DateFormat.getDateTimeInstance;

public class ReportDiabeticType2 implements Serializable {

    private long Date;//date
    private String TimeDate;
    private String blood_sugar;//editText
    private String Time_of_blood_sugar;//Time

    private String TypeOfMeal;//radio button
   // private String PostOfMeal;//take pic by camera

    private String TypeOfMedicien;//spinner
    private String UnitOfMedicien;//spinner

    private String Note;
    private  String user_key;
    private String key;
    private long timestamp;
    private long measruementTime;

    public void setTimeDate(String timeDate) {
        TimeDate = timeDate;
    }

    public String getTimeDate() {
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            DateFormat dateFormat = getDateTimeInstance();
            Date netDate = (new Date(getTimestamp()));
            return formatter.format(netDate);
        } catch (Exception e) {
            return "date";
        }
    }

    public String getTime(){
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            DateFormat dateFormat = getDateTimeInstance();
            Date netDate = (new Date(measruementTime));

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String shortTimeStr = sdf.format(netDate);
            return shortTimeStr ;
        } catch (Exception e) {
            // To change body of catch statement use File | Settings | File Templates.
            return "date";
        }
    }
    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }

    public String getBlood_sugar() {
        return blood_sugar;
    }

    public void setBlood_sugar(String blood_sugar) {
        this.blood_sugar = blood_sugar;
    }

    public String getTime_of_blood_sugar() {
        return Time_of_blood_sugar;
    }

    public void setTime_of_blood_sugar(String time_of_blood_sugar) {
        Time_of_blood_sugar = time_of_blood_sugar;
    }

    public String getTypeOfMeal() {
        return TypeOfMeal;
    }

    public void setTypeOfMeal(String typeOfMeal) {
        TypeOfMeal = typeOfMeal;
    }



    public String getTypeOfMedicien() {
        return TypeOfMedicien;
    }

    public void setTypeOfMedicien(String typeOfMedicien) {
        TypeOfMedicien = typeOfMedicien;
    }

    public String getUnitOfMedicien() {
        return UnitOfMedicien;
    }

    public void setUnitOfMedicien(String unitOfMedicien) {
        UnitOfMedicien = unitOfMedicien;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        this.Note = note;
    }


    public Map<String, Object> toMap() {


        HashMap<String, Object> result = new HashMap<>();
        result.put("DateTime", TimeDate);
        result.put("blood_sugar", blood_sugar);
        result.put("Time_of_blood_sugar", Time_of_blood_sugar);
        result.put("TypeOfMeal",TypeOfMeal);
        result.put("TypeOfMedicien",TypeOfMedicien);
        result.put("UnitOfMedicien",UnitOfMedicien);

        result.put("Note", Note);
        result.put("user_key", user_key);
        result.put("timestamp", timestamp);
        result.put("measruementTime", measruementTime);


        return result;
    }






    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setMeasruementTime(long measruementTime) {
        this.measruementTime = measruementTime;
    }

    public long getMeasruementTime() {
        return measruementTime;
    }
}
