package com.example.ta_papb_asiap.hospital;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetRS {

    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;

    @SerializedName("data")
//    @SerializedName("result")
    List<DataRS> listRS;

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

    public List<DataRS> getListRS() {
        return listRS;
    }

    public void setListRS(List<DataRS> listRS) {
        this.listRS = listRS;
    }
}
