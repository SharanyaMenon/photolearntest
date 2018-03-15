package com.example.toshimishra.photolearn;

/**
 * Created by Administrator on 2018/3/13.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.toshimishra.photolearn.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mNewsPagers;

    private List<String> tabs = new ArrayList<>(); //标签名称

    public void setNewsPagers(List<BaseFragment> newsPagers) {
        this.mNewsPagers = newsPagers;
    }

    public void setTabs(List<String> tabs) {
        this.tabs = tabs;
    }

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return mNewsPagers.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mNewsPagers.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
