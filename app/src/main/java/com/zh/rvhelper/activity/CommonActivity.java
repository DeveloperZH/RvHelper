package com.zh.rvhelper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


import com.zh.adapterhelperlibrary.callback.OnItemClickListener;
import com.zh.adapterhelperlibrary.callback.OnItemLongClickListener;
import com.zh.adapterhelperlibrary.data.AnimationType;
import com.zh.adapterhelperlibrary.widget.BaseItemDragHelper;
import com.zh.rvhelper.R;
import com.zh.rvhelper.adapter.CommonAdapter;
import com.zh.rvhelper.util.MyDragCallBack;

import java.util.ArrayList;
import java.util.List;

public class CommonActivity extends AppCompatActivity {

    private String TAG = CommonActivity.class.getSimpleName();
    private List<String> mDatas;
    private RecyclerView mRecycleView;
    private CommonAdapter mCommonAdapter;
    private View headView;
    private View footView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatas = new ArrayList<>();
        mRecycleView = findViewById(R.id.mRecycleView);
        initData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mCommonAdapter = new CommonAdapter(mDatas);
        headView = LayoutInflater.from(this).inflate(R.layout.head_view, null);
        mCommonAdapter.addHeadView(headView);
        footView = LayoutInflater.from(this).inflate(R.layout.foot_view, null);
        mCommonAdapter.addFooterView(footView);
        mRecycleView.setAdapter(mCommonAdapter);
        mCommonAdapter.attachRecycleView(mRecycleView);
        mCommonAdapter.setItemAnimation(AnimationType.ENTER_RIGHT);

        headView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCommonAdapter.addHeadView(footView);
            }
        });
        footView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommonAdapter.addFooterView(headView);
            }
        });
        mCommonAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClick(View view, int position) {
                Toast.makeText(CommonActivity.this, "itemClick = " + position, Toast.LENGTH_SHORT).show();
            }
        });

        mCommonAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public void longClick(View view, int position) {
                Toast.makeText(CommonActivity.this, "longClick = " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            mDatas.add("" + i);
        }
    }
}
