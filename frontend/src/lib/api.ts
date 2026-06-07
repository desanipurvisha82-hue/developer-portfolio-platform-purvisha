import axios from "axios";

// Configure api client with dynamic baseURL and automatic '/api' suffix enforcement
const getBaseURL = () => {
  let url = process.env.NEXT_PUBLIC_BACKEND_URL || process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080/api";
  
  // Strip trailing slash if present
  if (url.endsWith("/")) {
    url = url.slice(0, -1);
  }
  
  // Force /api suffix if not already present
  if (!url.endsWith("/api")) {
    url = url + "/api";
  }
  
  return url;
};

const api = axios.create({
  baseURL: getBaseURL(),
});





api.interceptors.request.use((config) => {
  if (typeof window !== "undefined") {
    const token = localStorage.getItem("token");
    if (token && token !== "undefined" && token !== "null") {
      config.headers.Authorization = `Bearer ${token}`;
    }
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && (error.response.status === 401 || error.response.status === 403)) {
      if (typeof window !== "undefined" && window.location.pathname.startsWith("/admin")) {
        localStorage.removeItem("token");
        window.location.href = "/login";
      }
    }
    return Promise.reject(error);
  }
);

export default api;