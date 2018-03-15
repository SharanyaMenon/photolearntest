package com.example.toshimishra.photolearn.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.toshimishra.photolearn.LearningSession;
import com.example.toshimishra.photolearn.LearningTitle;
import com.example.toshimishra.photolearn.Main2Activity;
import com.example.toshimishra.photolearn.Main3Activity;
import com.example.toshimishra.photolearn.ParticipantEditModeLearningTitlesQuizTiltlesActivity;
import com.example.toshimishra.photolearn.ParticipantEditmodeAddLearningTitle;
import com.example.toshimishra.photolearn.ParticipantEditmodeViewLearningItems;
import com.example.toshimishra.photolearn.R;
import com.example.toshimishra.photolearn.SampleRecyclerAdapter;
import com.example.toshimishra.photolearn.State;
import com.example.toshimishra.photolearn.Strings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 *
 */
public class FirstFragment extends BaseFragment implements SampleRecyclerAdapter.OnItemClickListener {

    private View mFirstFragmentView;
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;
    private ArrayList<LearningTitle> learningTitles;
    private SampleRecyclerAdapter adapter;
    private ArrayList<String> dataSet;


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFirstFragmentView = inflater.inflate(R.layout.fragment_first, container, false);
        mRecyclerView = (RecyclerView) mFirstFragmentView.findViewById(R.id.recy_learning);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataSet = new ArrayList<>();
        learningTitles = new ArrayList<>();
        adapter = new SampleRecyclerAdapter(getContext(), dataSet);
        Button button = (Button) mFirstFragmentView.findViewById(R.id.bt_Add_fragment);
//        if(State.isTrainerMode())
//            button.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!State.isTrainerMode()){
                    startActivity(new Intent(getContext(), ParticipantEditmodeAddLearningTitle.class));
                }

            }
        });


        //设置adapter
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query query = database.getReference().child("LearningSessions-LearningTitles").child(State.getCurrentSession().getSessionID()).orderByChild("userID").equalTo(getUid());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSet.clear();
                learningTitles.clear();
                for (DataSnapshot val : dataSnapshot.getChildren()) {
                    dataSet.add(val.getValue(LearningTitle.class).getTitle());
                    learningTitles.add(val.getValue(LearningTitle.class));
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return mFirstFragmentView;
    }

    @Override
    public void onItemClick(View view, int position, String name) {

        State.setCurrentLearningTitle(learningTitles.get(position));
        Toast.makeText(getContext(), State.getCurrentLearningTitle().getTitle(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), ParticipantEditmodeViewLearningItems.class));
    }

    private String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
