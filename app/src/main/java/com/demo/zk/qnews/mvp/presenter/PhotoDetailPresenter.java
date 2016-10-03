package com.demo.zk.qnews.mvp.presenter;

import com.demo.zk.qnews.common.PhotoRequestType;
import com.demo.zk.qnews.mvp.presenter.base.BasePresenter;

/**
 * ClassName:com.demo.zk.qnews.mvp.presenter
 * Author: zk<p>.
 * time: 2016/10/3 20:33.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface PhotoDetailPresenter extends BasePresenter {
    void handlePicture(String imageUrl, @PhotoRequestType.PhotoRequestTypeChecker int type);
}
