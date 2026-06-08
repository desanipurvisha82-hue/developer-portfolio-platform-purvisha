    package com.purvisha.portfolio_backend.service;

    import org.springframework.stereotype.Service;

    import com.purvisha.portfolio_backend.dto.ProfileRequest;
    import com.purvisha.portfolio_backend.entity.Profile;
    import com.purvisha.portfolio_backend.repository.ProfileRepository;

    import lombok.RequiredArgsConstructor;

    @Service
    @RequiredArgsConstructor
    public class ProfileService {

        private final ProfileRepository profileRepository;

        public Profile createOrUpdateProfile(ProfileRequest request) {

            Profile profile;

            if (profileRepository.count() > 0) {
                profile = profileRepository.findAll().get(0);
            } else {
                profile = new Profile();
            }

            profile.setFullName(request.getFullName());
            profile.setHeadline(request.getHeadline());
            profile.setAbout(request.getAbout());
            profile.setEmail(request.getEmail());
            profile.setPhone(request.getPhone());
            profile.setLocation(request.getLocation());
            profile.setLinkedinUrl(request.getLinkedinUrl());
            profile.setGithubUrl(request.getGithubUrl());
            profile.setResumeUrl(request.getResumeUrl());
            profile.setProfileImageUrl(request.getProfileImageUrl());

            return profileRepository.save(profile);
        }

        public Profile getProfile() {

            return profileRepository.findAll()
                    .stream()
                    .findFirst()
                    .orElse(Profile.builder()
                            .fullName("purvisha Patel")
                            .headline("Full Stack AI Developer")
                            .about("I build production-ready, AI-powered applications with Next.js and Spring Boot. Specialized in ATS optimization and job search integration.")
                            .email("purvisha@example.com")
                            .phone("+91 9876543210")
                            .location("Mumbai, India")
                            .githubUrl("https://github.com")
                            .linkedinUrl("https://linkedin.com")
                            .profileImageUrl("")
                            .resumeUrl("")
                            .resumeText("Name: purvisha Patel\nRole: Full Stack AI Developer\nSkills: Java, Spring Boot, React, Next.js, Docker, Kubernetes, AWS, TypeScript, REST APIs, PostgreSQL, MySQL\nExperience: 3 years building web apps")
                            .resumeName("purvisha_resume.pdf")
                            .build());
        }

        public Profile saveProfile(Profile profile) {
            return profileRepository.save(profile);
        }
    }