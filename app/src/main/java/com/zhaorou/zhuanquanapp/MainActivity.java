package com.zhaorou.zhuanquanapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhaorou.zhuanquanapp.base.BaseActivity;
import com.zhaorou.zhuanquanapp.base.BaseApplication;
import com.zhaorou.zhuanquanapp.constants.ZRDConstants;
import com.zhaorou.zhuanquanapp.eventbus.MessageEvent;
import com.zhaorou.zhuanquanapp.home.HomeFragment;
import com.zhaorou.zhuanquanapp.home.HomeJxFragment;
import com.zhaorou.zhuanquanapp.home.rd.RdFragment;
import com.zhaorou.zhuanquanapp.home.api.HomeApi;
import com.zhaorou.zhuanquanapp.home.model.AppUpdateModel;
import com.zhaorou.zhuanquanapp.network.HttpRequestUtil;
import com.zhaorou.zhuanquanapp.network.retrofit.AbsZCallback;
import com.zhaorou.zhuanquanapp.network.update.UpdateAppService;
import com.zhaorou.zhuanquanapp.user.UserFragment;
import com.zhaorou.zhuanquanapp.user.model.UserInfoModel;
import com.zhaorou.zhuanquanapp.user.model.UserMessageEvent;
import com.zhaorou.zhuanquanapp.utils.AccessibilityUtils;
import com.zhaorou.zhuanquanapp.utils.AssistantService;
import com.zhaorou.zhuanquanapp.utils.GsonHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.activity_main_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.activity_main_tab_home_iv)
    ImageView mTabHomeIv;
    @BindView(R.id.activity_main_tab_home_tv)
    TextView mTabHomeTv;
    @BindView(R.id.activity_main_tab_user_iv)
    ImageView mTabUserIv;
    @BindView(R.id.activity_main_tab_user_tv)
    TextView mTabUserTv;

    @BindView(R.id.activity_main_tab_rd_iv)
    ImageView mTabRdIv;
    @BindView(R.id.activity_main_tab_rd_tv)
    TextView mTabRdTv;

    @BindView(R.id.mainMsgTv)
    TextView mMainMsgTv;
    private VPAdapter mVPAdapter;

    private List<ImageView> mTabIconList;
    private List<TextView> mTabTextList;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTabIconList = new ArrayList<ImageView>() {{
            add(mTabHomeIv);
            add(mTabRdIv);
            add(mTabUserIv);
        }};
        mTabTextList = new ArrayList<TextView>() {{
            add(mTabHomeTv);
            add(mTabRdTv);
            add(mTabUserTv);
        }};
        mFragmentList = new ArrayList<Fragment>() {{
            add(new HomeFragment());
            add(new RdFragment());
            add(new UserFragment());
        }};
        setSelectedTab(mTabHomeIv, mTabHomeTv);
        initViewPager();
        EventBus.getDefault().register(this);
        appUpdate();

        showAccessibility();
        getUserInfo();


        String[] permissons = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        boolean hasPermissions = EasyPermissions.hasPermissions(getApplicationContext(), permissons);
        if (!hasPermissions) {
            EasyPermissions.requestPermissions(this, "需要读取储存权限", 0, permissons);
        }
        //     Log.d("mytest","isWeChat6_7_3:"+isWeChat6_7_3());

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @OnClick({R.id.activity_main_tab_home_iv, R.id.activity_main_tab_home_tv,
            R.id.activity_main_tab_rd_iv, R.id.activity_main_tab_rd_tv,
            R.id.activity_main_tab_user_iv, R.id.activity_main_tab_user_tv})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_tab_home_iv:
            case R.id.activity_main_tab_home_tv:
                setSelectedTab(mTabHomeIv, mTabHomeTv);
                mViewPager.setCurrentItem(0);

                break;
            case R.id.activity_main_tab_rd_iv:
            case R.id.activity_main_tab_rd_tv:
                setSelectedTab(mTabRdIv, mTabRdTv);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.activity_main_tab_user_iv:
            case R.id.activity_main_tab_user_tv:
                setSelectedTab(mTabUserIv, mTabUserTv);
                mViewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MessageEvent<Intent> message = new MessageEvent<>();
        message.setCommand(requestCode + "");
        message.setData(data);
        EventBus.getDefault().post(message);
    }

    private void setSelectedTab(ImageView imageView, TextView textView) {
        for (ImageView view : mTabIconList) {
            if (view == imageView) {
                view.setSelected(true);
            } else {
                view.setSelected(false);
            }
        }
        for (TextView view : mTabTextList) {
            if (view == textView) {
                view.setSelected(true);
            } else {
                view.setSelected(false);
            }
        }
    }

    private void initViewPager() {
        FragmentManager fm = getSupportFragmentManager();
        mVPAdapter = new VPAdapter(fm);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mVPAdapter);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setSelectedTab(mTabIconList.get(position), mTabTextList.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this, "拒绝权限将无法正常使用", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private class VPAdapter extends FragmentPagerAdapter {

        public VPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }


    private void showAccessibility() {
        if (!AccessibilityUtils.isAccessibilitySettingsOn(AssistantService.class.getName(), this)) {


            new AlertDialog.Builder(this).setMessage("打开【设置——>辅助功能/无障碍——>" + getString(R.string.app_name) + "——>开启】开启分享朋友圈自动粘贴文字功能")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AccessibilityUtils.openAccessibility(AssistantService.class.getName(), MainActivity.this);
                        }
                    }).create().show();
        }
    }

    public void appUpdate() {

        HttpRequestUtil.getRetrofitService(HomeApi.class).appUpdate().enqueue(new AbsZCallback<AppUpdateModel>() {
            @Override
            public void onSuccess(Call<AppUpdateModel> call, Response<AppUpdateModel> response) {

                if (response.body().getData() == null) {

                    return;
                }

                AppUpdateModel.DataBean.EntityBean data = response.body().getData().getEntity();

                if (data == null) {

                    return;
                }

                int code = Integer.valueOf(data.getCodeX());
                //int code = Integer.valueOf("3");
                if (BuildConfig.VERSION_CODE < code) {
                    showAppUpdateDialog(data.getUpdate_tip(), data.getDownload_url(), data.getMd5());
                }


            }

            @Override
            public void onFail(Call<AppUpdateModel> call, Throwable t) {

            }
        });

    }

    private void showAppUpdateDialog(String msg, final String apkUrl, final String apkMd5) {
        new AlertDialog.Builder(this)
                .setMessage(msg)
                .setTitle("更新")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), UpdateAppService.class);
                        //wifi下
                        intent.putExtra(UpdateAppService.IsNeedWifiDownloading, false);
                        //是否进行安装
                        intent.putExtra(UpdateAppService.TipsInstall, true);
                        intent.putExtra(UpdateAppService.APP_URL, apkUrl);
                        //md5
                        intent.putExtra(UpdateAppService.MD5_KEY, apkMd5.toUpperCase());

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            startForegroundService(intent);
                        } else {
                            startService(intent);
                        }

                    }
                }).create().show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(UserMessageEvent messageEvent) {

        if (!BuildConfig.isRd) {

            mMainMsgTv.setVisibility(View.GONE);
            return;
        }

        if ("0".equals(messageEvent.getMessageCount()) || "-1".equals(messageEvent.getMessageCount())) {
            mMainMsgTv.setVisibility(View.GONE);
        } else {
            mMainMsgTv.setVisibility(View.VISIBLE);
            mMainMsgTv.setText(messageEvent.getMessageCount());
        }
    }


    private void getUserInfo() {

        if (!isLogin()) {

            return;
        }
        Map<String, String> params = new HashMap<>(2);
        params.put("token", getToken());
        Call<ResponseBody> call = HttpRequestUtil.getRetrofitService().executeGet(ZRDConstants.HttpUrls.GET_USER_INFO, params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null && response.body() != null) {
                    try {
                        String responseStr = response.body().string();
                        JSONObject jsonObj = new JSONObject(responseStr);
                        if (jsonObj.optInt("code") == 200) {
                            UserInfoModel userInfoModel = GsonHelper.fromJson(responseStr, UserInfoModel.class);
                            UserInfoModel.DataBean data = userInfoModel.getData();
                            if (data != null && data.getUser() != null) {
                                UserInfoModel.DataBean.UserBean user = data.getUser();

                                if (user.getUnread_msg_count().equals("0")) {
                                    mMainMsgTv.setVisibility(View.GONE);
                                } else {
                                    mMainMsgTv.setVisibility(View.VISIBLE);
                                    mMainMsgTv.setText(user.getUnread_msg_count());
                                }

                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

}