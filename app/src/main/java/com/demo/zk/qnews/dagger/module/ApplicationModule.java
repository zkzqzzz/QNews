package com.demo.zk.qnews.dagger.module;

import android.content.Context;

import com.demo.zk.qnews.App;
import com.demo.zk.qnews.dagger.scope.ContextLife;
import com.demo.zk.qnews.dagger.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * ClassName:com.demo.zk.qnews.dagger.module
 * Author: zk<p>.
 * time: 2016/10/3 11:09.
 * Function: Application Module: module的作用是提供在应用的生命周期中存活的对象
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

@Module
public class ApplicationModule {
    private App mApplication;

    public ApplicationModule(App application) {
        mApplication = application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }

}