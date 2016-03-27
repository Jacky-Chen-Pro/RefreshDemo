package org.jackyonline.refreshdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by Jacky on 2016/3/13.
 */
public class ChooseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    public void goTextView(View view) {
        startActivity(new Intent(this,TextActivity.class));
    }

    public void goListView(View view) {
        startActivity(new Intent(this,ListActivity.class));
    }

    public void goWebView(View view) {
        startActivity(new Intent(this,WebViewActivity.class));
    }

    public void goRecyclerView(View view) {
        startActivity(new Intent(this,RecyclerViewActivity.class));
    }
}
