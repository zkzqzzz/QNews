package com.demo.zk.qnews.mvp.presenter;

import com.demo.zk.qnews.greendao.NewsChannelTable;
import com.demo.zk.qnews.mvp.presenter.base.BasePresenter;

/**
 * ClassName:com.demo.zk.qnews.mvp.presenter
 * Author: zk<p>.
 * time: 2016/10/3 20:16.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface NewsChannelPresenter extends BasePresenter {
    void onItemSwap(int fromPosition, int toPosition);

    void onItemAddOrRemove(NewsChannelTable newsChannel, boolean isChannelMine);
}
