package com.demo.zk.qnews.mvp.ui.adapter.pageradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.demo.zk.qnews.mvp.ui.fragment.PhotoDetailFragment;

import java.util.List;

/**
 * ClassName:com.demo.zk.qnews.mvp.ui.adapter.pageradapter
 * Author: zk<p>.
 * time: 2016/10/3 15:49.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class PhotoPagerAdapter extends FragmentStatePagerAdapter {
    private List<PhotoDetailFragment> mFragmentList;

    public PhotoPagerAdapter(FragmentManager fm, List<PhotoDetailFragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}

