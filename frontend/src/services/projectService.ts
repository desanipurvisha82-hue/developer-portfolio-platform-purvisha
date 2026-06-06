import api from "@/lib/api";

export const getProjects = async () => {
  const response = await api.get("/projects");
  return response.data;
};

export const createProject = async (project: any) => {
  const response = await api.post("/projects", project);
  return response.data;
};

export const updateProject = async (
  id: number,
  project: any
) => {
  const response = await api.put(
    `/projects/${id}`,
    project
  );

  return response.data;
};

export const deleteProject = async (
  id: number
) => {
  return api.delete(`/projects/${id}`);
};