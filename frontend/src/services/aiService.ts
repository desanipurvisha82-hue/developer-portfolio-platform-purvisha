import api from "@/lib/api";

export const generateCoverLetter = async (
  company: string,
  title: string,
  description: string
) => {
  const response = await api.post("/ai/cover-letter", {
    company,
    title,
    description,
  });
  return response.data;
};

export const generateRecruiterEmail = async (
  company: string,
  title: string,
  language: string = "English"
) => {
  const response = await api.post("/ai/recruiter-email", {
    company,
    title,
    language,
  });
  return response.data;
};