package com.example.androidantrianpasien.Model;

import java.io.Serializable;

public class ItemPoliklinik implements Serializable {

    String id, kode_poli, nama_poli;


    public String getId() {
        return id;
    }

    public String getKode_poli() {
        return kode_poli;
    }

    public String getNama_poli() {
        return nama_poli;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setKode_poli(String kode_poli) {
        this.kode_poli = kode_poli;
    }

    public void setNama_poli(String nama_poli) {
        this.nama_poli = nama_poli;
    }
}
