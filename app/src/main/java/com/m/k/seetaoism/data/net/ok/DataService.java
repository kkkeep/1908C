package com.m.k.seetaoism.data.net.ok;

import com.m.k.seetaoism.BuildConfig;
import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.data.net.ok.converter.MvpGsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataService {

    private static final long TIME_OUT = 20000;


    public static volatile ApiService mService;

    public static ApiService getService() {
        if (mService == null) {
            synchronized (DataService.class) {
                if (mService == null) {
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    /**
                     * 注意，如果有大文件下载，或者 response 里面的body 很大，要么不加HttpLoggingInterceptor 拦截器
                     * 如果非要加，日志级别不能是 BODY,否则容易内存溢出。
                     */
                    if (BuildConfig.DEBUG) {
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    } else {
                        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
                    }

                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.addInterceptor(logging);
                    builder.connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                            .writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                            .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                            .build();
//
                    Retrofit mRetrofit = new Retrofit.Builder()
                            .client(builder.build())
                            .addConverterFactory(MvpGsonConverterFactory.create()) // 帮我们把json 窜转为 entity 对象
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // 结合 rxjava 使用
                            .baseUrl(Constrant.BASE_URL)
                            .build();

                    mService = mRetrofit.create(ApiService.class);

                }

            }
        }

        return mService;
    }
}
