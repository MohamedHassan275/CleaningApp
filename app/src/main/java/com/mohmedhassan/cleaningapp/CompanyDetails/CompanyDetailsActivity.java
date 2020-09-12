package com.mohmedhassan.cleaningapp.CompanyDetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mohmedhassan.cleaningapp.APIUrl;
import com.mohmedhassan.cleaningapp.Companies.CompaniesActivity;
import com.mohmedhassan.cleaningapp.HTTP_POST.HttpCall_Post;
import com.mohmedhassan.cleaningapp.HTTP_POST.HttpRequest_Post;
import com.mohmedhassan.cleaningapp.ProfileActivity;
import com.mohmedhassan.cleaningapp.R;
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

public class CompanyDetailsActivity extends AppCompatActivity {


    MyAdapter adapter;
    //  ArrayList<CompanyDetails_item_Model> companyDetails_item_models;
    Context context;
    ProgressBar progressBar;
    RecyclerView recyclerview_item_order_companyDetails, recyclerview_item_companyDetails;
    GridView gridview__item_companyDetails;
    TextView CompanyName, CommentsCompany, ReviewsCompany, PriceCompany, PriceCompanyNumber;
    RatingBar RateCompany;
    private int selectedPosition = -1;
    Button Btn_packages, Btn_services, Btn_offers;
    CompanyDetails_item_Adapter companyDetails_item_adapter;
    ArrayList<CompanyDetails_item_Model> companyDetails_item_models = new ArrayList<>();

    ImageView imageViewHome, imageViewSort, imageViewProfile, back_company_details;
    LinearLayout linearLayoutHome;
    String companyName, reviewsCompany, commentCompany, priceCompany;
    String Services, Packages, Others;
    double rateCompany;
    Bundle b;
    boolean changeButton = false;

    JSONObject jsonObjectItemCompanyDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        /* companyName = editText.getText().toString().trim();
        reviewsCompany = ReviewsCompany.getText().toString().trim();
        commentCompany = CommentsCompany.getText().toString().trim();
        priceCompany = PriceCompany.getText().toString().trim();
        rateCompany = RateCompany.getRating();

        b = new Bundle();
        b = getIntent().getExtras();
        companyName = b.getString("company");
        commentCompany = b.getString("comment");
        reviewsCompany = b.getString("review");
        rateCompany = b.getDouble("rate");
        priceCompany = b.getString("minprice");*/

        recyclerview_item_order_companyDetails = findViewById(R.id.recyclerview_item_order_companyDetails);
        recyclerview_item_companyDetails = findViewById(R.id.recyclerview_item_companyDetails);
        gridview__item_companyDetails = findViewById(R.id.gridview__item_companyDetails);
        CompanyName = findViewById(R.id.tv_item_companyDetails_name);
        RateCompany = findViewById(R.id.ratingBar_companyDetails);
        CommentsCompany = findViewById(R.id.tv_item_comments_companyDetails);
        ReviewsCompany = findViewById(R.id.tv_item_reviews_companyDetails);
        PriceCompany = findViewById(R.id.tv_item_minimum_order_comapnyDetails);
        PriceCompanyNumber = findViewById(R.id.tv_item_minimum_order_number_comapnyDetails);
        imageViewHome = findViewById(R.id.imageview_home_companyDetais);
        imageViewSort = findViewById(R.id.imageview_sort_companyDetais);
        imageViewProfile = findViewById(R.id.imageview_profile_companyDetais);
        back_company_details = findViewById(R.id.back_company_details);
        linearLayoutHome = findViewById(R.id.linearlayout_home_companyDetais);
        Btn_packages = findViewById(R.id.btn_item_packages);
        Btn_services = findViewById(R.id.btn_item_services);
        Btn_offers = findViewById(R.id.btn_item_offers);
        progressBar = findViewById(R.id.m_progress_companyDetails);


        Btn_packages.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

           /*     if(v.getId()==R.id.btn_item_packages){
                    Btn_packages.setBackground(Drawable.createFromPath(String.valueOf(R.drawable.btn_design)));
                    Btn_packages.setTextColor(Color.BLUE);
                }
                Btn_services.setBackground(Drawable.createFromPath(String.valueOf(R.drawable.rectangle_item)));
                Btn_services.setTextColor(Color.parseColor("#b300d6d3"));*/


            }
        });
        Btn_services.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {


              /*  if(v.getId() == R.id.btn_item_services){
                    Btn_services.setBackground(Drawable.createFromPath(String.valueOf(R.drawable.rectangle3)));
                    Btn_services.setTextColor(Color.WHITE);
                }
                Btn_services.setBackground(Drawable.createFromPath(String.valueOf(R.drawable.btn_design)));
                Btn_services.setTextColor(Color.parseColor("#b300d6d3"));*/
            }
        });
        Btn_offers.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

              /*  if (v.getId() == R.id.btn_item_offers) {
                    Btn_offers.setBackgroundColor(Color.BLUE);
                    Btn_offers.setTextColor(Color.WHITE);
                }
                Btn_offers.setBackground(Drawable.createFromPath(String.valueOf(R.drawable.rectangle_58)));
                Btn_offers.setTextColor(Color.parseColor("#b300d6d3"));*/

            }
        });

        linearLayoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CompanyDetailsActivity.this, SideMenuActivity.class);
                startActivity(intent);

            }
        });
        back_company_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CompanyDetailsActivity.this, CompaniesActivity.class);
                startActivity(intent);

            }
        });
        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CompanyDetailsActivity.this, SideMenuActivity.class);
                startActivity(intent);

            }
        });
        imageViewSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CompanyDetailsActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });
        gridview__item_companyDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {

            }
        });


        RecycleViewItem();

    }

    private void RecycleViewItem() {


        companyDetails_item_adapter = new CompanyDetails_item_Adapter(context, companyDetails_item_models);
        recyclerview_item_companyDetails.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerview_item_companyDetails.setItemAnimator(new DefaultItemAnimator());
        recyclerview_item_companyDetails.setAdapter(companyDetails_item_adapter);
        companyDetails_item_adapter.notifyDataSetChanged();

        RecycleViewItemItem();

       /* String LanguageApp = "ar";
        HashMap<String, String> params = new HashMap<>();
        //Log.d("Verification","Mail: "+mail+" , code: "+verfication_num);
        params.put("token", "LjFklY5VQIP2Xu5wQ3Wt7tvdFTRJl1pwOtqFKITSBkVR9tNyr7kuJ4q7seOQ");
        params.put("lang", LanguageApp);
        initializeGetCompanyDetails(false, params);*/


    }

    private void RecycleViewItemItem() {

        CompanyDetails_item_Model companyDetails_item_model = new CompanyDetails_item_Model(R.drawable.iamge_item_company_details,
                "Car Wish","Clean","price","1500");
        companyDetails_item_models.add(companyDetails_item_model);

        companyDetails_item_model = new CompanyDetails_item_Model(R.drawable.iamge_item_company_details,
                "Car Wish","Clean","price","1250");
        companyDetails_item_models.add(companyDetails_item_model);

        companyDetails_item_model = new CompanyDetails_item_Model(R.drawable.iamge_item_company_details,
                "Car Wish","Clean","price","1300");
        companyDetails_item_models.add(companyDetails_item_model);

        companyDetails_item_model = new CompanyDetails_item_Model(R.drawable.iamge_item_company_details,
                "Car Wish","Clean","price","1200");
        companyDetails_item_models.add(companyDetails_item_model);

        companyDetails_item_model = new CompanyDetails_item_Model(R.drawable.iamge_item_company_details,
                "Car Wish","Clean","price","1000");
        companyDetails_item_models.add(companyDetails_item_model);

        companyDetails_item_model = new CompanyDetails_item_Model(R.drawable.iamge_item_company_details,
                "Car Wish","Clean","price","1750");
        companyDetails_item_models.add(companyDetails_item_model);


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

    public JSONObject getJSONObjectFromURL2(String urlString) throws IOException, JSONException {
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

    @SuppressLint("StaticFieldLeak")
    private void initializeGetCompanyDetails(final boolean loadMore, HashMap<String, String> params) {

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
        httpCall_post.setUrl(APIUrl.BASE_URL + "getCompanyDetails");
        httpCall_post.setParams(params);

        String vv = httpCall_post.getUrl() + params;
        new HttpRequest_Post() {
            @Override
            public void onResponse(StringBuilder response) {
                super.onResponse(response);
//                try {

                try {

                    JSONTokener Xobject = new JSONTokener(response.toString());
                    JSONObject Code = new JSONObject(Xobject);
                    String C = Code.getString("code");

                    if (C.contains("101")) {

                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(), " Error Upload Image  ..", Toast.LENGTH_LONG).show();

                        return;

                    }

                    progressBar.setVisibility(View.GONE);

                    JSONTokener tokener = new JSONTokener(response.toString());
                    JSONObject data = new JSONObject(tokener);
                    JSONArray object = data.getJSONArray("data");


                    for (int i = 0; i < object.length(); i++) {
                        //gets the ith Json object of JSONArray
                      //  CompanyDetails_item_Model companyDetails_item_model = new CompanyDetails_item_Model();
                        jsonObjectItemCompanyDetails = object.getJSONObject(i);
                      /*  companyDetails_item_model.setImageCompany(R.drawable.iamge_item_company_details);
                        companyDetails_item_model.setPrice(jsonObjectItemCompanyDetails.getString("offers") + "$");
                        companyDetails_item_model.setClean(jsonObjectItemCompanyDetails.getString("packages"));
                        companyDetails_item_model.setCarwish(jsonObjectItemCompanyDetails.getString("services"));
                        //check here

                        companyDetails_item_models.add(companyDetails_item_model);*/
                        companyDetails_item_adapter = new CompanyDetails_item_Adapter(context, companyDetails_item_models);
                        recyclerview_item_companyDetails.setLayoutManager(new GridLayoutManager(context, 2));
                        recyclerview_item_companyDetails.setItemAnimator(new DefaultItemAnimator());
                        recyclerview_item_companyDetails.setAdapter(companyDetails_item_adapter);
                        companyDetails_item_adapter.notifyDataSetChanged();

                        Toast.makeText(CompanyDetailsActivity.this, jsonObjectItemCompanyDetails.getString("offers"), Toast.LENGTH_SHORT).show();


                    }


                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }.execute(httpCall_post);


    }


}
