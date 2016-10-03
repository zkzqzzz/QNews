package com.demo.zk.qnews.mvp.interactor;

import com.demo.zk.qnews.listener.RequestCallBack;

import rx.Subscription;

/**
 * ClassName:com.demo.zk.qnews.mvp.interactor
 * Author: zk<p>.
 * time: 2016/10/3 15:13.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface NewsInteractor<T> {
    Subscription lodeNewsChannels(RequestCallBack<T> callback);
}
