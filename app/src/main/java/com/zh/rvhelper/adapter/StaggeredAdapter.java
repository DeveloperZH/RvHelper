package com.zh.rvhelper.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.zh.adapterhelperlibrary.BaseRvAdapter;
import com.zh.adapterhelperlibrary.BaseViewHolder;
import com.zh.rvhelper.R;
import com.zh.rvhelper.entity.StaggerBean;

import java.util.List;

/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/6/12 10:40</p>
 * <p>包名         com.zh.rvhelper.adapter</p>
 * <p>描述         TODO </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public class StaggeredAdapter extends BaseRvAdapter<StaggerBean, BaseViewHolder> {

    public StaggeredAdapter(@Nullable List<StaggerBean> data) {
        super(R.layout.item_stagger, data);
    }

    @Override
    public void convert(BaseViewHolder helper, StaggerBean item, int position) {
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        TextView tv_name = helper.getView(R.id.tv_name);
        iv_icon.setImageResource(item.imgSrc);
        tv_name.setText(item.imgTitle);
    }
}
