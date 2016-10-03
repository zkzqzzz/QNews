package com.demo.zk.qnews.mvp.interactor;

import com.demo.zk.qnews.listener.RequestCallBack;

import rx.Subscription;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * ClassName:com.demo.zk.qnews.mvp.interactor
 * Author: zk<p>.
 * time: 2016/10/3 15:54.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface NewsListInteractor<T> {
    Subscription loadNews(RequestCallBack<T> listener, String type, String id, int startPage);
}
