export interface Profile {
  id: number;
  fullName: string;
  headline: string;
  about: string;
  email: string;
  location: string;
  githubUrl: string;
  linkedinUrl: string;
}

export interface Skill {
  id: number;
  name: string;
  category: string;
  proficiency: number;
}

export interface Project {
  id: number;
  title: string;
  description: string;
  githubUrl: string;
  liveUrl: string;
}

export interface Experience {
  id: number;
  company: string;
  position: string;
  description: string;
}

export interface Education {
  id: number;
  institution: string;
  degree: string;
}

export interface Certificate {
  id: number;
  title: string;
  issuer: string;
}