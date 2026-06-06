import api from "@/lib/api";

export const getSkills = async () => {
  const response = await api.get("/skills");
  return response.data;
};

export const createSkill = async (skill: any) => {
  const response = await api.post("/skills", skill);
  return response.data;
};

export const updateSkill = async (
  id: number,
  skill: any
) => {
  const response = await api.put(
    `/skills/${id}`,
    skill
  );

  return response.data;
};

export const deleteSkill = async (
  id: number
) => {
  const response = await api.delete(
    `/skills/${id}`
  );

  return response.data;
};