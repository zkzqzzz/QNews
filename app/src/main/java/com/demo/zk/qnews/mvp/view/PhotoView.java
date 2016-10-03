package com.demo.zk.qnews.mvp.view;

import com.demo.zk.qnews.common.LoadNewsType;
import com.demo.zk.qnews.mvp.entity.PhotoGirl;
import com.demo.zk.qnews.mvp.view.base.BaseView;

import java.util.List;

/**
 * ClassName:com.demo.zk.qnews.mvp.view
 * Author: zk<p>.
 * time: 2016/10/3 20:25.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface PhotoView extends BaseView {
    void setPhotoList(List<PhotoGirl> photoGirls, @LoadNewsType.checker int loadType);
}

