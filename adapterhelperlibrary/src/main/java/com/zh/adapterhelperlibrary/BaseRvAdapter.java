package com.zh.adapterhelperlibrary;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;


import com.zh.adapterhelperlibrary.callback.OnItemDragCallback;
import com.zh.adapterhelperlibrary.callback.OpenCallback;
import com.zh.adapterhelperlibrary.widget.animation.AlphaAnimation;
import com.zh.adapterhelperlibrary.widget.animation.EnterLeftAnimation;
import com.zh.adapterhelperlibrary.widget.animation.EnterRightAnimation;
import com.zh.adapterhelperlibrary.callback.BaseAnimation;
import com.zh.adapterhelperlibrary.callback.OnItemClickListener;
import com.zh.adapterhelperlibrary.callback.OnItemLongClickListener;
import com.zh.adapterhelperlibrary.data.AnimationType;
import com.zh.adapterhelperlibrary.data.BaseConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @describe: RVAdapter的基类  普通列表直接继承该类即可
 * @author: Z H
 * @date: 2018/7/11 13:31
 * @version: ${version}
 */
public abstract class BaseRvAdapter<T, K extends BaseViewHolder> extends RecyclerView.Adapter<K>
        implements OpenCallback,OnItemDragCallback {

    private BaseAnimation mSelectAnimation;
    private BaseAnimation mDefaultAnimation = new EnterLeftAnimation();
    private Interpolator mInterpolator = new LinearInterpolator();
    /**
     * 默认不开启动画
     */
    private boolean isOpenAnimation = false;

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

    BaseRvAdapter(@Nullable List<T> data) {
        this(0, data);
    }


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
    public void onBindViewHolder(final K holder, @SuppressLint("RecyclerView") final int position) {
        if (isHeadView(position) || isFooterView(position)) {
            return;
        }
        if (mData != null) {
            convert(holder, mData.get(position - mHeaderViews.size()), position - mHeaderViews.size());
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


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == BaseConstants.BASE_TYPE_HEADER
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
        } else {
            addItemAnimation(holder);
        }
    }

    @Override
    public void onItemDateChange(int fromPos, int toPos) {
        Log.i("onItemDateChange","'onItemDateChange");
        Collections.swap(mData,fromPos,toPos);
        notifyItemMoved(fromPos,toPos);
    }

    @Override
    public void onItemSwipedDelete(int position) {
        mData.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 处理recycleView的滑动监听
     *
     * @param recyclerView 当前调用adapter的recycleView
     */
    private void doRvScroll(RecyclerView recyclerView) {
        if (null == recyclerView) {
            throw new NullPointerException("请在setAdapter()方法之后调用adapter.attachRecycleView()");
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    isOpenAnimation = true;
                } else if (dy < 0) {
                    isOpenAnimation = false;
                } else {
                    isOpenAnimation = false;
                }
            }
        });
    }

    /***********************************Item动画相关逻辑**********************************************************/


    @Override
    public void setOpenAnimation(boolean isOpenAnimation) {
        this.isOpenAnimation = isOpenAnimation;
    }


    @Override
    public void setItemAnimation(AnimationType animationType) {
        switch (animationType) {
            case ALPHA:
                mSelectAnimation = new AlphaAnimation();
                break;
            case ENTER_LEFT:
                mSelectAnimation = new EnterLeftAnimation();
                break;
            case ENTER_RIGHT:
                mSelectAnimation = new EnterRightAnimation();
                break;
            default:
                mSelectAnimation = new EnterLeftAnimation();
                break;
        }
    }


    private void addItemAnimation(BaseViewHolder holder) {
        if (isOpenAnimation) {
            BaseAnimation animation;
            if (mSelectAnimation == null) {
                animation = mDefaultAnimation;
            } else {
                animation = mSelectAnimation;
            }
            for (Animator animator : animation.getAnimation(holder.itemView)) {
                startAnim(animator);
            }
        }
    }

    private void startAnim(Animator anim) {
        anim.setDuration(BaseConstants.DEFAULT_ANIMATION_TIME).start();
        anim.setInterpolator(mInterpolator);
    }


    /***************************通用方法*********************************************************/

    /**
     * 继承BaseRvAdapter 必须实现该方法
     *
     * @param helper   BaseViewHolder本身或其子类
     * @param item     当前Item的数据集
     * @param position 当前item的position
     */
    protected abstract void convert(K helper, T item, int position);

    @Override
    public void attachRecycleView(RecyclerView recyclerView) {
        doRvScroll(recyclerView);
    }


    @Override
    public void addHeadView(View view) {
        if (mHeaderViews != null && mHeaderViews.size() > 0) {
            for (int i = 0; i < mHeaderViews.size(); i++) {
                if (mHeaderViews.get(i + BaseConstants.BASE_TYPE_HEADER) == view) {
                    return;
                }
            }
        }
        mHeaderViews.put(mHeaderViews.size() + BaseConstants.BASE_TYPE_HEADER, view);
        notifyDataSetChanged();
    }

    @Override
    public void addFooterView(View view) {
        if (mFooterViews != null && mFooterViews.size() > 0) {
            for (int i = 0; i < mFooterViews.size(); i++) {
                if (mFooterViews.get(i + BaseConstants.BASE_TYPE_FOOTER) == view) {
                    return;
                }
            }
        }
        mFooterViews.put(mFooterViews.size() + BaseConstants.BASE_TYPE_FOOTER, view);
        notifyDataSetChanged();
    }


}
