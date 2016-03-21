package com.usertesting.tuan.webservice;

import com.usertesting.tuan.data.TestItem;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.mime.TypedInput;

/**
 * Created by tuan on 3/17/2016.
 */
public interface ApiService {

    @GET("/sample_json")
    List<TestItem> getTestItems();

}
