package com.demo.zk.qnews.mvp.view;

import com.demo.zk.qnews.greendao.NewsChannelTable;
import com.demo.zk.qnews.mvp.view.base.BaseView;

import java.util.List;

/**
 * ClassName:com.demo.zk.qnews.mvp.view
 * Author: zk<p>.
 * time: 2016/10/3 20:15.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface NewsChannelView extends BaseView {
    void initRecyclerViews(List<NewsChannelTable> newsChannelsMine, List<NewsChannelTable> newsChannelsMore);
}
