package com.example.ta_papb_asiap.hospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ta_papb_asiap.R;
import com.example.ta_papb_asiap.api.ApiClient;

import java.util.List;

public class RSAdapter extends RecyclerView.Adapter<RSAdapter.MyViewHolder> {

    List<DataRS> result;
    Context context;

    public RSAdapter(Context context, List<DataRS> result) {
        this.result = result;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.hospital_column,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataRS res = result.get(position);
        holder._nama.setText(res.getNama());
        holder._alamat.setText(res.getAlamat());
        Glide.with(context)
                .load(ApiClient.BASE_URL+res.getFoto())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView _nama, _alamat;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);

            _nama = itemView.findViewById(R.id.name);
            _alamat = itemView.findViewById(R.id.alamat);

            img = itemView.findViewById(R.id.pp);
            img.setImageResource(R.drawable.ic_launcher_background);
        }
    }
}

