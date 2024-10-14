package com.example.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class rvAdapter extends RecyclerView.Adapter {
    Context ctx;
    List<Dokter> listDokter;

    public rvAdapter(Context ctx, List<Dokter> listDokter){
        this.ctx = ctx;
        this.listDokter = listDokter;
    }
    @NonNull
    @Override
    public rvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rvAdapter.ViewHolder holder, int position) {
        Dokter dokter = listDokter.get(position);
        holder.nama.setText(dokter.getNama());
        holder.speciality.setText(dokter.getSpeciality());
        holder.experience.setText(dokter.getExperience());
        Glide.with(ctx).load(dokter.getImage()).into(holder.image);



    }

    @Override
    public int getItemCount() {
        return listDokter.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView nama, speciality, experience;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.imageDokter);
            nama = itemView.findViewById(R.id.namaDokter);
            speciality = itemView.findViewById(R.id.spDokter);
            experience = itemView.findViewById(R.id.expDokter);
        }
    }
}
