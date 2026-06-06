package com.jaimin.portfolio_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaimin.portfolio_backend.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}