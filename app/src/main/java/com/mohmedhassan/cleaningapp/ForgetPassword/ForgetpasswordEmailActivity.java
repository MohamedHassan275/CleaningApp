package com.mohmedhassan.cleaningapp.ForgetPassword;

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

import com.mohmedhassan.cleaningapp.ForgetPasswordEmailCode.ForgetPassword_Email_Code;
import com.mohmedhassan.cleaningapp.HTTP_POST.HttpCall_Post;
import com.mohmedhassan.cleaningapp.HTTP_POST.HttpRequest_Post;
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
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class ForgetpasswordEmailActivity extends AppCompatActivity {

    private static final String KEY_EMPTY = "";
    TextView Tv_email_forgetpassword;
    EditText Ed_email_forgetpassword;
    Button Btn_next_forgetpassword;
    String EmailCode;
    ProgressBar progressBar;
    JSONObject item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword_email);

        Tv_email_forgetpassword = findViewById(R.id.tv_eamil_forgetpassword);
        Ed_email_forgetpassword = findViewById(R.id.ed_email_forgetpassword);
        Btn_next_forgetpassword = findViewById(R.id.btn_forgetpassword);
        progressBar = findViewById(R.id.m_progress_ForgetPassword);


        Ed_email_forgetpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Tv_email_forgetpassword.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        Btn_next_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EmailCode = Ed_email_forgetpassword.getText().toString();


                if (validateInputs()) {

                    HashMap<String,String> params = new HashMap<>();
                    //Log.d("Verification","Mail: "+mail+" , code: "+verfication_num);
                    params.put("email", EmailCode);
                    initializeRegister(false,params);

                }

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
        progressBar.setVisibility(View.VISIBLE);

        HttpCall_Post httpCall_post = new HttpCall_Post();
        httpCall_post.setMethodtype(HttpCall_Post.POST);
        httpCall_post.setUrl("https://x4to.com/public/api/sendmail");
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

                        Toast.makeText(getApplicationContext()," Email not found .....",Toast.LENGTH_LONG).show();

                        progressBar.setVisibility(View.GONE);

                        return;

                    }

                    progressBar.setVisibility(View.GONE);

                    JSONTokener tokener = new JSONTokener(response.toString());
                    JSONObject data = new JSONObject(tokener);
                    JSONArray object = data.getJSONArray("data");



                    for(int i=0; i<object.length(); i++) {
                         item = object.getJSONObject(i);  //gets the ith Json object of JSONArray
                       // email = item.getString("id");
                        code = item.getString("code");

                    }

                    Intent intent = new Intent(ForgetpasswordEmailActivity.this, ForgetPassword_Email_Code.class);
                    intent.putExtra("code",item.getString("code"));
                    intent.putExtra("email",item.getString("email"));
                    startActivity(intent);




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

        if (KEY_EMPTY.equals(EmailCode)) {
            Ed_email_forgetpassword.setError("Email cannot be empty");
            Ed_email_forgetpassword.requestFocus();
            return false;
        }

        if (!EmailCode.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            Ed_email_forgetpassword.setError("Invalid Email Address");
            Ed_email_forgetpassword.requestFocus();
            return false;
        }

        return true;
    }

}
