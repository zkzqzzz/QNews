package com.demo.zk.qnews.dagger.component;

import android.app.Activity;
import android.content.Context;

import com.demo.zk.qnews.dagger.module.ActivityModule;
import com.demo.zk.qnews.dagger.scope.ContextLife;
import com.demo.zk.qnews.dagger.scope.PerActivity;
import com.demo.zk.qnews.mvp.ui.activities.NewsActivity;
import com.demo.zk.qnews.mvp.ui.activities.NewsChannelActivity;
import com.demo.zk.qnews.mvp.ui.activities.NewsDetailActivity;
import com.demo.zk.qnews.mvp.ui.activities.NewsPhotoDetailActivity;
import com.demo.zk.qnews.mvp.ui.activities.PhotoActivity;
import com.demo.zk.qnews.mvp.ui.activities.PhotoDetailActivity;

import dagger.Component;

/**
 * ClassName:com.demo.zk.qnews.dagger.component
 * Author: zk<p>.
 * time: 2016/10/3 15:05.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(NewsActivity newsActivity);

    void inject(NewsDetailActivity newsDetailActivity);

    void inject(NewsChannelActivity newsChannelActivity);

    void inject(NewsPhotoDetailActivity newsPhotoDetailActivity);

    void inject(PhotoActivity photoActivity);

    void inject(PhotoDetailActivity photoDetailActivity);
}
