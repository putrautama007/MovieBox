package com.pau.putrautama.cataloguemovieuiux;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mBtnMasuk,mBtnCreateAccount;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.et_email_login);
        mPassword = findViewById(R.id.et_password_login);
        mBtnCreateAccount = findViewById(R.id.btn_make_account);
        mBtnMasuk = findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        mBtnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAccount();
            }
        });


        dontHaveAccount();
    }
    private void loginAccount() {
        String email = mEmail.getText().toString().trim();
        String password  = mPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, R.string.enter_email,Toast.LENGTH_LONG).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, R.string.unvalid_email, Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, R.string.enter_pass,Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(LoginActivity.this, R.string.login_succesful, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void dontHaveAccount(){
        mBtnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
