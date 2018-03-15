package com.example.toshimishra.photolearn;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by toshimishra on 06/03/18.
 */

public class Participant {

    DatabaseReference mDatabase;
    public String getUid(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
    public void createLearningTitle(String title){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child("LearningSessions-LearningTitles").child(State.getCurrentSession().getSessionID()).push().getKey();
        LearningTitle l = new LearningTitle(key,getUid(),title,State.getCurrentSession().getSessionID());
        mDatabase.child("LearningSessions-LearningTitles").child(l.getSessionID()).child(key).setValue(l);
        mDatabase.child("Users-LearningSessions-LearningTitles").child(getUid()).child(l.getSessionID()).child(key).setValue(l);

    }
    public void createLearningItem(String photoURL,String photoDesc, String GPS){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child("LearningSessions-LearningTitles-LearningItems").child(State.getCurrentSession().getSessionID()).child(State.getCurrentLearningTitle().getTitleID()).push().getKey();
        LearningItem i = new LearningItem(key,State.getCurrentLearningTitle().getTitleID(),photoURL,photoDesc,GPS,getUid());
        mDatabase.child("LearningSessions-LearningTitles-LearningItems").child(State.getCurrentSession().getSessionID()).child(State.getCurrentLearningTitle().getTitleID()).child(key).setValue(i);
    }

}
