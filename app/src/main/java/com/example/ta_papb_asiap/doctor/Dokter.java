package com.example.ta_papb_asiap.doctor;

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

//public class Dokter extends AppCompatActivity {

//    RecyclerView _category, _dokter;
//    String cate[];

//    String data1[], data2[], data3[], data4[], data5[];

//    List<KategoriModel> categotyList = new ArrayList<>();
//    KategoriAdapter adapter;
//    DokterAdapter adapter2;

//    static final String PRODUCT_URL = "http://192.168.1.2/rest_api/api/doctor";

//    List<Product> productList;

//    LinearLayoutManager layout;
//    ApiInterface _interface;
//    Dokter _doc;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dokter);
//
//        _category = findViewById(R.id.recycler);
//        _dokter = findViewById(R.id.recyclerDokter);
//

//        cate = getResources().getStringArray(R.array.kategori_dokter);

//
//        layout = new LinearLayoutManager(this);
//        _dokter.setLayoutManager(layout);
//        _interface = ApiClient.getClient().create(ApiInterface.class);
//        _doc = this;
//        refresh();
//    }
//
//    public void refresh() {
//        Call<GetDokter> dokter = _interface.getDokter();
//        dokter.enqueue(new Callback<GetDokter>() {
//            @Override
//            public void onResponse(Call<GetDokter> call, Response<GetKontak>
//                    response) {
//                List<Product> productList = response.body().getListDataKontak();
//                Log.d("Retrofit Get", "Jumlah data Kontak: " +
//                        String.valueOf(productList.size()));
//                adapter2 = new DokterAdapter(productList);
//                layout.setAdapter(adapter2);
//            }
//
//            @Override
//            public void onFailure(Call<GetDokter> call, Throwable t) {
//                Log.e("Retrofit Get", t.toString());
//            }
//        });
//    }

//        loadProducts();

//        data1 = getResources().getStringArray(R.array.nama_dokter);
//        data2 = getResources().getStringArray(R.array.spesialis);
//        data3 = getResources().getStringArray(R.array.rs_dokter);
//        data4 = getResources().getStringArray(R.array.rating);
//        data5 = getResources().getStringArray(R.array.jam);

//        KategoriAdapter adapterCat = new KategoriAdapter(this, cate);
//        _recycler.setAdapter(adapterCat);
//        _recycler.setLayoutManager(new LinearLayoutManager(this));

//        adapter = new KategoriAdapter(this, cate);
//        LinearLayoutManager layout1 = new LinearLayoutManager(getApplicationContext());
//        layout1.setOrientation(LinearLayoutManager.HORIZONTAL);
//        _category.setLayoutManager(layout1);
//        _category.setItemAnimator(new DefaultItemAnimator());
//        _category.setAdapter(adapter);

//        adapter2 = new DokterAdapter(this, data1, data2, data3, data4, data5);
//        LinearLayoutManager layout2 = new LinearLayoutManager(getApplicationContext());
//        layout2.setOrientation(LinearLayoutManager.VERTICAL);
//        _dokter.setLayoutManager(layout2);
//        _dokter.setItemAnimator(new DefaultItemAnimator());
//        _dokter.setAdapter(adapter2);

//    }

//    private void loadProducts() {
//        StringRequest request = new StringRequest(Request.Method.GET, PRODUCT_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONArray dokter = new JSONArray(response);
//
//                            for (int i = 0; i < dokter.length(); i++) {
//                                JSONObject dokterobjek = dokter.getJSONObject(i);
//                                int id = dokterobjek.getInt("id_dokter");
//                                String nama = dokterobjek.getString("Nama");
//                                String spesialis = dokterobjek.getString("Spesialis");
//                                String tempat = dokterobjek.getString("Tempat");
//                                String rating = dokterobjek.getString("Rating");
//                                String profil = dokterobjek.getString("Profil");
//
//                                Product produk = new Product(id, nama, spesialis, tempat, rating, profil);
//                                productList.add(produk);
//                            }
//
//                            adapter2 = new DokterAdapter(Dokter.this, productList);
//                            _dokter.setAdapter(adapter2);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Dokter.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        Volley.newRequestQueue(this).add(request);
//    }
//}

public class Dokter extends AppCompatActivity {

//    List<KategoriAdapter> categotyList = new ArrayList<>();
    KategoriAdapter adapter1;
    String cate[];

    List<DataDokter> result = new ArrayList<DataDokter>();

    RecyclerView _category, _dokter;

    ApiInterface service;
    DokterAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);
        service = ApiClient.getClient().create(ApiInterface.class);
        _dokter = findViewById(R.id.recyclerDokter);

        _category = findViewById(R.id.recycler);
        cate = getResources().getStringArray(R.array.kategori_dokter);

        adapter1 = new KategoriAdapter(this, cate);
        LinearLayoutManager layout1 = new LinearLayoutManager(getApplicationContext());
        layout1.setOrientation(LinearLayoutManager.HORIZONTAL);
        _category.setLayoutManager(layout1);
        _category.setItemAnimator(new DefaultItemAnimator());
        _category.setAdapter(adapter1);

//        Intent dok = getIntent();
//        Glide.with(Dokter.this)
//                .load(ApiClient.BASE_URL + dok.getStringExtra("Foto"));

        _dokter.setLayoutManager(new LinearLayoutManager(Dokter.this));

        adapter2 = new DokterAdapter(this, result);
        _dokter.setAdapter(adapter2);
        refresh();

//        _dokter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), DoctorDetails.class));
//            }
//        });

    }

    private void refresh() {
        Call<GetDokter> dokterCall = service.getDokter();
        dokterCall.enqueue(new Callback<GetDokter>() {
            @Override
            public void onResponse(Call<GetDokter> call, Response<GetDokter> response) {
                result.clear();
                result.addAll(response.body().getListDokter());
                Log.d("Retrofit Get", "Jumlah data Dokter : " +
                        String.valueOf(result.size()));
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GetDokter> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}