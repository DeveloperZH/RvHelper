package com.zh.adapterhelperlibrary;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * @describe: BaseViewHolder
 *    user有需要可以继承此类实现自己的viewHolder
 * @author: Z H
 * @date: 2018/7/11 13:31
 * @version: ${version}
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {


    private final SparseArray<View> views;

    BaseViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

}
