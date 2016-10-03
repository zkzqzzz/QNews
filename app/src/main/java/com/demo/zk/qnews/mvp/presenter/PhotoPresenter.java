package com.demo.zk.qnews.mvp.presenter;

import com.demo.zk.qnews.mvp.presenter.base.BasePresenter;

/**
 * ClassName:com.demo.zk.qnews.mvp.presenter
 * Author: zk<p>.
 * time: 2016/10/3 20:33.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface PhotoPresenter extends BasePresenter {
    void refreshData();

    void loadMore();
}
