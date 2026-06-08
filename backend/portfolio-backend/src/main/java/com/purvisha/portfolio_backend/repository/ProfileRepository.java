package com.purvisha.portfolio_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.purvisha.portfolio_backend.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}