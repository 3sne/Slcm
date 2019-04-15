package com.example.asus.slcm;

import android.util.Log;
import java.util.ArrayList;

public class User {

    public ArrayList<Subject> subjectList;
    private String mRegistrationNumber;
    private String mRawPassword;

    public User(String mRegistrationNumber, String mRawPassword) {
        this.mRegistrationNumber = mRegistrationNumber;
        this.mRawPassword = mRawPassword;
        subjectList = new ArrayList<>();
    }

    public void addSubject(Subject sub) {
        try {
            if (!subjectList.contains(sub)) { //maintain uniqueness
                subjectList.add(sub);
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
                Log.i("ATTENDANCE", "AY: " + s.getmAcadYear() + "\nATD: " + s.getmAttendancePercentage() + "\nDA: " + s.getmDaysAbsent() + "\nDP" + s.getmDaysPresent() + "\nTC: " + s.getmTotalClasses() + "\nSEM: " + s.getmSemester() + "\nSUB: " + s.getmSubjectName() + "\nSC: " + s.getmSubjectCode());
            }
        } catch (Exception e) {
            Log.e("printInfo", "Something went wrong: " + e.toString() + e.getClass());
        }
    }

    public String getmRegistrationNumber() {
        return mRegistrationNumber;
    }

    public String getmRawPassword() {
        return mRawPassword;
    }

}
