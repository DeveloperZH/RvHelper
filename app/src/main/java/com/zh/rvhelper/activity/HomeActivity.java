package com.zh.rvhelper.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.zh.rvhelper.R;


/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/1/26 11:18</p>
 * <p>包名         com.zh.rvadapterhelper</p>
 * <p>描述         TODO </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public class HomeActivity extends Activity {

    private TextView tv_common, tv_multi,tv_group,tv_drag;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tv_common = findViewById(R.id.tv_common);
        tv_multi = findViewById(R.id.tv_multi);
        tv_group = findViewById(R.id.tv_group);
        tv_drag = findViewById(R.id.tv_drag);
        bindEvent();
    }

    private void bindEvent() {

        tv_common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HomeActivity.this, CommonActivity.class);
                startActivity(intent);
            }
        });

        tv_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this,MultiActivity.class);
              startActivity(intent);
            }
        });

        tv_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HomeActivity.this,GroupActivity.class);
                startActivity(intent);
            }
        });

        tv_drag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HomeActivity.this,DragActivity.class);
                startActivity(intent);
            }
        });
    }
}
