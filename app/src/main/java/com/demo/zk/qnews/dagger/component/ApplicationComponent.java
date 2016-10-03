package com.demo.zk.qnews.dagger.component;

import android.content.Context;

import com.demo.zk.qnews.dagger.module.ApplicationModule;
import com.demo.zk.qnews.dagger.scope.ContextLife;
import com.demo.zk.qnews.dagger.scope.PerApp;

import dagger.Component;

/**
 * ClassName:com.demo.zk.qnews.dagger.component
 * Author: zk<p>.
 * time: 2016/10/3 11:08.
 * Function: ApplicationComponent  生命周期和application一样
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ContextLife("Application")
    Context getApplication();
}
