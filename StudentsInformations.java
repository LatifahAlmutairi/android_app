package com.example.schoolmedicalobservation;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class StudentsInformations implements Serializable {

    private String ID ;
    private String age ;
    private String blood_type;
    private String diabetic_type;

    private String length ;
    private String number_of_dose;
    private String phone1 ;
    private String phone2 ;
    private String weight ;
    private String name ;
    private String key ;
    private String user_key;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getDiabetic_type() {
        return diabetic_type;
    }

    public void setDiabetic_type(String diabetic_type) {
        this.diabetic_type = diabetic_type;
    }


    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getNumber_of_dose() {
        return number_of_dose;
    }

    public void setNumber_of_dose(String number_of_dose) {
        this.number_of_dose = number_of_dose;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public Map<String, Object> toMap() {


        HashMap<String, Object> result = new HashMap<>();
        result.put("age", age);
        result.put("blood_type", blood_type);
        result.put("diabetes_type", diabetic_type);

        result.put("length", length);
        result.put("number_of_dose", number_of_dose);
        result.put("phone1", phone1);
        result.put("phone2", phone2);
        result.put("weight", weight);
        result.put("name", name);
        result.put("user_key", user_key);



        return result;
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


