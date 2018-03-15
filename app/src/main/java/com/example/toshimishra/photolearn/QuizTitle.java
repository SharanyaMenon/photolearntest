package com.example.toshimishra.photolearn;

/**
 * Created by toshimishra on 06/03/18.
 */

public class QuizTitle extends Title {


    public QuizTitle() {
        super();
    }

    public  QuizTitle(String titleID, String userID, String title, String sessionID){
        super(titleID,userID,title,sessionID);
    }

    public void addQuizItem(String titleID,String photoURL, String options[],boolean isOptionAns[],String ansExp){
        QuizItem i = new QuizItem(titleID,photoURL,options,isOptionAns,ansExp);

        //TODO UPdate DB
    }

}
