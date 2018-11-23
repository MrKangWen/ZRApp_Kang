package com.zhaorou.zrapplication.base;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;


import com.zhaorou.zrapplication.R;
import com.zhaorou.zrapplication.constants.ZRDConstants;
import com.zhaorou.zrapplication.utils.AccessibilityUtils;
import com.zhaorou.zrapplication.utils.ActivityController;
import com.zhaorou.zrapplication.utils.AssistantService;
import com.zhaorou.zrapplication.utils.SPreferenceUtil;


public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_base);

        ActivityController.addActivity(this);
        initActionBar();
    }


    protected void setTitle(String title) {
        TextView titleView = findViewById(R.id.title_layout_title_tv);
        titleView.setText(title);
        findViewById(R.id.btn_left_layout_title_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityController.setCurrentActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    protected String getToken() {

        String token = SPreferenceUtil.getString(getApplicationContext(), ZRDConstants.SPreferenceKey.SP_LOGIN_TOKEN, "");

        return token;
    }

    protected boolean isLogin() {
        return !TextUtils.isEmpty(getToken());
    }


    /**
     * 是否开始辅助
     *
     * @return
     */
    public boolean isOpenService() {
        try {
            return AccessibilityUtils.isAccessibilitySettingsOn(AssistantService.class.getName(), this);
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 是否 6.7.3 或者以上
     * 6.7.3不支持多图
     *
     * @return
     */
    public boolean isWeChat6_7_3() {
        try {
            PackageInfo packageInfo = getApplication().getPackageManager().getPackageInfo("com.tencent.mm", 128);
            //1340 6.7.2

            Toast.makeText(this, "版本号：" + packageInfo.versionCode, Toast.LENGTH_LONG).show();
            int weChat6_7_2 = 1340;
            if (packageInfo.versionCode > weChat6_7_2) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
