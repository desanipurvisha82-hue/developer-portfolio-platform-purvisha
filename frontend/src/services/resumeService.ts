import api from "@/lib/api";

export const analyzeResume = async (resumeText: string) => {
  const response = await api.post("/ai/resume-analysis", {
    resume: resumeText,
  });
  return response.data;
};

export const uploadResumePDF = async (file: File) => {
  const formData = new FormData();
  formData.append("file", file);
  
  const response = await api.post("/resumes/upload", formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
  return response.data;
};

export const getResumeDetails = async () => {
  const response = await api.get("/resumes/details");
  return response.data;
};

export const deleteResume = async () => {
  const response = await api.delete("/resumes");
  return response.data;
};