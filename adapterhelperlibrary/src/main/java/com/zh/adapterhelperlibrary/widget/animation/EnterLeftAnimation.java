package com.zh.adapterhelperlibrary.widget.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.zh.adapterhelperlibrary.callback.BaseAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe: 从左边进入的动画
 * @author: Z H
 * @date: 2018/7/11 13:31
 * @version: ${version}
 */
public class EnterLeftAnimation implements BaseAnimation {

    @Override
    public List<Animator> getAnimation(View view) {
        List<Animator> animatorList = new ArrayList<>();
        animatorList.add( ObjectAnimator.ofFloat(view, "translationX", -view.getRootView().getWidth(), 0));
        return animatorList;
    }
}
