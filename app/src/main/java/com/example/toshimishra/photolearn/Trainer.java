package com.example.toshimishra.photolearn;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;
/**
 * Created by toshimishra on 06/03/18.
 */

public class Trainer{
    public String getUid(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
    public void createLearningSession(Date date,Integer moduleNumber, String courseCode) {
        final LearningSession s = new LearningSession(getUid(), date, moduleNumber, courseCode);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                writeSession(s);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Trainer", "Trainer:onCancelled", databaseError.toException());
            }
        });
    }
    private void writeSession(LearningSession s){

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("LearningSessions").child(s.getSessionID()).setValue(s);
        mDatabase.child("Users-LearningSessions").child(getUid()).child(s.getSessionID()).setValue(s);
        //todo cleanup
        /*Map<String, Object> add = new HashMap<>();
        add.put("/LearningSessions/" + s.getSessionID(), s);
        add.put("/Users-LearningSessions/" + user.getUid() + "/" + s.getSessionID(), s);
        mDatabase.updateChildren(add);*/

    }
    public void deleteLearningSession(int index){


    }

    public void createQuizTitle(String Title){

    }
}
