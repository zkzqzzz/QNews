package com.demo.zk.qnews.mvp.presenter.impl;

import com.demo.zk.qnews.common.LoadNewsType;
import com.demo.zk.qnews.listener.RequestCallBack;
import com.demo.zk.qnews.mvp.entity.NewsSummary;
import com.demo.zk.qnews.mvp.interactor.NewsListInteractor;
import com.demo.zk.qnews.mvp.interactor.impl.NewsListInteractorImpl;
import com.demo.zk.qnews.mvp.presenter.NewsListPresenter;
import com.demo.zk.qnews.mvp.presenter.base.BasePresenterImpl;
import com.demo.zk.qnews.mvp.view.NewsListView;

import java.util.List;

import javax.inject.Inject;

/**
 * ClassName:com.demo.zk.qnews.mvp.presenter.impl
 * Author: zk<p>.
 * time: 2016/10/3 15:54.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class NewsListPresenterImpl extends BasePresenterImpl<NewsListView, List<NewsSummary>>
        implements NewsListPresenter, RequestCallBack<List<NewsSummary>> {

    private NewsListInteractor<List<NewsSummary>> mNewsListInteractor;
    private String mNewsType;
    private String mNewsId;
    private int mStartPage;
    private boolean misFirstLoad;
    private boolean mIsRefresh = true;

    @Inject
    public NewsListPresenterImpl(NewsListInteractorImpl newsListInteractor) {
        mNewsListInteractor = newsListInteractor;
    }

    @Override
    public void onCreate() {
        if (mView != null) {
            loadNewsData();
        }
    }

    @Override
    public void beforeRequest() {
        if (!misFirstLoad) {
            mView.showProgress();
        }
    }

    @Override
    public void onError(String errorMsg) {
        super.onError(errorMsg);
        if (mView != null) {
            int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_ERROR : LoadNewsType.TYPE_LOAD_MORE_ERROR;
            mView.setNewsList(null, loadType);
        }
    }

    @Override
    public void success(List<NewsSummary> items) {
        misFirstLoad = true;
        if (items != null) {
            mStartPage += 20;
        }

        int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_SUCCESS : LoadNewsType.TYPE_LOAD_MORE_SUCCESS;
        if (mView != null) {
            mView.setNewsList(items, loadType);
            mView.hideProgress();
        }

    }

    @Override
    public void setNewsTypeAndId(String newsType, String newsId) {
        mNewsType = newsType;
        mNewsId = newsId;
    }

    @Override
    public void refreshData() {
        mStartPage = 0;
        mIsRefresh = true;
        loadNewsData();
    }

    @Override
    public void loadMore() {
        mIsRefresh = false;
        loadNewsData();
    }

    private void loadNewsData() {
        mSubscription = mNewsListInteractor.loadNews(this, mNewsType, mNewsId, mStartPage);
    }
}

