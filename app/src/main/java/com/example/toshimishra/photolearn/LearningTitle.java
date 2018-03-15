package com.example.toshimishra.photolearn;

import com.google.firebase.auth.FirebaseAuth;

import java.util.*;

/**
 * Created by toshimishra on 06/03/18.
 */

public class LearningTitle extends Title {

    public LearningTitle(){
        super();
    }
    public  LearningTitle(String titleID,String userID,String title,String sessionID){
        super(titleID,userID,title,sessionID);
    }


}
