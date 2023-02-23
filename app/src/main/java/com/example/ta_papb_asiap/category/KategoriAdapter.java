package com.example.ta_papb_asiap.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ta_papb_asiap.R;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.MyViewHolder> {

    String cate1[], data2[];
    Context context;

    public KategoriAdapter(Context ct, String cate[]) {
        context = ct;
        cate1 = cate;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View a = inflater.inflate(R.layout.category_column, parent, false);
        return new MyViewHolder(a);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.cat.setText(cate1[position]);
    }
    @Override
    public int getItemCount() {
        return cate1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cat = itemView.findViewById(R.id.category);
        }
    }
}
