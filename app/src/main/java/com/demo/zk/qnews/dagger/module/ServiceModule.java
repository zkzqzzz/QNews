package com.demo.zk.qnews.dagger.module;

import android.app.Service;
import android.content.Context;

import com.demo.zk.qnews.dagger.scope.ContextLife;
import com.demo.zk.qnews.dagger.scope.PerService;

import dagger.Module;
import dagger.Provides;

/**
 * ClassName:com.demo.zk.qnews.dagger.module
 * Author: zk<p>.
 * time: 2016/10/3 15:42.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

@Module
public class ServiceModule {
    private Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @PerService
    @ContextLife("Service")
    public Context ProvideServiceContext() {
        return mService;
    }
}

