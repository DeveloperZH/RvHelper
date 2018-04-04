package com.zh.rvhelper.entity;

import com.zh.adapterhelperlibrary.entity.BaseMultiEntity;

/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/2/8 16:27</p>
 * <p>包名         com.zh.rvhelper.entity</p>
 * <p>描述         TODO </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public class ContactEntity implements BaseMultiEntity{

    public static final int ITEM_TYPE_HEAD = 111111110;
    public static final int ITEM_TYPE_INFO = 222222221;

    public String name;
    public int type;

    public ContactEntity(String name, int type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
