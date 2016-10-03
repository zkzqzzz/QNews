package com.demo.zk.qnews.dagger.module;

import android.app.Activity;
import android.content.Context;

import com.demo.zk.qnews.dagger.scope.ContextLife;
import com.demo.zk.qnews.dagger.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * ClassName:com.demo.zk.qnews.dagger.module
 * Author: zk<p>.
 * time: 2016/10/3 15:07.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context ProvideActivityContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    public Activity ProvideActivity() {
        return mActivity;
    }
}
