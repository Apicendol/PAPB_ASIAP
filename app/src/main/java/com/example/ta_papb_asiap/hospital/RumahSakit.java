package com.example.ta_papb_asiap.hospital;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ta_papb_asiap.R;
import com.example.ta_papb_asiap.api.ApiClient;
import com.example.ta_papb_asiap.api.ApiInterface;
import com.example.ta_papb_asiap.category.KategoriAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RumahSakit extends AppCompatActivity {

    RecyclerView _category, _rs;
    String cate[];
    KategoriAdapter adapter1;

    List<DataRS> result = new ArrayList<DataRS>();

    ApiInterface service;
    com.example.ta_papb_asiap.hospital.RSAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rumah_sakit);
        service = ApiClient.getClient().create(ApiInterface.class);
        _rs = findViewById(R.id.recyclerRS);

        _category = findViewById(R.id.recycler);

        cate = getResources().getStringArray(R.array.kategori_rs);

//        KategoriAdapter adapterCat = new KategoriAdapter(this, cate);
//        _recycler.setAdapter(adapterCat);
//        _recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter1 = new KategoriAdapter(this, cate);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        _category.setLayoutManager(mLayoutManager);
        _category.setItemAnimator(new DefaultItemAnimator());
        _category.setAdapter(adapter1);

        _rs.setLayoutManager(new LinearLayoutManager(RumahSakit.this));

        adapter2 = new RSAdapter(this, result);
        _rs.setAdapter(adapter2);
        refresh();
    }

    private void refresh() {
        Call<GetRS> rsCall = service.getRS();
        rsCall.enqueue(new Callback<GetRS>() {
            @Override
            public void onResponse(Call<GetRS> call, Response<GetRS> response) {
                result.clear();
                result.addAll(response.body().getListRS());
                Log.d("Retrofit Get", "Jumlah data RS : " +
                        String.valueOf(result.size()));
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GetRS> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}