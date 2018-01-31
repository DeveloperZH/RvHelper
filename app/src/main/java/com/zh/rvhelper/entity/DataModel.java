package com.zh.rvhelper.entity;


import com.zh.adapterhelperlibrary.entity.BaseMultiEntity;

/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/1/25 16:57</p>
 * <p>包名         com.zh.rvadapterhelper</p>
 * <p>描述         TODO </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public class DataModel implements BaseMultiEntity {

    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;
    public static final int TYPE_THREE = 2;

    public int type;
    public String name;
    public int age;


    @Override
    public int getItemType() {
        return type;
    }
}
