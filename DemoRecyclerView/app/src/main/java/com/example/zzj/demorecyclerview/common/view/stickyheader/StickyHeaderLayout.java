package com.example.zzj.demorecyclerview.common.view.stickyheader;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.Stack;

import timber.log.Timber;

/**
 * Created by Administrator on 2017/2/6.
 */

public class StickyHeaderLayout extends FrameLayout {
    private RecyclerView mRecycler;
    private IStickyAdapter mStickyAdapter;
    private LinearLayoutManager mLayoutManager;
    private FrameLayout mHeaderContainer;
    private Stack<Integer> mStickyStack = new Stack<>();
    private int mHeaderHeight = -1;

    public StickyHeaderLayout(Context context) {
        super(context);
    }

    public StickyHeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        final View view = getChildAt(0);
        if (!(view instanceof RecyclerView)) {
            throw new RuntimeException("First child in StickyHeaderLayout must be RecyclerView");
        }
        mRecycler = (RecyclerView) view;

        RecyclerView.Adapter adapter = mRecycler.getAdapter();
        if (!(adapter instanceof IStickyAdapter)) {
            throw new RuntimeException("The Adapter must implement IStickyAdapter");
        }
        mStickyAdapter = (IStickyAdapter) adapter;

        RecyclerView.LayoutManager manager = mRecycler.getLayoutManager();
        if (!(manager instanceof LinearLayoutManager)) {
            throw new RuntimeException("Only support LinearLayoutManager");
        }
        mLayoutManager = (LinearLayoutManager) manager;

        mRecycler.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (mStickyStack.isEmpty()){
                    mStickyStack.push(mStickyAdapter.getFirstVisibleStickyHeaderPos(0));
                }
                int firstItemPos = mLayoutManager.findFirstVisibleItemPosition();
                int curHeaderPos = mStickyStack.peek();
                int firstHeaderPos = mStickyAdapter.getFirstVisibleStickyHeaderPos(firstItemPos);
                View firstHeader = mLayoutManager.findViewByPosition(firstHeaderPos);
                if (dy > 0){
                    int nextHeaderPos = mStickyAdapter.getFirstVisibleStickyHeaderPos(firstHeaderPos);
                    View nextHeader = mLayoutManager.findViewByPosition(nextHeaderPos);

                    if (nextHeader != null && nextHeader.getTop() == mHeaderHeight){
                        firstHeader = nextHeader;
                        firstHeaderPos = nextHeaderPos;
                    }
                }

                int top = firstHeader.getTop();
                if (top > 0 && top <= mHeaderHeight){
                    mHeaderContainer.setY(-(mHeaderHeight - top));
                    if (firstHeaderPos == curHeaderPos){
                        mStickyStack.pop();
                        updateStickyHeader(mStickyStack.peek());
                    }
                }else if (top <= 0){
                    mHeaderContainer.setY(0);
                    if (firstHeaderPos != curHeaderPos){
                        mStickyStack.push(firstItemPos);
                        updateStickyHeader(mStickyStack.peek());
                    }
                }
            }
        });

        mHeaderContainer = new FrameLayout(getContext());
        mHeaderContainer.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mHeaderContainer);
    }

    private void updateStickyHeader(int pos){
        View header = mStickyAdapter.getStickyHeaderByPos(pos);
        if (header != null){
            mHeaderContainer.removeAllViews();
            mHeaderContainer.addView(header);
            mHeaderHeight = mHeaderContainer.getHeight();
        }else {
            Timber.e("[updateStickyHeader], get null header for position: %d", pos);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        init();
        super.onLayout(changed, left, top, right, bottom);
    }
}
