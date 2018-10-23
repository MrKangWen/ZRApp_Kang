package com.zhaorou.zrapplication.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.zhaorou.zrapplication.base.BaseApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.zhaorou.zrapplication.base.BaseApplication.isWeChat6_7_3;

/**
 * @author kang
 * @date 2018/10/23 0023
 */
public class ShareUtils {

    public static void  shareMoments(final Context context,final List<String> list,final String mShareType,final String mTaoword ){

        final List<File> fileList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String imgUrl : list) {
                    File file = FileUtils.saveImageToSdCard(context.getExternalCacheDir(), imgUrl);
                    if (file != null) {
                        fileList.add(file);
                    }
                }
                Intent intent = new Intent();
                ComponentName comp = null;
                if (TextUtils.equals(mShareType, "WX")) {
                    comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
                }
                if (TextUtils.equals(mShareType, "WX_CIRCLE")) {
                    comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
                    intent.putExtra("Kdescription", mTaoword);
                }
                intent.setComponent(comp);
                intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                intent.setType("image/*");
                ArrayList<Uri> imgUriList = new ArrayList<>();
                if (BaseApplication.isWeChat6_7_3()) {
                    imgUriList.add(Uri.fromFile(fileList.get(0)));
                } else {
                    for (File file : fileList) {
                        imgUriList.add(Uri.fromFile(file));
                    }
                }
                intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imgUriList);
                context.startActivity(intent);

            }
        }).start();
    }
}
