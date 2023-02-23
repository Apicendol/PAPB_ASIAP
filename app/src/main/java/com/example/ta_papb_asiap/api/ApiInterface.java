package com.example.ta_papb_asiap.api;

import com.example.ta_papb_asiap.doctor.GetDokter;
import com.example.ta_papb_asiap.hospital.GetRS;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("dokter")
    Call<GetDokter> getDokter();

    @GET("dokter?id_Dokter={id_Dokter}")
    Call<GetDokter> getDokter(@Query("id_Dokter") String id_Dokter);

    @GET("rumahsakit")
    Call<GetRS> getRS();

    @GET("rumahsakit?id_rs={id_rs}")
    Call<GetRS> getRS(@Query("id_rs") String id_rs);

}
