package com.example.toshimishra.photolearn;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by toshimishra on 12/03/18.
 */

public class ParticipantChoiceLearningsessionMode extends AppCompatActivity{

    TextView text_ls;
    private DatabaseReference mDatabase;
    Button button;
    CheckBox box1,box2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_choice_learningsession_mode);
        text_ls = (TextView)findViewById(R.id.title_LS);
        button = (Button)findViewById(R.id.Go);
        box1 = (CheckBox) findViewById(R.id.checkBox);
        box2 = (CheckBox) findViewById(R.id.checkBox2);

        final String session_id = getIntent().getStringExtra("SESSION_ID");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query sessionQuery= database.getReference().child("LearningSessions").orderByChild("sessionID").equalTo(session_id);
        sessionQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            if(dataSnapshot.getChildrenCount()==1){
                State.setCurrentSession(dataSnapshot.child(session_id).getValue(LearningSession.class));
                text_ls.setText(State.getCurrentSession().getCourseCode());
            }
            else{
                Toast.makeText(getBaseContext(),"Enter Valid Session ID", Toast.LENGTH_LONG).show();
                finish();
            }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(ParticipantChoiceLearningsessionMode.this,ParticipantEditModeLearningTitlesQuizTiltlesActivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return true;
        }
        else if(i == R.id.action_switch){
            startActivity(new Intent(this, TrainerSessionsActivity.class));
            finish();
            return  true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
