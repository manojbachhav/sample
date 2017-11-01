package com.manoj.sampleapp.restApi;

import com.manoj.sampleapp.model.Sample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by manoj on 01/11/17.
 */

public interface RestService {
    @GET("sample.json")
    Call<List<Sample>> getSampleList();
}