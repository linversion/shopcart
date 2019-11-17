package com.linversion.commondemo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface RetrofitAPIs {
    @POST("box_28a4f0f6e8db1c7b3b4f")
    Observable<List<bean>> getCartList();
}
