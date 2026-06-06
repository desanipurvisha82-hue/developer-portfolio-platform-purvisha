"use client";

import { motion } from "framer-motion";
import {
  ResponsiveContainer,
  RadarChart,
  PolarGrid,
  PolarAngleAxis,
  PolarRadiusAxis,
  Radar,
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
} from "recharts";
import { Code2, Server, Database, Cloud, Star } from "lucide-react";

interface Skill {
  id: number;
  name: string;
  category: string;
  proficiency: number;
}

export default function Skills({ skills }: { skills: Skill[] }) {
  // If no skills are present, render a default set to showcase the portfolio beautifully
  const displaySkills = skills.length > 0 ? skills : [
    { id: 1, name: "Java", category: "Backend", proficiency: 90 },
    { id: 2, name: "Spring Boot", category: "Backend", proficiency: 85 },
    { id: 3, name: "React", category: "Frontend", proficiency: 88 },
    { id: 4, name: "Next.js", category: "Frontend", proficiency: 80 },
    { id: 5, name: "PostgreSQL", category: "Database", proficiency: 82 },
    { id: 6, name: "Docker", category: "DevOps", proficiency: 75 },
    { id: 7, name: "AWS", category: "DevOps", proficiency: 70 },
  ];

  // Group skills by category
  const groupedSkills = displaySkills.reduce((acc, skill) => {
    const cat = skill.category || "General";
    if (!acc[cat]) acc[cat] = [];
    acc[cat].push(skill);
    return acc;
  }, {} as Record<string, Skill[]>);

  // Compute category averages for the chart
  const chartData = Object.keys(groupedSkills).map((cat) => {
    const list = groupedSkills[cat];
    const avg = list.reduce((sum, item) => sum + item.proficiency, 0) / list.length;
    return {
      category: cat,
      Proficiency: Math.round(avg),
    };
  });

  const getCategoryIcon = (category: string) => {
    switch (category.toLowerCase()) {
      case "frontend":
        return <Code2 className="text-cyan-400" size={24} />;
      case "backend":
        return <Server className="text-purple-400" size={24} />;
      case "database":
        return <Database className="text-pink-400" size={24} />;
      case "devops":
      case "cloud":
        return <Cloud className="text-yellow-400" size={24} />;
      default:
        return <Star className="text-emerald-400" size={24} />;
    }
  };

  return (
    <section id="skills" className="max-w-7xl mx-auto px-6 py-24">
      <div className="text-center mb-16">
        <h2 className="text-4xl md:text-5xl font-extrabold bg-gradient-to-r from-cyan-400 to-blue-500 bg-clip-text text-transparent">
          Skills & Expertise
        </h2>
        <p className="text-slate-400 mt-3 max-w-xl mx-auto">
          Deep dive into my tech stack and proficiency ratings across frontend, backend, database, and devops layers
        </p>
      </div>

      <div className="grid lg:grid-cols-12 gap-8 items-start">
        {/* LEFT COLUMN: Skill Categories List */}
        <div className="lg:col-span-7 space-y-6">
          {Object.keys(groupedSkills).map((category) => (
            <motion.div
              key={category}
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.5 }}
              className="bg-slate-900/50 backdrop-blur-xl border border-slate-800/80 rounded-3xl p-6 shadow-xl"
            >
              <h3 className="text-xl font-bold text-white flex items-center gap-3 mb-6">
                <span className="p-2 rounded-xl bg-slate-950 border border-slate-850 flex items-center justify-center">
                  {getCategoryIcon(category)}
                </span>
                {category}
              </h3>

              <div className="space-y-5">
                {groupedSkills[category].map((skill) => (
                  <div key={skill.id} className="space-y-2">
                    <div className="flex justify-between items-center text-sm">
                      <span className="font-semibold text-slate-350">{skill.name}</span>
                      <span className="font-bold text-cyan-400">{skill.proficiency}%</span>
                    </div>

                    <div className="w-full bg-slate-950 border border-slate-850 h-3 rounded-full overflow-hidden">
                      <motion.div
                        initial={{ width: 0 }}
                        whileInView={{ width: `${skill.proficiency}%` }}
                        transition={{ duration: 1, ease: "easeOut" }}
                        className="bg-gradient-to-r from-cyan-500 to-blue-500 h-3 rounded-full"
                      />
                    </div>
                  </div>
                ))}
              </div>
            </motion.div>
          ))}
        </div>

        {/* RIGHT COLUMN: Recharts Distribution Chart */}
        <div className="lg:col-span-5 lg:sticky lg:top-28">
          <motion.div
            initial={{ opacity: 0, scale: 0.95 }}
            whileInView={{ opacity: 1, scale: 1 }}
            transition={{ duration: 0.6 }}
            className="bg-slate-900/50 backdrop-blur-xl border border-slate-800/80 rounded-3xl p-6 shadow-xl flex flex-col justify-between"
          >
            <h3 className="text-xl font-bold text-white mb-6">Stack Breakdown</h3>

            <div className="h-[350px] w-full flex items-center justify-center">
              <ResponsiveContainer width="100%" height="100%">
                {chartData.length >= 3 ? (
                  // Radar Chart
                  <RadarChart cx="50%" cy="50%" outerRadius="75%" data={chartData}>
                    <PolarGrid stroke="#334155" />
                    <PolarAngleAxis dataKey="category" stroke="#94a3b8" fontSize={12} />
                    <PolarRadiusAxis angle={30} domain={[0, 100]} stroke="#475569" />
                    <Radar
                      name="Proficiency"
                      dataKey="Proficiency"
                      stroke="#06b6d4"
                      fill="#06b6d4"
                      fillOpacity={0.25}
                    />
                  </RadarChart>
                ) : (
                  // Fallback Bar Chart if not enough categories for Radar
                  <BarChart data={chartData}>
                    <XAxis dataKey="category" stroke="#94a3b8" />
                    <YAxis domain={[0, 100]} stroke="#94a3b8" />
                    <Tooltip
                      contentStyle={{ backgroundColor: "#0f172a", borderColor: "#1e293b", borderRadius: "12px" }}
                      itemStyle={{ color: "#06b6d4" }}
                    />
                    <Bar dataKey="Proficiency" fill="#06b6d4" radius={[8, 8, 0, 0]} />
                  </BarChart>
                )}
              </ResponsiveContainer>
            </div>
            
            <p className="text-xs text-slate-500 text-center mt-6">
              Averages are calculated automatically from individual skill ratings
            </p>
          </motion.div>
        </div>
      </div>
    </section>
  );
}