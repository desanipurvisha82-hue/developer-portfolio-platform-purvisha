"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { login } from "../../src/services/authService";
import Swal from "sweetalert2";

export default function LoginPage() {
  const router = useRouter();

  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await login(formData);

      localStorage.setItem("token", response.token);

      Swal.fire({
        title: "Welcome Back!",
        text: "Logged in successfully.",
        icon: "success",
        timer: 1500,
        showConfirmButton: false,
        background: "#0f172a",
        color: "#ffffff",
      });

      router.push("/admin");
    } catch (error) {
      console.error(error);
      Swal.fire({
        title: "Login Failed",
        text: "Invalid email or password credentials.",
        icon: "error",
        background: "#0f172a",
        color: "#ffffff",
        confirmButtonColor: "#ef4444",
      });
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-slate-950">
      <form
        onSubmit={handleSubmit}
        className="bg-slate-900 p-8 rounded-xl w-full max-w-md space-y-4"
      >
        <h1 className="text-3xl font-bold text-white">
          Admin Login
        </h1>

        <input
          type="email"
          placeholder="Email"
          className="w-full p-3 rounded bg-slate-800 text-white"
          onChange={(e) =>
            setFormData({
              ...formData,
              email: e.target.value,
            })
          }
        />

        <input
          type="password"
          placeholder="Password"
          className="w-full p-3 rounded bg-slate-800 text-white"
          onChange={(e) =>
            setFormData({
              ...formData,
              password: e.target.value,
            })
          }
        />

        <button
          type="submit"
          className="w-full bg-cyan-500 text-black py-3 rounded"
        >
          Login
        </button>

        <p className="text-center text-white">
          Don't have an account?{" "}
          <a href="/register" className="text-cyan-400 hover:underline">
            Register
          </a>
        </p>
      </form>
    </div>
  );
}