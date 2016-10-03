package com.demo.zk.qnews.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.socks.library.KLog;

/**
 * ClassName:com.demo.zk.qnews.widget
 * Author: zk<p>.
 * time: 2016/10/3 16:40.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class PhotoViewPager extends ViewPager {
    public PhotoViewPager(Context context) {
        super(context);
    }

    public PhotoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (Exception e) {
            KLog.e(e.toString());
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            KLog.e(ex.toString());
        }
        return false;
    }
}
