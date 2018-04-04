package com.zh.rvhelper.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.zh.adapterhelperlibrary.BaseRvAdapter;
import com.zh.adapterhelperlibrary.BaseViewHolder;
import com.zh.rvhelper.R;

import java.util.List;

/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/1/24 16:40</p>
 * <p>包名         com.zh.rvadapterhelper</p>
 * <p>描述         TODO </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public class CommonAdapter extends BaseRvAdapter<String, BaseViewHolder> {

    public CommonAdapter(@Nullable List<String> data) {
        super(R.layout.item, data);
    }

    @Override
    public void convert(BaseViewHolder helper, String item, final int position) {
        TextView textView = helper.getView(R.id.tv);
        textView.setText("" + item);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("CommonAdapter",position + "---");
            }
        });
    }
}
