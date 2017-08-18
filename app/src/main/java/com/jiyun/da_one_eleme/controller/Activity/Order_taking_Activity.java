package com.jiyun.da_one_eleme.controller.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jiyun.da_one_eleme.R;
import com.jiyun.da_one_eleme.controller.base.BaseActivity;

public class Order_taking_Activity extends BaseActivity implements View.OnClickListener {

    private ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_taking_);
    }

    @Override
    protected int initView() {
        return R.layout.activity_order_taking_;
    }

    @Override
    protected void initListener() {
            imageView2.setOnClickListener(this);
    }

    @Override
    protected void getViewId() {
        imageView2 = (ImageView) findViewById(R.id.imageView2);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
