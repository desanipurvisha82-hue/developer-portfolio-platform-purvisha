import api from "@/lib/api";

export const getExperiences = async () => {
  const response = await api.get("/experiences");
  return response.data;
};

export const createExperience = async (data: any) => {
  const response = await api.post("/experiences", data);
  return response.data;
};

export const updateExperience = async (
  id: number,
  data: any
) => {
  const response = await api.put(
    `/experiences/${id}`,
    data
  );

  return response.data;
};

export const deleteExperience = async (id: number) => {
  const response = await api.delete(
    `/experiences/${id}`
  );

  return response.data;
};