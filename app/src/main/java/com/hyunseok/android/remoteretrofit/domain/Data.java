package com.hyunseok.android.remoteretrofit.domain;

/**
 * Created by Administrator on 2017-03-07.
 */

public class Data {

    private SearchParkingInfo SearchParkingInfo;

    public SearchParkingInfo getSearchParkingInfo ()
    {
        return SearchParkingInfo;
    }

    public void setSearchParkingInfo (SearchParkingInfo SearchParkingInfo)
    {
        this.SearchParkingInfo = SearchParkingInfo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [SearchParkingInfo = "+SearchParkingInfo+"]";
    }
}
