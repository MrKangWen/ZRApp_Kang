package com.zhaorou.zhuanquanapp.user.msg;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhaorou.zhuanquanapp.R;
import com.zhaorou.zhuanquanapp.base.BaseFragment;
import com.zhaorou.zhuanquanapp.home.api.HomeApi;
import com.zhaorou.zhuanquanapp.network.HttpRequestUtil;
import com.zhaorou.zhuanquanapp.user.model.SystemMsgModel;
import com.zhaorou.zhuanquanapp.widget.recyclerview.BaseListBindDataFragment;
import com.zhaorou.zhuanquanapp.widget.recyclerview.CombinationViewHolder;
import com.zhaorou.zhuanquanapp.widget.recyclerview.ListBindDataHelper;
import com.zhaorou.zhuanquanapp.widget.recyclerview.ListBindDataInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemMsgFragment extends BaseListBindDataFragment<SystemMsgModel, SystemMsgModel.DataBean.ListBean> {


    public SystemMsgFragment() {
        // Required empty public constructor
    }

    @Override
    public void initData() {
        super.initData();
        getHelper().setNeedLoadMore(false);
    }


    @Override
    public int getAdapterLayoutId() {
        return R.layout.item_msg_system;
    }

    @Override
    public List<SystemMsgModel.DataBean.ListBean> getAdapterList(SystemMsgModel result) {
        return result.getData().getList();
    }

    @Override
    public void bindData(CombinationViewHolder holder, final SystemMsgModel.DataBean.ListBean t, int position) {

        holder.setText(R.id.title, t.getTitle());
        holder.setText(R.id.date, t.getPublish_time());

        holder.getView(R.id.msgSystemRl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(t.getTitle(),t.getContent());
            }
        });
    }


    private void showDialog(String title, String msg) {


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    @Override
    public Call<SystemMsgModel> getCall(Map<String, Object> map) {
        return HttpRequestUtil.getRetrofitService(HomeApi.class).getSystemMsgList(getToken());
    }
}
