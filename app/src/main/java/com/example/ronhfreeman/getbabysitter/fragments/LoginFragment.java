package com.example.ronhfreeman.getbabysitter.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ronhfreeman.getbabysitter.R;
import com.example.ronhfreeman.getbabysitter.RetrofitService;
import com.example.ronhfreeman.getbabysitter.modules.Babysitter;
import com.example.ronhfreeman.getbabysitter.modules.BaseUser;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Data members
    @BindView(R.id.login_button)
    public LoginButton loginButton;

    @BindView(R.id.info)
    public TextView txtInfo;

    private CallbackManager callbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login,container, false);

        // Set the butter knife
        ButterKnife.bind(this, view);

        // Get the facebook call manager
        callbackManager = CallbackManager.Factory.create();

        // Set read permissions
        loginButton.setReadPermissions("email");

        // If using in a fragment
        loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Bundle params = new Bundle();
                params.putString("fields", "id,name,about,bio,email,birthday,first_name,gender,last_name");
                //params.putString("fields", "id,name");

                // Get the details from the user
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/me/",
                        params,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                try {
                                    txtInfo.setText(response.getJSONObject().getString("id").toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    // Get the json object
                                    JSONObject myFaceBookJsonObject = response.getJSONObject();

                                    // Get the fields from the json
                                    BigInteger fbid = BigInteger.valueOf(myFaceBookJsonObject.getInt("id"));
                                    String firstName = myFaceBookJsonObject.getString("first_name");
                                    String lastName = myFaceBookJsonObject.getString("last_name");
                                    String bio = myFaceBookJsonObject.getString("bio");
                                    String email = myFaceBookJsonObject.getString("email");
                                    Date birthday = (Date) myFaceBookJsonObject.get("birthday");

                                    // Use retrofit to get the user from the db
                                    JSONObject checkLoggedJson =
                                            RetrofitService.getWs().getUserByFbId(fbid);

                                    // Check if the user is null namely check if the user is register to the db
                                    if (checkLoggedJson == null) {
                                        // Get the details from the
                                        BaseUser baseUser = new BaseUser();
                                        // Create the user
                                        JSONObject idJson = RetrofitService.getWs().registerBabysitter(new Babysitter());
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                ).executeAsync();
            }

            @Override
            public void onCancel() {
                txtInfo.setText("Login attempt canceled.");
            }

                    @Override
                    public void onError(FacebookException exception) {
                        txtInfo.setText("Login attempt failed.");
                    }
                });

       return (view);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
