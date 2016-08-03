package com.example.zzj.demomaterialdesign;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabSelectedListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CoordinatorLayout mContainer;
    private BottomBar mBottomBar;
    private BottomSheetBehavior mBottomSheetBehavior;
    private View mBottomSheetView;
    private CustomBottomSheetFragment mBottomSheetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContainer = (CoordinatorLayout) findViewById(R.id.cl_container);

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.menu_bottom_bar, new OnMenuTabSelectedListener() {
            @Override
            public void onMenuItemSelected(@IdRes int menuItemId) {
                switch (menuItemId){
                    case R.id.item_location:
                        Snackbar.make(mContainer, "Location Selected", Snackbar.LENGTH_LONG).show();
                        break;
                    case R.id.item_recent:
                        Snackbar.make(mContainer, "Recent Selected", Snackbar.LENGTH_LONG).show();
                        break;
                    case R.id.item_favorite:
                        Snackbar.make(mContainer, "Favorite Selected", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        mBottomSheetView = findViewById(R.id.nsv_bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheetView);
        findViewById(R.id.btn_bottom_sheet).setOnClickListener(this);
        findViewById(R.id.btn_bottom_sheet_fragment).setOnClickListener(this);
    }

    public void setBadge(int position, String color, int count){
        BottomBarBadge mBadge = mBottomBar.makeBadgeForTabAt(position, color, count);
        mBadge.setAnimationDuration(200);
        mBadge.setAutoShowAfterUnSelection(true);
        mBadge.setAutoHideOnSelection(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mBottomBar.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bottom_sheet:
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            case R.id.btn_bottom_sheet_fragment:
                if (mBottomSheetFragment == null){
                    mBottomSheetFragment = new CustomBottomSheetFragment();
                }
                mBottomSheetFragment.show(getSupportFragmentManager(), "");
        }
    }
}
