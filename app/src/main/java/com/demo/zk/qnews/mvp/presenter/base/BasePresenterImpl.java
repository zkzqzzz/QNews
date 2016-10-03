package com.demo.zk.qnews.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.demo.zk.qnews.listener.RequestCallBack;
import com.demo.zk.qnews.mvp.view.base.BaseView;
import com.demo.zk.qnews.utils.MyUtils;

import rx.Subscription;

/**
 * ClassName:com.demo.zk.qnews.mvp.presenter.base
 * Author: zk<p>.
 * time: 2016/10/3 14:53.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class BasePresenterImpl<T extends BaseView, E>  implements BasePresenter,RequestCallBack<E> {
    protected T mView;
    protected Subscription mSubscription;

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        MyUtils.cancelSubscription(mSubscription);
    }

    @Override
    public void attachView(@NonNull BaseView view) {
        // TODO?
        mView = (T) view;
    }

    @Override
    public void beforeRequest() {
        mView.showProgress();
    }

    @Override
    public void success(E data) {
        mView.hideProgress();
    }

    @Override
    public void onError(String errorMsg) {
        mView.hideProgress();
        mView.showMsg(errorMsg);
    }
}
