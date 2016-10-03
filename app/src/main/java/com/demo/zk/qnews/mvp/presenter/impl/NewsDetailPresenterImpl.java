package com.demo.zk.qnews.mvp.presenter.impl;

import com.demo.zk.qnews.mvp.entity.NewsDetail;
import com.demo.zk.qnews.mvp.interactor.NewsDetailInteractor;
import com.demo.zk.qnews.mvp.interactor.impl.NewsDetailInteractorImpl;
import com.demo.zk.qnews.mvp.presenter.NewsDetailPresenter;
import com.demo.zk.qnews.mvp.presenter.base.BasePresenterImpl;
import com.demo.zk.qnews.mvp.view.NewsDetailView;

import javax.inject.Inject;

/**
 * ClassName:com.demo.zk.qnews.mvp.presenter.impl
 * Author: zk<p>.
 * time: 2016/10/3 20:27.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class NewsDetailPresenterImpl extends BasePresenterImpl<NewsDetailView, NewsDetail>
        implements NewsDetailPresenter {
    private NewsDetailInteractor<NewsDetail> mNewsDetailInteractor;
    private String mPostId;

    @Inject
    public NewsDetailPresenterImpl(NewsDetailInteractorImpl newsDetailInteractor) {
        mNewsDetailInteractor = newsDetailInteractor;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSubscription = mNewsDetailInteractor.loadNewsDetail(this, mPostId);

    }

    @Override
    public void success(NewsDetail data) {
        super.success(data);
        mView.setNewsDetail(data);
    }

    @Override
    public void setPosId(String postId) {
        mPostId = postId;
    }
}
