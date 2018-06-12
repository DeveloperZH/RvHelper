package com.zh.rvhelper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.zh.rvhelper.R;
import com.zh.rvhelper.adapter.StaggeredAdapter;
import com.zh.rvhelper.entity.StaggerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/6/12 10:39</p>
 * <p>包名         com.zh.rvhelper.activity</p>
 * <p>描述         瀑布流 </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public class StaggeredActivity extends Activity {

    private RecyclerView mRecycleView;
    private List<StaggerBean> mStaggerList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagger);
        mRecycleView = findViewById(R.id.mRecycleView);

        mStaggerList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            StaggerBean staggerBean = new StaggerBean(R.mipmap.ic_launcher, "我是" + i);
            mStaggerList.add(staggerBean);
        }

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(staggeredGridLayoutManager);
        StaggeredAdapter mStaggeredAdapter = new StaggeredAdapter(mStaggerList);
        mRecycleView.setAdapter(mStaggeredAdapter);
    }
}
