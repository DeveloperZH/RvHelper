package com.zh.adapterhelperlibrary.callback;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zh.adapterhelperlibrary.data.AnimationType;

/**
 * @describe: ${adapter暴露给外部调用的方法}
 * @author: Z H
 * @date: 2018/7/25 9:53
 * @pkgName: com.zh.adapterhelperlibrary.callback
 * @version: ${version}
 */
public interface OpenCallback {

    /*****************基础功能*******************************/

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

    /*****************动画相关*******************************/

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
}
