package org.jackyonline.refreshdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacky on 2016/3/13.
 */
public class RecyclerViewActivity extends FragmentActivity implements RefreshLayout.OnRefreshListener, RefreshLayout.OnLoadMoreListener {
    private RefreshLayout refreshLayout;
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;
    private int mLoadDataPosition = 0;
    private int mRefreshDataPosition = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == 0) {
                mDatas.add(0, "新刷新出来的数据:" + mRefreshDataPosition++ );
                mAdapter.notifyDataSetChanged();
                refreshLayout.refreshComplete();
            }else{
                mDatas.add(mDatas.size(), "加载更多的数据:" + mLoadDataPosition++);
                mAdapter.notifyDataSetChanged();
                refreshLayout.loadMoreComplete();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initData();

        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setOnRefreshListener(this);
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    RecyclerViewActivity.this).inflate(R.layout.item_recyclerview, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
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
