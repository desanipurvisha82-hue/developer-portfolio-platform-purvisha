import api from "@/lib/api";

export const getEducations = async () => {
  const response = await api.get("/educations");
  return response.data;
};

export const createEducation = async (data: any) => {
  const response = await api.post("/educations", data);
  return response.data;
};

export const updateEducation = async (
  id: number,
  data: any
) => {
  const response = await api.put(
    `/educations/${id}`,
    data
  );

  return response.data;
};

export const deleteEducation = async (
  id: number
) => {
  const response = await api.delete(
    `/educations/${id}`
  );

  return response.data;
};