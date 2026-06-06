import api from "@/lib/api";

export const getCertificates = async () => {
  const response = await api.get("/certificates");
  return response.data;
};

export const createCertificate = async (data: any) => {
  const response = await api.post("/certificates", data);
  return response.data;
};

export const updateCertificate = async (
  id: number,
  data: any
) => {
  const response = await api.put(
    `/certificates/${id}`,
    data
  );

  return response.data;
};

export const deleteCertificate = async (
  id: number
) => {
  const response = await api.delete(
    `/certificates/${id}`
  );

  return response.data;
};