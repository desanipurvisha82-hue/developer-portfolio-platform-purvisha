package com.purvisha.portfolio_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.purvisha.portfolio_backend.dto.CertificateRequest;
import com.purvisha.portfolio_backend.entity.Certificate;
import com.purvisha.portfolio_backend.service.CertificateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping
    public Certificate createCertificate(
            @RequestBody CertificateRequest request) {

        return certificateService.createCertificate(request);
    }

    @GetMapping
    public List<Certificate> getAllCertificates() {

        return certificateService.getAllCertificates();
    }

    @GetMapping("/{id}")
    public Certificate getCertificateById(
            @PathVariable Long id) {

        return certificateService.getCertificateById(id);
    }

    @PutMapping("/{id}")
    public Certificate updateCertificate(
            @PathVariable Long id,
            @RequestBody CertificateRequest request) {

        return certificateService.updateCertificate(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteCertificate(
            @PathVariable Long id) {

        certificateService.deleteCertificate(id);

        return "Certificate Deleted Successfully";
    }
}