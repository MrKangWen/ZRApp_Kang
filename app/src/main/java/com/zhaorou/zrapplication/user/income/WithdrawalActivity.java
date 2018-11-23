package com.zhaorou.zrapplication.user.income;

import android.os.Bundle;

import com.zhaorou.zrapplication.R;
import com.zhaorou.zrapplication.base.BaseActivity;

public class WithdrawalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        initView();
    }

    private void initView() {

        setTitle("提现");

    }
}
