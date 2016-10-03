package com.demo.zk.qnews.mvp.presenter.impl;

import com.demo.zk.qnews.common.Constants;
import com.demo.zk.qnews.event.ChannelChangeEvent;
import com.demo.zk.qnews.greendao.NewsChannelTable;
import com.demo.zk.qnews.mvp.interactor.impl.NewsChannelInteractorImpl;
import com.demo.zk.qnews.mvp.presenter.NewsChannelPresenter;
import com.demo.zk.qnews.mvp.presenter.base.BasePresenterImpl;
import com.demo.zk.qnews.mvp.view.NewsChannelView;
import com.demo.zk.qnews.utils.RxBus;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * ClassName:com.demo.zk.qnews.mvp.presenter.impl
 * Author: zk<p>.
 * time: 2016/10/3 20:16.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class NewsChannelPresenterImpl extends BasePresenterImpl<NewsChannelView,
        Map<Integer, List<NewsChannelTable>>> implements NewsChannelPresenter {

    private NewsChannelInteractorImpl mNewsChannelInteractor;
    private boolean mIsChannelChanged;

    @Inject
    public NewsChannelPresenterImpl(NewsChannelInteractorImpl newsChannelInteractor) {
        mNewsChannelInteractor = newsChannelInteractor;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mIsChannelChanged) {
            RxBus.getInstance().post(new ChannelChangeEvent());
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNewsChannelInteractor.lodeNewsChannels(this);
    }

    @Override
    public void success(Map<Integer, List<NewsChannelTable>> data) {
        super.success(data);
        mView.initRecyclerViews(data.get(Constants.NEWS_CHANNEL_MINE), data.get(Constants.NEWS_CHANNEL_MORE));
    }

    @Override
    public void onItemSwap(int fromPosition, int toPosition) {
        mNewsChannelInteractor.swapDb(fromPosition, toPosition);
        mIsChannelChanged = true;
    }

    @Override
    public void onItemAddOrRemove(NewsChannelTable newsChannel, boolean isChannelMine) {
        mNewsChannelInteractor.updateDb(newsChannel, isChannelMine);
        mIsChannelChanged = true;
    }
}
