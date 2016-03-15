package org.jackyonline.refreshdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Jacky on 2016/3/13.
 */
public class TextActivity extends FragmentActivity implements RefreshLayout.OnRefreshListener,RefreshLayout.OnLoadMoreListener {
    private RefreshLayout mRefreshLayout;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == 0) {
                mRefreshLayout.refreshComplete();
            }else{
                mRefreshLayout.loadMoreComplete();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRefreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);

        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
    }



    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

}
