package com.demo.zk.qnews.mvp.interactor.impl;

import com.demo.zk.qnews.App;
import com.demo.zk.qnews.R;
import com.demo.zk.qnews.greendao.NewsChannelTable;
import com.demo.zk.qnews.listener.RequestCallBack;
import com.demo.zk.qnews.mvp.interactor.NewsInteractor;
import com.demo.zk.qnews.repository.db.NewsChannelTableManager;
import com.demo.zk.qnews.utils.TransformUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * ClassName:com.demo.zk.qnews.mvp.interactor.impl
 * Author: zk<p>.
 * time: 2016/10/3 15:14.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class NewsInteractorImpl implements NewsInteractor<List<NewsChannelTable>> {

    @Inject
    public NewsInteractorImpl() {
    }

    @Override
    public Subscription lodeNewsChannels(final RequestCallBack<List<NewsChannelTable>> callback) {
        return Observable.create(new Observable.OnSubscribe<List<NewsChannelTable>>() {
            @Override
            public void call(Subscriber<? super List<NewsChannelTable>> subscriber) {
                NewsChannelTableManager.initDB();
                subscriber.onNext(NewsChannelTableManager.loadNewsChannelsMine());
                subscriber.onCompleted();
            }
        })
                .compose(TransformUtils.<List<NewsChannelTable>>defaultSchedulers())
                .subscribe(new Subscriber<List<NewsChannelTable>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(App.getAppContext().getString(R.string.db_error));
                    }

                    @Override
                    public void onNext(List<NewsChannelTable> newsChannelTables) {
                        callback.success(newsChannelTables);
                    }
                });
    }
}

