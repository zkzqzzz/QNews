package com.demo.zk.qnews.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.demo.zk.qnews.mvp.view.base.BaseView;

/**
 * ClassName:com.demo.zk.qnews.mvp.presenter.base
 * Author: zk<p>.
 * time: 2016/10/3 14:53.
 * Function: BasePresenter
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface BasePresenter {

    //void onResume();

    void onCreate();

    void attachView(@NonNull BaseView view);

    void onDestroy();
}
