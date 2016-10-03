package com.demo.zk.qnews.mvp.view;

import com.demo.zk.qnews.common.LoadNewsType;
import com.demo.zk.qnews.mvp.entity.NewsSummary;
import com.demo.zk.qnews.mvp.view.base.BaseView;

import java.util.List;

/**
 * ClassName:com.demo.zk.qnews.mvp.view
 * Author: zk<p>.
 * time: 2016/10/3 15:52.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface NewsListView extends BaseView {
    void setNewsList(List<NewsSummary> newsSummary, @LoadNewsType.checker int loadType);
}
