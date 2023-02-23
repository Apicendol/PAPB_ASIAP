package com.example.ta_papb_asiap.hospital;

import com.google.gson.annotations.SerializedName;

public class DataRS {
    @SerializedName("id_rs")
//    @Expose
    private String id;

    @SerializedName("nama_rs")
//    @Expose
    private String Nama;

    @SerializedName("alamat_rs")
//    @Expose
    private String Alamat;

    @SerializedName("foto_rs")
//    @Expose
    private String Foto;

    public DataRS(){}

    public DataRS(String id_rs, String nama_rs, String alamat_rs, String foto_rs) {
        this.id = id_rs;
        this.Nama = nama_rs;
        this.Alamat = alamat_rs;
        this.Foto = foto_rs;
    }


    public String getId() {
        return id;
    }

    public String getNama() {
        return Nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public String getFoto() {
        return Foto;
    }
}
