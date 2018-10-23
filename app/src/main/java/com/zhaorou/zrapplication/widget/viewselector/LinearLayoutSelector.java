package com.zhaorou.zrapplication.widget.viewselector;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by kang on 2018/9/25 0025.
 * 设置LinearLayout选择和默认的颜色
 * LinearLayout的圆度radius
 */

public class LinearLayoutSelector extends LinearLayout {
    public LinearLayoutSelector(Context context) {
        super(context);
    }

    private String TAG = this.getClass().getSimpleName();

    public LinearLayoutSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LinearLayoutSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        SelectorUtils.setBackground(context,attrs,this);
    }


}
