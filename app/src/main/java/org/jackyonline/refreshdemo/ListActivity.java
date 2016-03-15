package org.jackyonline.refreshdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacky on 2016/3/13.
 */
public class ListActivity extends FragmentActivity implements RefreshLayout.OnRefreshListener,RefreshLayout.OnLoadMoreListener {
    ListView listView;
    RefreshLayout refreshLayout;
    List<String> array = new ArrayList<>();
    ArrayAdapter<String> adapter;
    int i=0;
    int j=0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == 0) {
                array.add(0, "新刷新出来的数据：" + (++i));
                adapter.notifyDataSetChanged();
                refreshLayout.refreshComplete();
            }else{
                array.add(array.size(), "加载更多的数据：" + (++j));
                adapter.notifyDataSetChanged();
                refreshLayout.loadMoreComplete();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        for(int i=0; i<20; i++) {
            array.add("数据来咯："+i);
        }

        listView = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);

        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
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
