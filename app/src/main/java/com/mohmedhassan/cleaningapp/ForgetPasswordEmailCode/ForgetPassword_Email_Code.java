package com.mohmedhassan.cleaningapp.ForgetPasswordEmailCode;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mohmedhassan.cleaningapp.ForgetPasswordVerifyCode;
import com.mohmedhassan.cleaningapp.R;


public class ForgetPassword_Email_Code extends AppCompatActivity {

    private static final String KEY_EMPTY = "";
    EditText Ed_emailCode_forgetpassword;
    TextView Tv_emailCode_forgetpassword;
    Button Btn_emailCode_forgetpassword;
    String emailCode,Code,Email;
    ProgressBar m_progress_ForgetPassword_email_code;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password__email__code);

        Ed_emailCode_forgetpassword = findViewById(R.id.ed_emailCode_forgetpassword);
        Tv_emailCode_forgetpassword = findViewById(R.id.tv_eamilCode_forgetpassword);
        Btn_emailCode_forgetpassword = findViewById(R.id.btn_emailCode_forgetpassword);
        m_progress_ForgetPassword_email_code = findViewById(R.id.m_progress_ForgetPassword_email_code);



     /*   b = new Bundle();
        b = getIntent().getExtras();
        Code = b.getString("code");
        Email = b.getString("email");*/

        Ed_emailCode_forgetpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Tv_emailCode_forgetpassword.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        Btn_emailCode_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ForgetPassword_Email_Code.this, ForgetPasswordVerifyCode.class);
                startActivity(intent);

              /*  emailCode = Ed_emailCode_forgetpassword.getText().toString();

              //  Toast.makeText(ForgetPassword_Email_Code.this, "Verify", Toast.LENGTH_SHORT).show();
                m_progress_ForgetPassword_email_code.setVisibility(View.VISIBLE);

                    if(emailCode.equals(Code)){

                        m_progress_ForgetPassword_email_code.setVisibility(View.GONE);
                        Toast.makeText(ForgetPassword_Email_Code.this, "Verify Succefully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgetPassword_Email_Code.this, ForgetPasswordVerifyCode.class);
                        intent.putExtra("inputemail",Email);
                        startActivity(intent);
                        finish();

                    }else {
                        m_progress_ForgetPassword_email_code.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"invalid Code ..",Toast.LENGTH_LONG).show();

                    }*/



            }
        });

    }


    private boolean validateInputs() {

        if (KEY_EMPTY.equals(emailCode)) {
            Ed_emailCode_forgetpassword.setError("Verification Code cannot be empty");
            Ed_emailCode_forgetpassword.requestFocus();
            return false;
        }
        if ((emailCode.length() < 4)) {
            Ed_emailCode_forgetpassword.setError("Invalid Verification Code");
            Ed_emailCode_forgetpassword.requestFocus();
            return false;
        }

        return true;
    }
}
