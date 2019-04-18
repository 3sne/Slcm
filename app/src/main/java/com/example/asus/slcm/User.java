package com.example.asus.slcm;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    public ArrayList<Subject> subjectList;
    private String mRegistrationNumber;
    private String mRawPassword;
    private boolean areSubjectsLoaded;

    public User() {
        subjectList = new ArrayList<>();
    }

    public User(String mRegistrationNumber, String mRawPassword) {
        this.mRegistrationNumber = mRegistrationNumber;
        this.mRawPassword = mRawPassword;
        subjectList = new ArrayList<>();
        this.areSubjectsLoaded = false;
    }

    public void addSubject(Subject sub) {
        try {
            if (!subjectList.contains(sub)) { //maintain uniqueness
                subjectList.add(sub);
                areSubjectsLoaded = true;
            }
        } catch (Exception e) {
            Log.e("updateSubjectList", "Something went wrong: " + e.toString() + e.getClass());
        }
    }

    public void printUserInfoToConsole() {
        try {
            Log.i("USER", "RegNo >> " + mRegistrationNumber + "\nPassword >> " + mRawPassword);
            for (int i = 0; i < subjectList.size(); i++) {
                Subject s = subjectList.get(i);
                Log.i("ATTENDANCE", "AY: " + s.getmAcadYear() + " | ATD: " + s.getmAttendancePercentage() + " | " + s.getmDaysAbsent() + " + " + s.getmDaysPresent() + " = " + s.getmTotalClasses() + " |  " + s.getmSemester() + " | " + s.getmSubjectName() + " | " + s.getmSubjectCode());
            }
        } catch (Exception e) {
            Log.e("printInfo", "Something went wrong: " + e.toString() + e.getClass());
        }
    }

    public void setmRegistrationNumber(String mRegistrationNumber) {
        this.mRegistrationNumber = mRegistrationNumber;
    }

    public void setmRawPassword(String mRawPassword) {
        this.mRawPassword = mRawPassword;
    }

    public String getmRegistrationNumber() {
        return mRegistrationNumber;
    }

    public String getmRawPassword() {
        return mRawPassword;
    }

    public boolean getAreSubjectsLoaded() {
        return areSubjectsLoaded;
    }
}
