package com.manoj.sampleapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by manoj on 1/11/17.
 */


public class Sample {
    public int id;

    public String name;

    public Location location;

    @SerializedName("fromcentral")
    public FromCentral fromCentral;
}