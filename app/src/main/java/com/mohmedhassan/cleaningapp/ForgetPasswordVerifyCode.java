package com.mohmedhassan.cleaningapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mohmedhassan.cleaningapp.Forgetpassword_Password.Forgetpassword_Password_Activity;

public class ForgetPasswordVerifyCode extends AppCompatActivity {

    Button Btn_verifyDone_forgetpassword;
    String Email;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_verify_code);

        Btn_verifyDone_forgetpassword = findViewById(R.id.btn_verifyDone_forgetpassword);

/*

        b = new Bundle();
        b = getIntent().getExtras();
        Email = b.getString("inputemail");
*/

        Btn_verifyDone_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(ForgetPasswordVerifyCode.this, Forgetpassword_Password_Activity.class);
                startActivity(intent);

                /*Intent intent =new Intent(ForgetPasswordVerifyCode.this, Forgetpassword_Password_Activity.class);
                intent.putExtra("Code_email",b.getString("inputemail"));
                startActivity(intent);
                finish();*/
            }
        });
    }
}
