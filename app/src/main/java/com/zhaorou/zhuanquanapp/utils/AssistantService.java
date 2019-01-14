package com.zhaorou.zhuanquanapp.utils;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;


public class AssistantService extends AccessibilityService {

    /**
     * 助手服务是否正在运行
     */
    public static boolean isAssistantRunning = false;
    public static String mMoments = "";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        switch (eventType) {
            //窗口发生改变时会调用该事件
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                String className = event.getClassName().toString();
                /*
                    微信几个页面的包名+地址。用于判断在哪个页面
                 */

                String SnsLineUi = "com.tencent.mm.plugin.sns.ui.SnsUploadUI";
                if (className.equals(SnsLineUi)) {//发送朋友圈的界面
                    AccessibilityNodeInfo rootNode = getRootInActiveWindow();

                    if (rootNode == null) {
                        return;
                    }

                    findEditText(rootNode);
                    Log.d("AssistantService", "----------getViewIdResourceName：---");

                }

                break;
            default:
                break;
        }
    }


    private void findEditText(AccessibilityNodeInfo rootNode) {
        for (int i = 0; i < rootNode.getChildCount(); i++) {

            if ("android.widget.EditText".equals(rootNode.getChild(i).getClassName().toString())) {
                //   ClipboardManager cm = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                Bundle arguments = new Bundle();
                arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, mMoments);
                AccessibilityNodeInfo edtView = rootNode.getChild(i);
                Log.d("AssistantService", "设置：---" + mMoments);
                edtView.performAction(AccessibilityNodeInfo.FOCUS_INPUT);
                edtView.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
                break;
            }

            if (rootNode.getChild(i).getChildCount() != 0) {
                findEditText(rootNode.getChild(i));
            }

        }


    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        isAssistantRunning = true;
    }
}
