package com.example.tugas1.controller;

import com.example.tugas1.model.dto.KtpAddRequest;
import com.example.tugas1.model.dto.KtpDto;
import com.example.tugas1.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ktp")
public class KtpController {

    @Autowired
    private KtpService ktpService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> add(@RequestBody KtpAddRequest request) {
        try {
            KtpDto result = ktpService.AddKtp(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "message", "Data berhasil ditambahkan",
                    "data", result
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> list() {
        List<KtpDto> result = ktpService.getAllKtp();
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Data berhasil diambil",
                "data", result
        ));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> get(@PathVariable("id") Integer id) {
        try {
            KtpDto result = ktpService.getKtpById(id);
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Data berhasil ditemukan",
                    "data", result
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable("id") Integer id,
            @RequestBody KtpAddRequest request
    ) {
        try {
            KtpDto result = ktpService.UpdateKtp(id, request);
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Data berhasil diperbarui",
                    "data", result
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Integer id) {
        try {
            ktpService.DeleteKtp(id);
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Data berhasil dihapus"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }
}
