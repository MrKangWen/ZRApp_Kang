package com.zhaorou.zrapplication.network.retrofit;

import android.util.Log;

import com.zhaorou.zrapplication.BuildConfig;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author kang
 * @date 2018/11/21 0021
 */
public class ReceivedInterceptor implements Interceptor {
    private static final String TAG = "ReceivedInterceptor";
    private static final Charset UTF8 = Charset.forName("UTF-8");
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        String url = chain.request().url().url().toString();
        if (BuildConfig.DEBUG) {
            BufferedSource source = originalResponse.body().source();
            source.buffer();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Log.w(TAG, "intercept ::: URL:[" + url + "] ::: body: " + buffer.clone().readString(UTF8)); //打印后台请求返回的Json
        }
        return originalResponse;
    }
}
