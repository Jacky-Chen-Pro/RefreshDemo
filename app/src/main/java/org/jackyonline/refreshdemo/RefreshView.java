package org.jackyonline.refreshdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class RefreshView extends FrameLayout implements CanRefresh {

    private ImageView ivArrow;

    private TextView tvRefresh;

    private ProgressBar progressBar;


    private Animation rotateUp;

    private Animation rotateDown;

    //下拉箭头是否已经旋转
    private boolean rotated = false;

    private CharSequence completeStr = "complete";
    private CharSequence refreshingStr = "refreshing";
    private CharSequence pullStr = "pull";
    private CharSequence releaseStr = "release";

    public RefreshView(Context context) {
        this(context, null);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down);

        View v = LayoutInflater.from(getContext()).inflate(R.layout.layout_classic_refresh, null);
        addView(v);

        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }


    public CharSequence getReleaseStr() {
        return releaseStr;
    }

    public void setReleaseStr(CharSequence releaseStr) {
        this.releaseStr = releaseStr;
    }

    public CharSequence getPullStr() {
        return pullStr;
    }

    public void setPullStr(CharSequence pullStr) {
        this.pullStr = pullStr;
    }

    public CharSequence getRefreshingStr() {
        return refreshingStr;
    }

    public void setRefreshingStr(CharSequence refreshingStr) {
        this.refreshingStr = refreshingStr;
    }

    public CharSequence getCompleteStr() {
        return completeStr;
    }

    public void setCompleteStr(CharSequence completeStr) {
        this.completeStr = completeStr;
    }

    //系统解析完View之后才会调用onFinishInflate方法，所以我们自定义组件时可以onFinishInflate方法中获取指定子View的引用。
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    //刷新布局的重置
    @Override
    public void onReset() {
        rotated = false;

        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }

    @Override
    public void onPrepare() {

    }


    //刷新完成操作
    @Override
    public void onComplete() {
        rotated = false;

        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        tvRefresh.setText(completeStr);
    }

    //手势释放进行刷新操作
    @Override
    public void onRelease() {
        rotated = false;
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText(refreshingStr);
    }

    //下拉高度和头部高度比例
    @Override
    public void onPositionChange(float currentPercent) {
        ivArrow.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        if (currentPercent < 1) {
            //没有达到下拉布局的高度
            if (rotated) {
                ivArrow.clearAnimation();
                ivArrow.startAnimation(rotateDown);
                rotated = false;
            }

            tvRefresh.setText(pullStr);
        } else {
            tvRefresh.setText(releaseStr);
            if (!rotated) {
                //如果箭头还没有旋转，则旋转箭头
                ivArrow.clearAnimation();
                ivArrow.startAnimation(rotateUp);
                rotated = true;
            }

        }
    }

    @Override
    public void setIsHeaderOrFooter(boolean isHead) {
        if (!isHead) {
            //如果不是上方的下拉刷新，箭头应该是朝上的，所以旋转了180度
            ivArrow.setRotation(180);
        }
    }
}
