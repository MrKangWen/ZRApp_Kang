package com.zhaorou.zrapplication.user.income;

import android.os.Bundle;
import android.widget.TextView;

import com.zhaorou.zrapplication.R;
import com.zhaorou.zrapplication.base.BaseActivity;
import com.zhaorou.zrapplication.base.BaseModel;
import com.zhaorou.zrapplication.network.HttpRequestUtil;
import com.zhaorou.zrapplication.network.retrofit.AbsZCallback;
import com.zhaorou.zrapplication.user.api.UserApi;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 提现
 * @author kang
 */
public class WithdrawalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        initView();
    }

    TextView withdrawalTvMoney;

    private void initView() {

        setTitle("提现");
        findViewById(R.id.withdrawalTvSubmit).setOnClickListener(v -> userWithdrawal());
        withdrawalTvMoney = findViewById(R.id.withdrawalTvMoney);

        String money = getIntent().getStringExtra("MONEY");
        withdrawalTvMoney.setText(String.format("余额：￥%s", money));
    }


    private void userWithdrawal() {

        Map<String, Object> map = new HashMap<>(1);
        map.put("token", getToken());

        HttpRequestUtil.getRetrofitService(UserApi.class).userWithdrawal(map).enqueue(new AbsZCallback<BaseModel>() {
            @Override
            public void onSuccess(Call<BaseModel> call, Response<BaseModel> response) {
                showToast(response.body().getMsg());
            }

            @Override
            public void onFail(Call<BaseModel> call, Throwable t) {

                showToast(t.getMessage());
            }
        });

    }
}
