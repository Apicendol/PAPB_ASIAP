package com.example.ta_papb_asiap.doctor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataDokter implements Serializable {
    @SerializedName("id_Dokter")
//    @Expose
    private String id;

    @SerializedName("Nama")
//    @Expose
    private String Nama;

    @SerializedName("Spesialis")
//    @Expose
    private String Spesialis;

    @SerializedName("Tempat")
//    @Expose
    private String Tempat;

    @SerializedName("Rating")
//    @Expose
    private String Rating;

    @SerializedName("jam_praktek")
//    @Expose
    private String Jam;

    @SerializedName("hari_praktek")
//    @Expose
    private String Hari;

    @SerializedName("Profil")
//    @Expose
    private String Profil;

    public DataDokter(){}

//    public DataDokter(String nama, String Spesialis, String Tempat, String Rating) {
//        this.Nama = nama;
//        this.Spesialis = Spesialis;
//        this.Tempat = Tempat;
//        this.Rating = Rating;
//    }

    public DataDokter(String id_Dokter, String nama, String Spesialis, String Tempat, String Rating, String Jam, String Hari, String Profil) {
        this.id = id_Dokter;
        this.Nama = nama;
        this.Spesialis = Spesialis;
        this.Tempat = Tempat;
        this.Rating = Rating;
        this.Jam = Jam;
        this.Hari = Hari;
        this.Profil = Profil;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return Nama;
    }

    public String getSpesialis() {
        return Spesialis;
    }

    public String getTempat() {
        return Tempat;
    }

    public String getRating() {
        return Rating;
    }

    public String getProfil() {
        return Profil;
    }

    public String getJam() {
        return Jam;
    }

    public String getHari() {
        return Hari;
    }
}
