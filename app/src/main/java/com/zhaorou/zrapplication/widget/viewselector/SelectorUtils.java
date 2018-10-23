package com.zhaorou.zrapplication.widget.viewselector;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.zhaorou.zrapplication.R;


/**
 * Created by kang on 2018/9/26 0026.
 */

public class SelectorUtils {


    private static final String TAG = "SelectorUtils";

    static void setBackground(Context context, AttributeSet attrs, View view) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.button_selector);//从xml那传来的一组值

        //选中
        int selectedColor = typedArray.getColor(R.styleable.button_selector_selectedColor, context.getResources().getColor(R.color.color_style_btn_selected));
        int pressedColor;
        //点击
        if(selectedColor==context.getResources().getColor(R.color.color_style_btn_selected)){
            pressedColor = typedArray.getColor(R.styleable.button_selector_pressedColor, context.getResources().getColor(R.color.color_style_btn_normal));
        }else {
            pressedColor = typedArray.getColor(R.styleable.button_selector_pressedColor, selectedColor);
        }

        //enable false
        int enabledColor = typedArray.getColor(R.styleable.button_selector_enabledColor, context.getResources().getColor(R.color.color_btn_enable_false));

        float r = typedArray.getFloat(R.styleable.button_selector_radiu, 6);
        typedArray.recycle();
        float radius =dip2px(context, r);


        setBackground(view, selectedColor, pressedColor, enabledColor, radius);
    }

    public static void setBackground(View view, int selectedColor, int pressedColor, int enabledColor, float radius) {
        StateListDrawable drawable = new StateListDrawable();
        GradientDrawable selectedBg = new GradientDrawable();
        GradientDrawable pressedBg = new GradientDrawable();
        GradientDrawable enabledBg = new GradientDrawable();

        selectedBg.setCornerRadius(radius);
        pressedBg.setCornerRadius(radius);
        enabledBg.setCornerRadius(radius);

        selectedBg.setColor(selectedColor);
        pressedBg.setColor(pressedColor);
        enabledBg.setColor(enabledColor);



        drawable.addState(new int[]{android.R.attr.state_selected}, selectedBg);
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressedBg);
        drawable.addState(new int[]{android.R.attr.state_enabled}, selectedBg);
        drawable.addState(new int[]{}, enabledBg);

        view.setBackgroundDrawable(drawable);
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
