package com.demo.zk.qnews.mvp.view.base;

/**
 * ClassName:com.demo.zk.qnews.mvp.view.base
 * Author: zk<p>.
 * time: 2016/10/3 14:52.
 * Function: BaseView
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface BaseView {
    void showProgress();

    void hideProgress();

    void showMsg(String message);
}
