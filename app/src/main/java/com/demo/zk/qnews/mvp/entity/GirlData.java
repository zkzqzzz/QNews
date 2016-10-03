package com.demo.zk.qnews.mvp.entity;

import java.util.List;

/**
 * ClassName:com.demo.zk.qnews.mvp.entity
 * Author: zk<p>.
 * time: 2016/10/3 14:48.
 * Function:  妹子图
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class GirlData {
    private boolean isError;
    private List<PhotoGirl> results;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public void setResults(List<PhotoGirl> results) {
        this.results = results;
    }

    public List<PhotoGirl> getResults() {
        return results;
    }
}
