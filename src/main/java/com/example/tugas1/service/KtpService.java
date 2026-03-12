package com.example.tugas1.service;

import com.example.tugas1.model.dto.KtpAddRequest;
import com.example.tugas1.model.dto.KtpDto;
import java.util.List;

public interface KtpService {
    KtpDto AddKtp(KtpAddRequest request);

    List<KtpDto> getAllKtp();

    KtpDto getKtpById(Integer id);

    KtpDto UpdateKtp(Integer id, KtpAddRequest request);

    void DeleteKtp(Integer id);
}
