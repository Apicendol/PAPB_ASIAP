package com.example.ta_papb_asiap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ta_papb_asiap.user.Model;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    TextView _username;
    CircleImageView _pp;
    Button _select, _save, _logout, _back;
    ImageButton _calendar, _home;

    DatabaseReference db;
    FirebaseAuth _auth;

    Uri img;
    String myUri = "";
    StorageTask uploadTask;
    StorageReference storageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        _username = findViewById(R.id.username);
        _pp = findViewById(R.id.pp);
        _select = findViewById(R.id.select);
        _save = findViewById(R.id.submit);
        _logout = findViewById(R.id.logout);
        _back = findViewById(R.id.back);
        _calendar = findViewById(R.id.schedule);
        _home = findViewById(R.id.home);

        _select.setOnClickListener(this);
        _save.setOnClickListener(this);
        _logout.setOnClickListener(this);
        _back.setOnClickListener(this);
        _calendar.setOnClickListener(this);
        _home.setOnClickListener(this);

        _username.setText(Model.current.getNama());

        _auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference().child("User");
        storageProfile = FirebaseStorage.getInstance().getReference().child("Profile Pic");

        getUserinfo();
    }

    private void getUserinfo() {
        db.child(_auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    String name = dataSnapshot.child("nama").getValue().toString();
                    _username.setText(name);
                    if (dataSnapshot.hasChild("image")) {
                        String image = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(_pp);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == _save.getId()) {
            uploadImage();
        } else if (v.getId() == _select.getId()) {
            CropImage.activity().setAspectRatio(1, 1).start(Profile.this);
        } else if (v.getId() == _logout.getId()) {
            FirebaseAuth.getInstance().signOut();
//            startActivity(new Intent(getApplicationContext(), Login.class));
            Intent i = new Intent(Profile.this, Login.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
        } else if (v.getId() == _back.getId() || v.getId() == _home.getId()) {
            finish();
        } else if (v.getId() == _calendar.getId()) {
            startActivity(new Intent(getApplicationContext(), com.example.ta_papb_asiap.Queue.class));
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode  == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            img = result.getUri();
            _pp.setImageURI(img);
        } else {
            Toast.makeText(this, "Upload Gagal", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadImage() {
        if (img != null) {
            final StorageReference file = storageProfile.child(_auth.getCurrentUser().getUid() + ".jpg");
            uploadTask = file.putFile(img);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return file.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri download = task.getResult();
                        myUri = download.toString();

                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("image", myUri);

                        db.child(_auth.getCurrentUser().getUid()).updateChildren(userMap);
                    }
                }
            });
            Toast.makeText(this, "Data Diperbarui", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Penyimpanan Gagal", Toast.LENGTH_SHORT).show();
        }
    }
}