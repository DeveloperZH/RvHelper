package com.zh.adapterhelperlibrary.widget.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.zh.adapterhelperlibrary.callback.BaseAnimation;
import com.zh.adapterhelperlibrary.data.BaseConstants;

import java.util.ArrayList;
import java.util.List;


/**
 * @describe: 渐变的动画
 * @author: Z H
 * @date: 2018/7/11 13:31
 * @version: ${version}
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
