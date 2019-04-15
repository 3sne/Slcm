package com.example.asus.slcm;

import android.util.Log;

public class Subject {

    private String mAcadYear;
    private String mAttendancePercentage;
    private String mDaysAbsent;
    private String mDaysPresent;
    private String mTotalClasses;
    private String mSemester;
    private String mSubjectName;
    private String mSubjectCode;

    public Subject(String mAcadYear, String mAttendancePercentage, String mDaysAbsent, String mDaysPresent, String mTotalClasses, String mSemester, String mSubjectName, String mSubjectCode) {
        try {
            this.mAcadYear = mAcadYear;
            this.mAttendancePercentage = mAttendancePercentage;
            this.mDaysAbsent = mDaysAbsent;
            this.mDaysPresent = mDaysPresent;
            this.mTotalClasses = mTotalClasses;
            this.mSemester = mSemester;
            this.mSubjectName = mSubjectName;
            this.mSubjectCode = mSubjectCode;
        } catch (Exception e) {
            Log.e("Subject Constructor", "Something went wrong: " + e.toString());
        }
    }

    public String getmAcadYear() {
        return mAcadYear;
    }

    public void setmAcadYear(String mAcadYear) {
        this.mAcadYear = mAcadYear;
    }

    public String getmAttendancePercentage() {
        return mAttendancePercentage;
    }

    public void setmAttendancePercentage(String mAttendancePercentage) {
        this.mAttendancePercentage = mAttendancePercentage;
    }

    public String getmDaysAbsent() {
        return mDaysAbsent;
    }

    public void setmDaysAbsent(String mDaysAbsent) {
        this.mDaysAbsent = mDaysAbsent;
    }

    public String getmDaysPresent() {
        return mDaysPresent;
    }

    public void setmDaysPresent(String mDaysPresent) {
        this.mDaysPresent = mDaysPresent;
    }

    public String getmTotalClasses() {
        return mTotalClasses;
    }

    public void setmTotalClasses(String mTotalClasses) {
        this.mTotalClasses = mTotalClasses;
    }

    public String getmSemester() {
        return mSemester;
    }

    public void setmSemester(String mSemester) {
        this.mSemester = mSemester;
    }

    public String getmSubjectName() {
        return mSubjectName;
    }

    public void setmSubjectName(String mSubjectName) {
        this.mSubjectName = mSubjectName;
    }

    public String getmSubjectCode() {
        return mSubjectCode;
    }

    public void setmSubjectCode(String mSubjectCode) {
        this.mSubjectCode = mSubjectCode;
    }
}
