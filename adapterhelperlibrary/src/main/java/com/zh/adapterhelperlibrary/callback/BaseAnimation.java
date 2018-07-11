package com.zh.adapterhelperlibrary.callback;

import android.animation.Animator;
import android.view.View;
import android.view.animation.Animation;

import java.util.List;

/**
 * @describe: 渐变的动画
 * @author: Z H
 * @date: 2018/7/11 13:31
 * @version: ${version}
 */
public interface BaseAnimation {

    /**
     * get animation of View
     *
     * @param view rv的itemView
     * @return List<Animator> view的动画集合
     */
    List<Animator> getAnimation(View view);
}
