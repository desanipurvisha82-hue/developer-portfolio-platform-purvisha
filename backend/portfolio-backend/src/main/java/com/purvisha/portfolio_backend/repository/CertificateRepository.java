package com.purvisha.portfolio_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.purvisha.portfolio_backend.entity.Certificate;

public interface CertificateRepository
        extends JpaRepository<Certificate, Long> {

}