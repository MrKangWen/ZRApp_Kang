package com.zhaorou.zhuanquanapp.widget.viewselector;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by kang on 2018/9/25 0025.
 * 设置按钮选择和默认的颜色
 * 按钮的圆度radius
 */

public class TextViewSelector extends AppCompatTextView {
    public TextViewSelector(Context context) {
        super(context);
    }

    private String TAG = this.getClass().getSimpleName();

    public TextViewSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TextViewSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        SelectorUtils.setBackground(context,attrs,this);
    }
    public void setBackgroundColor(Context context,int color,float radius){

        SelectorUtils.setBackground(context,this,color,radius);
    }
}
