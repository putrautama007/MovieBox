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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pau.putrautama.cataloguemovieuiux.model.User;

public class RegisterActivity extends AppCompatActivity {

    EditText mEmail,mPassword,mTlp,mNamaLengkap;
    Button mBtnRegister,mBtnHaveAccount;
    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private User user;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = findViewById(R.id.et_email_register);
        mPassword = findViewById(R.id.et_password_register);
        mTlp = findViewById(R.id.et_phone_register);
        mNamaLengkap = findViewById(R.id.et_nama_register);
        mBtnHaveAccount = findViewById(R.id.btn_have_account);
        mBtnRegister = findViewById(R.id.btn_register);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");


        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount();
            }
        });

        allreadyHaveAccount();
    }
    public void registerAccount(){
        final String namaLengkap = mNamaLengkap.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
        final String password  = mPassword.getText().toString().trim();
        final String noHp = mTlp.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,R.string.enter_email,Toast.LENGTH_LONG).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, R.string.unvalid_email, Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,R.string.enter_pass,Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length()<6){
            Toast.makeText(this, R.string.min_pass, Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(noHp)){
            Toast.makeText(this, R.string.enter_phone,Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(namaLengkap)){
            Toast.makeText(this, R.string.full_name,Toast.LENGTH_LONG).show();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            createAccount(namaLengkap,noHp,email,password);
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            Log.d("Firebase", "signUpWithEmail:success");
                            Toast.makeText(RegisterActivity.this, R.string.register_success, Toast.LENGTH_SHORT).show();
                        } else {

                            if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(RegisterActivity.this, R.string.email_allready_register, Toast.LENGTH_SHORT).show();
                            }else {
                                Log.w( "Firebase", "signUpWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, R.string.register_failed, Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
    }
    public void allreadyHaveAccount(){
        mBtnHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void createAccount(String namaLengkap, String noTlp, String email, String password){
        userId = mAuth.getUid();

        user = new User(namaLengkap,noTlp,email,password);
        mFirebaseDatabase.child(userId).setValue(user);
    }
}
