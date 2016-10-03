package com.demo.zk.qnews.mvp.ui.activities;

import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import com.demo.zk.qnews.R;
import com.demo.zk.qnews.mvp.ui.activities.base.BaseActivity;

import butterknife.BindView;

/**
 * ClassName:com.demo.zk.qnews.mvp.ui.activities
 * Author: zk<p>.
 * time: 2016/10/3 15:04.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.msg_tv)
    TextView mMsgTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        mMsgTv.setAutoLinkMask(Linkify.ALL);
        mMsgTv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
