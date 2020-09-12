package com.mohmedhassan.cleaningapp.Edit_ProfileUser;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
*/
import com.mohmedhassan.cleaningapp.APIUrl;
import com.mohmedhassan.cleaningapp.HTTP_GET.HttpCall_Get;
import com.mohmedhassan.cleaningapp.HTTP_GET.HttpRequest_Get;
import com.mohmedhassan.cleaningapp.HTTP_POST.HttpCall_Post;
import com.mohmedhassan.cleaningapp.HTTP_POST.HttpRequest_Post;
import com.mohmedhassan.cleaningapp.ProfileActivity;
import com.mohmedhassan.cleaningapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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

//import de.hdodenhof.circleimageview.CircleImageView;

public class Edit_ProfileUserActivity extends AppCompatActivity {


    Bitmap bitmap;
    String imageProfile;
    String AccessToken;
    Bundle b;
    private int GALLERY = 1, CAMERA = 2;
    int PICK_IMAGE_REQUEST = 111;

    EditText Ed_Email, Ed_Mobile, Ed_Age, Ed_Password;
    TextView Tv_Email, Tv_Mobile, Tv_Age, Tv_Password, Tv_Country, Tv_City;
    Button Btn_save_profile;
    ProgressBar progressBar, progressBarImage;
    String Url_iamge = "http://x4to.com/public/api/addImage";
    Spinner Sp_Country, Sp_City;
    private ArrayAdapter<CharSequence> arrayAdapter_Country;
    private ArrayAdapter<CharSequence> arrayAdapter_City;
    ArrayList<country_intity_Country> countryList = new ArrayList<>();
    ArrayList<String> spinnercountry = new ArrayList<>();
    private static final String KEY_EMPTY = "";
    ArrayList<city_intity_Country> cityList = new ArrayList<>();
    ArrayList<String> spinnercity = new ArrayList<>();
    Uri filePath;
    String country_id;
    String AccessCodeCountry, LanguageApp;
    ImageView back_edit_profile, ImageProfile;
    String email, age, password, sp_countryName, sp_cityName, imageProfilee, country_name, Lang;
    int mobile, ImageView;
    private static final int STORAGE_PERMISSION_CODE = 123;
    //  private RequestQueue rQueue;
    private ArrayList<HashMap<String, String>> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


      //  requestMultiplePermissions();


        Ed_Email = findViewById(R.id.ed_email_profile);
        Ed_Mobile = findViewById(R.id.ed_mobile_profile);
        Ed_Age = findViewById(R.id.ed_age_profile);
        Ed_Password = findViewById(R.id.ed_password_profile);
        Tv_Email = findViewById(R.id.tv_eamil_profile);
        Tv_Mobile = findViewById(R.id.tv_mobile_profile);
        Tv_Age = findViewById(R.id.tv_age_profile);
        Tv_Country = findViewById(R.id.tv_country_profile);
        Tv_City = findViewById(R.id.tv_city_profile);
        Tv_Password = findViewById(R.id.tv_password_profile);
        ImageProfile = findViewById(R.id.imageview_profile);
        Sp_Country = findViewById(R.id.sp_country__profile);
        Sp_City = findViewById(R.id.sp_city__profilee);
        Btn_save_profile = findViewById(R.id.btn_save_profile);
        back_edit_profile = findViewById(R.id.back_edit_profile);
        progressBar = findViewById(R.id.m_progress_profile);
        progressBarImage = findViewById(R.id.m_progress_Image_profile);



      //  GetCountry();
        // GetCity(country_id);
        setOnClickListener();


    }



    @SuppressLint("ResourceType")
    private void setOnClickListener() {

        Ed_Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Tv_Email.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Ed_Mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Tv_Mobile.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Ed_Age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Tv_Age.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Ed_Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Tv_Password.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ImageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);


            }
        });

        Sp_Country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                country_id = countryList.get(position).getCountry_id();
                GetCity(country_id);
                Sp_City.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        Btn_save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Edit_ProfileUserActivity.this, ProfileActivity.class);
                startActivity(intent);
                Toast.makeText(Edit_ProfileUserActivity.this, "Upload The Photo Done", Toast.LENGTH_SHORT).show();

                //  UploadProfile();


            }
        });
        back_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Edit_ProfileUserActivity.this, ProfileActivity.class);
                startActivity(intent);


            }
        });

       /* arrayAdapter_Country = ArrayAdapter.createFromResource(this,
                R.array.country, android.R.layout.simple_spinner_item);
        arrayAdapter_Country.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sp_Country.setAdapter(arrayAdapter_Country);

        arrayAdapter_City = ArrayAdapter.createFromResource(this,
                R.array.city, android.R.layout.simple_spinner_item);
        arrayAdapter_City.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sp_City.setAdapter(arrayAdapter_City);
*/

    }



    private void UploadProfile() {

        email = Ed_Email.getText().toString().trim();
        mobile = Integer.parseInt(Ed_Mobile.getText().toString().trim());
        age = Ed_Age.getText().toString().trim();
        password = Ed_Password.getText().toString().trim();
        sp_countryName = Sp_Country.getSelectedItem().toString().trim();
        sp_cityName = Sp_City.getSelectedItem().toString().trim();
        Lang = "ar";
        if (validateInputs()) {

            HashMap<String, String> params = new HashMap<>();
            //Log.d("Verification","Mail: "+mail+" , code: "+verfication_num);
            params.put("token", "LjFklY5VQIP2Xu5wQ3Wt7tvdFTRJl1pwOtqFKITSBkVR9tNyr7kuJ4q7seOQ");
            params.put("lang", Lang);
            params.put("email", email);
            params.put("mobile", String.valueOf(mobile));
            params.put("age", age);
            params.put("country_id", sp_countryName);
            params.put("city_id", sp_cityName);
            params.put("password", password);
            initializeUpdataProfile(false, params);

        } else {

            //  progressBar.setVisibility(View.GONE);
            Toast.makeText(Edit_ProfileUserActivity.this, "try again", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean validateInputs() {

        if (KEY_EMPTY.equals(email)) {
            Ed_Email.setError("email cannot be empty");
            Ed_Email.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(mobile)) {
            Ed_Mobile.setError("mobile cannot be empty");
            Ed_Mobile.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(age)) {
            Ed_Age.setError("age cannot be empty");
            Ed_Age.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(sp_countryName)) {
            Sp_Country.setPrompt("country cannot be empty");
            Sp_Country.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(sp_cityName)) {
            Sp_City.setPrompt("city cannot be empty");
            Sp_City.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(password)) {
            Ed_Password.setError("password cannot be empty");
            Ed_Password.requestFocus();
            return false;
        }
        if (Ed_Mobile.length() < 11) {
            Ed_Mobile.setError("invalid Mobile");
            Ed_Mobile.requestFocus();
            return false;
        }
        if (Ed_Password.length() < 8) {
            Ed_Password.setError("invalid password");
            Ed_Password.requestFocus();
            return false;
        }

        if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            Ed_Email.setError("invalid email address");
            Ed_Email.requestFocus();
            return false;
        }


        return true;
    }


    private void UploadPhoto() {

        String image_Url = StringImage(bitmap);
        Log.d("img", image_Url);
        HashMap<String, String> params = new HashMap<>();
        params.put("token", "LjFklY5VQIP2Xu5wQ3Wt7tvdFTRJl1pwOtqFKITSBkVR9tNyr7kuJ4q7seOQ");
        //   params.put("profile_picture", String.valueOf(imageBytes));
        initializeUpdataPhoto(false, params);

    }

    public String StringImage(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);

    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                //getting image from gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                ImageProfile.setImageBitmap(bitmap);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri contentURI = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
                ImageProfile.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(Edit_ProfileUserActivity.this, "Failed!", Toast.LENGTH_SHORT).show();

            }
        }
    }


    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void GetCountry() {

        // get country
        //  AccessCodeCountry ="LjFklY5VQIP2Xu5wQ3Wt7tvdFTRJl1pwOtqFKITSBkVR9tNyr7kuJ4q7seOQ";
        LanguageApp = "ar";
        HashMap<String, String> params = new HashMap<>();
        //Log.d("Verification","Mail: "+mail+" , code: "+verfication_num);
        params.put("lang", LanguageApp);
        initializeGetCountry(false, params);

    }

    private void GetCity(String country_id) {


        //  AccessCodeCountry = "LjFklY5VQIP2Xu5wQ3Wt7tvdFTRJl1pwOtqFKITSBkVR9tNyr7kuJ4q7seOQ";
        LanguageApp = "ar";
        HashMap<String, String> params = new HashMap<>();
        //Log.d("Verification","Mail: "+mail+" , code: "+verfication_num);
        params.put("lang", LanguageApp);
        params.put("id", country_id);
        initializeGetCity(false, params);


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
        urlConnection.setRequestMethod("GET");
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
    private void initializeGetCountry(final boolean loadMore, HashMap<String, String> params) {

//        try {
//            getJSONObjectFromURL("https://api2.x4to.com/adduser?password=ddd&name=aaa&email=xx");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
       // progressBar.setVisibility(View.VISIBLE);

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

                  //      progressBar.setVisibility(View.GONE);

                        return;

                    }

                 //   progressBar.setVisibility(View.GONE);

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

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Edit_ProfileUserActivity.this,
                            android.R.layout.simple_spinner_item, spinnercountry); //selected item will look like a spinner set from XML
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Sp_Country.setAdapter(spinnerArrayAdapter);
                 //   Toast.makeText(Edit_ProfileUserActivity.this, country, Toast.LENGTH_SHORT).show();
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

    @SuppressLint("StaticFieldLeak")
    private void initializeGetCity(final boolean loadMore, HashMap<String, String> params) {

//        try {
//            getJSONObjectFromURL("https://api2.x4to.com/adduser?password=ddd&name=aaa&email=xx");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
     //   progressBar.setVisibility(View.VISIBLE);

        HttpCall_Get call_get = new HttpCall_Get();
        call_get.setMethodtype(HttpCall_Get.GET);
        call_get.setUrl(APIUrl.BASE_URL + "getCitiesRegister");
        call_get.setParams(params);

        String vv = call_get.getUrl() + params;
        new HttpRequest_Get() {
            @Override
            public void onResponse(StringBuilder response) {
                super.onResponse(response);
//                try {
                try {

                    String city = "";
                    String city_id = "";

                    JSONTokener Xobject = new JSONTokener(response.toString());
                    JSONObject Code = new JSONObject(Xobject);
                    String C = Code.getString("code");

                    if (C.contains("101")) {

                        Toast.makeText(getApplicationContext(), " City already exist  ..", Toast.LENGTH_LONG).show();

                    //    progressBar.setVisibility(View.GONE);

                        return;

                    }
                    progressBar.setVisibility(View.GONE);

                    JSONTokener tokener = new JSONTokener(response.toString());
                    JSONObject data = new JSONObject(tokener);
                    JSONArray object = data.getJSONArray("data");


                    for (int i = 0; i < object.length(); i++) {
                        JSONObject itemCity = object.getJSONObject(i);  //gets the ith Json object of JSONArray
                        city = itemCity.getString("city_name");
                        city_id  = itemCity.getString("id");
                        cityList.add(new city_intity_Country(city,city_id));
                        spinnercity.add(city);
                         Toast.makeText(Edit_ProfileUserActivity.this, city, Toast.LENGTH_SHORT).show();

                    }
                    ArrayAdapter<String> spinnerArrayAdapterCity = new ArrayAdapter<String>(Edit_ProfileUserActivity.this,
                            android.R.layout.simple_spinner_item, spinnercity); //selected item will look like a spinner set from XML
                    spinnerArrayAdapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Sp_City.setAdapter(spinnerArrayAdapterCity);
                    Tv_City.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }.execute(call_get);


    }


    public void trustEveryone2() {
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
  /*  private void uploadMedia() {
        try {

            String charset = "UTF-8";
            File uploadFile1 = new File(“img path“);
            String requestURL = ContactsContract.Contacts.Data.BASE_URL+ ContactsContract.Contacts.Data.URL_UPLOAD_REACTION_TEST;

            MultipartUtility multipart = new MultipartUtility(requestURL, charset);

//            multipart.addHeaderField("User-Agent", "CodeJava");
//            multipart.addHeaderField("Test-Header", "Header-Value");

            multipart.addFormField(“token”, “…..”);

            multipart.addFilePart("uploadedfile", uploadFile1);

            List<String> response = multipart.finish();

            Log.v("rht", "SERVER REPLIED:");

            for (String line : response) {
                Log.v("rht", "Line : "+line);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

   /* private void uploadImage(final Bitmap bitmap) {

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Url_iamge,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        //   Log.d("ressssssoo", new String(response.data));
                        try {

                            JSONObject Code = new JSONObject();
                            String C = Code.getString("code");
                            if (C.contains("101")) {

                                Toast.makeText(getApplicationContext(), " Error Upload Image  ..", Toast.LENGTH_LONG).show();
                                progressBarImage.setVisibility(View.GONE);
                                return;

                            }

                            progressBarImage.setVisibility(View.GONE);

                            JSONObject data = new JSONObject();
                            JSONArray object = data.getJSONArray("data");


                            for (int i = 0; i < object.length(); i++) {

                                //    url = dataobj.optString("profile_picture");

                                Toast.makeText(Edit_ProfileUserActivity.this, "Upload Done", Toast.LENGTH_SHORT).show();
                            }
                            //  Picasso.get().load(url).into(ImageProfile);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error code", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("token", "LjFklY5VQIP2Xu5wQ3Wt7tvdFTRJl1pwOtqFKITSBkVR9tNyr7kuJ4q7seOQ");
                return params;
            }

            *//*
             *pass files using below method
             * *//*
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("profile_picture", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };


        Volley.newRequestQueue(this).add(volleyMultipartRequest);*/

      /*  volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rQueue = Volley.newRequestQueue(Edit_ProfileUserActivity.this);
        rQueue.add(volleyMultipartRequest);*/


    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

  /*  private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(

                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }*/


    @SuppressLint("StaticFieldLeak")
    private void initializeUpdataProfile(final boolean loadMore, HashMap<String, String> params) {

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
        httpCall_post.setUrl(APIUrl.BASE_URL + "updateProfile");
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

                        Toast.makeText(getApplicationContext(), " Error Upload Image  ..", Toast.LENGTH_LONG).show();

                        progressBar.setVisibility(View.GONE);

                        return;

                    }
                    progressBar.setVisibility(View.GONE);


                    Toast.makeText(Edit_ProfileUserActivity.this, "Upload Done", Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }.execute(httpCall_post);


    }

    @SuppressLint("StaticFieldLeak")
    private void initializeUpdataPhoto(final boolean loadMore, HashMap<String, String> params) {

        progressBarImage.setVisibility(View.VISIBLE);


        HttpCall_Post httpCall_post = new HttpCall_Post();
        httpCall_post.setMethodtype(HttpCall_Post.POST);
        httpCall_post.setUrl("http://x4to.com/public/api/addImage");
        httpCall_post.setParams(params);

        String vv = httpCall_post.getUrl() + params;
        new HttpRequest_Post() {
            @Override
            public void onResponse(StringBuilder response) {
                super.onResponse(response);
//                try {
                try {


                    //   String name = "";
                    //    String age = "";
                    String image = "";

                    JSONTokener Xobject = new JSONTokener(response.toString());
                    JSONObject Code = new JSONObject(Xobject);
                    String C = Code.getString("code");

                    if (C.contains("101")) {

                        Toast.makeText(getApplicationContext(), " Error Upload Image  ..", Toast.LENGTH_LONG).show();

                        progressBarImage.setVisibility(View.GONE);

                        return;

                    }

                    progressBarImage.setVisibility(View.GONE);

                    JSONTokener tokener = new JSONTokener(response.toString());
                    JSONObject data = new JSONObject(tokener);
                    JSONArray object = data.getJSONArray("data");


                    for (int i = 0; i < object.length(); i++) {
                        JSONObject itemImage = object.getJSONObject(i);  //gets the ith Json object of JSONArray
                        //    name  = itemCity.getString("name");
                        image = itemImage.getString("profile_picture");
                        //   email  = itemCity.getString("email");


                    }

                    Toast.makeText(Edit_ProfileUserActivity.this, image, Toast.LENGTH_SHORT).show();
                    //  Toast.makeText(Edit_ProfileUserActivity.this, age, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(Edit_ProfileUserActivity.this, email, Toast.LENGTH_SHORT).show();


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