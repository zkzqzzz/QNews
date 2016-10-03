package com.demo.zk.qnews.common;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ClassName:com.demo.zk.qnews.common
 * Author: zk<p>.
 * time: 2016/10/3 10:48.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class PhotoRequestType {
    public static final int TYPE_SHARE = 1;
    public static final int TYPE_SAVE = 2;
    public static final int TYPE_SET_WALLPAPER = 3;

    @IntDef({TYPE_SHARE, TYPE_SAVE, TYPE_SET_WALLPAPER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PhotoRequestTypeChecker {

    }
}
