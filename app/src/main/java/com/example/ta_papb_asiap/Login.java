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

import com.example.ta_papb_asiap.user.Model;
import com.example.ta_papb_asiap.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth _auth;
    FirebaseUser _user;
    EditText _txtEmail, _txtPassword;
    Button _login;
    TextView _regis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _txtEmail = findViewById(R.id.emailInput);
        _txtPassword = findViewById(R.id.passInput);

        _login = findViewById(R.id.masuk);
        _login.setOnClickListener(this);

        _regis = findViewById(R.id.txtDaftar);
        _regis.setOnClickListener(this);

        _auth = FirebaseAuth.getInstance();

        Intent in = getIntent();
        _txtEmail.setText(in.getStringExtra("email"));

        _user = _auth.getCurrentUser();
        if (_user != null) {
            if (_user.isEmailVerified()) {
                Intent home = new Intent(this, Home.class);
                home.putExtra("email", _user.getEmail());
                startActivity(home);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == _login.getId()) {
            String email = _txtEmail.getText().toString();
            String pass = _txtPassword.getText().toString();
            SignIn(email, pass);
        } else if (v.getId() == _regis.getId()) {
            Intent regis = new Intent(this, Register.class);
            regis.putExtra("asal", 99);
//            this.startActivityForResult(regis, reqCode);
            startActivity(regis);
            finish();
        }
    }

    private void SignIn(String email, String pass) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Masukkan Email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Masukkan Password", Toast.LENGTH_SHORT).show();
        } else {
//            loading.ma

            _auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
//                                FirebaseUser user = _auth.getCurrentUser();
//                                if (user != null) {
//                                    if (user.isEmailVerified()) {
//                                        Intent home = new Intent(Login.this, Home.class);
//                                        home.putExtra("email", _txtEmail.getText().toString());
//                                        startActivity(home);
//                                    } else {
//                                        Toast.makeText(Login.this, "Not verified", Toast.LENGTH_LONG).show();
//                                    }
//                                }
                                FirebaseDatabase.getInstance().getReference("User")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                FirebaseUser user = _auth.getCurrentUser();
                                                if (user != null) {
                                                    if (user.isEmailVerified()) {
                                                        Model.current = dataSnapshot.getValue(User.class);
//                                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                                        Intent home = new Intent(Login.this, Home.class);
                                                        home.putExtra("email", _txtEmail.getText().toString());
                                                        startActivity(home);
                                                        Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(Login.this, "Not verified", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                            } else {
                                Toast.makeText(Login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}