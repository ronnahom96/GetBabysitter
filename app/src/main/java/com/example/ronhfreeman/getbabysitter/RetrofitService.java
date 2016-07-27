package com.example.ronhfreeman.getbabysitter;


import com.example.ronhfreeman.getbabysitter.modules.Babysitter;
import com.example.ronhfreeman.getbabysitter.modules.Client;

import org.json.JSONObject;

import java.math.BigInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Ronh on 21/07/2016.
 */
public class RetrofitService {
    private Retrofit retrofit;
    private static RetrofitService retrofitService;
    private GetBabysitterAPI getBabysitterAPI;
    private final String BASE_URL = "188.166.30.133:3000/";

    private RetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        getBabysitterAPI = retrofit.create(GetBabysitterAPI.class);
    }

    JSONObject jsonUser;

    public JSONObject getUserByFbId(BigInteger fbid) {
        // Create the call
        Call<JSONObject> jsonUserCall = getBabysitterAPI.getUserByFbId(fbid);

        // Execute the call
        jsonUserCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                jsonUser = response.body();
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

            }
        });

        return (jsonUser);
    }

    public static RetrofitService getWs(){
        // Check if the instante is null
        if (retrofitService == null) {
            retrofitService = new RetrofitService();
        }
        return retrofitService;
    }


    public JSONObject registerClient(Client client) {
        return (getWs().registerClient(client));
    }

    public JSONObject registerBabysitter(Babysitter babysitter) {
        return (getWs().registerBabysitter(babysitter));
    }
}
