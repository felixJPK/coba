package com.example.final_project;

public class Dokter {
    String nama;
    String speciality;
    String experience;
    String image;

    public Dokter(String nama, String speciality, String experience, String image){
        this.nama = nama;
        this.speciality = speciality;
        this.experience = experience;
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getExperience() {
        return experience;
    }

    public String getImage() {
        return image;
    }
}
