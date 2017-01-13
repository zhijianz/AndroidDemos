package com.example.zzj.demorecyclerview.gank.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zzj.demorecyclerview.common.Image;
import com.example.zzj.demorecyclerview.common.Utils;
import com.example.zzj.demorecyclerview.gank.bean.GankBean;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/1/13.
 */

public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.WelfareHolder> {
    List<GankBean> mList;

    public WelfareAdapter(List<GankBean> list){
        if (list == null){
            mList = Collections.emptyList();
        }else {
            mList = list;
        }
    }

    public void setData(List<GankBean> data){
        if (data == null){
            mList = Collections.emptyList();
        }else {
            mList = data;
        }
        notifyDataSetChanged();
    }

    @Override
    public WelfareHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        ImageView view = new ImageView(context);
        int width = Utils.getScreenWidth(context) / 3;
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);

        return new WelfareHolder(view);
    }

    @Override
    public void onBindViewHolder(WelfareHolder holder, int position) {
        GankBean data = mList.get(position);
        if (data != null){
            Image.loadImage((ImageView) holder.itemView, data.url);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class WelfareHolder extends RecyclerView.ViewHolder{

        public WelfareHolder(View itemView) {
            super(itemView);
        }
    }
}
