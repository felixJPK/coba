package com.example.final_project;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;
    AutoCompleteTextView autoComplete;
    ArrayList<String> patientNames;
    ArrayAdapter<String> adapter;
    RecyclerView rv;
    List<Dokter> listDokter;
    rvAdapter rvAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = FirebaseDatabase.getInstance();

        autoComplete = findViewById(R.id.autoComplete);
        patientNames = new ArrayList<>();
        ref = database.getReference("patients");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                patientNames.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String nama = snapshot.child("nama").getValue(String.class);
                    if (nama != null) {
                        patientNames.add(nama);
                    }
                }
                adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_dropdown_item_1line, patientNames);
                autoComplete.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        autoComplete.setThreshold(1);

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listDokter = new ArrayList<>();

        ref = database.getReference("doctors");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDokter.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String nama = snapshot.child("nama").getValue(String.class);
                    String speciality = snapshot.child("speciality").getValue(String.class);
                    String experience = snapshot.child("experience").getValue(String.class);
                    String image = snapshot.child("image").getValue(String.class);
                    Log.d("FirebaseData", "Nama: " + nama + ", Speciality: " + speciality + ", Experience: " + experience + ", Image: " + image);
                    if (nama != null && speciality != null && experience != null && image != null) {
                        Dokter dokter = new Dokter(nama, speciality, experience, image);
                        listDokter.add(dokter);
                    }
                }
                rvAd = new rvAdapter(MainActivity2.this, listDokter);
                rv.setAdapter(rvAd);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}