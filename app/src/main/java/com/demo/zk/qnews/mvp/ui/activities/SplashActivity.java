package com.demo.zk.qnews.mvp.ui.activities;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.demo.zk.qnews.R;
import com.socks.library.KLog;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * ClassName:com.demo.zk.qnews.mvp.ui.activities
 * Author: zk<p>.
 * time: 2016/10/3 10:04.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.logo_outer_iv)
    ImageView mLogoOuterIv;
    @BindView(R.id.logo_inner_iv)
    ImageView mLogoInnerIv;
    @BindView(R.id.app_name_tv)
    TextView mAppNameTv;

    boolean isShowingRubberEffect = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.zoomin, 0);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initAnimation();
    }

    /**
     * startLogoInner1：先显示背景图
     * startLogoOuterAndAppName：动画效果：logo进入背景图框中，AppName显示在logo下
     */
    private void initAnimation() {
        startLogoInner1();
        startLogoOuterAndAppName();
    }
    private void startLogoInner1() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_top_in);
        mLogoInnerIv.startAnimation(animation);
    }
    /**
     * 使用ValueAnimator 属性动画
     */
    private void startLogoOuterAndAppName(){
        //调用ofFloat(int...values)方法创建ValueAnimator对象
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(1000);
        //为目标对象的属性变化设置监听器
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //动画每播放一帧时调用
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 当前的分度值范围为0.0f->1.0f
                // 分度值是动画执行的百分比。区别于AnimatedValue。
                float fraction = animation.getAnimatedFraction();
                KLog.d("fraction: " + fraction);
                if (fraction >= 0.8 && !isShowingRubberEffect) {
                    isShowingRubberEffect = true;
                    startLogoOuter();
                    startShowAppName();
                    finishActivity();
                } else if (fraction >= 0.95) {
                    valueAnimator.cancel();
                    startLogoInner2();
                }

            }
        });
        valueAnimator.start();
    }

    /**
     * com.daimajia.androidanimations.library.YoYo;
     * 实现各种动画效果
     */
    //logo的跳入效果
    private void startLogoOuter() {
        YoYo.with(Techniques.RubberBand).duration(1000).playOn(mLogoOuterIv);
    }
    //AppName的渐进效果
    private void startShowAppName() {
        YoYo.with(Techniques.FadeIn).duration(1000).playOn(mAppNameTv);
    }
    //外部框的弹跳效果
    private void startLogoInner2() {
        YoYo.with(Techniques.Bounce).duration(1000).playOn(mLogoInnerIv);
    }
    private void finishActivity() {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        startActivity(new Intent(SplashActivity.this, NewsActivity.class));
                        overridePendingTransition(0, android.R.anim.fade_out);
                        finish();
                    }
                });
    }
}
