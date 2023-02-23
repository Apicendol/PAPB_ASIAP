package com.example.ta_papb_asiap;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ta_papb_asiap.api.ApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class DoctorDetails extends AppCompatActivity {

    Button _tanggal, _confirm;
    Dialog _num;
    DatePickerDialog _date;
    int _angka = 1;
    ImageView _img, _pp;
    TextView _que, _name, _cat, _place, _day, _time, _rating;
    DatabaseReference db;
    FirebaseAuth _auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        _num = new Dialog(this);
        _pp = findViewById(R.id.profil);
        _name = findViewById(R.id.name);
        _cat = findViewById(R.id.category);
        _place = findViewById(R.id.hospital);
        _day = findViewById(R.id.day);
        _time = findViewById(R.id.jam);
        _rating = findViewById(R.id.rating);

        _auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference().child("User");

        Intent in = getIntent();
        _name.setText(in.getStringExtra("Nama"));
        _cat.setText(in.getStringExtra("Kate"));
        _place.setText(in.getStringExtra("Temp"));
        _day.setText(in.getStringExtra("Hari"));
        _time.setText(in.getStringExtra("Jam"));
        _rating.setText(in.getStringExtra("Rating"));

        Glide.with(DoctorDetails.this)
                .load(ApiClient.BASE_URL + in.getStringExtra("Foto"))
                .fitCenter()
                .apply(new RequestOptions().override(500,580))
                .into(_pp);

        _confirm = findViewById(R.id.confirm);
        _confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _num.setContentView(R.layout.queue_confirm);
                _num.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                _que = _num.findViewById(R.id.noUrut);

                _angka++;

                _que.setText(String.format("%02d", _angka));

                String queue = _que.getText().toString();

                HashMap<String, Object> users = new HashMap<>();
                users.put("antrian", queue);
                db.child(_auth.getCurrentUser().getUid()).updateChildren(users);

                Toast.makeText(DoctorDetails.this, "Antrian Berhasil", Toast.LENGTH_SHORT).show();

                _img = _num.findViewById(R.id.circle);
                _img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent que = new Intent(view.getContext(), Home.class);
                        que.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(que);
                        finish();
                    }
                });
                _num.show();
            }
        });

        _tanggal = findViewById(R.id.tanggal);
        _tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                _date = new DatePickerDialog(DoctorDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        _tanggal.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);

                        String date = _tanggal.getText().toString();

                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("tanggal", date);
                        db.child(_auth.getCurrentUser().getUid()).updateChildren(userMap);

                    }
                }, mYear, mMonth, mDay);
                _date.show();
            }
        });
    }
}