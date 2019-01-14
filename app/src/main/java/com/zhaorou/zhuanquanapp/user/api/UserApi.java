package com.zhaorou.zhuanquanapp.user.api;

import com.zhaorou.zhuanquanapp.base.BaseModel;
import com.zhaorou.zhuanquanapp.home.model.OrderListModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author kang
 * @date 2018/11/21 0021
 */
public interface UserApi {


    /**
     * order list
     */
    @FormUrlEncoded
    @POST("api/get_mine_order_list")
    Call<OrderListModel> getOrderList(@FieldMap Map<String, Object> params);


    /**
     * order list
     */
    @FormUrlEncoded
    @POST("api/add_withdraw_record")
    Call<BaseModel> userWithdrawal(@FieldMap Map<String, Object> params);

    /**
     * 自定义文案尾巴
     */
    @FormUrlEncoded
    @POST("api/upd_userinfo")
    Call<BaseModel> postDiyText(@FieldMap Map<String, Object> params);
}
