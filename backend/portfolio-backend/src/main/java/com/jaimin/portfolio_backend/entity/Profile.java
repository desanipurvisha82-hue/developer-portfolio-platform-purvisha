        package com.jaimin.portfolio_backend.entity;

        import jakarta.persistence.*;
        import lombok.*;

        @Entity
        @Table(name = "profiles")
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public class Profile {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

            private String fullName;

            private String headline;

            @Column(length = 5000)
            private String about;

            private String email;

            private String phone;

            private String location;

            private String linkedinUrl;

            private String githubUrl;

            private String resumeUrl;

            private String profileImageUrl;

            @Column(columnDefinition = "TEXT")
            private String resumeText;

            private String resumeName;
        }