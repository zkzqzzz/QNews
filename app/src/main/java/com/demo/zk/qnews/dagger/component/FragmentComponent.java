package com.demo.zk.qnews.dagger.component;

import android.app.Activity;
import android.content.Context;

import com.demo.zk.qnews.dagger.module.FragmentModule;
import com.demo.zk.qnews.dagger.scope.ContextLife;
import com.demo.zk.qnews.dagger.scope.PerFragment;
import com.demo.zk.qnews.mvp.ui.fragment.NewsListFragment;

import dagger.Component;

/**
 * ClassName:com.demo.zk.qnews.dagger.component
 * Author: zk<p>.
 * time: 2016/10/3 15:40.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(NewsListFragment newsListFragment);
}
