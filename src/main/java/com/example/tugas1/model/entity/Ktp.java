package com.example.tugas1.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "KTP")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ktp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nomorKtp", unique = true, nullable = false)
    private String nomorKtp;

    @Column(name = "namaLengkap")
    private String namaLengkap;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "tanggalLahir")
    private LocalDate tanggalLahir;

    @Column(name = "jenisKelamin")
    private String jenisKelamin;
}
