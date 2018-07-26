package com.zh.rvhelper.util.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @describe: ${todo}
 * @author: Z H
 * @date: 2018/7/26 19:34
 * @pkgName: com.zh.rvhelper.util.ItemDecoration
 * @version: ${version}
 */
public class SectionDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;
    private Paint mTextPaint;

    public SectionDecoration() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(40);
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(40);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = 100;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            int top = childView.getTop() - 100;
            int bottom = childView.getTop();
            c.drawRect(left, top, right, bottom, mPaint);
            c.drawText("cur " + i, right / 2, childView.getTop() - 50, mTextPaint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
