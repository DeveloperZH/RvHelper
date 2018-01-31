package com.zh.rvhelper.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.zh.adapterhelperlibrary.BaseMultiAdapter;
import com.zh.adapterhelperlibrary.BaseViewHolder;
import com.zh.rvhelper.R;
import com.zh.rvhelper.entity.DataModel;

import java.util.List;


/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/1/25 14:48</p>
 * <p>包名         com.zh.rvadapterhelper</p>
 * <p>描述         TODO </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public class MyMultiAdapter extends BaseMultiAdapter<DataModel, BaseViewHolder> {


    public MyMultiAdapter(@Nullable List<DataModel> data) {
        super(data);
        bindTypeToView(DataModel.TYPE_ONE, R.layout.item1);
        bindTypeToView(DataModel.TYPE_TWO,R.layout.item2);
        bindTypeToView(DataModel.TYPE_THREE,R.layout.item3);
    }

    @Override
    public void convert(BaseViewHolder helper, DataModel item) {
        int viewType = helper.getItemViewType();
        switch (viewType) {
            case DataModel.TYPE_ONE:
                TextView tv_name = helper.getView(R.id.tv_name);
                tv_name.setText(item.name);
                break;
            case DataModel.TYPE_TWO:
                TextView tv_age = helper.getView(R.id.tv_age);
                tv_age.setText(item.age + "");
                break;
            case DataModel.TYPE_THREE:
                TextView tv_name1 = helper.getView(R.id.tv_name);
                TextView tv_age1 = helper.getView(R.id.tv_age);
                tv_name1.setText(item.name);
                tv_age1.setText(item.age + "");
                break;
        }
    }
}
