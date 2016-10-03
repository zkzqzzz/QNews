package com.demo.zk.qnews.mvp.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.zk.qnews.App;
import com.demo.zk.qnews.dagger.component.DaggerFragmentComponent;
import com.demo.zk.qnews.dagger.component.FragmentComponent;
import com.demo.zk.qnews.dagger.module.FragmentModule;
import com.demo.zk.qnews.mvp.presenter.base.BasePresenter;
import com.demo.zk.qnews.utils.MyUtils;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * ClassName:com.demo.zk.qnews.mvp.ui.fragment.base
 * Author: zk<p>.
 * time: 2016/10/3 15:40.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

    protected FragmentComponent mFragmentComponent;
    protected T mPresenter;

    private View mFragmentView;

    public abstract void initInjector();

    public abstract void initViews(View view);

    public abstract int getLayoutId();

    protected Subscription mSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((App) getActivity().getApplication()).getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
        initInjector();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mFragmentView);
            initViews(mFragmentView);
        }
        return mFragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        refWatcher.watch(this);

        if (mPresenter != null) {
            mPresenter.onDestroy();
        }

        MyUtils.cancelSubscription(mSubscription);
    }
}

