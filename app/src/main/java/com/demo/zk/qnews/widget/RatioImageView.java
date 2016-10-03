package com.demo.zk.qnews.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * ClassName:com.demo.zk.qnews.widget
 * Author: zk<p>.
 * time: 2016/10/3 16:35.
 * Function: RatioImageView  比例
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class RatioImageView extends ImageView {

    private int originalWidth = -1;
    private int originalHeight = -1;

    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOriginalSize(int originalWidth, int originalHeight) {
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (originalWidth > 0 && originalHeight > 0) {
            float ratio = (float) originalWidth / (float) originalHeight;

            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            if (width > 0) {
                height = (int) ((float) width / ratio);
            }

            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
