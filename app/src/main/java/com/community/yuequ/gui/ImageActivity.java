package com.community.yuequ.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.community.yuequ.R;
import com.community.yuequ.gui.adapter.VideoListAdapter;
import com.community.yuequ.view.DividerGridItemDecoration;

public class ImageActivity extends AppCompatActivity {
    private RecyclerView recycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        recycleView = ((RecyclerView)findViewById(R.id.recycleView));

        recycleView.setLayoutManager(new GridLayoutManager(this,2));
        recycleView.addItemDecoration(new DividerGridItemDecoration(this));
        recycleView.setAdapter(new VideoListAdapter(this));
    }
}
