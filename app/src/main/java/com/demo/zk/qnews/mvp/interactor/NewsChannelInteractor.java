package com.demo.zk.qnews.mvp.interactor;

import com.demo.zk.qnews.greendao.NewsChannelTable;
import com.demo.zk.qnews.listener.RequestCallBack;

import rx.Subscription;

/**
 * ClassName:com.demo.zk.qnews.mvp.interactor
 * Author: zk<p>.
 * time: 2016/10/3 20:17.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface NewsChannelInteractor<T> {
    Subscription lodeNewsChannels(RequestCallBack<T> callback);

    void swapDb(int fromPosition,int toPosition);

    void updateDb(NewsChannelTable newsChannel, boolean isChannelMine);
}
