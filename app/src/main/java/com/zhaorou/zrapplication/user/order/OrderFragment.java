package com.zhaorou.zrapplication.user.order;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhaorou.zrapplication.R;
import com.zhaorou.zrapplication.base.BaseFragment;
import com.zhaorou.zrapplication.home.model.OrderListModel;
import com.zhaorou.zrapplication.network.HttpRequestUtil;
import com.zhaorou.zrapplication.network.retrofit.AbsZCallback;
import com.zhaorou.zrapplication.user.api.UserApi;
import com.zhaorou.zrapplication.widget.recyclerview.BaseListBindDataFragment;
import com.zhaorou.zrapplication.widget.recyclerview.CombinationViewHolder;
import com.zhaorou.zrapplication.widget.viewselector.TextViewSelector;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author kang
 */
public class OrderFragment extends BaseListBindDataFragment<OrderListModel, OrderListModel.DataBean.ListBean> {

    public OrderFragment() {
        // Required empty public constructor
    }


    public static OrderFragment newInstance(String status) {

        Bundle args = new Bundle();
        args.putString("status", status);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getAdapterLayoutId() {
        return R.layout.item_order;
    }

    @Override
    public List<OrderListModel.DataBean.ListBean> getAdapterList(OrderListModel result) {
        return result.getData().getList();
    }

    @Override
    public void bindData(CombinationViewHolder holder, OrderListModel.DataBean.ListBean t, int position) {

        holder.setText(R.id.orderTvGoodsTitle, t.getItem_title());
        holder.setText(R.id.orderTvShopTile, "所属店铺：" + t.getSeller_shop_title());
        holder.setText(R.id.orderTvPayPrice, t.getPay_price());
        holder.setText(R.id.orderTvYGPrice, t.getPub_share_pre_fee());
        holder.setText(R.id.orderTvTC, t.getZr_sub_rate());

        //结算
        holder.setText(R.id.orderTvTotalPrice, t.getAlipay_total_price());
        //结算 预估
        holder.setText(R.id.orderTvCommission, t.getCommission());
   //     holder.setText(R.id.orderTvTotalPrice, t.getAlipay_total_price());
        //   t.getTk_status();

        holder.setText(R.id.orderTvCreateTime, t.getCreate_time() + " 创建");
        holder.setText(R.id.orderTvOrderCreateTime, t.getOrder_create_time() + " 结算");


        int radius = 20;
        TextViewSelector status = holder.getView(R.id.orderTvStatus);


        switch (t.getTk_status()) {

            case 3:
                status.setText("已结算");
                status.setBackgroundColor(getContext(), getResources().getColor(R.color.colorGreen_04be02), radius);
                break;
            case 12:
                status.setText("已付款");
                status.setBackgroundColor(getContext(), getResources().getColor(R.color.color_style_btn_selected), radius);
                break;
            case 13:
                status.setText("已失效");
                status.setBackgroundColor(getContext(), getResources().getColor(R.color.colorGray_999999), radius);
                break;
            case 14:

                status.setText("已成功");
                status.setBackgroundColor(getContext(), getResources().getColor(R.color.colorGreen_04be02), radius);
                break;
            default:

                status.setText("已成功");
                status.setBackgroundColor(getContext(), getResources().getColor(R.color.colorGreen_04be02), radius);
                break;


        }


    }

    @Override
    public Call<OrderListModel> getCall(Map<String, Object> map) {

        String status = getArguments().getString("status");

        map.put("page", "1");
        map.put("token", getToken());
        if (!TextUtils.isEmpty(status)) {
            map.put("status", status);
        }


        //  不传获取全部、 3：订单结算，12：订单付款， 13：订单失效，14：订单成功
        return HttpRequestUtil.getRetrofitService(UserApi.class).getOrderList(map);
    }
}
