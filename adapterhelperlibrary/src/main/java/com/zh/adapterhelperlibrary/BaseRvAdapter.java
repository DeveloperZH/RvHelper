package com.zh.adapterhelperlibrary;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.zh.adapterhelperlibrary.callback.OnItemClickListener;
import com.zh.adapterhelperlibrary.callback.OnItemLongClickListener;
import com.zh.adapterhelperlibrary.data.Constans;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/1/24 16:41</p>
 * <p>包名         com.zh.rvadapterhelper</p>
 * <p>描述         RVAdapter的基类  普通列表直接继承该类即可</p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public abstract class BaseRvAdapter<T, K extends BaseViewHolder> extends RecyclerView.Adapter<K> {

    List<T> mData;
    private int mLayoutResId;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();


    public BaseRvAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        if (layoutResId != 0) {
            this.mLayoutResId = layoutResId;
        }
    }

    public BaseRvAdapter(@Nullable List<T> data) {
        this(0, data);
    }

    public BaseRvAdapter(@LayoutRes int layoutResId) {
        this(layoutResId, null);
    }

    public abstract void convert(K helper, T item,int position);

    @SuppressWarnings("unchecked")
    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        K baseViewHolder;
        if (mHeaderViews.get(viewType) != null) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mHeaderViews.get(viewType).setLayoutParams(params);
            baseViewHolder = (K) new BaseViewHolder(mHeaderViews.get(viewType));
        } else if (mFooterViews.get(viewType) != null) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mFooterViews.get(viewType).setLayoutParams(params);
            baseViewHolder = (K) new BaseViewHolder(mFooterViews.get(viewType));
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(mLayoutResId, parent, false);
            baseViewHolder = (K) new BaseViewHolder(itemView);
        }
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(final K holder, final int position) {
        if (isHeadView(position) || isFooterView(position)) {
            return;
        }
        if (mData != null) {
            convert(holder, mData.get(position - mHeaderViews.size()),position - mHeaderViews.size());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.itemClick(holder.itemView, position - mHeaderViews.size());
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.longClick(holder.itemView, position - mHeaderViews.size());
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mHeaderViews == null && mFooterViews == null && mData == null) ?
                0 : mHeaderViews.size() + mFooterViews.size() + mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeadView(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterView(position)) {
            return mFooterViews.keyAt(position - mData.size() - mHeaderViews.size());
        }
        return super.getItemViewType(position - mHeaderViews.size());
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    boolean isHeadView(int position) {
        return position < mHeaderViews.size();
    }

    boolean isFooterView(int position) {
        return position >= mHeaderViews.size() + mData.size();
    }

    public void addHeadView(View view) {
        if (mHeaderViews != null && mHeaderViews.size() > 0) {
            for (int i = 0; i < mHeaderViews.size(); i++) {
                if (mHeaderViews.get(i + Constans.BASE_TYPE_HEADER) == view) {
                    return;
                }
            }
        }
        mHeaderViews.put(mHeaderViews.size() + Constans.BASE_TYPE_HEADER, view);
        notifyDataSetChanged();
    }

    public void addFooterView(View view) {
        if (mFooterViews != null && mFooterViews.size() > 0) {
            for (int i = 0; i < mFooterViews.size(); i++) {
                if (mFooterViews.get(i + Constans.BASE_TYPE_FOOTER) == view) {
                    return;
                }
            }
        }
        mFooterViews.put(mFooterViews.size() + Constans.BASE_TYPE_FOOTER, view);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == Constans.BASE_TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow((K) holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }
}
