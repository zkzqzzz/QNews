package com.demo.zk.qnews.mvp.interactor.impl;

import com.demo.zk.qnews.App;
import com.demo.zk.qnews.R;
import com.demo.zk.qnews.common.HostType;
import com.demo.zk.qnews.listener.RequestCallBack;
import com.demo.zk.qnews.mvp.entity.GirlData;
import com.demo.zk.qnews.mvp.entity.PhotoGirl;
import com.demo.zk.qnews.mvp.interactor.PhotoInteractor;
import com.demo.zk.qnews.repository.network.RetrofitManager;
import com.demo.zk.qnews.utils.TransformUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * ClassName:com.demo.zk.qnews.mvp.interactor.impl
 * Author: zk<p>.
 * time: 2016/10/3 20:32.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class PhotoInteractorImpl implements PhotoInteractor<List<PhotoGirl>> {

    @Inject
    public PhotoInteractorImpl() {
    }

    @Override
    public Subscription loadPhotos(final RequestCallBack<List<PhotoGirl>> listener, int size, int page) {
        return RetrofitManager.getInstance(HostType.GANK_GIRL_PHOTO)
                .getPhotoListObservable(size, page)
                .map(new Func1<GirlData, List<PhotoGirl>>() {
                    @Override
                    public List<PhotoGirl> call(GirlData girlData) {
                        return girlData.getResults();
                    }
                })
                .compose(TransformUtils.<List<PhotoGirl>>defaultSchedulers())
                .subscribe(new Subscriber<List<PhotoGirl>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(App.getAppContext().getString(R.string.load_error));
                    }

                    @Override
                    public void onNext(List<PhotoGirl> photoGirls) {
                        listener.success(photoGirls);
                    }
                })
                ;
    }
}

