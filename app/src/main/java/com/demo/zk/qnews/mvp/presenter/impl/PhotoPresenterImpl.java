package com.demo.zk.qnews.mvp.presenter.impl;

import com.demo.zk.qnews.common.LoadNewsType;
import com.demo.zk.qnews.listener.RequestCallBack;
import com.demo.zk.qnews.mvp.entity.PhotoGirl;
import com.demo.zk.qnews.mvp.interactor.impl.PhotoInteractorImpl;
import com.demo.zk.qnews.mvp.presenter.PhotoPresenter;
import com.demo.zk.qnews.mvp.presenter.base.BasePresenterImpl;
import com.demo.zk.qnews.mvp.view.PhotoView;

import java.util.List;

import javax.inject.Inject;

/**
 * ClassName:com.demo.zk.qnews.mvp.presenter.impl
 * Author: zk<p>.
 * time: 2016/10/3 20:33.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class PhotoPresenterImpl extends BasePresenterImpl<PhotoView, List<PhotoGirl>>
        implements PhotoPresenter, RequestCallBack<List<PhotoGirl>> {
    private PhotoInteractorImpl mPhotoInteractor;
    private static int SIZE = 20;
    private int mStartPage = 1;
    private boolean misFirstLoad;
    private boolean mIsRefresh = true;

    @Inject
    public PhotoPresenterImpl(PhotoInteractorImpl photoInteractor) {
        mPhotoInteractor = photoInteractor;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadPhotoData();
    }

    @Override
    public void beforeRequest() {
        if (!misFirstLoad) {
            mView.showProgress();
        }
    }

    @Override
    public void onError(String errorMsg) {
        super.onError(errorMsg);
        if (mView != null) {
            int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_ERROR : LoadNewsType.TYPE_LOAD_MORE_ERROR;
            mView.setPhotoList(null, loadType);
        }
    }

    @Override
    public void success(List<PhotoGirl> items) {
        super.success(items);
        misFirstLoad = true;
        if (items != null) {
            mStartPage += 1;
        }

        int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_SUCCESS : LoadNewsType.TYPE_LOAD_MORE_SUCCESS;
        if (mView != null) {
            mView.setPhotoList(items, loadType);
            mView.hideProgress();
        }
    }

    @Override
    public void refreshData() {
        mStartPage = 1;
        mIsRefresh = true;
        loadPhotoData();
    }

    @Override
    public void loadMore() {
        mIsRefresh = false;
        loadPhotoData();
    }

    private void loadPhotoData() {
        mPhotoInteractor.loadPhotos(this, SIZE, mStartPage);
    }
}

