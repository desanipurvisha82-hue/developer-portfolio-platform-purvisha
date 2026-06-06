# AI-Powered Developer Portfolio & Job Recommendation Platform

An enterprise-grade, full-stack, mobile-responsive developer portfolio and job recommendation engine. This platform provides automated ATS optimization metrics, recruiter contact management, visual Kanban boards, and a retro retro-compiler mini-game.

---

## 🚀 Key Feature Highlights

*   **Public Portfolio Dashboard**: High-fidelity UI featuring stats summary counters, radar charts showcasing tech skills categorizations, projects grids, and interactive contact panels.
*   **ATS Job Match Scanner**: Public recruiters can paste any job description. The platform calculates a weighted compatibility score against the candidate's skills, projects, and experiences, listing matching stacks and skills gaps.
*   **Google Meet Integration**: Visitors can schedule a 1-on-1 meeting. The backend automatically generates a Google Meet link and dispatches HTML calendar confirmations to both the visitor and the owner.
*   **Kanban Board Applications Tracker**: Admin panel tracking full CRUD job applications clustered dynamically into Applied, Interview, Offer, Rejected, and Accepted pipelines.
*   **ATS Resume Parser & Diagnostic**: Admin portal to upload PDF resumes, extract plaintext via Apache PDFBox, calculate completeness check metrics, and log ATS enhancements suggestions.
*   **Interactive Arcade Cabinet**: Integrates the "Bug Hunter" retro compiler mini-game styled with neon flows, CRT scanline overlay filters, and responsive touch D-pads for mobile viewports.

---

## 🛠️ Technology Stack

### Frontend Client
*   **Framework**: Next.js 15 (TypeScript)
*   **Styling**: Tailwind CSS
*   **Animation**: Framer Motion & Lucide Icons
*   **Data Visualization**: Recharts
*   **Dialogs & Alerts**: SweetAlert2 (Notion/Linear Glassmorphic Theme)
*   **HTTP Client**: Axios (configured with automated JWT redirection interceptors)

### Backend Services
*   **Framework**: Spring Boot 3 & Java 21
*   **Security**: Spring Security & JSON Web Tokens (JWT)
*   **Database ORM**: Spring Data JPA & Hibernate
*   **Database Engine**: PostgreSQL / MySQL
*   **Document Utility**: Apache PDFBox (PDF parsing)
*   **Notification Engine**: Spring Mail (JavaMailSender)

---

## 🔧 Local Installation & Setup

### Prerequisites
*   Java Development Kit (JDK) 21 or higher
*   Node.js v18 or higher
*   PostgreSQL running locally (Database name: `portfolio_db`)

---

### Step 1: Configure & Start Backend API Server
1.  Navigate into the backend project directory:
    ```bash
    cd backend/portfolio-backend
    ```
2.  Open `src/main/resources/application.properties` and update your PostgreSQL datasource and Gmail credentials:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/portfolio_db
    spring.datasource.username=postgres
    spring.datasource.password=your_db_password

    spring.mail.username=your-gmail-account@gmail.com
    spring.mail.password=your-gmail-app-password
    ```
3.  Build and run the Spring Boot application:
    ```bash
    ./mvnw.cmd spring-boot:run
    ```
    The server will start listening on port `8080`.

---

### Step 2: Configure & Start Next.js Frontend
1.  Navigate into the frontend project directory:
    ```bash
    cd ../../frontend
    ```
2.  Install dependencies:
    ```bash
    npm install
    ```
3.  Start the Next.js development server:
    ```bash
    npm run dev
    ```
    Open your browser and navigate to `http://localhost:3000`.

---

## 📁 Repository Structure
```
c:/Users/SMTC/developer-portfolio-platform/
├── backend/                  # Spring Boot 3 project root
│   └── portfolio-backend/
│       ├── pom.xml           # Dependencies configuration (PDFBox, Mail, JWT)
│       └── src/main/         # Spring MVC controllers, DTOs, services, entities
├── frontend/                 # Next.js 15 client dashboard
│   ├── app/                  # Next.js App Router (Admin pages & Home layout)
│   ├── src/components/       # Layout cards, Arcade, ATS matchers, Sidebars
│   └── src/lib/api.ts        # Axios client & JWT headers interceptor
└── README.md                 # Project roadmap and instructions
```
