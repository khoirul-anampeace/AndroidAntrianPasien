package com.example.androidantrianpasien.Model;

import java.io.Serializable;

public class ItemDokter implements Serializable {

    String id, kode_dokter, kode_poli, nama_dokter;


    public String getId() {
        return id;
    }

    public String getKode_dokter() {
        return kode_dokter;
    }

    public String getKode_poli() {
        return kode_poli;
    }

    public String getNama_dokter() {
        return nama_dokter;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setKode_dokter(String kode_dokter) {
        this.kode_dokter = kode_dokter;
    }

    public void setKode_poli(String kode_poli) {
        this.kode_poli = kode_poli;
    }

    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }
}
