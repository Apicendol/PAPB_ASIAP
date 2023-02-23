package com.example.ta_papb_asiap.doctor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ta_papb_asiap.DoctorDetails;
import com.example.ta_papb_asiap.R;
import com.example.ta_papb_asiap.api.ApiClient;

import java.util.List;

//public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.MyViewHolder> {
//
//    List<Product> mproductList;
//
//    public DokterAdapter(List<Product> productList) {
//        mproductList = productList;
//    }

//    String data1[], data2[], data3[], data4[], data5[];
//    Context context;

//    public DokterAdapter(Context ct, String list1[], String list2[], String list3[], String list4[], String list5[]) {
//        context = ct;
//        data1 = list1;
//        data2 = list2;
//        data3 = list3;
//        data4 = list4;
//        data5 = list5;
//    }

//    @NonNull
//    @Override
//    public DokterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View b = inflater.inflate(R.layout.doctor_column, parent, false);
//        return new DokterAdapter.MyViewHolder(b);

//        View b = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_column, parent, false);
//        MyViewHolder mViewHolder = new MyViewHolder(b);
//        return mViewHolder;
//    }

//    @Override
//    public void onBindViewHolder(DokterAdapter.MyViewHolder holder, int position) {
//        holder._name.setText(mproductList.get(position).getId());
//        holder._spesialis.setText(mproductList.get(position).getNama());
//        holder._rs.setText(mproductList.get(position).getTempat());
//        holder._rating.setText(mproductList.get(position).getRating());
//        holder._profil.setText(mproductList.get(position).getNomor());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mIntent = new Intent(view.getContext(), EditActivity.class);
//                mIntent.putExtra("Id", mKontakList.get(position).getId());
//                mIntent.putExtra("Nama", mKontakList.get(position).getNama());
//                mIntent.putExtra("Nomor", mKontakList.get(position).getNomor());
//                view.getContext().startActivity(mIntent);
//            }
//        });

//        holder._name.setText(data1[position]);
//        holder._spesialis.setText(data2[position]);
//        holder._rs.setText(data3[position]);
//        holder._rating.setText(data4[position]);
//        holder._jam.setText(data5[position]);

//    }
//    @Override
//    public int getItemCount() {
//        return mproductList.size();
//    }

//    public class MyViewHolder extends RecyclerView.ViewHolder {

//        TextView mTextViewId, mTextViewNama, mTextViewNomor;

//        TextView _name, _spesialis, _rs, _rating, _jam;

//        TextView _name, _spesialis, _rs, _rating;
//        ImageView _profil;

//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            _name = itemView.findViewById(R.id.name);
//            _spesialis = itemView.findViewById(R.id.category);
//            _rs = itemView.findViewById(R.id.hospital);
//            _rating = itemView.findViewById(R.id.rating);
//            _profil = itemView.findViewById(R.id.pp);
//        }
//    }
//}

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.MyViewHolder> {

    List<DataDokter> result;
    Context context;

    public DokterAdapter(Context context, List<DataDokter> result) {
//        super();
        this.result = result;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.doctor_column, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataDokter res = result.get(position);
        holder._nama.setText(res.getNama());
        holder._spesialis.setText(res.getSpesialis());
        holder._tempat.setText(res.getTempat());
        holder._rating.setText(res.getRating());
        holder._jam.setText(res.getJam() + " WIB");
//        holder.img.setTag(res.getProfil());
//        holder._day.setText(res.getHari());

        Glide.with(context)
                .load(ApiClient.BASE_URL+res.getProfil())
                .fitCenter()
                .into(holder.img);

//        Glide.with(context)
//                .load(ApiClient.BASE_URL+res.getProfil())
//                .into(holder.img);

//        holder._card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                context.startActivity(new Intent(context, DoctorDetails.class).putExtra("data", DataDokter.class));
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DoctorDetails.class);
//                mIntent.putExtra("Id", res.getId());
                mIntent.putExtra("Nama", res.getNama());
                mIntent.putExtra("Kate", res.getSpesialis());
                mIntent.putExtra("Temp", res.getTempat());
                mIntent.putExtra("Jam", res.getJam() + " WIB");
                mIntent.putExtra("Hari", res.getHari() + ",");
                mIntent.putExtra("Rating", res.getRating());
//                mIntent.putExtra("Foto", res.getProfil());
                mIntent.putExtra("Foto", res.getProfil());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView _id, _nama, _spesialis, _tempat, _rating, _jam, _day;
        ImageView img;
        CardView _card;

        public MyViewHolder(View itemView) {
            super(itemView);

            _nama = itemView.findViewById(R.id.name);
            _spesialis = itemView.findViewById(R.id.category);
            _tempat = itemView.findViewById(R.id.hospital);
            _rating = itemView.findViewById(R.id.rating);
            _jam = itemView.findViewById(R.id.jam);

            img = itemView.findViewById(R.id.pp);
//            img.setImageResource(R.drawable.ic_launcher_background);

            _card = itemView.findViewById(R.id.card);
        }
    }
}

