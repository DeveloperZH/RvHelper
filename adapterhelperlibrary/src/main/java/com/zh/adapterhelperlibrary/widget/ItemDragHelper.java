package com.zh.adapterhelperlibrary.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.zh.adapterhelperlibrary.BaseRvAdapter;


/**
 * @describe: ${todo}
 * @author: Z H
 * @date: 2018/7/25 14:0188
 * @pkgName: com.zh.adapterhelperlibrary.widget
 * @version: ${version}
 */
public class ItemDragHelper extends ItemTouchHelper.Callback {

    private String TAG = ItemDragHelper.class.getSimpleName();
    private BaseRvAdapter mBaseAdapter;

    public ItemDragHelper(BaseRvAdapter adapter) {
        this.mBaseAdapter = adapter;
    }

    /**
     * 用于设置是否处理拖拽事件和滑动事件，以及拖拽和滑动操作的方向
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //如果是列表类型的，拖拽只有ItemTouchHelper.UP、ItemTouchHelper.DOWN两个方向
        //如果是网格类型的，拖拽则有UP、DOWN、LEFT、RIGHT四个方向
        //另外，滑动方向列表类型的，有START和END两个方法，如果是网格类型的一般不设置支持滑动操作可以将swipeFlags = 0置为0，表示不支持滑动操作！
        int dragFlags; //拖拽标识
        int swipeFlags; //滑动标识
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }
        swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        //需要调用return makeMovementFlags(dragFlags, swipeFlags);将设置的标志位return回去
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * 如果我们设置了相关的dragFlags ，那么当我们长按item的时候就会进入拖拽并在拖拽过程中不断回调onMove()方法,
     * 我们就在这个方法里获取当前拖拽的item和已经被拖拽到所处位置的item的ViewHolder。
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.i(TAG, "viewHolder --> " + viewHolder.getAdapterPosition() + "--- target-->" + target.getAdapterPosition());
        int fromPos = viewHolder.getAdapterPosition();
        int toPos = target.getAdapterPosition();
        mBaseAdapter.onItemDateChange(fromPos, toPos);
        return true;
    }

    /**
     * 如果我们设置了相关的swipeFlags，那么当我们滑动item的时候就会调用onSwipe()方法，
     * 一般的话在使用LinearLayoutManager的时候，在这个方法里可以删除item，来实现滑动删除！
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i(TAG, "direction == " + direction);
        mBaseAdapter.onItemSwipedDelete(viewHolder.getAdapterPosition());
    }


    /**
     * 在选中item的时候  回调该方法
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        switch (actionState) {
            case ItemTouchHelper.ACTION_STATE_IDLE:
                Log.i(TAG, "闲置状态");
                // TODO: 2018/7/25 这种状态 itemView 为Null 需要在clearView操作
                break;
            case ItemTouchHelper.ACTION_STATE_DRAG:
                Log.i(TAG, "拖拽状态");
                break;
            case ItemTouchHelper.ACTION_STATE_SWIPE:
                Log.i(TAG, "滑动状态");
                break;
            default:
                break;
        }
    }


    /**
     * 当用户操作完毕某个item并且其动画也结束后会调用该方法，
     * 一般我们在该方法内恢复ItemView的初始状态，防止由于复用而产生的显示错乱问题
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        Log.i(TAG, "clearView" + "POS = " + viewHolder.getAdapterPosition());
    }

    /**
     * 是否支持滑动
     * 默认支持 true
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    /**
     * 是否支持长按滑动
     * 默认支持true
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    /**
     * 实现我们自定义的交互规则或者自定义的动画效果
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
