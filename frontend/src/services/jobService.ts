import api from "@/lib/api";

export const getJobs = async (country: string = "in", keyword: string = "Developer", remote: boolean = false) => {
  const response = await api.get("/jobs/search", {
    params: {
      keyword,
      country,
      remote,
    },
  });
  return response.data;
};