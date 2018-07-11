package com.zh.adapterhelperlibrary.callback;

import android.view.View;

/**
 * @describe: itemClick接口
 * @author: Z H
 * @date: 2018/7/11 13:31
 * @version: ${version}
 */
public interface OnItemClickListener {
    /**
     * @param view     rv的itemView
     * @param position 当前item的position
     */
    void itemClick(View view, int position);
}
