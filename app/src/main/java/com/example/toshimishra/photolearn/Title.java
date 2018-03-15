package com.example.toshimishra.photolearn;

import  java.util.*;
/**
 * Created by toshimishra on 06/03/18.
 */

public abstract  class Title {

    private String userID;
    private String title;
    private String sessionID;
    private String titleID;

    public String getTitleID() {
        return titleID;
    }

    private Date timeStamp;

    public String getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
    }

    public String getSessionID() {
        return sessionID;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }


    public Title(){

    }
    public  Title(String titleID,String userID,String title,String sessionID){
        this.userID = userID;
        this.title = title;
        this.sessionID = sessionID;
        this.titleID = titleID;
        // TODO : set timestamp and ID
    }



}
