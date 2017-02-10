package com.example.zzj.demorecyclerview.common.view.stickyheader;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/2/6.
 */

public abstract class IStickyAdapter extends RecyclerView.Adapter {
    abstract int getFirstVisibleStickyHeaderPos(int offset);
    abstract View getStickyHeaderByPos(int position);
}
