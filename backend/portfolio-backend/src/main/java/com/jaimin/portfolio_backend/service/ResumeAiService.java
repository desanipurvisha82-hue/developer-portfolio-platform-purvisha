package com.jaimin.portfolio_backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.jaimin.portfolio_backend.dto.ResumeAnalysisDTO;

@Service
public class ResumeAiService {

    // Common technical keywords to check for
    private static final List<String> ATS_KEYWORDS = List.of(
            "Java", "Spring Boot", "React", "Next.js", "TypeScript", "JavaScript",
            "Docker", "Kubernetes", "AWS", "CI/CD", "PostgreSQL", "MySQL", 
            "REST API", "JWT", "Microservices", "Git", "GitLab", "GitHub", 
            "Unit Testing", "Hibernate", "Lombok", "Redis", "Cloud", "Agile"
    );

    public ResumeAnalysisDTO analyzeResume(String resumeText) {
        if (resumeText == null || resumeText.trim().isEmpty()) {
            return ResumeAnalysisDTO.builder()
                    .score(20)
                    .atsScore(15)
                    .strengths(List.of("None detected"))
                    .weaknesses(List.of("Empty Resume: Please upload a valid PDF or paste your resume content."))
                    .missingKeywords(ATS_KEYWORDS)
                    .recommendation("Upload a completed resume containing your profile details, skills, experience, and projects.")
                    .build();
        }

        String textLower = resumeText.toLowerCase();

        List<String> strengths = new ArrayList<>();
        List<String> weaknesses = new ArrayList<>();
        List<String> missingKeywords = new ArrayList<>();
        
        int score = 40; // Base score for non-empty resume

        // 1. Check for Contact Details
        boolean hasEmail = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}").matcher(resumeText).find();
        boolean hasPhone = Pattern.compile("(\\+?\\d{1,3}[- ]?)?\\d{10}").matcher(resumeText).find() || textLower.contains("phone") || textLower.contains("mobile");
        
        if (hasEmail && hasPhone) {
            strengths.add("Contact Information: Email and Phone number are clearly visible.");
            score += 10;
        } else {
            weaknesses.add("Missing Contact Information: Ensure your email and phone number are present and easily readable.");
            score -= 5;
        }

        // 2. Check for key sections
        if (textLower.contains("experience") || textLower.contains("work history") || textLower.contains("employment")) {
            strengths.add("Work Experience Section: Defined career history.");
            score += 15;
        } else {
            weaknesses.add("Missing Work Experience Section: Clearly list your professional experience.");
            score -= 10;
        }

        if (textLower.contains("education") || textLower.contains("university") || textLower.contains("college") || textLower.contains("degree")) {
            strengths.add("Education Section: Stated academic background.");
            score += 10;
        } else {
            weaknesses.add("Missing Education Section: Include your degrees, college names, and graduation dates.");
            score -= 5;
        }

        if (textLower.contains("project") || textLower.contains("portfolio")) {
            strengths.add("Projects Section: Highlighted practical assignments.");
            score += 10;
        } else {
            weaknesses.add("Missing Projects Section: Detail 2-3 technical projects along with the tech stacks utilized.");
            score -= 5;
        }

        if (textLower.contains("skills") || textLower.contains("technolog")) {
            strengths.add("Skills Section: Defined skill list.");
            score += 10;
        } else {
            weaknesses.add("Missing Skills Section: Group your technical skills (e.g. Frontend, Backend, Tools).");
            score -= 10;
        }

        // 3. Keyword Match Analysis
        int foundKeywordsCount = 0;
        for (String keyword : ATS_KEYWORDS) {
            if (textLower.contains(keyword.toLowerCase())) {
                foundKeywordsCount++;
            } else {
                missingKeywords.add(keyword);
            }
        }

        int keywordMatchPercentage = (foundKeywordsCount * 100) / ATS_KEYWORDS.size();
        score += (int) (keywordMatchPercentage * 0.25); // Weight of keyword overlap

        // Bound scores
        int atsScore = Math.max(25, Math.min(score - 5, 98));
        int resumeScore = Math.max(30, Math.min(score, 100));

        if (keywordMatchPercentage > 50) {
            strengths.add("Tech Stack Keyword Density: Strong matching for roles using " + 
                ATS_KEYWORDS.stream().filter(textLower::contains).limit(3).toList());
        } else {
            weaknesses.add("Low Keyword Density: Your resume matches less than 50% of typical developer tech terms.");
        }

        // Actionable Recommendation
        String recommendation;
        if (atsScore >= 80) {
            recommendation = "Excellent! Your resume is highly optimized for ATS. Consider applying to roles immediately. Keep it updated.";
        } else if (atsScore >= 60) {
            recommendation = "Good! Your resume has core structures. To improve, integrate missing keywords (like " + 
                (missingKeywords.isEmpty() ? "Docker" : missingKeywords.get(0)) + ") and describe your achievements using action verbs.";
        } else {
            recommendation = "Needs Work. Make sure to structure your resume using standard headers (Experience, Projects, Education, Skills). Add key technology terms relative to the roles you are targeting.";
        }

        return ResumeAnalysisDTO.builder()
                .score(resumeScore)
                .atsScore(atsScore)
                .strengths(strengths)
                .weaknesses(weaknesses)
                .missingKeywords(missingKeywords)
                .recommendation(recommendation)
                .build();
    }
}