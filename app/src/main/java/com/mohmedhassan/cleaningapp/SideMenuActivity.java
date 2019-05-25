package com.mohmedhassan.cleaningapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mohmedhassan.cleaningapp.Companies.CompaniesActivity;
import com.mohmedhassan.cleaningapp.Login.LoginActivity;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class SideMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    LinearLayout Linearlayour_CarWash,Linearlayour_HouseCleaning,Linearlayour_Landry;
    CircleImageView ImageProfile;
    ImageView imageViewGoProfile;
    TextView textViewGoProfile;
    Button btn_sign_out;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);


        Linearlayour_CarWash = findViewById(R.id.linearlayout_car_wash);
        Linearlayour_HouseCleaning = findViewById(R.id.linearlayout_house_cleaning);
        Linearlayour_Landry = findViewById(R.id.linearlayout_landry);



        SharedPreferences sharedPreferences = getSharedPreferences("names", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
         drawer.addDrawerListener(toggle);
         toggle.syncState();

         NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
         View headerLayout = navigationView.getHeaderView(0);
         ImageProfile = headerLayout.findViewById(R.id.circle_imageview);
         imageViewGoProfile = headerLayout.findViewById(R.id.imageView_go_profile_sideMenu);
         textViewGoProfile = headerLayout.findViewById(R.id.tv_go_profile_sideMenu);
         btn_sign_out = headerLayout.findViewById(R.id.btn_sign_out_side_menu);
         navigationView.setNavigationItemSelectedListener(this);


        Linearlayour_CarWash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SideMenuActivity.this, CompaniesActivity.class);
                startActivity(intent);

            }
        });
       imageViewGoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SideMenuActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });
      /*  btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SideMenuActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();

            }
        });*/
        textViewGoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SideMenuActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });


      ImageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SideMenuActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });



        Linearlayour_HouseCleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        Linearlayour_Landry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }
    private void DevineView() {


    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            onDestroy();
        } else {
            super.onBackPressed();
            onDestroy();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slide_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            // Handle the camera action
        } else if (id == R.id.nav_order) {

        } else if (id == R.id.nav_vouchres) {

        } else if (id == R.id.nav_point) {

        } else if (id == R.id.nav_contact_us) {

        } else if (id == R.id.nav_about_app) {

        } else if (id == R.id.nav_change_language) {



            Intent intent = new Intent(SideMenuActivity.this, ChangeLangaugeAcitivty.class);
            startActivity(intent);

        }else if (R.id.nav_sign_out == item.getActionView().findViewById(R.id.btn_sign_out_side_menu).getId()) {

            SharedPreferences sharedPreferences = getSharedPreferences("names", Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            startActivity(new Intent(SideMenuActivity.this, LoginActivity.class));
            finish();

            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean SelectLanguage() {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.change_language, null);

        Button   btn_arabic = (Button) alertLayout.findViewById(R.id.btn_arabic_changeLanguage);
        Button   btn_english = (Button) alertLayout.findViewById(R.id.btn_english_changeLanguage);

        btn_arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switchLocal(context,"ar",SideMenuActivity.this);

            }
        });


        btn_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchLocal(context,"en",SideMenuActivity.this);



            }
        });

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        // this is set the View from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch

        dialog = alert.create();
        dialog.show();
        return false;

    }

    public static void switchLocal(Context context, String lcode, Activity activity) {
        if (lcode.equalsIgnoreCase(""))
            return;
        Resources resources = context.getResources();
        Locale locale = new Locale(lcode);
        Locale.setDefault(locale);
        android.content.res.Configuration config = new
                android.content.res.Configuration();
        config.locale = locale;
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        //restart base activity
        activity.finish();
        activity.startActivity(activity.getIntent());
    }



}
