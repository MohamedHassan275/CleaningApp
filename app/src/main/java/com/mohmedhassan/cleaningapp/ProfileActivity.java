package com.mohmedhassan.cleaningapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mohmedhassan.cleaningapp.CompanyDetails.CompanyDetailsActivity;
import com.mohmedhassan.cleaningapp.Edit_ProfileUser.Edit_ProfileUserActivity;
import com.mohmedhassan.cleaningapp.HTTP_POST.HttpCall_Post;
import com.mohmedhassan.cleaningapp.HTTP_POST.HttpRequest_Post;
import com.squareup.picasso.Picasso;

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

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    Context context;
    CircleImageView ImageUser;
    TextView NameUser,CityUser;
    ProgressBar progressBar;
    LinearLayout linearLayout_EditProfile,linearLayout_Order,linearLayout_Offers,linearLayout_Share,linearLayout_Coupons,
    linearLayout_Setting,linearLayout_SignOut;
    ImageView imageViewHome,imageViewSort,imageViewFillter,back_profile;
    String nameUser, imageuser,cityUser;
    final String ShowProfileApi = "http://x4to.com/public/api/getUserDetails";
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageUser = findViewById(R.id.imageview_profile_user);
        NameUser = findViewById(R.id.tv_name_profile);
        CityUser = findViewById(R.id.tv_city_profileDetails);
        linearLayout_EditProfile = findViewById(R.id.linearlayout_edit_profile);
        linearLayout_Order = findViewById(R.id.linearlayout_order_profile);
        linearLayout_Offers = findViewById(R.id.linearlayout_offers_profile);
        linearLayout_Share = findViewById(R.id.linearlayout_share_profile);
        linearLayout_Coupons = findViewById(R.id.linearlayout_coupons_profile);
        linearLayout_Setting = findViewById(R.id.linearlayout_setting_profile);
        linearLayout_SignOut = findViewById(R.id.linearlayout_signOut_profile);
        imageViewHome = findViewById(R.id.imageview_home_profile);
        imageViewSort = findViewById(R.id.imageview_sort_profile);
        back_profile = findViewById(R.id.back_profile);
        imageViewFillter = findViewById(R.id.imageview_fillter_profile);
        progressBar = findViewById(R.id.m_progress_show_profile);


        linearLayout_EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfileActivity.this, Edit_ProfileUserActivity.class);
                startActivity(intent);
            }
        });
        back_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfileActivity.this, CompanyDetailsActivity.class);
                startActivity(intent);
            }
        });
        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfileActivity.this,SideMenuActivity.class);
                startActivity(intent);
            }
        });
        imageViewSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageViewFillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ShowProfile();



    }

    private void ShowProfile() {


        String LanguageApp = "ar";
            HashMap<String,String> params = new HashMap<>();
            //Log.d("Verification","Mail: "+mail+" , code: "+verfication_num);
            params.put("lang",LanguageApp);
            params.put("token","LjFklY5VQIP2Xu5wQ3Wt7tvdFTRJl1pwOtqFKITSBkVR9tNyr7kuJ4q7seOQ");
            initializeShowProfile(false,params);


    }


    public void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
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
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);
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


   /* public String StringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }*/


    @SuppressLint("StaticFieldLeak")
    private void initializeShowProfile(final boolean loadMore, HashMap<String, String> params) {

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
        httpCall_post.setUrl(APIUrl.BASE_URL+"getUserDetails");
        httpCall_post.setParams(params);

        String vv= httpCall_post.getUrl() +  params ;
        new HttpRequest_Post() {
            @Override
            public void onResponse(StringBuilder response) {
                super.onResponse(response);
//                try {
                try {


                    /*  nameUser  = NameUser.getText().toString();
                      imageuser  = ImageUser.getResources().toString();
                      cityUser  = CityUser.getText().toString();
*/

              //      URL url = new URL("profile_picture");

                   String   nameUser = "" ;
                    String  image = "" ;
                    String   cityUser = "" ;


                    JSONTokener Xobject = new JSONTokener(response.toString());
                    JSONObject Code = new JSONObject(Xobject);
                    String C = Code.getString("code");

                    if (C.contains("101") ) {

                        Toast.makeText(getApplicationContext()," Error Upload Image  ..",Toast.LENGTH_LONG).show();

                        progressBar.setVisibility(View.GONE);

                        return;

                    }

                    progressBar.setVisibility(View.GONE);

                    JSONTokener tokener = new JSONTokener(response.toString());
                    JSONObject data = new JSONObject(tokener);
                    JSONArray object = data.getJSONArray("data");

                    for(int i=0; i<object.length(); i++) {
                        JSONObject   itemCountry = object.getJSONObject(i);  //gets the ith Json object of JSONArray
                         nameUser  = itemCountry.getString("name");
                         image = itemCountry.getString("profile_picture");
                         cityUser  = itemCountry.getString("city_name");


                        NameUser.setText(nameUser);
                        Picasso.get().load(image).into(ImageUser);
                        CityUser.setText(cityUser);





                    }

                /*    byte[] decodedString = Base64.decode(imageuser,Base64.NO_WRAP);
                    InputStream inputStream  = new ByteArrayInputStream(decodedString);
                    bitmap  = BitmapFactory.decodeStream(inputStream);*/
                  //  ImageUser.setImageBitmap(bitmap);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }.execute(httpCall_post);


    }



}