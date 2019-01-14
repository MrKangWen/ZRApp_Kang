package com.zhaorou.zhuanquanapp.base;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhaorou.zhuanquanapp.R;
import com.zhaorou.zhuanquanapp.constants.ZRDConstants;
import com.zhaorou.zhuanquanapp.network.imp.HttpDialogLoading;
import com.zhaorou.zhuanquanapp.utils.AccessibilityUtils;
import com.zhaorou.zhuanquanapp.utils.AssistantService;
import com.zhaorou.zhuanquanapp.utils.SPreferenceUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements HttpDialogLoading {


    public BaseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    protected Map<String, Object> getTokenMap() {

        String token = SPreferenceUtil.getString(getContext(), ZRDConstants.SPreferenceKey.SP_LOGIN_TOKEN, "");

        Log.d("mytest","token:"+token);
        Map<String, Object> map = new HashMap<>(2);
        map.put("token", token);
        return map;
    }

    protected String getToken() {

        String token = SPreferenceUtil.getString(getContext(), ZRDConstants.SPreferenceKey.SP_LOGIN_TOKEN, "");

        return token;
    }

    protected boolean isLogin() {
        return !TextUtils.isEmpty(getToken());
    }

    abstract public void initData();

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void goToLogin() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTipsDialog(String msg) {

    }

    public boolean startsWithHttp(String url) {
        return url.startsWith("http");
    }


    /**
     * 是否开始辅助
     *
     * @return
     */
    public boolean isOpenService() {
        try {
            return AccessibilityUtils.isAccessibilitySettingsOn(AssistantService.class.getName(), getActivity());
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
            PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo("com.tencent.mm", 128);
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