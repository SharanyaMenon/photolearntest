package com.example.toshimishra.photolearn;

/**
 * Created by toshimishra on 06/03/18.
 */

public class QuizItem extends Item {
    String options[];
    boolean isOptionAns[];
    String ansExp; //For explanation of the option

    public QuizItem(String titleID,String photoURL, String options[],boolean isOptionAns[],String ansExp) {
        super(titleID, photoURL);
        this.ansExp = ansExp;
        int i = 0;
        for (String option : options) {
            this.options[i] = options[i];
            this.isOptionAns[i] = isOptionAns[i];
            i++;
        }
    }
    public void updateQuizItem(String photoURL, String options[],boolean isOptionAns[],String ansExp){
        super.update(photoURL);
        this.ansExp = ansExp;
        int i = 0;
        for (String option : options) {
            this.options[i] = options[i];
            this.isOptionAns[i] = isOptionAns[i];
            i++;
        }

    }
}
