package com.linversion.commondemo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    //这个接口后期可以写个公共类的属性
    public static final String BASE_URL = "https://jsonbox.io";
    private final Retrofit mRetrofit;

    public static class SINGLE_HOLDER {
        public static final RetrofitManager INSTANCE = new RetrofitManager(BASE_URL);
    }

    public static RetrofitManager getInstance() {
        return SINGLE_HOLDER.INSTANCE;
    }


    private RetrofitManager(String baseUrl) {
        mRetrofit = buildRetrofit();
    }

    private OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
    }

    private Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .client(buildOkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
