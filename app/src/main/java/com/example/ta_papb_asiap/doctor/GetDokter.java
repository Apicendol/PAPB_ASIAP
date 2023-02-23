package com.example.ta_papb_asiap.doctor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDokter {

//    @SerializedName("status")
    String status;
//    @SerializedName("message")
    String message;

    @SerializedName("data")
//    @SerializedName("result")
//    @SerializedName("response")
    List<DataDokter> listDokter;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataDokter> getListDokter() {
        return listDokter;
    }

    public void setListDokter(List<DataDokter> listDokter) {
        this.listDokter = listDokter;
    }
}
