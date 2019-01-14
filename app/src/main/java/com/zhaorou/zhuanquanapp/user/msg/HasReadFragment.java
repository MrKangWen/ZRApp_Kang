package com.zhaorou.zhuanquanapp.user.msg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhaorou.zhuanquanapp.R;
import com.zhaorou.zhuanquanapp.home.api.HomeApi;
import com.zhaorou.zhuanquanapp.network.HttpRequestUtil;
import com.zhaorou.zhuanquanapp.user.model.HasReadMsgModel;
import com.zhaorou.zhuanquanapp.user.model.SystemMsgModel;
import com.zhaorou.zhuanquanapp.widget.recyclerview.BaseListBindDataFragment;
import com.zhaorou.zhuanquanapp.widget.recyclerview.CombinationViewHolder;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class HasReadFragment extends BaseListBindDataFragment<HasReadMsgModel, HasReadMsgModel.DataBean.ListBean> {


    public HasReadFragment() {
        // Required empty public constructor
    }

    @Override
    public void initData() {
        super.initData();
        getHelper().setNeedLoadMore(false);
    }



    @Override
    public int getAdapterLayoutId() {
        return R.layout.item_msg_has_read;
    }

    @Override
    public List<HasReadMsgModel.DataBean.ListBean> getAdapterList(HasReadMsgModel result) {
        return result.getData().getList();
    }

    @Override
    public void bindData(CombinationViewHolder holder, HasReadMsgModel.DataBean.ListBean t, int position) {

        holder.setText(R.id.title, t.getContent());
        holder.setText(R.id.date, t.getCreate_time());
    }

    @Override
    public Call<HasReadMsgModel> getCall(Map<String, Object> map) {
        return HttpRequestUtil.getRetrofitService(HomeApi.class).getReadList(getToken());
    }
}
