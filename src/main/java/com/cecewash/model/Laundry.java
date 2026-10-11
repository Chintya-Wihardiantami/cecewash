package com.cecewash.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "pesanan_laundry")
public class Laundry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nama pelanggan wajib diisi")
    private String namaPelanggan;

    @NotBlank(message = "Nomor HP wajib diisi")
    @Pattern(regexp = "[0-9]{9,15}", message = "Nomor HP harus berisi 9 sampai 15 angka")
    private String nomorHp;

    @NotBlank(message = "Jenis layanan wajib dipilih")
    private String jenisLayanan;

    @NotNull(message = "Berat pakaian wajib diisi")
    @DecimalMin(value = "0.1", message = "Berat minimal 0.1 kg")
    private Double berat;

    private Integer totalHarga;

    private String status = "BARU";

    public Laundry() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public String getJenisLayanan() {
        return jenisLayanan;
    }

    public void setJenisLayanan(String jenisLayanan) {
        this.jenisLayanan = jenisLayanan;
    }

    public Double getBerat() {
        return berat;
    }

    public void setBerat(Double berat) {
        this.berat = berat;
    }

    public Integer getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Integer totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
