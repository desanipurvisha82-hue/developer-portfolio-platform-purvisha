"use client";

import { useEffect, useState } from "react";
import {
  FolderKanban,
  Code2,
  Briefcase,
  Award,
  GraduationCap,
  FileText,
  MessageSquare,
  ClipboardList,
  Gauge,
} from "lucide-react";

import {
  ResponsiveContainer,
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  PieChart,
  Pie,
  Cell,
} from "recharts";

import { getDashboardStats } from "@/services/dashboardService";

export default function AdminDashboard() {
  const [stats, setStats] = useState({
    projects: 0,
    skills: 0,
    experiences: 0,
    educations: 0,
    certificates: 0,
    applications: 0,
    profileScore: 0,
    atsScore: 0,
  });

  useEffect(() => {
    loadStats();
  }, []);

  const loadStats = async () => {
    try {
      const data = await getDashboardStats();
      setStats(data);
    } catch (error) {
      console.error(error);
    }
  };

  const cards = [
    {
      title: "Projects",
      value: stats.projects,
      icon: FolderKanban,
      color: "from-cyan-500 to-blue-500",
    },
    {
      title: "Skills",
      value: stats.skills,
      icon: Code2,
      color: "from-purple-500 to-pink-500",
    },
    {
      title: "Experience",
      value: stats.experiences,
      icon: Briefcase,
      color: "from-green-500 to-emerald-500",
    },
    {
      title: "Education",
      value: stats.educations,
      icon: GraduationCap,
      color: "from-yellow-500 to-orange-500",
    },
    {
      title: "Certificates",
      value: stats.certificates,
      icon: Award,
      color: "from-red-500 to-pink-500",
    },
    {
      title: "Applications",
      value: stats.applications,
      icon: ClipboardList,
      color: "from-indigo-500 to-purple-500",
    },
    {
      title: "Profile Score",
      value: stats.profileScore + "%",
      icon: Gauge,
      color: "from-teal-500 to-emerald-500",
    },
    {
      title: "ATS Score",
      value: stats.atsScore + "%",
      icon: FileText,
      color: "from-blue-500 to-cyan-500",
    },
  ];

  const chartData = [
    { name: "Projects", value: stats.projects },
    { name: "Skills", value: stats.skills },
    { name: "Experience", value: stats.experiences },
    { name: "Education", value: stats.educations },
    { name: "Certificates", value: stats.certificates },
    { name: "Applications", value: stats.applications },
  ];

  const COLORS = [
    "#06b6d4",
    "#8b5cf6",
    "#22c55e",
    "#f59e0b",
    "#ef4444",
    "#6366f1",
  ];

  return (
    <div className="space-y-8">
      <div>
        <h1 className="text-5xl font-bold bg-gradient-to-r from-cyan-400 to-blue-500 bg-clip-text text-transparent">
          Dashboard
        </h1>

        <p className="text-slate-400 mt-2">
          Portfolio Analytics & Recruiting Control
        </p>
      </div>

      {/* Cards */}

      <div className="grid gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-8">
        {cards.map((item) => {
          const Icon = item.icon;

          return (
            <div
              key={item.title}
              className="rounded-3xl bg-slate-900 border border-slate-800 p-6 flex flex-col justify-between"
            >
              <div
                className={`w-14 h-14 rounded-2xl bg-gradient-to-r ${item.color}
                flex items-center justify-center`}
              >
                <Icon size={26} />
              </div>

              <div>
                <p className="mt-5 text-slate-400 text-sm">
                  {item.title}
                </p>

                <h2 className="text-3xl font-extrabold mt-2 text-white">
                  {item.value}
                </h2>
              </div>
            </div>
          );
        })}
      </div>

      {/* Charts */}

      <div className="grid lg:grid-cols-2 gap-8">
        <div className="bg-slate-900 rounded-3xl p-6 border border-slate-800">
          <h2 className="text-2xl font-bold mb-6">
            Portfolio Statistics
          </h2>

          <ResponsiveContainer width="100%" height={350}>
            <BarChart data={chartData}>
              <XAxis dataKey="name" />
              <YAxis />
              <Tooltip />
              <Bar dataKey="value" fill="#06b6d4" radius={[8, 8, 0, 0]} />
            </BarChart>
          </ResponsiveContainer>
        </div>

        <div className="bg-slate-900 rounded-3xl p-6 border border-slate-800">
          <h2 className="text-2xl font-bold mb-6">
            Distribution
          </h2>

          <ResponsiveContainer width="100%" height={350}>
            <PieChart>
              <Pie
                data={chartData}
                dataKey="value"
                outerRadius={120}
              >
                {chartData.map((_, index) => (
                  <Cell
                    key={index}
                    fill={COLORS[index]}
                  />
                ))}
              </Pie>
              <Tooltip />
            </PieChart>
          </ResponsiveContainer>
        </div>
      </div>

      {/* Portfolio Health & Summary */}

      <div className="grid lg:grid-cols-2 gap-8">

        {/* Portfolio Health */}

        <div className="bg-slate-900 rounded-3xl p-6 border border-slate-800">
          <h2 className="text-2xl font-bold mb-6">
            Portfolio Health
          </h2>

          <div className="space-y-6">

            <div>
              <div className="flex justify-between mb-2">
                <span>Projects</span>
                <span>{stats.projects}</span>
              </div>

              <div className="h-3 bg-slate-700 rounded-full">
                <div
                  className="h-3 bg-cyan-500 rounded-full"
                  style={{
                    width: `${Math.min(stats.projects * 10, 100)}%`,
                  }}
                />
              </div>
            </div>

            <div>
              <div className="flex justify-between mb-2">
                <span>Skills</span>
                <span>{stats.skills}</span>
              </div>

              <div className="h-3 bg-slate-700 rounded-full">
                <div
                  className="h-3 bg-purple-500 rounded-full"
                  style={{
                    width: `${Math.min(stats.skills * 10, 100)}%`,
                  }}
                />
              </div>
            </div>

            <div>
              <div className="flex justify-between mb-2">
                <span>Certificates</span>
                <span>{stats.certificates}</span>
              </div>

              <div className="h-3 bg-slate-700 rounded-full">
                <div
                  className="h-3 bg-pink-500 rounded-full"
                  style={{
                    width: `${Math.min(stats.certificates * 10, 100)}%`,
                  }}
                />
              </div>
            </div>

          </div>
        </div>

        {/* Quick Summary */}

        <div className="bg-slate-900 rounded-3xl p-6 border border-slate-800">
          <h2 className="text-2xl font-bold mb-6">
            Quick Summary
          </h2>

          <div className="space-y-4">

            <div className="p-4 rounded-xl bg-slate-800">
              🚀 Active Projects: {stats.projects}
            </div>

            <div className="p-4 rounded-xl bg-slate-800">
              💻 Skills Added: {stats.skills}
            </div>

            <div className="p-4 rounded-xl bg-slate-800">
              🎓 Education Records: {stats.educations}
            </div>

            <div className="p-4 rounded-xl bg-slate-800">
              🏆 Certificates: {stats.certificates}
            </div>

            <div className="p-4 rounded-xl bg-slate-800">
              💼 Experience Records: {stats.experiences}
            </div>

          </div>
        </div>

      </div>
    </div>
  );
}