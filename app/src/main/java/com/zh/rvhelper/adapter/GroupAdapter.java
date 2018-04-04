package com.zh.rvhelper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zh.rvhelper.R;
import com.zh.rvhelper.entity.ContactEntity;

import java.util.List;

/**
 * <p>公司名       tsingning</p>
 * <p>创建者       Z H</p>
 * <p>创建时间     2018/2/8 15:45</p>
 * <p>包名         com.zh.rvhelper.activity</p>
 * <p>描述         TODO </p>
 * <p>svn版本      $Revision$ </p>
 * <p>更新者       $Author$</p>
 * <p>更新时间     $Date$</p>
 */
public class GroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ContactEntity> resultList;

    public GroupAdapter(Context context, List<ContactEntity> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ContactEntity.ITEM_TYPE_INFO) {
            View head_view = LayoutInflater.from(context).inflate(R.layout.group_info, parent, false);
            Log.i("onCreateViewHolder","head_view");
            return new ContactHolder(head_view);
        } else {
            View info_view = LayoutInflater.from(context).inflate(R.layout.group_head, parent, false);
            Log.i("onCreateViewHolder","info_view");
            return new CharacterHolder(info_view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CharacterHolder) {
            ((CharacterHolder) holder).tv_head.setText(resultList.get(position).name);
        } else if (holder instanceof ContactHolder) {
            ((ContactHolder) holder).tv_group_info.setText(resultList.get(position).name);
        }
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        return resultList.get(position).type;
    }


    public class CharacterHolder extends RecyclerView.ViewHolder {
        TextView tv_head;

        CharacterHolder(View view) {
            super(view);

            tv_head = (TextView) view.findViewById(R.id.tv_head);
        }
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        TextView tv_group_info;

        ContactHolder(View view) {
            super(view);
            tv_group_info = (TextView) view.findViewById(R.id.tv_group_info);
        }
    }
}
