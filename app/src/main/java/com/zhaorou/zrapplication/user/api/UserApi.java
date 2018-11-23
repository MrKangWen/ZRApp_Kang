package com.zhaorou.zrapplication.user.api;

import com.zhaorou.zrapplication.base.BaseModel;
import com.zhaorou.zrapplication.home.model.OrderListModel;

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
}
