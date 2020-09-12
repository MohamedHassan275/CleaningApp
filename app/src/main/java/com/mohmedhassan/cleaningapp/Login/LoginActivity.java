package com.mohmedhassan.cleaningapp.Login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/*
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
*/
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
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
    Button Btn_Login;
    String NameHolder, PasswordHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Btn_Login = findViewById(R.id.btn_login);
        Tv_Register_Login = findViewById(R.id.tv_register_login);
        Tv_forgetpassword = findViewById(R.id.tv_forgetpassword_login);
        UserName = findViewById(R.id.ed_email_login);
        Password = findViewById(R.id.ed_password_login);
        Tv_email = findViewById(R.id.tv_eamil_login);
        Tv_password = findViewById(R.id.tv_password_login);
        Tv_sign_in_login = findViewById(R.id.tv_sign_in_login);
        progressBar = findViewById(R.id.m_progress_login);
        Tv_LogOut_login = findViewById(R.id.tv_LogOut_login);



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


                String email = UserName.getText().toString();
                final String password = Password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }



                Intent intent = new Intent(LoginActivity.this, SideMenuActivity.class);
                startActivity(intent);
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

        return true;
    }


}
