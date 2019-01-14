package com.zhaorou.zhuanquanapp.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhaorou.zhuanquanapp.R;
import com.zhaorou.zhuanquanapp.base.BaseActivity;
import com.zhaorou.zhuanquanapp.base.BaseApplication;
import com.zhaorou.zhuanquanapp.base.BaseModel;
import com.zhaorou.zhuanquanapp.constants.ZRDConstants;
import com.zhaorou.zhuanquanapp.home.dialog.LoadingDialog;
import com.zhaorou.zhuanquanapp.login.LoginActivity;
import com.zhaorou.zhuanquanapp.login.LoginByAccountActivity;
import com.zhaorou.zhuanquanapp.network.HttpRequestUtil;
import com.zhaorou.zhuanquanapp.network.retrofit.AbsZCallback;
import com.zhaorou.zhuanquanapp.user.api.UserApi;
import com.zhaorou.zhuanquanapp.user.model.UserMessageEvent;
import com.zhaorou.zhuanquanapp.utils.ApplicationUtils;
import com.zhaorou.zhuanquanapp.utils.SPreferenceUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.activity_settings_btn_link_taoword)
    ImageView mBtnLinkTao;


    @BindView(R.id.activity_settings_version_info_tv)
    TextView mVersionInfo;

    private String mLinkTao;
    private LoadingDialog mLoadingDialog;
    boolean mIsDiyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        mLoadingDialog = new LoadingDialog(this);
        mLinkTao = SPreferenceUtil.getString(SettingsActivity.this, ZRDConstants.SPreferenceKey.SP_LINK_TAO, "1");
        if (TextUtils.equals(mLinkTao, "2")) {
            mBtnLinkTao.setImageResource(R.drawable.icon_toggle_on);
        } else if (TextUtils.equals(mLinkTao, "1")) {
            mBtnLinkTao.setImageResource(R.drawable.icon_toggle_off);
        }
        String versionName = ApplicationUtils.getVersionName();
        mVersionInfo.setText(versionName);

/*
        mIsDiyText = SPreferenceUtil.getBoolean(SettingsActivity.this, ZRDConstants.SPreferenceKey.SP_DIY_TEXT, false);
        if (mIsDiyText) {
            mSettingsIvDiyText.setImageResource(R.drawable.icon_toggle_on);
        }

        mSettingsIvDiyText.setOnClickListener(v -> {
            mIsDiyText = SPreferenceUtil.getBoolean(SettingsActivity.this, ZRDConstants.SPreferenceKey.SP_DIY_TEXT, false);
            if (mIsDiyText) {
                SPreferenceUtil.put(SettingsActivity.this, ZRDConstants.SPreferenceKey.SP_DIY_TEXT, false);
                mSettingsIvDiyText.setImageResource(R.drawable.icon_toggle_off);

            } else {
                SPreferenceUtil.put(SettingsActivity.this, ZRDConstants.SPreferenceKey.SP_DIY_TEXT, true);
                mSettingsIvDiyText.setImageResource(R.drawable.icon_toggle_on);
            }

        });
*/


        findViewById(R.id.settingsTvDiyText).setOnClickListener(v -> {
            showDiyDialog();
        });
    }

    AlertDialog mAlertDialog;

    private void showDiyDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("自定义文案");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_diy_text, null);

        EditText editText = view.findViewById(R.id.edtText);

        view.findViewById(R.id.diyTextBtnSubmit).setOnClickListener(v -> {
            if (mAlertDialog != null) {
                mAlertDialog.dismiss();
            }

            postDiyTips(editText.getText().toString());

        });
        builder.setView(view);
        mAlertDialog = builder.show();

        //pop_end

    }


    private void postDiyTips(String tips) {

   /*     if (TextUtils.isEmpty(tips)) {
            return;
        }*/

        Map<String, Object> map = new HashMap<>(2);

        map.put("token", getToken());
        map.put("pop_end", tips);

        HttpRequestUtil.getRetrofitService(UserApi.class).postDiyText(map).enqueue(new AbsZCallback<BaseModel>() {
            @Override
            public void onSuccess(Call<BaseModel> call, Response<BaseModel> response) {

                showToast("成功设置！");

                BaseApplication.getApplication().setDiyTips(tips);
            }

            @Override
            public void onFail(Call<BaseModel> call, Throwable t) {
                showToast("设置失败！");
            }
        });

    }


    @OnClick({R.id.activity_settings_layout_title_left_btn_rl, R.id.activity_settings_btn_link_taoword,
            R.id.activity_settings_version_info_ll, R.id.activity_settings_btn_logout})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_settings_layout_title_left_btn_rl:
                finish();
                break;
            case R.id.activity_settings_btn_link_taoword:
                String token = SPreferenceUtil.getString(SettingsActivity.this, ZRDConstants.SPreferenceKey.SP_LOGIN_TOKEN, "");
                if (TextUtils.isEmpty(token)) {
                    Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    mLinkTao = SPreferenceUtil.getString(SettingsActivity.this, ZRDConstants.SPreferenceKey.SP_LINK_TAO, "1");
                    Map<String, String> params = new HashMap<>();
                    if (TextUtils.equals(mLinkTao, "1")) {
                        SPreferenceUtil.put(SettingsActivity.this, ZRDConstants.SPreferenceKey.SP_LINK_TAO, "2");
                        mBtnLinkTao.setImageResource(R.drawable.icon_toggle_on);
                        params.put("tkl_type", "2");
                    } else if (TextUtils.equals(mLinkTao, "2")) {
                        SPreferenceUtil.put(SettingsActivity.this, ZRDConstants.SPreferenceKey.SP_LINK_TAO, "1");
                        mBtnLinkTao.setImageResource(R.drawable.icon_toggle_off);
                        params.put("tkl_type", "1");
                    }
                    params.put("token", token);
                    Call<ResponseBody> call = HttpRequestUtil.getRetrofitService().executePost(ZRDConstants.HttpUrls.UPDATE_TKL_TYPE, params);
                    mLoadingDialog.show();
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response != null && response.body() != null) {
                                try {
                                    String str = response.body().string();
                                    JSONObject jsonObj = new JSONObject(str);
                                    if (jsonObj != null && jsonObj.optInt("code") == 200) {
                                        String data = jsonObj.optString("data");
                                        Toast.makeText(SettingsActivity.this, data, Toast.LENGTH_SHORT).show();
                                    } else {
                                        String data = jsonObj.optString("data");
                                        Toast.makeText(SettingsActivity.this, data, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            mLoadingDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(SettingsActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                            mLoadingDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.activity_settings_version_info_ll:

                break;
            case R.id.activity_settings_btn_logout:
                AlertDialog alertDialog = new AlertDialog.Builder(SettingsActivity.this)
                        .setMessage("是否退出当前账号？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", (dialog, which) -> {

                            EventBus.getDefault().post(new UserMessageEvent("-1"));

                            int sequence = SPreferenceUtil.getInt(getApplicationContext(), ZRDConstants.SPreferenceKey.SP_PUSH_ALIAS, 0);
                            JPushInterface.deleteAlias(getApplicationContext(), sequence);
                            SPreferenceUtil.put(SettingsActivity.this, ZRDConstants.SPreferenceKey.SP_LOGIN_TOKEN, "");
                            Toast.makeText(SettingsActivity.this, "已退出登录", Toast.LENGTH_SHORT).show();
                            finish();
                        }).create();
                alertDialog.show();

                break;
            default:
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
        }
    }
}
