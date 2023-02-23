package com.example.ta_papb_asiap.user;

public class User {
    String email, nama, password;

    String antrian, tanggal;

    public User() {
    }

//    public User(String email, String nama, String password) {
//        this.email = email;
//        this.nama = nama;
//        this.password = password;
//    }

    public User(String email, String nama, String password, String antrian, String tanggal) {
        this.email = email;
        this.nama = nama;
        this.password = password;
        this.antrian = antrian;
        this.tanggal = tanggal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAntrian() {
        return antrian;
    }

    public void setAntrian(String antrian) {
        this.antrian = antrian;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
