package com.example.the_dagger.learnit.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.the_dagger.learnit.R;
import com.example.the_dagger.learnit.paytm.Paytm;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class P2PTestActivity extends AppCompatActivity {

    Button bSiginOtp, bValidateOtp, bTransact;
    EditText etParentMobile, etChildMobile, etOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2_ptest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        bSiginOtp = (Button) findViewById(R.id.bSinginOtp);
        bValidateOtp = (Button) findViewById(R.id.bValidateOtp);
        bTransact = (Button) findViewById(R.id.bTransfer);

        etParentMobile = (EditText) findViewById(R.id.etParentMobile);
        etChildMobile = (EditText) findViewById(R.id.etChilePhone);
        etOtp = (EditText) findViewById(R.id.etOtp);

        final JSONObject signinResponse = new JSONObject();
        bSiginOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paytm paytm = Paytm.getInstance();
                Map<String, String> header = new HashMap<String, String>();
                JSONObject body = paytm.getSinginOtpBody(etParentMobile.getText().toString(), "staging-hackathalon", "wallet", "token");

                paytm.signinOtp(header, body, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Singin", response.toString());
                        // TODO : Handle errors
                        try {
                            signinResponse.put("state", response.get("state"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Signin", error.toString());
                    }
                });

            }
        });

        final JSONObject validateOtp = new JSONObject();
        bValidateOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Paytm paytm = Paytm.getInstance();
                    Map<String, String> header = new HashMap<String, String>();
                    header.put("authorization", "Basic " + paytm.getClientIdSecret());
                    JSONObject body = paytm.getValidateOtpBody(etOtp.getText().toString(), signinResponse.getString("state"));
                    Log.e("Validate", "Body :" + body.toString());
                    paytm.validateOtp(header, body, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.e("Validate", response.toString(4));
                                validateOtp.put("access_token", response.get("access_token"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Validate", error.toString());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        bTransact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Paytm paytm = Paytm.getInstance();
                    Map<String, String> header = new HashMap<String, String>();
                    header.put("ssotoken", validateOtp.getString("access_token"));
                    JSONObject body = paytm.getP2pTransferBody(0, 0, etChildMobile.getText().toString(), 10, "INR", "Loan", "127.0.0.1", "PayTm", "P2P_TRANSFER");
                    paytm.p2pTransfer(header, body, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.e("Transact", response.toString(4));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Transact", error.toString());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }



}
