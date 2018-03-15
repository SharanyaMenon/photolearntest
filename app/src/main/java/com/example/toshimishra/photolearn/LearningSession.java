package com.example.toshimishra.photolearn;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by toshimishra on 06/03/18.
 */

public class LearningSession {

    private String sessionID;
    private String userID;
    private Date courseDate;
    private String courseCode;
    private Integer moduleNumber;

    public LearningSession(){

    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public LearningSession(String userID, Date date, Integer moduleNumber, String courseCode) {
        this.sessionID = formatDate(date) + courseCode + moduleNumber.toString();
        this.userID = userID;
        this.courseDate = date;
        this.courseCode = courseCode;
        this.moduleNumber = moduleNumber;
    }

    public String getUserID() {
        return userID;
    }

    public Date getCourseDate() {
        return courseDate;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Integer getModuleNumber() {
        return moduleNumber;
    }

    public String getSessionID() {
        return sessionID;
    }
    public void deleteSession() {

    }
    private String formatDate(Date date){
        return (date.getDate() + "-" + date.getMonth()+"-"+date.getYear());
    }
}
