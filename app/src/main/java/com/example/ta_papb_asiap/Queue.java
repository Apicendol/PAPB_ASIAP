package com.example.ta_papb_asiap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ta_papb_asiap.user.Model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Queue extends AppCompatActivity {

    TextView _kiri, _kanan;
    Button _cancel, _back;
    Intent que;
    DatabaseReference db;
    FirebaseAuth _auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        _kiri = findViewById(R.id.berjalan);
        _kanan = findViewById(R.id.pelanggan);
        _cancel = findViewById(R.id.cancel);
        _back = findViewById(R.id.back);

        _kanan.setText(Model.current.getAntrian());

        _auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference().child("User");

        que = getIntent();
        _kiri.setText(que.getStringExtra("antri"));

//        String kiri = _kiri.getText().toString();
//        String kanan = _kanan.getText().toString();
//
//        if (kiri.equals(kanan)) {
//            Toast.makeText(this, "Giliran Anda", Toast.LENGTH_LONG).show();
//        }

        _cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("antrian").setValue("00");
//                db.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("tanggal").setValue("");
                db.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("tanggal").removeValue();
                Toast.makeText(Queue.this, "Antrian dibatalkan", Toast.LENGTH_SHORT).show();
            }
        });

        _back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), Home.class));
                finish();
            }
        });

        queueInfo();
    }

    private void queueInfo() {
        db.child(_auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    String queue = dataSnapshot.child("antrian").getValue().toString();
                    _kanan.setText(queue);

//                    String kiri = _kiri.getText().toString();
//                    String kanan = _kanan.getText().toString();
//
//                    if (kiri.equals(kanan)) {
//                        Toast.makeText(Queue.this, "Giliran Anda", Toast.LENGTH_LONG).show();
//                    } else if (kanan.equals("00")) {
//                        Toast.makeText(Queue.this, "Antrian belum dibuat", Toast.LENGTH_SHORT).show();
////                        _kiri.setText("00");
//                    }
                } else {
                    db.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("antrian").setValue("00");
                }
                String kiri = _kiri.getText().toString();
                String kanan = _kanan.getText().toString();

                if (kiri.equals(kanan)) {
                    Toast.makeText(Queue.this, "Giliran Anda", Toast.LENGTH_LONG).show();
                }
//                else if (!kiri.equals(kanan)) {
//                    Toast.makeText(Queue.this, "Antrian belum dibuat", Toast.LENGTH_SHORT).show();
//                }
//                else if (kanan.equals("00")) {
//                    Toast.makeText(Queue.this, "Antrian belum dibuat", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}