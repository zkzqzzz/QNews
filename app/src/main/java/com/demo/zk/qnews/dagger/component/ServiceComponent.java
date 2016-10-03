package com.demo.zk.qnews.dagger.component;

import android.content.Context;

import com.demo.zk.qnews.dagger.module.ServiceModule;
import com.demo.zk.qnews.dagger.scope.ContextLife;
import com.demo.zk.qnews.dagger.scope.PerService;

import dagger.Component;

/**
 * ClassName:com.demo.zk.qnews.dagger.component
 * Author: zk<p>.
 * time: 2016/10/3 15:41.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    @ContextLife("Service")
    Context getServiceContext();

    @ContextLife("Application")
    Context getApplicationContext();
}
