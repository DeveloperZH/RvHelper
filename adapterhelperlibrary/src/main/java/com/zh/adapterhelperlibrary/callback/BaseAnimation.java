package com.zh.adapterhelperlibrary.callback;

import android.animation.Animator;
import android.view.View;
import android.view.animation.Animation;

import java.util.List;

/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/6/12 13:22</p>
 * <p>包名         com.zh.adapterhelperlibrary.callback</p>
 * <p>描述         Rv的item动画的基类 </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public interface BaseAnimation {

    List<Animator> getAnimation(View view);
}
