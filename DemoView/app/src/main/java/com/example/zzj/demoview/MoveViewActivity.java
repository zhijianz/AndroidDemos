package com.example.zzj.demoview;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.zzj.demoview.views.CoordinateViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoveViewActivity extends AppCompatActivity {

    @Bind(R.id.cl_container)
    CoordinateViewGroup mContainer;
    @Bind(R.id.v_sub)
    View mSubView;
    @Bind(R.id.s_operate_sub)
    Switch mSubviewSwitch;

    private static final String TAG = "Translation";

    public static void navigateFrom(Context context){
        Intent intent = new Intent(context, MoveViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_view);
        ButterKnife.bind(this);
    }

    private void printLog() {
        Log.i(TAG, "=== printLog start ===");
        String log = "left = %d, top = %d, x = %f, y = %f, translateX = %f, translateY = %f, mScrollX = %d, mScrollY = %d";
        Log.i(TAG, "Container: " + String.format(log, mContainer.getLeft(), mContainer.getTop(), mContainer.getX(), mContainer.getY(), mContainer.getTranslationX(), mContainer.getTranslationY(), mContainer.getScrollX(), mContainer.getScrollY()));
        Log.i(TAG, "SubView: " + String.format(log, mSubView.getLeft(), mSubView.getTop(), mSubView.getX(), mSubView.getY(), mSubView.getTranslationX(), mSubView.getTranslationY(), mSubView.getScrollX(), mSubView.getScrollY()));
        Log.i(TAG, "### printLog end ###");
    }

    @OnClick(R.id.btn_scroll_left)
    void onScrollLeftClick() {
        getOperationView().scrollBy(10, 0);
        printLog();
    }

    @OnClick(R.id.btn_scroll_right)
    void onScrollRightClick() {
        getOperationView().scrollBy(-10, 0);
        printLog();
    }

    @OnClick(R.id.btn_translate_left)
    void onTranslateLeftClick() {
        View view = getOperationView();
        view.setTranslationX(view.getTranslationX() - 10);
        printLog();
    }

    @OnClick(R.id.btn_translate_right)
    void onTranslateRightClick() {
        View view = getOperationView();
        view.setTranslationX(view.getTranslationX() + 10);
        printLog();
    }

    @OnClick(R.id.btn_x_left)
    void onXLeftBtnClick(){
        View view = getOperationView();
        view.setX(view.getX() - 10);
        printLog();
    }

    @OnClick(R.id.btn_x_right)
    void onXRightBtnClick(){
        View view = getOperationView();
        view.setX(view.getX() + 10);
        printLog();
    }

    @OnClick(R.id.btn_layout_left)
    void onLayoutLeftClick() {
        View view = getOperationView();
        view.setLeft(view.getLeft() - 10);
        view.setRight(view.getRight() - 10);
        view.postInvalidate();
        printLog();
    }

    @OnClick(R.id.btn_layout_right)
    void onLayoutRightClick() {
        View view = getOperationView();
        view.setLeft(view.getLeft() + 10);
        view.setRight(view.getRight() + 10);
        view.postInvalidate();
        printLog();
    }

    @OnClick(R.id.btn_request_left)
    void onRequestLeftClick() {
        View view = getOperationView();
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.leftMargin -= 10;
        lp.rightMargin -= 10;
        view.requestLayout();
        printLog();
    }

    @OnClick(R.id.btn_request_right)
    void onRequestRightClick() {
        View view = getOperationView();
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.leftMargin += 10;
        lp.rightMargin += 10;
        view.requestLayout();
        printLog();
    }

    @OnClick(R.id.v_sub)
    void onSubViewClick() {
        Toast.makeText(this, "onSubViewClick", Toast.LENGTH_SHORT).show();
    }

    private View getOperationView() {
        return mSubviewSwitch.isChecked() ? mSubView : mContainer;
    }
}
