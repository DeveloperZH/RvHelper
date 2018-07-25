package com.zh.adapterhelperlibrary.callback;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zh.adapterhelperlibrary.data.AnimationType;

/**
 * @describe: ${todo}
 * @author: Z H
 * @date: 2018/7/25 9:53
 * @pkgName: com.zh.adapterhelperlibrary.callback
 * @version: ${version}
 */
public interface OpenCallback {

    /*****************基础功能*******************************/

    void addHeadView(View view);

    void addFooterView(View view);

    /*****************动画相关*******************************/
    void setOpenAnimation(boolean isOpenAnimation);


    void setItemAnimation(AnimationType animationType);
}
