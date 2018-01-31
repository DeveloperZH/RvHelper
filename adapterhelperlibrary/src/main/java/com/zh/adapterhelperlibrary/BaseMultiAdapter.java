package com.zh.adapterhelperlibrary;

import android.annotation.SuppressLint;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.zh.adapterhelperlibrary.entity.BaseMultiEntity;

import java.util.List;

/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/1/25 16:50</p>
 * <p>包名         com.zh.rvadapterhelper</p>
 * <p>描述         多布局的BaseAdapter  该实体需要实现BaseMultiEntity接口</p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public abstract class BaseMultiAdapter<T extends BaseMultiEntity, K extends BaseViewHolder> extends BaseRvAdapter<T, K> {

    private SparseArray<Integer> layouts;

    public BaseMultiAdapter(@Nullable List<T> data) {
        super(data);
    }

    /**
     * 将type和layout绑定
     */
    @SuppressLint("UseSparseArrays")
    public void bindTypeToView(int type, @LayoutRes int view) {
        if (layouts == null) {
            layouts = new SparseArray<>();
        }
        layouts.put(type, view);
    }

    @SuppressWarnings("unchecked")
    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        K baseViewHolder;
        View itemView = null;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (mHeaderViews.get(viewType) != null) {
            mHeaderViews.get(viewType).setLayoutParams(params);
            baseViewHolder = (K) new BaseViewHolder(mHeaderViews.get(viewType));
        } else if (mFooterViews.get(viewType) != null) {
            mFooterViews.get(viewType).setLayoutParams(params);
            baseViewHolder = (K) new BaseViewHolder(mFooterViews.get(viewType));
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent,false);
            baseViewHolder = (K) new BaseViewHolder(itemView);
        }
        return baseViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeadView(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterView(position)) {
            return mFooterViews.keyAt(position - mData.size() - mHeaderViews.size());
        }
        T t = mData.get(position - mHeaderViews.size());
        if (t != null) {
            return t.getItemType();
        }
        return super.getItemViewType(position);
    }


    private int getLayoutId(int viewType) {
        return layouts.get(viewType);
    }

}
