package com.demo.zk.qnews.mvp.view;

import com.demo.zk.qnews.mvp.entity.NewsDetail;
import com.demo.zk.qnews.mvp.view.base.BaseView;

/**
 * ClassName:com.demo.zk.qnews.mvp.view
 * Author: zk<p>.
 * time: 2016/10/3 20:23.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface NewsDetailView extends BaseView {
    void setNewsDetail(NewsDetail newsDetail);
}
