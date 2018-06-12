package com.zh.adapterhelperlibrary.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.zh.adapterhelperlibrary.callback.BaseAnimation;
import com.zh.adapterhelperlibrary.data.BaseConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/6/12 13:24</p>
 * <p>包名         com.zh.adapterhelperlibrary.animation</p>
 * <p>描述         渐变的动画 </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public class AlphaAnimation implements BaseAnimation {

    private float mFromAlpha;
    private float mEndAlpha;

    public AlphaAnimation() {
        this(BaseConstants.DEFAULT_FROM_ALPHA, BaseConstants.DEFAULT_END_ALPHA);
    }

    public AlphaAnimation(float fromAlpha, float endAlpha) {
        this.mFromAlpha = fromAlpha;
        this.mEndAlpha = endAlpha;
    }


    @Override
    public List<Animator> getAnimation(View view) {
        List<Animator> animatorList = new ArrayList<>();
        animatorList.add(ObjectAnimator.ofFloat(view, "alpha", mFromAlpha, mEndAlpha));
        return animatorList;
    }
}
