package com.zh.rvhelper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.zh.rvhelper.R;
import com.zh.rvhelper.adapter.GroupAdapter;
import com.zh.rvhelper.entity.ContactEntity;
import com.zh.rvhelper.util.ContactComparator;
import com.zh.rvhelper.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/2/8 16:20</p>
 * <p>包名         com.zh.rvhelper.activity</p>
 * <p>描述         TODO </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public class GroupActivity extends Activity {

    private RecyclerView mRecyclerView;
    private MyGroupAdapter mGroupAdapter;
    private List<String> mPYList;
    private List<String> mNameList;
    private List<ContactEntity> resultList;


     @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mPYList = new ArrayList<>();
        mNameList = new ArrayList<>();
        resultList = new ArrayList<>();

        String[] contactNames = new String[]{"张三丰", "郭靖", "黄蓉", "黄老邪", "赵敏",
                "123", "天山童姥", "任我行", "于万亭", "陈家洛", "韦小宝", "$6", "穆人清", "陈圆圆", "郭芙",
                "郭襄", "穆念慈", "东方不败", "梅超风", "林平之", "林远图", "灭绝师太", "段誉", "鸠摩智"};
        mNameList = Arrays.asList(contactNames);

        Map<String, String> map = new HashMap<>();
        List<String> pYNameList = new ArrayList<>();
        for (int i = 0; i < mNameList.size(); i++) {
            String pinyin = Util.getPingYin(mNameList.get(i));
            pYNameList.add(pinyin);
            map.put(pinyin, mNameList.get(i));
        }

        Collections.sort(pYNameList, new ContactComparator());

        for (int i = 0; i < pYNameList.size(); i++) {
            String name = pYNameList.get(i);
            String character = (name.charAt(0) + "").toUpperCase(Locale.ENGLISH);
            if (!mPYList.contains(character)) {
                if (character.hashCode() >= "A".hashCode() && character.hashCode() <= "Z".hashCode()) {  // 是字母
                    mPYList.add(character);
                    resultList.add(new ContactEntity(character, ContactEntity.ITEM_TYPE_HEAD));
                } else {
                    if (!mPYList.contains("#")) {
                        mPYList.add("#");
                        resultList.add(new ContactEntity("#", ContactEntity.ITEM_TYPE_HEAD));
                    }
                }
            }
            resultList.add(new ContactEntity(map.get(name), ContactEntity.ITEM_TYPE_INFO));
        }
        for (ContactEntity contactEntity : resultList) {
            Log.i("GroupActivity", "resultList == " + contactEntity.name + "---type = " + contactEntity.type);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mGroupAdapter = new GroupAdapter(this, resultList);
        mGroupAdapter = new MyGroupAdapter(resultList);
        mRecyclerView.setAdapter(mGroupAdapter);
    }
}
