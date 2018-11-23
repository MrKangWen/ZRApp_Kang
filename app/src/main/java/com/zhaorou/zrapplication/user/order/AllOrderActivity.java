package com.zhaorou.zrapplication.user.order;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zhaorou.zrapplication.R;
import com.zhaorou.zrapplication.base.BaseActivity;

import com.zhaorou.zrapplication.widget.recyclerview.TabLayoutPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllOrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);


        setTitle("所有订单");
        //  不传获取全部、 3：订单结算，12：订单付款， 13：订单失效，14：订单成功

        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(OrderFragment.newInstance(""));
        listFragment.add(OrderFragment.newInstance("3"));
        listFragment.add(OrderFragment.newInstance("13"));
        List<String> listTitle = new ArrayList<>();
        listTitle.add("所有订单");
        listTitle.add("有效订单");
        listTitle.add("失效订单");
        TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getSupportFragmentManager(), listFragment, listTitle);

        ViewPager viewPager = findViewById(R.id.orderViewPager);
        viewPager.setAdapter(tabLayoutPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.rdTabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
