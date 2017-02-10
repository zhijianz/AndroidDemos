package com.example.zzj.demorecyclerview.gank.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.zzj.demorecyclerview.R;
import com.example.zzj.demorecyclerview.common.Image;
import com.example.zzj.demorecyclerview.common.Utils;
import com.example.zzj.demorecyclerview.gank.bean.GankBean;
import com.example.zzj.demorecyclerview.itempool.Item;
import com.example.zzj.demorecyclerview.itempool.ItemEventHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/13.
 */

public class WelfareItem extends Item<GankBean> {
    @BindView(R.id.card_container) FrameLayout container;
    @BindView(R.id.image) ImageView img;

    @NonNull
    @Override
    public View onCreateItemView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_welfare, parent, false);
        ButterKnife.bind(this, root);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) root.getLayoutParams();
        params.width = Utils.getScreenWidth(parent.getContext()) / 3;
        params.height = Utils.getScreenWidth(parent.getContext()) / 3;
        img.getLayoutParams().width = params.width;
        img.getLayoutParams().height = params.height;
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return root;
    }

    @Override
    public void onBindItem(@NonNull RecyclerView.ViewHolder holder, @NonNull GankBean gankBean, ItemEventHandler eventHandler) {
        container.setTag(holder.getPosition());
        Image.loadImage(img, gankBean.url);
    }
}
