package com.example.toshimishra.photolearn;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.toshimishra.photolearn.fragment.FirstFragment;
import com.example.toshimishra.photolearn.fragment.SecondFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shara on 14/3/2018.
 */

public class TrainerViewTitlesActivity extends AppCompatActivity implements SampleRecyclerAdapter.OnItemClickListener {


    private RecyclerView mRecyclerView;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    private List<Fragment> fragments = new ArrayList<>();

    private List<String> tabs = new ArrayList<>();

    private ViewHolder holder;

//    private Button button;

    private TextView text;

    @Override
    public void onItemClick(View view, int position, String name) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_view_learningtitles_quiztitles);
        initData();
        initView();
        text = (TextView) findViewById(R.id.title_LT);
        text.setText(State.getCurrentSession().getCourseCode());

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
                holder = new TrainerViewTitlesActivity.ViewHolder(tab.getCustomView());
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
}
