import api from "@/lib/api";

export const getInterviewQuestions = async (role: string) => {
  const response = await api.post("/ai/interview-questions", { role });
  return response.data;
};