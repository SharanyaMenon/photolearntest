package com.example.toshimishra.photolearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by toshimishra on 13/03/18.
 */

class TrainerViewLearningTitlesQuizTitlesActivity extends AppCompatActivity implements SampleRecyclerAdapter.OnItemClickListener{

    @Override
    public void onItemClick(View view, int position, String name) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }
}
