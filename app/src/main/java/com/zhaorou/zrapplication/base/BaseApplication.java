package com.zhaorou.zrapplication.base;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;

import com.facebook.stetho.Stetho;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhaorou.zrapplication.constants.ZRDConstants;
import com.zhaorou.zrapplication.network.HttpRequestUtil;
import com.zhaorou.zrapplication.utils.ApplicationUtils;
import com.zhaorou.zrapplication.utils.SPreferenceUtil;

import cn.jpush.android.api.JPushInterface;

public class BaseApplication extends Application {

    protected static IWXAPI mWxapi;
    protected static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        ApplicationUtils.setApplicationContext(this);
        HttpRequestUtil.init();
        initWXAPI();
        //解决android N（>=24）系统以上分享 路径为file://时的 android.os.FileUriExposedException异常
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        Stetho.initializeWithDefaults(this);
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);


    }

    private void initWXAPI() {
        mWxapi = WXAPIFactory.createWXAPI(this, ZRDConstants.AppIds.WX_APP_ID, true);
        mWxapi.registerApp(ZRDConstants.AppIds.WX_APP_ID);

    }


    public static IWXAPI getWXAPI() {
        return mWxapi;
    }

    public static Application getApplication() {
        return mApplication;
    }
    /**
     * 是否 6.7.3 或者以上
     * 6.7.3不支持多图
     *
     * @return
     */
    public static boolean isWeChat6_7_3() {
        try {
            PackageInfo packageInfo = getApplication().getPackageManager().getPackageInfo("com.tencent.mm", 128);
            //1340 6.7.2
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
