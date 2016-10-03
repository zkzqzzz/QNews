package com.demo.zk.qnews.mvp.view;

import com.demo.zk.qnews.greendao.NewsChannelTable;
import com.demo.zk.qnews.mvp.view.base.BaseView;

import java.util.List;

/**
 * ClassName:com.demo.zk.qnews.mvp.view
 * Author: zk<p>.
 * time: 2016/10/3 15:13.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface NewsView extends BaseView {
    void initViewPager(List<NewsChannelTable> newsChannels);
}
