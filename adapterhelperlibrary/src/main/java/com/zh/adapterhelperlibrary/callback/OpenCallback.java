package com.zh.adapterhelperlibrary.callback;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.zh.adapterhelperlibrary.data.AnimationType;
import com.zh.adapterhelperlibrary.widget.BaseItemDragHelper;

/**
 * @describe: adapter暴露给外部调用的方法
 * @author: Z H
 * @date: 2018/7/25 9:53
 * @pkgName: com.zh.adapterhelperlibrary.callback
 * @version: ${version}
 */
public interface OpenCallback {

    /**
     * 添加HeadView
     *
     * @param view headView
     */
    void addHeadView(View view);

    /**
     * 添加FooterView
     *
     * @param view FooterView
     */
    void addFooterView(View view);

    /**
     * 将adapter和RV绑定
     *
     * @param recyclerView 当前的recycleView
     */
    void attachRecycleView(RecyclerView recyclerView);


    /**
     * 是否开启动画
     * 直接调用该方法  rv上下滑动都会有动画  如果想去掉下拉时候的动画
     * 在调用该方法之前调用 {@link attachRecycleView(recyclerView)}
     *
     * @param isOpenAnimation true 开启 false 关闭
     */
    void setOpenAnimation(boolean isOpenAnimation);

    /**
     * 设置item动画的类型
     *
     * @param animationType {@link AnimationType}
     */
    void setItemAnimation(AnimationType animationType);


    /**
     * 是否开启item的滑动模式
     * 默认开启
     *
     * @param swipeEnabled true or false
     */
    void setItemSwipeEnabled(boolean swipeEnabled);

    /**
     * 是否开启item的长按拖拽模式
     * 默认开启
     *
     * @param dragEnabled true or false
     */
    void setItemLongPressDragEnabled(boolean dragEnabled);

    /**
     * 设置拖拽的回调
     * 当复写该方法 默认支持拖拽 支持滑动
     * 如果需要禁用某功能 调用 {@link setItemSwipeEnabled}
     * 或者{@link setItemLongPressDragEnabled}
     *
     * @param helper BaseItemDragHelper的子类
     */
    void setDragCallback(BaseItemDragHelper helper);
}
