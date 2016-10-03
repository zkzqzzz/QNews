package com.demo.zk.qnews.mvp.presenter.impl;

import com.demo.zk.qnews.greendao.NewsChannelTable;
import com.demo.zk.qnews.mvp.interactor.NewsInteractor;
import com.demo.zk.qnews.mvp.interactor.impl.NewsInteractorImpl;
import com.demo.zk.qnews.mvp.presenter.NewsPresenter;
import com.demo.zk.qnews.mvp.presenter.base.BasePresenterImpl;
import com.demo.zk.qnews.mvp.view.NewsView;

import java.util.List;

import javax.inject.Inject;

/**
 * ClassName:com.demo.zk.qnews.mvp.presenter.impl
 * Author: zk<p>.
 * time: 2016/10/3 15:12.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class NewPresenterImpl extends BasePresenterImpl<NewsView, List<NewsChannelTable>>
        implements NewsPresenter {

    private NewsInteractor<List<NewsChannelTable>> mNewsInteractor;

    @Inject
    public NewPresenterImpl(NewsInteractorImpl newsInteractor) {
        mNewsInteractor = newsInteractor;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadNewsChannels();
    }

    /**
     * 获得新闻频道列表的subscription
     */
    private void loadNewsChannels() {
        mSubscription = mNewsInteractor.lodeNewsChannels(this);
    }

    @Override
    public void success(List<NewsChannelTable> data) {
        super.success(data);
        mView.initViewPager(data);
    }

    @Override
    public void onChannelDbChanged() {
        loadNewsChannels();
    }
}

