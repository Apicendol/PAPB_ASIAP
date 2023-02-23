package com.example.ta_papb_asiap;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ta_papb_asiap.doctor.Dokter;
import com.example.ta_papb_asiap.hospital.RumahSakit;

public class Home extends AppCompatActivity {

    ImageButton _dokter, _emergency, _obat, _rs;
    Dialog ambulan, fitur;
    Button _call;
    ImageButton _profile, _home, _calendar;

    TextView _id;
    int _no = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        _id = findViewById(R.id.id);

        _dokter = findViewById(R.id.cariDokter);
        _rs = findViewById(R.id.cariRS);

        _calendar = findViewById(R.id.schedule);
        _profile = findViewById(R.id.profile);

        ambulan = new Dialog(Home.this);
        fitur = new Dialog(Home.this);

        _dokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Dokter.class));
            }
        });

        _rs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RumahSakit.class));
            }
        });

        _profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });

        _calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent que = new Intent(getApplicationContext(), Queue.class);

                _no++;

                _id.setText(String.format("%02d", _no));
                String num = _id.getText().toString();

//                String num = String.format("%02d", _no);

                String noAntri = getIntent().getStringExtra("no");
                que.putExtra("no", noAntri);

//                String cancel = getIntent().getStringExtra("zero");
//                que.putExtra("zero", cancel);

                que.putExtra("antri", num);
//                view.getContext().startActivity(que);
                startActivity(que);
//                finish();
            }
        });

        _emergency = findViewById(R.id.emergency);
        _emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambulan.setContentView(R.layout.call_emergency);
                ambulan.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                _call = ambulan.findViewById(R.id.call);
                _call.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Uri uri = Uri.parse("tel:118");
                        Intent it = new Intent(Intent.ACTION_DIAL, uri);
                        startActivity(it);
                        return true;
                    }
                });

                ambulan.show();
            }
        });

        _obat = findViewById(R.id.beliObat);
        _obat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fitur.setContentView(R.layout.fitur_unavailable);
                fitur.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                fitur.show();
            }
        });
    }
}