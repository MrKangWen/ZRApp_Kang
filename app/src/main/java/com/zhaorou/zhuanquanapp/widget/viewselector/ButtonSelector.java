package com.zhaorou.zhuanquanapp.widget.viewselector;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by kang on 2018/9/25 0025.
 * 设置按钮选择和默认的颜色
 * 按钮的圆度radius
 */

public class ButtonSelector extends AppCompatButton {
    public ButtonSelector(Context context) {
        super(context);
    }

    private String TAG = this.getClass().getSimpleName();

    public ButtonSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ButtonSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        SelectorUtils.setBackground(context,attrs,this);
    }



}
