package com.example.tugas1.mapper;

import com.example.tugas1.model.dto.KtpDto;
import com.example.tugas1.model.entity.Ktp;
import org.springframework.stereotype.Component;

@Component
public class KtpMapper {

    public static final KtpMapper MAPPER = new KtpMapper();

    public Ktp toEntity(KtpDto dto) {
        if (dto == null) return null;
        return Ktp.builder()
                .id(dto.getId())
                .nomorKtp(dto.getNomorKtp())
                .namaLengkap(dto.getNamaLengkap())
                .alamat(dto.getAlamat())
                .tanggalLahir(dto.getTanggalLahir())
                .jenisKelamin(dto.getJenisKelamin())
                .build();
    }

    public KtpDto toUserDtoData(Ktp ktp) {
        if (ktp == null) return null;
        return KtpDto.builder()
                .id(ktp.getId())
                .nomorKtp(ktp.getNomorKtp())
                .namaLengkap(ktp.getNamaLengkap())
                .alamat(ktp.getAlamat())
                .tanggalLahir(ktp.getTanggalLahir())
                .jenisKelamin(ktp.getJenisKelamin())
                .build();
    }
}
