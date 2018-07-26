package com.zh.rvhelper.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zh.adapterhelperlibrary.BaseRvAdapter;
import com.zh.adapterhelperlibrary.BaseViewHolder;
import com.zh.adapterhelperlibrary.BaseItemDragHelper;
import com.zh.rvhelper.R;
import com.zh.rvhelper.util.itemdecoration.SectionDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe: ${todo}
 * @author: Z H
 * @date: 2018/7/26 10:56
 * @pkgName: com.zh.rvhelper.activity
 * @version: ${version}
 */
public class DragActivity extends Activity {

    RecyclerView mRecycleView;
    List<String> dataList;
    private Paint mPaint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList = new ArrayList<>();
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mRecycleView = findViewById(R.id.mRecycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(linearLayoutManager);
        DragAdapter dragAdapter = new DragAdapter(getDataList());
        mRecycleView.setAdapter(dragAdapter);
        dragAdapter.setOpenAnimation(true);
        //添加分割线
//        mRecycleView.addItemDecoration(new CommonDecoration());
        mRecycleView.addItemDecoration(new SectionDecoration());

        //添加item拖拽功能
        dragAdapter.attachRecycleView(mRecycleView);
        dragAdapter.setDragCallback(new MyDragHelper(dragAdapter));
        //开启滑动删除功能
        dragAdapter.setItemSwipeEnabled(true);
    }

    private class MyDragHelper extends BaseItemDragHelper {

        public MyDragHelper(BaseRvAdapter adapter) {
            super(adapter);
        }
    }


    private List<String> getDataList() {
        for (int i = 0; i < 100; i++) {
            dataList.add("我是测试数据   " + i);
        }
        return dataList;
    }


    class DragAdapter extends BaseRvAdapter<String, BaseViewHolder> {

        public DragAdapter(@Nullable List<String> data) {
            super(R.layout.item_drag, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item, int position) {
            TextView tvDrag = helper.getView(R.id.tv_drag);
            tvDrag.setText(item);
        }
    }
}
