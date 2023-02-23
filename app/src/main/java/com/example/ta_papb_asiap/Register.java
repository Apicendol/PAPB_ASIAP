package com.example.ta_papb_asiap;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ta_papb_asiap.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth _auth;
    FirebaseDatabase db;
    DatabaseReference _user;
    Button _daftar;
    TextView _login;
    EditText _username, _txtEmail, _txtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        _username = findViewById(R.id.username);
        _txtEmail = findViewById(R.id.email);
        _txtPassword = findViewById(R.id.password);

        _daftar = findViewById(R.id.masuk);
        _daftar.setOnClickListener(this);

        _login = findViewById(R.id.txtDaftar);
        _login.setOnClickListener(this);

        _auth = FirebaseAuth.getInstance();

        db = FirebaseDatabase.getInstance();
        _user = db.getReference("User");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == _login.getId()) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        } else if (v.getId() == _daftar.getId()) {
            String email = _txtEmail.getText().toString();
            String pass = _txtPassword.getText().toString();

            RegisterUser(email, pass);
        }
    }

    private void RegisterUser(String email, String pass) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Masukkan Email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Masukkan Password", Toast.LENGTH_SHORT).show();
        } else {
            _auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        User user = new User();
                        user.setNama(_username.getText().toString());
                        user.setEmail(_txtEmail.getText().toString());
                        user.setPassword(_txtPassword.getText().toString());
                        user.setAntrian("00");

                        _user.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        FirebaseUser user = _auth.getCurrentUser();
                                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(Register.this, "Email verifikasi dikirim ke " + email, Toast.LENGTH_SHORT).show();
                                                Intent regis = new Intent(getApplicationContext(), Login.class);

                                                String _email = _txtEmail.getText().toString();

                                                regis.putExtra("email", _email);
                                                setResult(RESULT_OK, regis);
                                                startActivity(regis);
                                                finish();
                                            }
                                        });
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Register.this, "Registrasi Gagal" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
    }
}