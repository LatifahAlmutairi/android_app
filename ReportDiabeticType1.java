package com.example.schoolmedicalobservation;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static java.text.DateFormat.getDateTimeInstance;

public class ReportDiabeticType1 implements Serializable {
    private long timestamp;//Date
    private long measruementTime ;
    private String TimeDate;
    private String blood_sugar;//edir Text, user Enters number
    private String Time_of_blood_sugar;//time

    private int needOrDontNeedCorrectiveDose;//radio Button



    private String user_key;

    private String numOfdose;//eidit Text ,user Enter number
    private String blood_sugarIfhigh_CorrectiveDose;//eidit Text ,user Enters number
    private String normalRate;//eidit Text ,user Enters number
    private String resultOfCorrectiveDose;//text view show result

    private String typeOfdailyDose;//spinner
    private String UnitOfDose;//spinner

    private String Note;//eidit Text ,user Enters massage

    private String key ;

    public void setTimeDate(String timeDate) {
        TimeDate = timeDate;
    }

    public String getTimeDate() {
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            DateFormat dateFormat = getDateTimeInstance();
            Date netDate = (new Date(timestamp));
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

    public String getBlood_sugarIfhigh_CorrectiveDose() {
        return blood_sugarIfhigh_CorrectiveDose;
    }

    public String getNormalRate() {
        return normalRate;
    }

    public void setNormalRate(String normalRate) {
        this.normalRate = normalRate;
    }

    public String getNumOfdose() {
        return numOfdose;
    }

    public void setNumOfdose(String numOfdose) {
        this.numOfdose = numOfdose;
    }

    public String getResultOfCorrectiveDose() {
        return resultOfCorrectiveDose;
    }

    public void setResultOfCorrectiveDose(String resultOfCorrectiveDose) {
        this.resultOfCorrectiveDose = resultOfCorrectiveDose;
    }

    public void setBlood_sugarIfhigh_CorrectiveDose(String blood_sugarIfhigh_CorrectiveDose) {
        this.blood_sugarIfhigh_CorrectiveDose = blood_sugarIfhigh_CorrectiveDose;
    }

    public String getTypeOfdailyDose() {
        return typeOfdailyDose;
    }

    public void setTypeOfdailyDose(String typeOfdailyDose) {
        this.typeOfdailyDose = typeOfdailyDose;
    }

    public String getUnitOfDose() {
        return UnitOfDose;
    }

    public void setUnitOfDose(String unitOfDose) {
        UnitOfDose = unitOfDose;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public int getNeedOrDontNeedCorrectiveDose() {
        return needOrDontNeedCorrectiveDose;
    }

    public void setNeedOrDontNeedCorrectiveDose(int needOrDontNeedCorrectiveDose) {
        this.needOrDontNeedCorrectiveDose = needOrDontNeedCorrectiveDose;
    }

    public Map<String, Object> toMap() {


        HashMap<String, Object> result = new HashMap<>();
        result.put("DateTime", TimeDate);
        result.put("blood_sugar", blood_sugar);
        result.put("Time_of_blood_sugar", Time_of_blood_sugar);
        result.put("NeedOrdontNeedCorrectiveDose",needOrDontNeedCorrectiveDose);
        result.put("numOfDose", numOfdose);
        result.put("blood_sugarIfhigh_CorrectiveDose", blood_sugarIfhigh_CorrectiveDose);
        result.put("normalRate", normalRate);
        result.put("resultOfCorrectiveDose", resultOfCorrectiveDose);
        result.put("typeOfdailyDose", typeOfdailyDose);
        result.put("UnitOfDose", UnitOfDose);
        result.put("Note", Note);
        result.put("user_key", user_key);
        result.put("timestamp",timestamp);
        result.put("measruementTime",measruementTime);


        return result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getMeasruementTime() {
        return measruementTime;
    }

    public void setMeasruementTime(long measruementTime) {
        this.measruementTime = measruementTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }
}
