package com.demo.zk.qnews.listener;

/**
 * ClassName:com.demo.zk.qnews.listener
 * Author: zk<p>.
 * time: 2016/10/3 14:56.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public interface RequestCallBack<T> {

    void beforeRequest();

    void success(T data);

    void onError(String errorMsg);
}
