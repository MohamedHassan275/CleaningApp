package com.mohmedhassan.cleaningapp.Companies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.mohmedhassan.cleaningapp.APIUrl;
import com.mohmedhassan.cleaningapp.CompanyDetails.CompanyDetailsActivity;
import com.mohmedhassan.cleaningapp.HTTP_POST.HttpCall_Post;
import com.mohmedhassan.cleaningapp.HTTP_POST.HttpRequest_Post;
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

public class CompaniesActivity extends AppCompatActivity {

    Context context;
    EditText editTextSearch;
    SearchView SearchInput;
    private DividerItemDecoration dividerItemDecoration;
    private LinearLayoutManager linearLayoutManager;
    Companies_itemAdapter companies_itemAdapter;
    private ArrayList<DataModelCompanies_item> dataModelCompanyDeatils = new ArrayList<>();
    private ArrayList<DataModelCompanies_item> Array_Sort ;
    String AccessCodeCountry;
    LinearLayout linearlayout_addpriceCompanies;
    private Companies_item_OrederAdapter companyAdapter;
    private ArrayList<DataModel_itemOreder> dataModels = new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerView_Item, recyclerview_CompanyDetais;
    ProgressBar progressBar;
    JSONObject jsonObjectCompanies;
    ImageView back_companies;
    DataModelCompanies_item modelCompanyDeatils;
    private String[] dataModel;
    SearchView searchView;
    int textlength = 0;
    String company,Comment,Reviews,Price;
    double Rate;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        recyclerView_Item = findViewById(R.id.recyclerview_Name);
        linearlayout_addpriceCompanies = findViewById(R.id.linearlayout_addpriceCompanies);
        recyclerview_CompanyDetais = findViewById(R.id.recyclerview_CompanyDetais);
        SearchInput = findViewById(R.id.search_input);
        back_companies = findViewById(R.id.back_companies);
        progressBar = findViewById(R.id.m_progress_company);
        editTextSearch = (EditText) findViewById(R.id.editText);

      //  dataModelCompanyDeatils = populateList();
        Array_Sort = new ArrayList<>();
      //  Array_Sort = populateList();

        RecycleViewItem();
        RecycleViewCompanyDetails();


        back_companies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CompaniesActivity.this, SideMenuActivity.class);
                startActivity(intent);
            }
        });
        recyclerview_CompanyDetais.addOnItemTouchListener(new RecyclerTouchListener(CompaniesActivity.this,
                new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position)  {
              /*  DataModelCompanies_item spaceListData = dataModelCompanyDeatils.get(position);
                Toast.makeText(context, spaceListData.getCompanyName(), Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(CompaniesActivity.this, CompanyDetailsActivity.class);
                startActivity(intent);

              /*  try {
                    Intent intent = new Intent(CompaniesActivity.this, CompanyDetailsActivity.class);
                    //String company = jsonObjectCompanies.getString("company_name");
                    intent.putExtra("company", jsonObjectCompanies.getString("company_name"));
                    intent.putExtra("comment", jsonObjectCompanies.getString("comment_count"));
                    intent.putExtra("review", jsonObjectCompanies.getString("review_count"));
                    intent.putExtra("minprice", jsonObjectCompanies.getString("min_price") + "$");
                    intent.putExtra("rate", jsonObjectCompanies.getDouble("review_rate"));
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

              //  Toast.makeText(CompaniesActivity.this, spaceListData.getCompanyName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }, recyclerview_CompanyDetais));

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textlength = editTextSearch.getText().length();
                Array_Sort.clear();
                for (int i = 0; i < dataModelCompanyDeatils.size(); i++) {
                    if (textlength <= dataModelCompanyDeatils.get(i).getCompanyName().length()) {
                        Log.d("ertyyy", dataModelCompanyDeatils.get(i).getCompanyName().toLowerCase().trim());
                        if (dataModelCompanyDeatils.get(i).getCompanyName().toLowerCase().trim().contains(
                                editTextSearch.getText().toString().toLowerCase().trim())) {
                            Array_Sort.add(dataModelCompanyDeatils.get(i));
                        }
                    }
                }
                companies_itemAdapter = new Companies_itemAdapter(context, Array_Sort);
                recyclerview_CompanyDetais.setAdapter(companies_itemAdapter);
                recyclerview_CompanyDetais.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        linearlayout_addpriceCompanies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CompaniesActivity.this, CompanyDetailsActivity.class);
                startActivity(intent);

            }
        });

    }

    private ArrayList<DataModelCompanies_item> populateList(){

        ArrayList<DataModelCompanies_item> list = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            DataModelCompanies_item imageModel = new DataModelCompanies_item();
            imageModel.setCompanyName(String.valueOf(dataModelCompanyDeatils.get(i)));
            list.add(imageModel);
        }

        return list;
    }


    private void RecycleViewItem() {


        DataModel_itemOreder movie = new DataModel_itemOreder(R.string.top_company);
        dataModels.add(movie);
        movie = new DataModel_itemOreder(R.string.all_company);
        dataModels.add(movie);
        movie = new DataModel_itemOreder(R.string.highest_price);
        dataModels.add(movie);
        movie = new DataModel_itemOreder(R.string.lowest_price);
        dataModels.add(movie);

        companyAdapter = new Companies_item_OrederAdapter(context, dataModels);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this
                , LinearLayoutManager.HORIZONTAL, false);
        recyclerView_Item.setLayoutManager(mLayoutManager);
        recyclerView_Item.setItemAnimator(new DefaultItemAnimator());
        recyclerView_Item.setAdapter(companyAdapter);



    }

    private void RecycleViewCompanyDetails() {

      //  String searchInput = String.valueOf(SearchInput);
        String From = "3";
        String To = "9";
        String Order = "";
        String City = "";
        String Price = "";
        String SearchInput = "";
        String Language = "ar";
        HashMap<String, String> params = new HashMap<>();
        //Log.d("Verification","Mail: "+mail+" , code: "+verfication_num);
        params.put("token", "LjFklY5VQIP2Xu5wQ3Wt7tvdFTRJl1pwOtqFKITSBkVR9tNyr7kuJ4q7seOQ");
        params.put("from", From);
        params.put("to", To);
        params.put("order_by", Order);
        params.put("city_id", City);
        params.put("min_price", Price);
        params.put("search", SearchInput);
        params.put("lang", Language);
        initializeGetCompanies(false, params);

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
    private void initializeGetCompanies(final boolean loadMore, HashMap<String, String> params) {

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
        httpCall_post.setUrl(APIUrl.BASE_URL + "getCompanies");
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
                        DataModelCompanies_item modelCompanyDeatils = new DataModelCompanies_item();
                        jsonObjectCompanies = object.getJSONObject(i);
                        modelCompanyDeatils.setCompanyName(jsonObjectCompanies.getString("company_name"));
                        modelCompanyDeatils.setPhoto(R.drawable.image_company);
                        modelCompanyDeatils.setComment(R.string.commnets);
                        modelCompanyDeatils.setReviews(R.string.reviews);
                        modelCompanyDeatils.setMinimum_Order(R.string.minimum_order);
                        modelCompanyDeatils.setCar_Gps(R.string.car_companies);
                        modelCompanyDeatils.setDelivery_Gps(R.string.gps_delevary);
                        modelCompanyDeatils.setCommentsNumber(jsonObjectCompanies.getString("comment_count"));
                        modelCompanyDeatils.setReviewsNumber(jsonObjectCompanies.getString("review_count"));
                        modelCompanyDeatils.setMunimum_Order_Number(jsonObjectCompanies.getString("min_price") + "$");
                        //check here
                        if(!(jsonObjectCompanies.getString("review_rate") != null)
                                &&!jsonObjectCompanies.getString("review_rate").equalsIgnoreCase("") )
                            modelCompanyDeatils.setRating(jsonObjectCompanies.getDouble("review_rate"));
                        else
                            modelCompanyDeatils.setRating(3);

                        dataModelCompanyDeatils.add(modelCompanyDeatils);
                        companies_itemAdapter = new Companies_itemAdapter(context, dataModelCompanyDeatils);
                        mLayoutManager = new LinearLayoutManager(context
                                , LinearLayoutManager.VERTICAL, false);
                        recyclerview_CompanyDetais.setLayoutManager(mLayoutManager);
                        recyclerview_CompanyDetais.setItemAnimator(new DefaultItemAnimator());
                        recyclerview_CompanyDetais.setAdapter(companies_itemAdapter);
                        companies_itemAdapter.notifyDataSetChanged();

                        Toast.makeText(CompaniesActivity.this, jsonObjectCompanies.getString("min_price"), Toast.LENGTH_SHORT).show();

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





