package com.zh.rvhelper.activity;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.zh.adapterhelperlibrary.BaseMultiAdapter;
import com.zh.adapterhelperlibrary.BaseViewHolder;
import com.zh.rvhelper.R;
import com.zh.rvhelper.entity.ContactEntity;

import java.util.List;

/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/2/10 10:49</p>
 * <p>包名         com.zh.rvhelper.activity</p>
 * <p>描述         TODO </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public class MyGroupAdapter extends BaseMultiAdapter<ContactEntity, BaseViewHolder> {

    public MyGroupAdapter(@Nullable List<ContactEntity> data) {
        super(data);
        bindTypeToView(ContactEntity.ITEM_TYPE_HEAD, R.layout.group_head);
        bindTypeToView(ContactEntity.ITEM_TYPE_INFO, R.layout.group_info);
    }

    @Override
    public void convert(BaseViewHolder helper, ContactEntity item) {
        int viewType = helper.getItemViewType();
        switch (viewType){
            case ContactEntity.ITEM_TYPE_HEAD:
                TextView tv_head = helper.getView(R.id.tv_head);
                tv_head.setText(item.name);
                break;
            case ContactEntity.ITEM_TYPE_INFO:
                TextView tv_group_info = helper.getView(R.id.tv_group_info);
                tv_group_info.setText(item.name);
                break;
        }
    }
}
