package com.zh.rvhelper.util.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * @describe: ${todo}
 * @author: Z H
 * @date: 2018/7/26 19:31
 * @pkgName: com.zh.rvhelper.util.ItemDecoration
 * @version: ${version}
 */
public class CommonDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;

    public CommonDecoration() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    //这里的Rect指的是 整个itemView
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + 2;
            Log.i("onDraw", left + "---" + top + "---" + right + "---" + bottom);
            c.drawLine(left, top, right, top, mPaint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int pos = parent.getChildAdapterPosition(child);
            boolean isLeft = pos % 2 == 0;
            if (isLeft) {
                float left = child.getLeft();
                float right = left + 10;
                float top = child.getTop();
                float bottom = child.getBottom();
                c.drawRect(left, top, right, bottom, mPaint);
            } else {
                float right = child.getRight();
                float left = right - 10;
                float top = child.getTop();
                float bottom = child.getBottom();
                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }


}
