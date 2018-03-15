package com.example.toshimishra.photolearn;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by toshimishra on 14/03/18.
 */

public class ParticipantEditmodeAddLearningTitle extends AppCompatActivity  {

    Button button;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_editmode_add_learningtitle);
        button = (Button)findViewById(R.id.bt_Add);
        et = (EditText)findViewById(R.id.xh_txt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et.getText().toString();
                new Participant().createLearningTitle(title);
                finish();

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
