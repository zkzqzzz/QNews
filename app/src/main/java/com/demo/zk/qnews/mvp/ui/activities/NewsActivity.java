package com.demo.zk.qnews.mvp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.demo.zk.qnews.R;
import com.demo.zk.qnews.annotation.BindValues;
import com.demo.zk.qnews.common.Constants;
import com.demo.zk.qnews.event.ChannelChangeEvent;
import com.demo.zk.qnews.event.ScrollToTopEvent;
import com.demo.zk.qnews.greendao.NewsChannelTable;
import com.demo.zk.qnews.mvp.presenter.impl.NewPresenterImpl;
import com.demo.zk.qnews.mvp.ui.activities.base.BaseActivity;
import com.demo.zk.qnews.mvp.ui.adapter.pageradapter.NewsFragmentPagerAdapter;
import com.demo.zk.qnews.mvp.ui.fragment.NewsListFragment;
import com.demo.zk.qnews.mvp.view.NewsView;
import com.demo.zk.qnews.utils.MyUtils;
import com.demo.zk.qnews.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * ClassName:com.demo.zk.qnews.mvp.ui.activities
 * Author: zk<p>.
 * time: 2016/10/3 10:12.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
@BindValues(mIsHasNavigationView = true)
public class NewsActivity extends BaseActivity implements NewsView{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;


    private String mCurrentViewPagerName;
    private List<String> mChannelNames;

    //使用这句话啊就是执行了NewPresenterImp的构造函数了
    @Inject
    NewPresenterImpl mNewsPresenter;

    private List<Fragment> mNewsFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscription = RxBus.getInstance().toObservable(ChannelChangeEvent.class)
                .subscribe(new Action1<ChannelChangeEvent>() {
                    @Override
                    public void call(ChannelChangeEvent channelChangeEvent) {
                        mNewsPresenter.onChannelDbChanged();
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    /**
     * 将BaseActivity中的各变量本地化
     */
    @Override
    public void initViews() {
        mBaseNavView = mNavView;
        mPresenter = mNewsPresenter;
        mPresenter.attachView(this);
    }

    /**
     * R.id.fab:跳到最顶
     * R.id.add_channel_iv:添加频道
     * @param view
     */
    @OnClick({R.id.fab, R.id.add_channel_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                RxBus.getInstance().post(new ScrollToTopEvent());
                break;
            case R.id.add_channel_iv:
                Intent intent = new Intent(this, NewsChannelActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 初始化新闻的viewpager
     * @param newsChannels
     */
    @Override
    public void initViewPager(List<NewsChannelTable> newsChannels){
        final List<String> channelNames = new ArrayList<>();
        if (newsChannels != null) {
            setNewsList(newsChannels, channelNames);
            setViewPager(channelNames);
        }
    }

    /**
     * 根据 newsChannels来确定显示多少个频道
     * @param newsChannels
     * @param channelNames
     */
    private void setNewsList(List<NewsChannelTable> newsChannels, List<String> channelNames) {
        mNewsFragmentList.clear();
        for (NewsChannelTable newsChannel : newsChannels) {
            NewsListFragment newsListFragment = createListFragments(newsChannel);
            mNewsFragmentList.add(newsListFragment);
            channelNames.add(newsChannel.getNewsChannelName());
        }
    }

    /**
     * 频道ID,频道类型，频道位置
     * 传给要生成的fragment
     * @param newsChannel
     * @return
     */
    private NewsListFragment createListFragments(NewsChannelTable newsChannel) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.NEWS_ID, newsChannel.getNewsChannelId());
        bundle.putString(Constants.NEWS_TYPE, newsChannel.getNewsChannelType());
        bundle.putInt(Constants.CHANNEL_POSITION, newsChannel.getNewsChannelIndex());
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * viewpager适配器
     * @param channelNames
     */
    private void setViewPager(List<String> channelNames) {
        NewsFragmentPagerAdapter adapter = new NewsFragmentPagerAdapter(
                getSupportFragmentManager(), channelNames, mNewsFragmentList);
        mViewPager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewPager);
        MyUtils.dynamicSetTabLayoutMode(mTabs);
//        mTabs.setTabsFromPagerAdapter(adapter);
        setPageChangeListener();

        mChannelNames = channelNames;
        int currentViewPagerPosition = getCurrentViewPagerPosition();
        mViewPager.setCurrentItem(currentViewPagerPosition, false);
    }

    /**
     * 获得当前选中的位置
     * @return
     */
    private int getCurrentViewPagerPosition() {
        int position = 0;
        if (mCurrentViewPagerName != null) {
            for (int i = 0; i < mChannelNames.size(); i++) {
                if (mCurrentViewPagerName.equals(mChannelNames.get(i))) {
                    position = i;
                }
            }
        }
        return position;
    }

    /**
     * viewpager改变（被选择）的监听
     */
    private void setPageChangeListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentViewPagerName = mChannelNames.get(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMsg(String message) {
        Snackbar.make(mFab, message, Snackbar.LENGTH_SHORT).show();
    }
}
