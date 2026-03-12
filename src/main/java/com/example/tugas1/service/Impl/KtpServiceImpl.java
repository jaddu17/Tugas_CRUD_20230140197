package com.example.tugas1.service.Impl;

import com.example.tugas1.mapper.KtpMapper;
import com.example.tugas1.model.dto.KtpAddRequest;
import com.example.tugas1.model.dto.KtpDto;
import com.example.tugas1.model.entity.Ktp;
import com.example.tugas1.repository.KtpRepository;
import com.example.tugas1.service.KtpService;
import com.example.tugas1.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository ktpRepository;

    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public KtpDto AddKtp(KtpAddRequest request) {
        validationUtil.validate(request);

        if (ktpRepository.existsByNomorKtp(request.getNomorKtp())) {
            throw new RuntimeException("Nomor KTP already exists: " + request.getNomorKtp());
        }

        Ktp saveKtp = Ktp.builder()
                .nomorKtp(request.getNomorKtp())
                .namaLengkap(request.getNamaLengkap())
                .alamat(request.getAlamat())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .build();

        ktpRepository.save(saveKtp);

        return KtpMapper.MAPPER.toUserDtoData(saveKtp);
    }

    @Override
    public List<KtpDto> getAllKtp() {
        List<Ktp> ktps = ktpRepository.findAll();
        List<KtpDto> ktpDtos = new ArrayList<>();
        for (Ktp ktp : ktps) {
            ktpDtos.add(KtpMapper.MAPPER.toUserDtoData(ktp));
        }
        return ktpDtos;
    }

    @Override
    public KtpDto getKtpById(Integer id) {
        Ktp ktp = ktpRepository.findById(id).orElseThrow(() -> new RuntimeException("KTP not found"));
        return KtpMapper.MAPPER.toUserDtoData(ktp);
    }

    @Override
    public KtpDto UpdateKtp(Integer id, KtpAddRequest request) {
        validationUtil.validate(request);

        Ktp existingKtp = ktpRepository.findById(id).orElseThrow(() -> new RuntimeException("KTP not found"));

        if (!existingKtp.getNomorKtp().equals(request.getNomorKtp())
                && ktpRepository.existsByNomorKtp(request.getNomorKtp())) {
            throw new RuntimeException("Nomor KTP already exists: " + request.getNomorKtp());
        }

        Ktp ktp = Ktp.builder()
                .id(existingKtp.getId())
                .nomorKtp(request.getNomorKtp())
                .namaLengkap(request.getNamaLengkap())
                .alamat(request.getAlamat())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .build();

        ktpRepository.save(ktp);

        return KtpMapper.MAPPER.toUserDtoData(ktp);
    }

    @Override
    public void DeleteKtp(Integer id) {
        Ktp ktp = ktpRepository.findById(id).orElseThrow(() -> new RuntimeException("KTP not found"));
        ktpRepository.delete(ktp);
    }
}
