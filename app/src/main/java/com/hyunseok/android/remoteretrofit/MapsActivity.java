package com.hyunseok.android.remoteretrofit;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hyunseok.android.remoteretrofit.domain.Data;
import com.hyunseok.android.remoteretrofit.domain.Row;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String APIurl = "http://openapi.seoul.go.kr:8088/575446554e6c6f7236306247414f6a/json/SearchParkingInfo/1/10/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // 중심점을 서울로 이동
        LatLng seoul = new LatLng(37.566696, 126.977942); // 서울시청
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 13));

        // 1. 레트로핏을 생성한다.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://openapi.seoul.go.kr:8088/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 2. 사용할 인터페이스를 설정한다.
        SeoulOpenService service = retrofit.create(SeoulOpenService.class);

        // 3. 데이터를 가져온다
        Call<Data> result = service.getData("중구");

        // 4. 데이터를 가져오는 부분은 네트워크를 통해서 오기 때문에 비동기 처리된다.
        result.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                // 값이 정상적으로 리턴됐을 경우
                if(response.isSuccessful()) {

                    Data data = response.body(); // 원래 반환값인 jsonString 이 Data Class 로 변환되어 리턴된다. -> JSONOjbect 로 변환할 필요가 없다.

                    for (Row row : data.getSearchParkingInfo().getRow()) {

                        LatLng parking = new LatLng(convertDouble(row.getLAT()), convertDouble(row.getLNG()));

                        int capacity = convertInt(row.getCAPACITY());

                        mMap.addMarker(new MarkerOptions().position(parking).title("주차공간" + row.getCAPACITY())).showInfoWindow();

                    }
                } else {
                    Log.e("Retrofit", response.message());
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    // 값이 없을 경우 0으로 세팅 해준다.
    private double convertDouble(String value) {
        double result = 0;
        try {
            result = Double.parseDouble(value);
        } catch (Exception e) {

        }
        return result;
    }

    private int convertInt(String value) {
        int result = 0;
        try {
            result = Integer.parseInt(value);
        } catch (Exception e) {

        }
        return result;
    }
}
