"use client";

import { motion } from "framer-motion";
import {
  Code,
  GraduationCap,
  Briefcase,
  Award,
  Terminal,
  MapPin,
  Mail,
  Phone,
} from "lucide-react";

interface AboutProps {
  profile: {
    fullName: string;
    headline: string;
    about: string;
    email?: string;
    phone?: string;
    location?: string;
  };
}

export default function About({ profile }: AboutProps) {
  const stats = [
    { label: "Years Experience", value: "3+", icon: Briefcase, color: "text-cyan-400 bg-cyan-500/10 border-cyan-500/20" },
    { label: "SaaS Projects", value: "10+", icon: Code, color: "text-purple-400 bg-purple-500/10 border-purple-500/20" },
    { label: "Certifications", value: "5+", icon: Award, color: "text-pink-400 bg-pink-500/10 border-pink-500/20" },
    { label: "Degrees", value: "1", icon: GraduationCap, color: "text-yellow-400 bg-yellow-500/10 border-yellow-500/20" },
  ];

  return (
    <section id="about" className="max-w-7xl mx-auto px-6 py-24">
      <div className="grid lg:grid-cols-12 gap-12 items-stretch">
        
        {/* LEFT COLUMN: Career Bio & Details */}
        <motion.div
          initial={{ opacity: 0, x: -30 }}
          whileInView={{ opacity: 1, x: 0 }}
          transition={{ duration: 0.6 }}
          className="lg:col-span-7 flex flex-col justify-between bg-slate-900/50 backdrop-blur-xl border border-slate-800/80 rounded-3xl p-8 md:p-10 shadow-xl"
        >
          <div className="space-y-6">
            <h2 className="text-4xl font-extrabold text-white flex items-center gap-3">
              <Terminal size={32} className="text-cyan-400" />
              About Me
            </h2>
            
            <p className="text-lg leading-relaxed text-slate-300">
              {profile.about}
            </p>
          </div>

          {/* Quick Details Table */}
          <div className="grid sm:grid-cols-2 gap-4 mt-8 pt-6 border-t border-slate-800/60 text-sm">
            <div className="flex items-center gap-3">
              <MapPin size={18} className="text-slate-500" />
              <div>
                <span className="text-slate-550 text-xs block uppercase font-bold">Location</span>
                <span className="text-slate-300 font-medium">{profile.location || "Mumbai, India"}</span>
              </div>
            </div>

            <div className="flex items-center gap-3">
              <Mail size={18} className="text-slate-500" />
              <div>
                <span className="text-slate-550 text-xs block uppercase font-bold">Email</span>
                <span className="text-slate-300 font-medium">{profile.email || "purvisha@example.com"}</span>
              </div>
            </div>

            <div className="flex items-center gap-3">
              <Phone size={18} className="text-slate-500" />
              <div>
                <span className="text-slate-550 text-xs block uppercase font-bold">Contact</span>
                <span className="text-slate-300 font-medium">{profile.phone || "+91 9876543210"}</span>
              </div>
            </div>
          </div>
        </motion.div>

        {/* RIGHT COLUMN: Statistics Grid */}
        <motion.div
          initial={{ opacity: 0, x: 30 }}
          whileInView={{ opacity: 1, x: 0 }}
          transition={{ duration: 0.6 }}
          className="lg:col-span-5 grid grid-cols-2 gap-6"
        >
          {stats.map((item, index) => {
            const Icon = item.icon;
            return (
              <div
                key={index}
                className="flex flex-col justify-between bg-slate-900/40 backdrop-blur-xl border border-slate-800/80 rounded-3xl p-6 hover:border-slate-700 transition-all hover:scale-[1.03]"
              >
                <div className={`w-12 h-12 rounded-xl flex items-center justify-center border ${item.color}`}>
                  <Icon size={20} />
                </div>
                
                <div className="mt-8">
                  <h3 className="text-4xl font-extrabold text-white">
                    {item.value}
                  </h3>
                  <p className="text-slate-450 text-xs uppercase font-bold tracking-wider mt-1">
                    {item.label}
                  </p>
                </div>
              </div>
            );
          })}
        </motion.div>

      </div>
    </section>
  );
}