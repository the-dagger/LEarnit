package com.example.the_dagger.learnit.paytm;

import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vineet on 2/10/16.
 */

public class Paytm {

    private static Paytm paytm;

    private String signinOtpUrl = "https://accounts-uat.paytm.com/signin/otp";
    private String validateOtpUrl = "https://accounts-uat.paytm.com//signin/validate/otp";
    private String p2pTransferUrl = "https://trust-uat.paytm.in/wallet-web/sendMoney";
    private String validateTokenUrl = " https://trust-uat.paytm.in/wallet-web/validateTransaction";
    private String checkBalanceUrl = " https://trust-uat.paytm.in/wallet-web/checkBalance";

    private String clientId = "staging-hackathalon";
    private String clientSecret = "51e6d096-56f6-40b4-a2b9-9e0f8fa704b8";

    private Paytm() {

    }

    public static Paytm getInstance() {
        if (paytm == null) {
            paytm = new Paytm();
        }
        return paytm;
    }


    public void signinOtp(final Map<String,
            String> header, JSONObject body,
                          Response.Listener<JSONObject> successListener,
                          Response.ErrorListener errorListener) {

        RequestQueue requestQueue = Polley.getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, signinOtpUrl, body, successListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(25000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);

    }

    // Used during singin
    public void validateOtp(final Map<String,
            String> header, JSONObject body,
                            Response.Listener<JSONObject> successListener,
                            Response.ErrorListener errorListener) {
        RequestQueue requestQueue = Polley.getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, validateOtpUrl, body, successListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(25000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);

    }

    public void p2pTransfer(final Map<String,
            String> header, JSONObject body,
                            Response.Listener<JSONObject> successListener,
                            Response.ErrorListener errorListener) {
        RequestQueue requestQueue = Polley.getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, p2pTransferUrl, body, successListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(25000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);

    }

    // Used at the end of request money
    public void validateToken(final Map<String,
            String> header, JSONObject body,
                            Response.Listener<JSONObject> successListener,
                            Response.ErrorListener errorListener) {
        RequestQueue requestQueue = Polley.getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, validateTokenUrl, body, successListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(25000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);

    }

    public void checkBalance(final Map<String,
            String> header, JSONObject body,
                              Response.Listener<JSONObject> successListener,
                              Response.ErrorListener errorListener) {
        RequestQueue requestQueue = Polley.getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, checkBalanceUrl, body, successListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(25000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);

    }

    public JSONObject getSinginOtpBody(String phone, String clientId, String scope, String responseType) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("phone", phone);
            jsonObject.put("clientId", clientId);
            jsonObject.put("scope", scope);
            jsonObject.put("responseType", responseType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject getValidateOtpBody(String otp, String state) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("otp", otp);
            jsonObject.put("state", state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject getP2pTransferBody(int isToVerify, int isLimitApplicable,
                                   String payeeMobile, int amount,
                                   String currencyCode, String comment, String ipAddress,
                                   String platformName, String operationType) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject request = new JSONObject();
            request.put("isToVerify", isToVerify);
            request.put("isLimitApplicable", isLimitApplicable);
            request.put("payeePhoneNumber", payeeMobile);
            request.put("amount", amount);
            request.put("currencyCode", currencyCode);
            request.put("comment", comment);

            jsonObject.put("request", request);
            jsonObject.put("ipAddress", ipAddress);
            jsonObject.put("platformName", platformName);
            jsonObject.put("operationType", operationType);
            jsonObject.put("channel", "");
            jsonObject.put("version", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject getValidateTokenBody(String state, String otp, String ipAddress,
                                           String platformName, String operationType) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject request = new JSONObject();
            request.put("state", state);
            request.put("otp", otp);

            jsonObject.put("request", request);
            jsonObject.put("ipAddress", ipAddress);
            jsonObject.put("platformName", platformName);
            jsonObject.put("operationType", operationType);
            jsonObject.put("channel", "");
            jsonObject.put("version", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String getClientIdSecret() {
        return Base64.encodeToString((clientId + ":" + clientSecret).getBytes(), Base64.DEFAULT);
    }
}
