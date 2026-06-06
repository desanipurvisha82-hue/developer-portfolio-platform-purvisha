package com.jaimin.portfolio_backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaimin.portfolio_backend.dto.JobDTO;
import com.jaimin.portfolio_backend.dto.SkillMatchResult;
import com.jaimin.portfolio_backend.entity.Experience;
import com.jaimin.portfolio_backend.entity.Profile;
import com.jaimin.portfolio_backend.entity.Project;
import com.jaimin.portfolio_backend.entity.Skill;
import com.jaimin.portfolio_backend.repository.ExperienceRepository;
import com.jaimin.portfolio_backend.repository.ProjectRepository;
import com.jaimin.portfolio_backend.repository.SkillRepository;

@Service
public class JobService {

    private final RestTemplate restTemplate;
    private final AiJobMatchService aiJobMatchService;
    private final SkillRepository skillRepository;
    private final ProjectRepository projectRepository;
    private final ExperienceRepository experienceRepository;
    private final ProfileService profileService;

    @Value("${adzuna.app.id:}")
    private String appId;

    @Value("${adzuna.app.key:}")
    private String appKey;

    public JobService(
            RestTemplate restTemplate,
            AiJobMatchService aiJobMatchService,
            SkillRepository skillRepository,
            ProjectRepository projectRepository,
            ExperienceRepository experienceRepository,
            ProfileService profileService) {
        this.restTemplate = restTemplate;
        this.aiJobMatchService = aiJobMatchService;
        this.skillRepository = skillRepository;
        this.projectRepository = projectRepository;
        this.experienceRepository = experienceRepository;
        this.profileService = profileService;
    }

    public List<JobDTO> getJobs() {
        return searchJobs("Developer", "in", false);
    }

    public List<JobDTO> searchJobs(String keyword, String country, boolean remote) {
        if (country == null || country.isEmpty()) {
            country = "in";
        }
        if (keyword == null || keyword.isEmpty()) {
            keyword = "Developer";
        }

        List<JobDTO> jobList = new ArrayList<>();

        // 1. Try to fetch from live Adzuna API if keys are configured
        if (appId != null && !appId.isEmpty() && appKey != null && !appKey.isEmpty()) {
            try {
                String remoteParam = remote ? "&where=remote" : "";
                String url = "https://api.adzuna.com/v1/api/jobs/"
                        + country.toLowerCase()
                        + "/search/1"
                        + "?app_id=" + appId
                        + "&app_key=" + appKey
                        + "&results_per_page=15"
                        + "&what=" + keyword
                        + "&max_days_old=30"
                        + remoteParam;

                String response = restTemplate.getForObject(url, String.class);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response);
                JsonNode resultsNode = root.path("results");

                if (resultsNode.isArray()) {
                    for (JsonNode node : resultsNode) {
                        String title = node.path("title").asText();
                        String company = node.path("company").path("display_name").asText("Confidential");
                        String location = node.path("location").path("display_name").asText("Remote");
                        String description = node.path("description").asText("");
                        String applyLink = node.path("redirect_url").asText("https://www.adzuna.com");
                        
                        double salaryMin = node.path("salary_min").asDouble(0);
                        double salaryMax = node.path("salary_max").asDouble(0);
                        String salary = "Competitive";
                        if (salaryMin > 0 && salaryMax > 0) {
                            salary = String.format("%.0f - %.0f", salaryMin, salaryMax);
                        } else if (salaryMin > 0) {
                            salary = String.format("From %.0f", salaryMin);
                        }

                        // Call AI matching
                        SkillMatchResult match = calculateMatchForJob(description);

                        jobList.add(JobDTO.builder()
                                .title(title)
                                .company(company)
                                .location(location)
                                .description(description)
                                .applyLink(applyLink)
                                .salary(salary)
                                .matchScore(match.getScore())
                                .matchedSkills(match.getMatchedSkills())
                                .missingSkills(match.getMissingSkills())
                                .recommendation(match.getRecommendation())
                                .roadmap(match.getRoadmap())
                                .recruiterEmail("hr@" + company.toLowerCase().replaceAll("[^a-z]", "") + ".com")
                                .build());
                    }
                }
            } catch (Exception e) {
                System.err.println("Error calling Adzuna API: " + e.getMessage() + ". Using fallback mock jobs.");
            }
        }

        // 2. If live search is empty or failed, load premium mock jobs matching the keyword
        if (jobList.isEmpty()) {
            jobList = generateMockJobs(keyword, country);
        }

        return jobList;
    }

    public String getLiveJobs(String country) {
        if (appId == null || appId.isEmpty() || appKey == null || appKey.isEmpty()) {
            return "{\"results\":[]}";
        }
        String url = "https://api.adzuna.com/v1/api/jobs/"
                + country
                + "/search/1"
                + "?app_id=" + appId
                + "&app_key=" + appKey
                + "&results_per_page=20";

        return restTemplate.getForObject(url, String.class);
    }

    private SkillMatchResult calculateMatchForJob(String description) {
        List<String> userSkills = skillRepository.findAll()
                .stream()
                .map(Skill::getName)
                .collect(Collectors.toList());

        Profile profile = profileService.getProfile();
        String profileHeadline = profile != null ? profile.getHeadline() : "";
        String resumeText = profile != null ? profile.getResumeText() : "";

        String experienceText = experienceRepository.findAll()
                .stream()
                .map(exp -> exp.getPosition() + " at " + exp.getCompany() + ": " + exp.getDescription())
                .collect(Collectors.joining("\n"));

        String projectsText = projectRepository.findAll()
                .stream()
                .map(proj -> proj.getTitle() + ": " + proj.getDescription() + ". Technologies: " + proj.getTechnologies())
                .collect(Collectors.joining("\n"));

        return aiJobMatchService.calculateMatch(
                description,
                userSkills,
                profileHeadline,
                experienceText,
                projectsText,
                resumeText);
    }

    private List<JobDTO> generateMockJobs(String keyword, String country) {
        List<JobDTO> mockList = new ArrayList<>();
        String currency = getCurrencySymbol(country);

        String kw = keyword.toLowerCase();
        if (kw.contains("java") || kw.contains("spring") || kw.contains("backend")) {
            mockList.add(createMockJob("Senior Backend Engineer (Spring Boot)", "Netflix", "Los Gatos, CA", currency + "180,000 - " + currency + "220,000",
                    "Looking for a Senior Java Developer to optimize and build backend streaming services using Java 21, Spring Boot, Spring Security, Microservices, Kafka, Redis, and AWS. Requires experience containerizing services with Docker and deploying to Kubernetes clusters."));
            mockList.add(createMockJob("Spring Boot Developer", "Siemens", "Munich, Germany", currency + "85,000 - " + currency + "95,000",
                    "Siemens is looking for a Spring Boot Developer to join our smart grid team. Core requirements include Java, Spring Boot, Spring Security, Hibernate, JPA, PostgreSQL, REST APIs, and Docker. Experience with CI/CD pipelines and AWS cloud deployment is a plus."));
            mockList.add(createMockJob("Software Engineer - Core Services", "Stripe", "Dublin, Ireland", currency + "110,000 - " + currency + "130,000",
                    "Build reliable, scalable APIs using Java, Spring Boot, PostgreSQL, Redis, and Docker. Experience with JWT tokens, Spring Security, JUnit testing, and AWS architecture. Should be ATS-focused and write high-quality, readable code."));
        } else if (kw.contains("react") || kw.contains("frontend") || kw.contains("next")) {
            mockList.add(createMockJob("Senior Frontend Developer", "Vercel", "Remote", currency + "140,000 - " + currency + "160,000",
                    "Vercel is looking for a Next.js and React expert. Optimize dashboard interfaces, build responsive pages, design modern components, and ensure type safety using TypeScript, Tailwind CSS, and Framer Motion. Knowledge of REST APIs, GraphQL, and static site generation required."));
            mockList.add(createMockJob("UI Developer", "Canva", "Sydney, Australia", currency + "125,000 - " + currency + "145,000",
                    "Design premium user interfaces. Tech stack: React, TypeScript, Tailwind CSS, Recharts for charts, and GSAP/Framer Motion for smooth animations. Experience with state management (Zustand/Redux) and REST API integration."));
        } else {
            // Full stack or generic
            mockList.add(createMockJob("Full Stack Developer (React & Java)", "Linear", "San Francisco, CA", currency + "150,000 - " + currency + "175,000",
                    "Join our core product team. You will work on frontend layouts using React, Next.js, TypeScript, and Tailwind CSS, as well as robust backend REST APIs using Java 21, Spring Boot, Hibernate, JPA, and MySQL. Experience containerizing with Docker, deploy to AWS, and configure CI/CD."));
            mockList.add(createMockJob("Software Developer", "Atlassian", "Sydney, Australia", currency + "115,000 - " + currency + "135,000",
                    "Join Atlassian to build the next generation of collaboration tools. Tech stack includes TypeScript, React, Node.js, Spring Boot, PostgreSQL, Docker, AWS, and Git. Requires clean architecture knowledge and passion for writing unit tests."));
            mockList.add(createMockJob("Junior Full Stack Engineer", "Notion", "San Francisco, CA", currency + "90,000 - " + currency + "110,000",
                    "Notion is seeking a Full Stack Engineer to support editor features. Experience with React, TypeScript, Node.js, Spring Boot, PostgreSQL, Docker, and REST APIs. Familiarity with Tailwind CSS and Framer Motion is a major plus."));
        }

        return mockList;
    }

    private JobDTO createMockJob(String title, String company, String location, String salary, String description) {
        SkillMatchResult match = calculateMatchForJob(description);
        return JobDTO.builder()
                .title(title)
                .company(company)
                .location(location)
                .salary(salary)
                .description(description)
                .matchScore(match.getScore())
                .matchedSkills(match.getMatchedSkills())
                .missingSkills(match.getMissingSkills())
                .recommendation(match.getRecommendation())
                .roadmap(match.getRoadmap())
                .recruiterEmail("hiring@" + company.toLowerCase().replaceAll("[^a-z]", "") + ".com")
                .build();
    }

    private String getCurrencySymbol(String country) {
        switch (country.toLowerCase()) {
            case "us": return "$";
            case "ca": return "C$";
            case "au": return "A$";
            case "gb": return "£";
            case "de": return "€";
            case "in": default: return "₹";
        }
    }
}