package com.mohmedhassan.cleaningapp.Register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mohmedhassan.cleaningapp.APIUrl;
import com.mohmedhassan.cleaningapp.Edit_ProfileUser.country_intity_Country;
import com.mohmedhassan.cleaningapp.HTTP_GET.HttpCall_Get;
import com.mohmedhassan.cleaningapp.HTTP_GET.HttpRequest_Get;
import com.mohmedhassan.cleaningapp.Login.LoginActivity;
import com.mohmedhassan.cleaningapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class RegisterActivity extends AppCompatActivity  {

    private static final String KEY_EMPTY = "";
    ProgressBar progressBar;
    String NameHolder, EmailHolder, PasswordHolder,Country;
    EditText Ed_username_register, Ed_email_register, Ed_password_register;
    TextView  Login_Register, Tv_name, Tv_email, Tv_password,Tv_Country;
    Spinner SpCountry;
    Button Btn_Register;
    String LanguageApp;
    ArrayList<country_intity_Country> countryList = new ArrayList<>();
    ArrayList<String> spinnercountry = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        DevineView();
        SetOnClickListener();
        GetCountry();


    }

    private void GetCountry() {

        LanguageApp = "ar";
        HashMap<String, String> params = new HashMap<>();
        //Log.d("Verification","Mail: "+mail+" , code: "+verfication_num);
        params.put("lang", LanguageApp);
        initializeGetCountry(false, params);
    }

    @SuppressLint("StaticFieldLeak")
    private void initializeGetCountry(final boolean loadMore, HashMap<String, String> params) {

//        try {
//            getJSONObjectFromURL("https://api2.x4to.com/adduser?password=ddd&name=aaa&email=xx");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
      //  progressBar.setVisibility(View.VISIBLE);

        HttpCall_Get httpCall_get = new HttpCall_Get();
        httpCall_get.setMethodtype(HttpCall_Get.GET);
        httpCall_get.setUrl(APIUrl.BASE_URL + "getContriesRegister");
        httpCall_get.setParams(params);

        String vv = httpCall_get.getUrl() + params;
        new HttpRequest_Get() {
            @Override
            public void onResponse(StringBuilder response) {
                super.onResponse(response);
//                try {
                try {

                    String Country_id = "";
                    String country = "";
                    //  sp_countryName = Sp_Country.getSelectedItem().toString().trim();

                    JSONTokener Xobject = new JSONTokener(response.toString());
                    JSONObject Code = new JSONObject(Xobject);
                    String C = Code.getString("code");

                    if (C.contains("101")) {

                        Toast.makeText(getApplicationContext(), " Country already exist  ..", Toast.LENGTH_LONG).show();

                     //   progressBar.setVisibility(View.GONE);

                        return;

                    }

                  //  progressBar.setVisibility(View.GONE);

                    JSONTokener tokener = new JSONTokener(response.toString());
                    JSONObject data = new JSONObject(tokener);
                    JSONArray object = data.getJSONArray("data");

                    for (int i = 0; i < object.length(); i++) {
                        JSONObject itemCountry = object.getJSONObject(i);  //gets the ith Json object of JSONArray
                        country = itemCountry.getString("country_name");
                        Country_id = itemCountry.getString("id");
                        countryList.add(new country_intity_Country(country, Country_id));
                        //    Toast.makeText(Edit_ProfileUserActivity.this, country, Toast.LENGTH_SHORT).show();

                        spinnercountry.add(country);

                    }

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                            android.R.layout.simple_spinner_item, spinnercountry); //selected item will look like a spinner set from XML
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    SpCountry.setAdapter(spinnerArrayAdapter);
                 //   Toast.makeText(RegisterActivity.this, country, Toast.LENGTH_SHORT).show();
                    Tv_Country.setVisibility(View.VISIBLE);
                    // Toast.makeText(Edit_ProfileUserActivity.this, Country_id, Toast.LENGTH_SHORT).show();

                    //  Toast.makeText(Edit_ProfileUserActivity.this, country, Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }.execute(httpCall_get);


    }


    private void DevineView() {

        Btn_Register = findViewById(R.id.btn_register);
        Login_Register = findViewById(R.id.tv_login_register);
        Ed_username_register = findViewById(R.id.ed_username_register);
        Ed_email_register = findViewById(R.id.ed_email_register);
        Ed_password_register = findViewById(R.id.ed_password_register);
        Tv_name = findViewById(R.id.tv_username_register);
        Tv_email = findViewById(R.id.tv_eamil_register);
        Tv_password = findViewById(R.id.tv_password_register);
        SpCountry = findViewById(R.id.sp_country_register);
        Tv_Country = findViewById(R.id.tv_country_register);
        progressBar = findViewById(R.id.m_progress_register);
        
    }

    private void SetOnClickListener() {


        SpCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        Ed_email_register.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Tv_email.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Ed_username_register.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Tv_name.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Ed_password_register.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Tv_password.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EmailHolder = Ed_email_register.getText().toString();
                NameHolder = Ed_username_register.getText().toString();
                PasswordHolder = Ed_password_register.getText().toString();
                Country = SpCountry.getSelectedItem().toString();


                if(validateInputs()){

                    HashMap<String,String> params = new HashMap<>();
                    //Log.d("Verification","Mail: "+mail+" , code: "+verfication_num);
                    params.put("email",EmailHolder);
                    params.put("name",NameHolder);
                    params.put("password",PasswordHolder);
                    params.put("country_id",Country);
                    initializeRegister(false,params);

                }

            }
        });
        Login_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }

    public void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }});
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager(){
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }}}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }

    public JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        trustEveryone();
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }

    @SuppressLint("StaticFieldLeak")
    private void initializeRegister(final boolean loadMore, HashMap<String, String> params) {

//        try {
//            getJSONObjectFromURL("https://api2.x4to.com/adduser?password=ddd&name=aaa&email=xx");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
     progressBar.setVisibility(View.VISIBLE);

        HttpCall_Get httpCall_get = new HttpCall_Get();
        httpCall_get.setMethodtype(HttpCall_Get.GET);
        httpCall_get.setUrl(APIUrl.BASE_URL_LoginAndRegister+"adduser");
        httpCall_get.setParams(params);

        String vv= httpCall_get.getUrl() +  params ;
        new HttpRequest_Get() {
            @Override
            public void onResponse(StringBuilder response) {
                super.onResponse(response);
//                try {
                try {

                    String User_id = "" ;
                    String User_name = "" ;


                    JSONTokener Xobject = new JSONTokener(response.toString());
                    JSONObject Code = new JSONObject(Xobject);
                    String C = Code.getString("code");

                    if (C.contains("101") ) {

                        Toast.makeText(getApplicationContext()," Email already exist  ..",Toast.LENGTH_LONG).show();

                      progressBar.setVisibility(View.GONE);

                        return;

                    }

                  progressBar.setVisibility(View.GONE);

                    JSONTokener tokener = new JSONTokener(response.toString());
                    JSONObject data = new JSONObject(tokener);
                    JSONArray object = data.getJSONArray("data");



                    for(int i=0; i<object.length(); i++) {
                        JSONObject item = object.getJSONObject(i);  //gets the ith Json object of JSONArray
                        User_id = item.getString("id");
                        User_name = item.getString("name");


                    }

                    Toast.makeText(getApplicationContext(),User_id,Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),User_name,Toast.LENGTH_LONG).show();

                    Ed_email_register.setText(" ");
                    Ed_username_register.setText(" ");
                    Ed_password_register.setText(" ");




                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }.execute(httpCall_get);


    }


    private boolean validateInputs() {

        if (KEY_EMPTY.equals(EmailHolder)) {
            Ed_email_register.setError("Email cannot be empty");
            Ed_email_register.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(NameHolder)) {
            Ed_username_register.setError("NameItem cannot be empty");
            Ed_username_register.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(PasswordHolder)) {
            Ed_password_register.setError("Password cannot be empty");
            Ed_password_register.requestFocus();
            return false;
        }

        if (!EmailHolder.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            Ed_email_register.setError("Invalid Email Address");
            Ed_email_register.requestFocus();
            return false;
        }

        return true;
    }

}
