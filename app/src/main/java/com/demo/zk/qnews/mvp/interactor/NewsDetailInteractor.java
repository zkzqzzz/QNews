package com.demo.zk.qnews.mvp.interactor;

import com.demo.zk.qnews.listener.RequestCallBack;

import rx.Subscription;

/**
 * ClassName:com.demo.zk.qnews.mvp.interactor
 * Author: zk<p>.
 * time: 2016/10/3 20:27.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface NewsDetailInteractor<T> {
    Subscription loadNewsDetail(RequestCallBack<T> callBack, String postId);
}
