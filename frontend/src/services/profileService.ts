import api from "@/lib/api";

export const getProfile = async () => {
  const response = await api.get("/profile");
  return response.data;
};

export const createProfile = async (
  data: any
) => {
  const response = await api.post(
    "/profile",
    data
  );

  return response.data;
};

export const updateProfile = async (
  data: any
) => {
  const response = await api.put(
    "/profile",
    data
  );

  return response.data;
};