import api from "@/lib/api";

export const matchJob = async (description: string) => {
  const response = await api.post("/ai/match-job", {
    description,
  });
  return response.data;
};