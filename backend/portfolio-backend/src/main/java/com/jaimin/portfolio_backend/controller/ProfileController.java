package com.jaimin.portfolio_backend.controller;

import org.springframework.web.bind.annotation.*;

import com.jaimin.portfolio_backend.dto.ProfileRequest;
import com.jaimin.portfolio_backend.entity.Profile;
import com.jaimin.portfolio_backend.service.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public Profile createProfile(
            @RequestBody ProfileRequest request) {

        return profileService.createOrUpdateProfile(request);
    }

    @GetMapping
    public Profile getProfile() {
        return profileService.getProfile();
    }

    @PutMapping
    public Profile updateProfile(
            @RequestBody ProfileRequest request) {

        return profileService.createOrUpdateProfile(request);
    }
}