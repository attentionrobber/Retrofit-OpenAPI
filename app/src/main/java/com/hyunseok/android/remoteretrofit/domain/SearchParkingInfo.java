package com.hyunseok.android.remoteretrofit.domain;

import java.util.List;

/**
 * Created by Administrator on 2017-03-07.
 */

public class SearchParkingInfo
{
    private RESULT RESULT;

    private String list_total_count;

    private List<Row> row; // TODO 배열을 못받을 수도 있으므로 List로 바꿔줘야함.

    public RESULT getRESULT ()
    {
        return RESULT;
    }

    public void setRESULT (RESULT RESULT)
    {
        this.RESULT = RESULT;
    }

    public String getList_total_count ()
    {
        return list_total_count;
    }

    public void setList_total_count (String list_total_count)
    {
        this.list_total_count = list_total_count;
    }

    public List<Row> getRow ()
    {
        return row;
    }

    public void setRow (List<Row> row)
    {
        this.row = row;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [RESULT = "+RESULT+", list_total_count = "+list_total_count+", row = "+row+"]";
    }
}