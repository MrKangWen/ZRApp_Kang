package com.zhaorou.zrapplication.user.income;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhaorou.zrapplication.R;
import com.zhaorou.zrapplication.base.BaseActivity;
import com.zhaorou.zrapplication.user.order.AllOrderActivity;

public class IncomeViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_view);
        setTitle("收益表");

        initView();
    }

    private void initView() {

        findViewById(R.id.incomeLLAllOrder).setOnClickListener(v -> {

            startActivity(new Intent(IncomeViewActivity.this, AllOrderActivity.class));

        });
        findViewById(R.id.incomeTvWithdrawal).setOnClickListener(v -> {

            startActivity(new Intent(IncomeViewActivity.this, WithdrawalActivity.class));

        });


    }
}
