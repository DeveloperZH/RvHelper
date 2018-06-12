package com.zh.adapterhelperlibrary.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.zh.adapterhelperlibrary.callback.BaseAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/6/12 14:38</p>
 * <p>包名         com.zh.adapterhelperlibrary.animation</p>
 * <p>描述         TODO </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public class EnterLeftAnimation implements BaseAnimation {

    @Override
    public List<Animator> getAnimation(View view) {
        List<Animator> animatorList = new ArrayList<>();
        animatorList.add( ObjectAnimator.ofFloat(view, "translationX", -view.getRootView().getWidth(), 0));
        return animatorList;
    }
}
