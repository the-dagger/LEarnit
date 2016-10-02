package com.example.the_dagger.learnit.activity;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.the_dagger.learnit.R;
import com.example.the_dagger.learnit.paytm.Paytm;
import com.example.the_dagger.learnit.paytm.Polley;
import com.example.the_dagger.learnit.utility.SuperPrefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText username, age, childphone, parentphone, password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SuperPrefs.newInstance(getApplicationContext()).stringExists("access_token")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        username = (EditText) findViewById(R.id.username);
        age = (EditText) findViewById(R.id.age);
        childphone = (EditText) findViewById(R.id.childPhone);
        parentphone = (EditText) findViewById(R.id.parentPhone);
        password = (EditText) findViewById(R.id.password);

        loginButton = (Button) findViewById(R.id.Loginbutton);
        Polley.init(getApplicationContext());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty() || age.getText().toString().isEmpty() || childphone.getText().toString().isEmpty() || parentphone.toString().isEmpty() ||
                        password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_LONG).show();
                    return;
                }

                SuperPrefs.newInstance(getApplicationContext()).setString("child_phone", childphone.getText().toString());
                Paytm paytm = Paytm.getInstance();
                Map<String, String> header = new HashMap<String, String>();
                JSONObject body = paytm.getSinginOtpBody(parentphone.getText().toString(), "staging-hackathalon", "wallet", "token");

                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Registering on Paytm server");
                progressDialog.setCancelable(false);
                progressDialog.setIndeterminate(true);

                final JSONObject signinResponse = new JSONObject();

                paytm.signinOtp(header, body, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        Log.e("Singin", response.toString());
                        progressDialog.dismiss();
                        try {
                            signinResponse.put("state", response.get("state"));
                            // TODO : Handle errors


                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setTitle("Enter OTP");

                            final EditText input = new EditText(LoginActivity.this);
                            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            input.setHint("OTP here!!!");
                            builder.setView(input);

                            builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String otp = input.getText().toString();
                                    try {
                                        validateToken(otp, response.getString("state") );
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Internet connection error", Toast.LENGTH_LONG).show();
                        Log.e("Signin", error.toString());
                    }
                });
                progressDialog.show();
            }
        });
    }

    void validateToken (String otp, String state) {
        try {
            Paytm paytm = Paytm.getInstance();
            Map<String, String> header = new HashMap<String, String>();
            header.put("authorization", "Basic " + paytm.getClientIdSecret());
            JSONObject body = paytm.getValidateOtpBody(otp, state);
            Log.e("Validate", "Body :" + body.toString());
            paytm.validateOtp(header, body, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        // TODO : Handle Error

                        Log.e("Validate", response.toString(4));
                        SuperPrefs superPrefs = SuperPrefs.newInstance(getApplicationContext());
                        superPrefs.setString("access_token",response.getString("access_token"));
                        SuperPrefs.newInstance(getApplicationContext());

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
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
}
