package com.demo.zk.qnews.common;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ClassName:com.demo.zk.qnews.common
 * Author: zk<p>.
 * time: 2016/10/3 10:47.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class HostType {
    /**
     * 多少种Host类型
     */
    public static final int TYPE_COUNT = 4;

    /**
     * 网易新闻视频的host
     */
    public static final int NETEASE_NEWS_VIDEO = 1;

    /**
     * 新浪图片的host
     */
    public static final int GANK_GIRL_PHOTO = 2;

    /**
     * 新闻详情html图片的host
     */
    public static final int NEWS_DETAIL_HTML_PHOTO = 3;

    /**
     * 查询天气的host
     */
    public static final int WEATHER_CITY = 4;

    /**
     * 替代枚举的方案，使用IntDef保证类型安全
     */
    @IntDef({NETEASE_NEWS_VIDEO, GANK_GIRL_PHOTO, NEWS_DETAIL_HTML_PHOTO,WEATHER_CITY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HostTypeChecker {

    }

}
