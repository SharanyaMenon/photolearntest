package com.example.toshimishra.photolearn;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.example.toshimishra.photolearn.fragment.FirstFragment;
import com.example.toshimishra.photolearn.fragment.SecondFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ParticipantEditModeLearningTitlesQuizTiltlesActivity extends AppCompatActivity {
    // 模拟listview中加载的数据Simulate the data loaded in the listview.
    private RecyclerView mRecyclerView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabs = new ArrayList<>(); //标签名称
    private ViewHolder holder;
    private Button button;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_viewmode_view_learningtitles_quiztitles);
        initData();
        initView();
        text = (TextView) findViewById(R.id.title_LT);
        text.setText(State.getCurrentSession().getCourseCode());
        button = (Button) findViewById(R.id.bt_Add_LT);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParticipantEditModeLearningTitlesQuizTiltlesActivity.this, ParticipantEditmodeAddLearningTitle.class));
            }
        });


    }

    private void initData() {
        tabs.add("Learning Title");
        tabs.add("Quiz Title");
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);


        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        setTabView();
    }

    /**
     * 设置Tab的样式
     */
    private void setTabView() {
        holder = null;
        for (int i = 0; i < tabs.size(); i++) {
            //依次获取标签
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            //为每个标签设置布局
            tab.setCustomView(R.layout.tab_item);
            holder = new ViewHolder(tab.getCustomView());
            //为标签填充数据
            holder.tvTabName.setText(tabs.get(i));
            //默认选择第一项
            if (i == 0) {
                holder.tvTabName.setSelected(true);
                holder.tvTabName.setTextSize(18);
            }
        }

        //tab选中的监听事件
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                holder = new ViewHolder(tab.getCustomView());
                holder.tvTabName.setSelected(true);
                //选中后字体变大
                holder.tvTabName.setTextSize(18);
                //让Viewpager跟随TabLayout的标签切换
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                holder = new ViewHolder(tab.getCustomView());
                holder.tvTabName.setSelected(false);
                //恢复为默认字体大小
                holder.tvTabName.setTextSize(16);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    class ViewHolder {
        TextView tvTabName;

        public ViewHolder(View tabView) {
            tvTabName = (TextView) tabView.findViewById(R.id.tv_tab_name);
        }
    }

    class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        //显示标签上的文字
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }

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
        } else if (i == R.id.action_switch) {
            startActivity(new Intent(this, TrainerSessionsActivity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    //像素单位转换
    public int dip2px(int dip) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);
    }

    private String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }


}
