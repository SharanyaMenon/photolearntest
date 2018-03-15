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

import com.example.toshimishra.photolearn.LearningTitle;
import com.example.toshimishra.photolearn.Main3Activity;
import com.example.toshimishra.photolearn.ParticipantEditmodeAddLearningTitle;
import com.example.toshimishra.photolearn.QuizTitle;
import com.example.toshimishra.photolearn.R;
import com.example.toshimishra.photolearn.SampleRecyclerAdapter;
import com.example.toshimishra.photolearn.State;
import com.example.toshimishra.photolearn.Strings;
import com.example.toshimishra.photolearn.TrainerAddQuizTitle;
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
public class SecondFragment extends BaseFragment implements SampleRecyclerAdapter.OnItemClickListener {

    private View mSecondFragmentView;

    private RecyclerView mRecyclerView;

    private DatabaseReference mDatabase;

    private ArrayList<QuizTitle> quizTitles;

    private SampleRecyclerAdapter adapter;

    private ArrayList<String> dataSet;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mSecondFragmentView = inflater.inflate(R.layout.fragment_second, container, false);
        mRecyclerView = (RecyclerView) mSecondFragmentView.findViewById(R.id.recy_quiz);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //    ArrayList<String> list = new ArrayList<>();
        //      list.add("test1");
        //   list.add("test2");


        dataSet = new ArrayList<String>();
        quizTitles = new ArrayList<QuizTitle>();
        adapter = new SampleRecyclerAdapter(getContext(), dataSet);
        Log.d("adapter initialised", "");
        Button button = (Button) mSecondFragmentView.findViewById(R.id.bt_Add_fragment);
        if(!State.isTrainerMode())
            button.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(State.isTrainerMode()) {
                    startActivity(new Intent(getContext(), TrainerAddQuizTitle.class));
                }
            }
        });
//        SampleRecyclerAdapter adapter = new SampleRecyclerAdapter(getContext(), Strings.string_frag2);

        //设置adapter
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query query = database.getReference().child("LearningSessions-LearningTitles").child(State.getCurrentSession().getSessionID()).orderByChild("userID").equalTo(getUid());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSet.clear();
                quizTitles.clear();
                for (DataSnapshot val : dataSnapshot.getChildren()) {
                    dataSet.add(val.getValue(QuizTitle.class).getTitle());
                    quizTitles.add(val.getValue(QuizTitle.class));
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return mSecondFragmentView;
    }

    @Override
    public void onItemClick(View view, int position, String name) {
        Toast.makeText(getContext(), "click " + position, Toast.LENGTH_SHORT).show();
    }

    private String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
