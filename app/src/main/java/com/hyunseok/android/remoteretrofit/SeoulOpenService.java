package com.hyunseok.android.remoteretrofit;

import com.hyunseok.android.remoteretrofit.domain.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * json 을 변환해주는 중간 인터페이스
 * Created by Administrator on 2017-03-07.
 */

public interface SeoulOpenService {

        // http://openapi.seoul.go.kr:8088/575446554e6c6f7236306247414f6a/json/SearchParkingInfo/1/10/
        @GET("575446554e6c6f7236306247414f6a/json/SearchParkingInfo/1/1000/{gu}") //
        Call<Data> getData(@Path("gu") String value);
}
