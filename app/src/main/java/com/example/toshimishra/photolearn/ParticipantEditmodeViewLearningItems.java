package com.example.toshimishra.photolearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toshimishra on 14/03/18.
 */

public class ParticipantEditmodeViewLearningItems extends AppCompatActivity implements SampleRecyclerAdapter.OnItemClickListener {
    // 模拟listview中加载的数据Simulate the data loaded in the listview.
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;
    private List<String> dataSet;
    private List<LearningItem> learningItems;
    private SampleRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_editmode_view_learningitems);
        dataSet = new ArrayList<>();
        learningItems = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recy_learningitem);
        //设置布局管理器Set the layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SampleRecyclerAdapter(this,dataSet);
        //设置adapter    Setadapter
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference().child("LearningSessions-LearningTitles-LearningItems").child(State.getCurrentSession().getSessionID()).child(State.getCurrentLearningTitle().getTitleID());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSet.clear();
                learningItems.clear();
                for ( DataSnapshot val : dataSnapshot.getChildren()){
                    dataSet.add(val.getValue(LearningItem.class).getPhotoDesc());
                    learningItems.add(val.getValue(LearningItem.class));
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Button button = (Button)findViewById(R.id.bt_Add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParticipantEditmodeViewLearningItems.this, ParticipantEditmodeAddLearningItem.class));
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
            startActivity(new Intent(this, ParticipantEnterLearningsessionActivity.class));
            finish();
            return  true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onItemClick(View view, int position, String name) {

        Toast.makeText(getApplicationContext(),"click "+position,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ParticipantEditmodeViewLearningItems.this,Main3Activity.class));
        //startActivity(new Intent(TrainerSessionsActivity.this,Main3Activity.class));
    }

    private String getUid(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }


}

