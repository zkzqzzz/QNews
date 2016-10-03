package com.demo.zk.qnews.dagger.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.demo.zk.qnews.dagger.scope.ContextLife;
import com.demo.zk.qnews.dagger.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * ClassName:com.demo.zk.qnews.dagger.module
 * Author: zk<p>.
 * time: 2016/10/3 15:41.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideActivityContext() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Fragment provideFragment() {
        return mFragment;
    }
}

