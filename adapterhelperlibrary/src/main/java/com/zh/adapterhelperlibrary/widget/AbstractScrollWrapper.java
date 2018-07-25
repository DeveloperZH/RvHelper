package com.zh.adapterhelperlibrary.widget;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * @describe: ${todo}
 * @author: Z H
 * @date: 2018/7/25 9:46
 * @pkgName: com.zh.adapterhelperlibrary.widget
 * @version: ${version}
 */
@SuppressLint("NewApi")
public abstract class AbstractScrollWrapper extends RecyclerView.OnScrollListener {

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        Log.i("AbstractScrollWrapper", "dy ==> " + dy);
    }
}
