package com.mohmedhassan.cleaningapp.Forgetpassword_Password;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mohmedhassan.cleaningapp.HTTP_POST.HttpCall_Post;
import com.mohmedhassan.cleaningapp.HTTP_POST.HttpRequest_Post;
import com.mohmedhassan.cleaningapp.Login.LoginActivity;
import com.mohmedhassan.cleaningapp.R;

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
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class Forgetpassword_Password_Activity extends AppCompatActivity {

    private static final String KEY_EMPTY = "";
    TextView Tv_password_forgetpassword,Tv_password_Confirm_forgetpassword;
    EditText Ed_password_forgetpassword,Ed_Confirm_password_forgetpassword;
    Button Btn_Submit_forgetpassword;
    String passwordHolder,confirmPasswordHolder,Email;
    ProgressBar m_progress_ForgetPassword_password;
    JSONObject item;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword_password);

        Tv_password_forgetpassword = findViewById(R.id.tv_password_forgetpassword);
        Tv_password_Confirm_forgetpassword = findViewById(R.id.tv_Confirm_password_forgetpassword);
        Ed_password_forgetpassword = findViewById(R.id.ed_password_forgetpassword);
        Ed_Confirm_password_forgetpassword = findViewById(R.id.ed__Confirm_password_forgetpassword);
        Btn_Submit_forgetpassword = findViewById(R.id.btn_submit_forgetpassword);
        m_progress_ForgetPassword_password = findViewById(R.id.m_progress_ForgetPassword_password);

        b = new Bundle();
        b = getIntent().getExtras();
        Email = b.getString("Code_email");

        Ed_password_forgetpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Tv_password_forgetpassword.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        Ed_Confirm_password_forgetpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Tv_password_Confirm_forgetpassword.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        Btn_Submit_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                passwordHolder = Ed_password_forgetpassword.getText().toString();


                HashMap<String,String> params = new HashMap<>();
                //Log.d("Verification","Mail: "+mail+" , code: "+verfication_num);
                params.put("email",b.getString("Code_email"));
                params.put("newpassword", passwordHolder);
                initializeRegister(false,params);

              /*  if(validateInputs()){



                }*/

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
        urlConnection.setRequestMethod("POST");
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
        m_progress_ForgetPassword_password.setVisibility(View.VISIBLE);

        HttpCall_Post httpCall_post = new HttpCall_Post();
        httpCall_post.setMethodtype(HttpCall_Post.POST);
        httpCall_post.setUrl("https://x4to.com/public/api/changepassword");
        httpCall_post.setParams(params);

        String vv= httpCall_post.getUrl() +  params ;
        new HttpRequest_Post() {
            @Override
            public void onResponse(StringBuilder response) {
                super.onResponse(response);
//                try {
                try {

                    String email = "" ;
                    String code = "" ;

                    JSONTokener Xobject = new JSONTokener(response.toString());
                    JSONObject Code = new JSONObject(Xobject);
                    String C = Code.getString("code");

                    if (C.contains("101") ) {

                        Toast.makeText(getApplicationContext()," Error on Change Password .....",Toast.LENGTH_LONG).show();

                        m_progress_ForgetPassword_password.setVisibility(View.GONE);

                        return;

                    }

                        m_progress_ForgetPassword_password.setVisibility(View.GONE);


                       Intent intent = new Intent(Forgetpassword_Password_Activity.this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(Forgetpassword_Password_Activity.this, "Change password Succefully", Toast.LENGTH_SHORT).show();






                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }.execute(httpCall_post);
    }

    private boolean validateInputs() {

        if (KEY_EMPTY.equals(passwordHolder)) {
            Ed_password_forgetpassword.setError("password cannot be empty");
            Ed_password_forgetpassword.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(confirmPasswordHolder)) {
            Ed_Confirm_password_forgetpassword.setError("password cannot be empty");
            Ed_Confirm_password_forgetpassword.requestFocus();
            return false;
        }

        if (!passwordHolder.equals(confirmPasswordHolder)) {
            Ed_Confirm_password_forgetpassword.setError("Password and Confirm Password does not match");
            Ed_Confirm_password_forgetpassword.requestFocus();
            return false;
        }


        return true;
    }
}
