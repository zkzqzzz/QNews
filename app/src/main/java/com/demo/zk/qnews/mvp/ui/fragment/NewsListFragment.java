package com.demo.zk.qnews.mvp.ui.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.demo.zk.qnews.R;
import com.demo.zk.qnews.common.Constants;
import com.demo.zk.qnews.common.LoadNewsType;
import com.demo.zk.qnews.event.ScrollToTopEvent;
import com.demo.zk.qnews.mvp.entity.NewsPhotoDetail;
import com.demo.zk.qnews.mvp.entity.NewsSummary;
import com.demo.zk.qnews.mvp.presenter.impl.NewsListPresenterImpl;
import com.demo.zk.qnews.mvp.ui.activities.NewsDetailActivity;
import com.demo.zk.qnews.mvp.ui.activities.NewsPhotoDetailActivity;
import com.demo.zk.qnews.mvp.ui.adapter.NewsListAdapter;
import com.demo.zk.qnews.mvp.ui.fragment.base.BaseFragment;
import com.demo.zk.qnews.mvp.view.NewsListView;
import com.demo.zk.qnews.utils.NetUtil;
import com.demo.zk.qnews.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * ClassName:com.demo.zk.qnews.mvp.ui.fragment
 * Author: zk<p>.
 * time: 2016/10/3 15:44.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class NewsListFragment extends BaseFragment implements NewsListView, NewsListAdapter.OnNewsListItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.news_rv)
    RecyclerView mNewsRV;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.empty_view)
    TextView mEmptyView;

    @Inject
    NewsListAdapter mNewsListAdapter;
    @Inject
    NewsListPresenterImpl mNewsListPresenter;
    @Inject
    Activity mActivity;

    private String mNewsId;
    private String mNewsType;

    private boolean mIsAllLoaded;

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initSwipeRefreshLayout();
        initRecyclerView();
        initPresenter();
        registerScrollToTopEvent();
    }

    /**
     * 下拉刷新
     */
    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(getActivity().getResources().getIntArray(R.array.gplus_colors)
        );
    }

    /**
     * 根据获取的频道类型和频道ID
     *
     */
    private void initPresenter() {
        mNewsListPresenter.setNewsTypeAndId(mNewsType, mNewsId);
        mPresenter = mNewsListPresenter;
        mPresenter.attachView(this);
        mPresenter.onCreate();
    }

    /**
     * 注册返回最顶的观察者
     */
    private void registerScrollToTopEvent() {
        mSubscription = RxBus.getInstance().toObservable(ScrollToTopEvent.class)
                .subscribe(new Action1<ScrollToTopEvent>() {
                    @Override
                    public void call(ScrollToTopEvent scrollToTopEvent) {
                        mNewsRV.getLayoutManager().scrollToPosition(0);
                    }
                });
    }

    /**
     * News RecyclerView
     */
    private void initRecyclerView() {
        mNewsRV.setHasFixedSize(true);
        mNewsRV.setLayoutManager(new LinearLayoutManager(mActivity,
                LinearLayoutManager.VERTICAL, false));
        mNewsRV.setItemAnimator(new DefaultItemAnimator());
        mNewsRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();

                if (!mIsAllLoaded && visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition >= totalItemCount - 1) {
                    //加载更多
                    mNewsListPresenter.loadMore();
                    mNewsListAdapter.showFooter();
                    mNewsRV.scrollToPosition(mNewsListAdapter.getItemCount() - 1);
                }
            }

        });
        mNewsListAdapter.setOnItemClickListener(this);
        mNewsRV.setAdapter(mNewsListAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initValues();
        NetUtil.isNetworkErrThenShowMsg();
    }

    /**
     *
     * 获得频道ID 和频道类型
     */
    private void initValues() {
        if (getArguments() != null) {
            mNewsId = getArguments().getString(Constants.NEWS_ID);
            mNewsType = getArguments().getString(Constants.NEWS_TYPE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    /**
     *
     * @param newsSummary   新闻概括
     * @param loadType  加载类型 -> 加载是否成功
     */
    @Override
    public void setNewsList(List<NewsSummary> newsSummary, @LoadNewsType.checker int loadType) {
        switch (loadType) {
            case LoadNewsType.TYPE_REFRESH_SUCCESS:
                mSwipeRefreshLayout.setRefreshing(false);//不刷新
                mNewsListAdapter.setList(newsSummary);
                mNewsListAdapter.notifyDataSetChanged();
                checkIsEmpty(newsSummary);
                break;
            case LoadNewsType.TYPE_REFRESH_ERROR:
                mSwipeRefreshLayout.setRefreshing(false);
                checkIsEmpty(newsSummary);
                break;
            case LoadNewsType.TYPE_LOAD_MORE_SUCCESS:
                mNewsListAdapter.hideFooter();
                if (newsSummary == null || newsSummary.size() == 0) {
                    mIsAllLoaded = true;
                    Snackbar.make(mNewsRV, getString(R.string.no_more), Snackbar.LENGTH_SHORT).show();
                } else {
                    mNewsListAdapter.addMore(newsSummary);
                }
                break;
            case LoadNewsType.TYPE_LOAD_MORE_ERROR:
                mNewsListAdapter.hideFooter();
                break;
        }
    }

    /**
     * 检查获取的 newsSummary是否为空
     * @param newsSummary
     */
    private void checkIsEmpty(List<NewsSummary> newsSummary) {
        if (newsSummary == null && mNewsListAdapter.getList() == null) {
            mNewsRV.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);

        } else {
            mNewsRV.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMsg(String message) {
        mProgressBar.setVisibility(View.GONE);
        // 网络不可用状态在此之前已经显示了提示信息
        if (NetUtil.isNetworkAvailable()) {
            Snackbar.make(mNewsRV, message, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mNewsListPresenter.onDestroy();
    }

    /**
     * 点击了图片或者新闻详情
     * @param view
     * @param position
     * @param isPhoto
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onItemClick(View view, int position, boolean isPhoto) {
        if (isPhoto) {
            NewsPhotoDetail newsPhotoDetail = getPhotoDetail(position);
            goToPhotoDetailActivity(newsPhotoDetail);
        } else {
            goToNewsDetailActivity(view, position);
        }
    }

    private NewsPhotoDetail getPhotoDetail(int position) {
        NewsSummary newsSummary = mNewsListAdapter.getList().get(position);
        NewsPhotoDetail newsPhotoDetail = new NewsPhotoDetail();
        newsPhotoDetail.setTitle(newsSummary.getTitle());
        setPictures(newsSummary, newsPhotoDetail);
        return newsPhotoDetail;
    }

    private void setPictures(NewsSummary newsSummary, NewsPhotoDetail newsPhotoDetail) {
        List<NewsPhotoDetail.Picture> pictureList = new ArrayList<>();

        if (newsSummary.getAds() != null) {
            for (NewsSummary.AdsBean entity : newsSummary.getAds()) {
                setValuesAndAddToList(pictureList, entity.getTitle(), entity.getImgsrc());
            }
        } else if (newsSummary.getImgextra() != null) {
            for (NewsSummary.ImgextraBean entity : newsSummary.getImgextra()) {
                setValuesAndAddToList(pictureList, null, entity.getImgsrc());
            }
        } else {
            setValuesAndAddToList(pictureList, null, newsSummary.getImgsrc());
        }

        newsPhotoDetail.setPictures(pictureList);
    }

    private void setValuesAndAddToList(List<NewsPhotoDetail.Picture> pictureList, String title, String imgsrc) {
        NewsPhotoDetail.Picture picture = new NewsPhotoDetail.Picture();
        if (title != null) {
            picture.setTitle(title);
        }
        picture.setImgSrc(imgsrc);

        pictureList.add(picture);
    }

    /**
     * 图片详情
     * @param newsPhotoDetail
     */
    private void goToPhotoDetailActivity(NewsPhotoDetail newsPhotoDetail) {
        Intent intent = new Intent(getActivity(), NewsPhotoDetailActivity.class);
        intent.putExtra(Constants.PHOTO_DETAIL, newsPhotoDetail);
        startActivity(intent);
    }

    /**
     * 新闻详情
     * @param view
     * @param position
     */
    private void goToNewsDetailActivity(View view, int position) {
        Intent intent = setIntent(position);
        startActivity(view, intent);
    }

    /**
     * 新闻详情
     * @param position
     * @return
     */
    @NonNull
    private Intent setIntent(int position) {
        List<NewsSummary> newsSummaryList = mNewsListAdapter.getList();

        Intent intent = new Intent(mActivity, NewsDetailActivity.class);
        intent.putExtra(Constants.NEWS_POST_ID, newsSummaryList.get(position).getPostid());
        intent.putExtra(Constants.NEWS_IMG_RES, newsSummaryList.get(position).getImgsrc());
        return intent;
    }

    /**
     *
     * @param view
     * @param intent
     */
    private void startActivity(View view, Intent intent) {
        ImageView newsSummaryPhotoIv = (ImageView) view.findViewById(R.id.news_summary_photo_iv);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(mActivity, newsSummaryPhotoIv, Constants.TRANSITION_ANIMATION_NEWS_PHOTOS);
            startActivity(intent, options.toBundle());
        } else {
/*            ActivityOptionsCompat.makeCustomAnimation(this,
                    R.anim.slide_bottom_in, R.anim.slide_bottom_out);
            这个我感觉没什么用处，类似于
            overridePendingTransition(R.anim.slide_bottom_in, android.R.anim.fade_out);*/

/*            ActivityOptionsCompat.makeThumbnailScaleUpAnimation(source, thumbnail, startX, startY)
            这个方法可以用于4.x上，是将一个小块的Bitmpat进行拉伸的动画。*/

            //让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity(mActivity, intent, options.toBundle());
        }
    }

    @Override
    public void onRefresh() {
        mNewsListPresenter.refreshData();
    }

    @OnClick(R.id.empty_view)
    public void onClick() {
        mSwipeRefreshLayout.setRefreshing(true);
        mNewsListPresenter.refreshData();
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}

