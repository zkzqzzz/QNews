package com.demo.zk.qnews.mvp.presenter;

import com.demo.zk.qnews.mvp.presenter.base.BasePresenter;

/**
 * ClassName:com.demo.zk.qnews.mvp.presenter
 * Author: zk<p>.
 * time: 2016/10/3 15:53.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface NewsListPresenter extends BasePresenter {
    void setNewsTypeAndId(String newsType, String newsId);

    void refreshData();

    void loadMore();
}

