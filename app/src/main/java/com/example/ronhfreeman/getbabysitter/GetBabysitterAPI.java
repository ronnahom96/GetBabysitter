package com.example.ronhfreeman.getbabysitter;

import com.example.ronhfreeman.getbabysitter.modules.Babysitter;
import com.example.ronhfreeman.getbabysitter.modules.BaseUser;
import com.example.ronhfreeman.getbabysitter.modules.Client;

import org.json.JSONObject;

import java.math.BigInteger;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Ronh on 21/07/2016.
 */
public interface GetBabysitterAPI {
    @GET("fbid")
    Call<JSONObject> getUserByFbId(@Path("fbid") BigInteger fbid);

    @POST("client")
    Call<JSONObject> registerClient(@Field("user")BaseUser baseUser, @Field("profile")Client client);

    @POST("babysitter")
    Call<JSONObject> registerBabysitter(@Field("user")BaseUser baseUser, @Field("profile")Babysitter Babysitter);
}
