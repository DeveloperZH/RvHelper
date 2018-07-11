package com.zh.adapterhelperlibrary.callback;

import android.view.View;

/**
 * @describe: itemLongClick接口
 * @author: Z H
 * @date: 2018/7/11 13:31
 * @version: ${version}
 */
public interface OnItemLongClickListener {
    /**
     * @param view     rv的itemView
     * @param position 当前item的position
     */
    void longClick(View view, int position);
}
