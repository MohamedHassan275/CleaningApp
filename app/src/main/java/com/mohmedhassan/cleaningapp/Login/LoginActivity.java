package com.mohmedhassan.cleaningapp.Login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.mohmedhassan.cleaningapp.APIUrl;
import com.mohmedhassan.cleaningapp.ForgetPassword.ForgetpasswordEmailActivity;
import com.mohmedhassan.cleaningapp.HTTP_GET.HttpCall_Get;
import com.mohmedhassan.cleaningapp.HTTP_GET.HttpRequest_Get;
import com.mohmedhassan.cleaningapp.R;
import com.mohmedhassan.cleaningapp.Register.RegisterActivity;
import com.mohmedhassan.cleaningapp.SideMenuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class LoginActivity extends AppCompatActivity  {

    private static final String KEY_EMPTY = "";
    ProgressBar progressBar;
    EditText UserName, Password;
    TextView  Tv_Register_Login, Tv_email, Tv_password,Tv_forgetpassword,Tv_LogOut_login,Tv_sign_in_login;
    Button Btn_Login,Btn_Facebook;
    String NameHolder, PasswordHolder,Lang;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";
    LoginButton Facebook;
    Context context;
    JSONObject item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    /*    FacebookSdk.sdkInitialize(getApplicationContext());
          Log.d("AppLog", "key:" + FacebookSdk.getApplicationSignature(this));
          AppEventsLogger.activateApp(this);*/

        SharedPreferences sharedPreferences = getSharedPreferences("names", LoginActivity.MODE_PRIVATE);
        NameHolder = sharedPreferences.getString("username", "");
        //  Password = sharedPreferences.getString("password", "");

        if (!NameHolder.isEmpty()) {

            Intent intent = new Intent(LoginActivity.this, SideMenuActivity.class);
            startActivity(intent);
        }


        Btn_Login = findViewById(R.id.btn_login);
        Tv_Register_Login = findViewById(R.id.tv_register_login);
        Tv_forgetpassword = findViewById(R.id.tv_forgetpassword_login);
        UserName = findViewById(R.id.ed_email_login);
        Password = findViewById(R.id.ed_password_login);
        Tv_email = findViewById(R.id.tv_eamil_login);
        Tv_password = findViewById(R.id.tv_password_login);
        Tv_sign_in_login = findViewById(R.id.tv_sign_in_login);
        progressBar = findViewById(R.id.m_progress_login);
        Btn_Facebook = findViewById(R.id.btn_facebook_login);
        Facebook = findViewById(R.id.facebook_login);
        Tv_LogOut_login = findViewById(R.id.tv_LogOut_login);
        Facebook.setReadPermissions(Arrays.asList("public_profile"));

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        accessToken = AccessToken.getCurrentAccessToken();

        getHashkey();
        if (isLoggedIn)  {

            Toast.makeText(LoginActivity.this, String.valueOf("User is login"), Toast.LENGTH_SHORT).show();
            Tv_LogOut_login.setVisibility(View.VISIBLE);
           /* Intent intent = new Intent(LoginActivity.this, Forgetpassword_Password_Activity.class);
            startActivity(intent);*/


        }else{

          //  Toast.makeText(LoginActivity.this, String.valueOf("User is not login"), Toast.LENGTH_SHORT).show();
            Tv_LogOut_login.setVisibility(View.GONE);

        }
        Tv_sign_in_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        Tv_LogOut_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              /*  LoginManager.getInstance().logOut();
                Toast.makeText(LoginActivity.this, String.valueOf("User is not login"), Toast.LENGTH_SHORT).show();
                Tv_LogOut_login.setVisibility(View.GONE);*/

            }
        });
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // show_toast(loginResult.getAccessToken().getToken() );
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object,
                                                    GraphResponse response) {

                                Log.e("String Response:>",object.toString());

                                try {
                                    String id = object.getString("id");

                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    // String gender = object.getString("gender");
                                    //String birthday = object.getString("birthday");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });


                Bundle permission_param = new Bundle();
                //width and height is optional parameters
                permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
                request.setParameters(permission_param);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

                Toast.makeText(LoginActivity.this, "Login Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        Btn_Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == Btn_Facebook) {
                    Facebook.performClick();

                 //   LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
                }


            }
        });

        UserName.addTextChangedListener(new TextWatcher() {
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
        Password.addTextChangedListener(new TextWatcher() {
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

        Btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NameHolder = UserName.getText().toString();
                PasswordHolder = Password.getText().toString();
                Lang = "ar";
             /*   SharedPreferences sharedPreferences = getSharedPreferences("names", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", NameHolder);
                APIUrl.USER_NAME = NameHolder;
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, SideMenuActivity.class);
                startActivity(intent);
                finish();*/

             if (validateInputs()) {

                    HashMap<String, String> params = new HashMap<>();
                    //Log.d("Verification","Mail: "+mail+" , code: "+verfication_num);
                    params.put("lang",Lang);
                    params.put("name",NameHolder);
                    params.put("password", PasswordHolder);
                    initializeLogin(false, params);

                }
            }
        });
        Tv_Register_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
        Tv_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, ForgetpasswordEmailActivity.class);
                startActivity(intent);

            }
        });

        // DevineView();
        // SetOnClickListener();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
    private void initializeLogin(final boolean loadMore, HashMap<String, String> params) {

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
        httpCall_get.setUrl(APIUrl.BASE_URL_LoginAndRegister+"login");
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
                    String accesstoken = "" ;


                    JSONTokener Xobject = new JSONTokener(response.toString());
                    JSONObject Code = new JSONObject(Xobject);
                    String C = Code.getString("code");

                    if (C.contains("101") ) {

                        Toast.makeText(getApplicationContext(),"invalid name and password  ..",Toast.LENGTH_LONG).show();

                        progressBar.setVisibility(View.GONE);

                        return;


                    }

                    progressBar.setVisibility(View.GONE);

                    JSONTokener tokener = new JSONTokener(response.toString());
                    JSONObject data = new JSONObject(tokener);
                    JSONArray object = data.getJSONArray("data");

                    for(int i=0; i<object.length(); i++) {
                         item = object.getJSONObject(i);  //gets the ith Json object of JSONArray
                        User_id = item.getString("id");
                        User_name = item.getString("name");
                        accesstoken = item.getString("email");

                        SharedPreferences sharedPreferences = getSharedPreferences("names", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", NameHolder);
                        editor.putString("", "");
                        APIUrl.USER_NAME = NameHolder;
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this, SideMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }

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

        if (KEY_EMPTY.equals(NameHolder)) {
            UserName.setError("NameItem cannot be empty");
            UserName.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(PasswordHolder)) {
            Password.setError("Password cannot be empty");
            Password.requestFocus();
            return false;
        }
        /*if (!KEY_EMPTY.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            UserName.setError("Invalid Email Address");
            UserName.requestFocus();
            return false;
        }*/



        return true;
    }

    public void getHashkey(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                Log.i("Base64", Base64.encodeToString(md.digest(),Base64.NO_WRAP));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("NameItem not found", e.getMessage(), e);

        } catch (NoSuchAlgorithmException e) {
            Log.d("Error", e.getMessage(), e);
        }
    }

}
