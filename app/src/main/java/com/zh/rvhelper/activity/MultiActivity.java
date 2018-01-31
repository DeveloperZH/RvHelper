package com.zh.rvhelper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.zh.adapterhelperlibrary.callback.OnItemClickListener;
import com.zh.rvhelper.R;
import com.zh.rvhelper.adapter.MyMultiAdapter;
import com.zh.rvhelper.entity.DataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/1/25 16:58</p>
 * <p>包名         com.zh.rvadapterhelper</p>
 * <p>描述         TODO </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public class MultiActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<DataModel> modelList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        mRecyclerView = findViewById(R.id.mRecyclerView);

        initData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
         MyMultiAdapter adapter = new MyMultiAdapter(modelList);
        View headView= LayoutInflater.from(this).inflate(R.layout.head_view,null);
        adapter.addHeadView(headView);
        View footView = LayoutInflater.from(this).inflate(R.layout.foot_view,null);
        adapter.addFooterView(footView);
        mRecyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClick(View view, int position) {
                Toast.makeText(MultiActivity.this, "itemClick = " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 50; i++) {
            DataModel dataModel = new DataModel();
            if (i >= 0 && i < 10) {
                dataModel.type = DataModel.TYPE_ONE;
            } else if (i >= 10 && i < 20) {
                dataModel.type = DataModel.TYPE_TWO;
            } else if (i >= 20 && i < 30) {
                dataModel.type = DataModel.TYPE_THREE;
            } else if (i >=30 && i<40){
                dataModel.type = DataModel.TYPE_ONE;
            }else {
                dataModel.type = DataModel.TYPE_THREE;
            }
            dataModel.name = "name " + i;
            dataModel.age = i;
            modelList.add(dataModel);
        }
    }
}
