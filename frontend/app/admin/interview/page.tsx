"use client";

import { useState } from "react";
import { getInterviewQuestions } from "@/services/interviewService";
import Swal from "sweetalert2";
import { motion, AnimatePresence } from "framer-motion";
import {
  HelpCircle,
  ChevronDown,
  ChevronUp,
  Cpu,
  UserCheck,
  MessageSquare,
  Sparkles,
} from "lucide-react";

interface Question {
  category: string;
  question: string;
  answer: string;
}

const ROLES = [
  "Java Developer",
  "Spring Boot Developer",
  "React Developer",
  "Full Stack Developer",
];

export default function InterviewPage() {
  const [role, setRole] = useState(ROLES[0]);
  const [questions, setQuestions] = useState<Question[]>([]);
  const [loading, setLoading] = useState(false);
  const [expandedIndex, setExpandedIndex] = useState<number | null>(null);

  const handleGenerate = async () => {
    setLoading(true);
    setExpandedIndex(null);
    try {
      const data = await getInterviewQuestions(role);
      setQuestions(data);
      Swal.fire({
        title: "Questions Generated!",
        text: `Technical, HR, and Scenario questions loaded for ${role}.`,
        icon: "success",
        toast: true,
        position: "top-end",
        showConfirmButton: false,
        timer: 3000,
        background: "#0f172a",
        color: "#ffffff",
      });
    } catch (error) {
      console.error(error);
      Swal.fire({
        title: "Generation Failed",
        text: "Could not connect to AI endpoint. Please try again.",
        icon: "error",
        background: "#0f172a",
        color: "#ffffff",
        confirmButtonColor: "#ef4444",
      });
    } finally {
      setLoading(false);
    }
  };

  const getCategoryIcon = (category: string) => {
    switch (category.toLowerCase()) {
      case "technical":
        return <Cpu className="text-cyan-400" size={18} />;
      case "hr":
        return <UserCheck className="text-pink-400" size={18} />;
      case "scenario":
        return <MessageSquare className="text-yellow-400" size={18} />;
      default:
        return <HelpCircle className="text-purple-400" size={18} />;
    }
  };

  const getCategoryBadge = (category: string) => {
    switch (category.toLowerCase()) {
      case "technical":
        return "bg-cyan-500/10 text-cyan-400 border-cyan-500/20";
      case "hr":
        return "bg-pink-500/10 text-pink-400 border-pink-500/20";
      case "scenario":
        return "bg-yellow-500/10 text-yellow-400 border-yellow-500/20";
      default:
        return "bg-purple-500/10 text-purple-400 border-purple-500/20";
    }
  };

  return (
    <div className="space-y-8">
      {/* Header */}
      <div>
        <h1 className="text-4xl font-bold bg-gradient-to-r from-cyan-400 to-blue-500 bg-clip-text text-transparent">
          AI Interview Preparation
        </h1>
        <p className="text-slate-400 mt-2">
          Generate Technical, HR, and Scenario questions customized to your profile
        </p>
      </div>

      {/* Role Selector Card */}
      <div className="bg-slate-900 border border-slate-800 rounded-3xl p-6">
        <h3 className="font-bold text-xl mb-4 flex items-center gap-2">
          <Sparkles className="text-cyan-400" size={20} />
          Select Target Role
        </h3>
        
        <div className="flex flex-col sm:flex-row gap-4 items-stretch sm:items-center">
          <select
            value={role}
            onChange={(e) => setRole(e.target.value)}
            disabled={loading}
            className="flex-1 bg-slate-950 border border-slate-800 rounded-xl px-4 py-3 text-white focus:border-cyan-500 focus:outline-none transition-colors"
          >
            {ROLES.map((r) => (
              <option key={r} value={r}>
                {r}
              </option>
            ))}
          </select>

          <button
            onClick={handleGenerate}
            disabled={loading}
            className="px-6 py-3 rounded-xl bg-cyan-500 text-black font-bold hover:scale-105 transition-all shadow-lg shadow-cyan-500/10 disabled:opacity-50 disabled:hover:scale-100 flex items-center justify-center gap-2 cursor-pointer"
          >
            {loading ? (
              <>
                <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-black"></div>
                Generating...
              </>
            ) : (
              "Generate Questions"
            )}
          </button>
        </div>
      </div>

      {/* Questions Listing */}
      <div className="space-y-4">
        {questions.length === 0 ? (
          !loading && (
            <div className="flex flex-col items-center justify-center py-20 border border-dashed border-slate-800 rounded-3xl bg-slate-900/10">
              <HelpCircle className="text-slate-600 mb-4" size={48} />
              <h4 className="text-lg font-semibold text-slate-400">No Questions Loaded</h4>
              <p className="text-slate-500 text-sm max-w-xs text-center mt-1">
                Select your target job role above and trigger the AI question generator.
              </p>
            </div>
          )
        ) : (
          questions.map((q, i) => {
            const isExpanded = expandedIndex === i;
            return (
              <div
                key={i}
                className="bg-slate-900 border border-slate-800/80 hover:border-slate-800 rounded-2xl overflow-hidden transition-colors"
              >
                {/* Accordion Trigger */}
                <button
                  onClick={() => setExpandedIndex(isExpanded ? null : i)}
                  className="w-full text-left p-5 flex justify-between items-start gap-4 cursor-pointer hover:bg-slate-900/50"
                >
                  <div className="space-y-2 flex-1">
                    <div className="flex items-center gap-2">
                      <span
                        className={`flex items-center gap-1.5 px-3 py-1 rounded-full border text-xs font-semibold uppercase ${getCategoryBadge(
                          q.category
                        )}`}
                      >
                        {getCategoryIcon(q.category)}
                        {q.category}
                      </span>
                    </div>
                    <h3 className="font-bold text-white text-lg md:text-xl pr-6 leading-snug">
                      {q.question}
                    </h3>
                  </div>

                  <div className="text-slate-500 mt-1">
                    {isExpanded ? <ChevronUp size={20} /> : <ChevronDown size={20} />}
                  </div>
                </button>

                {/* Collapsible Content */}
                <AnimatePresence initial={false}>
                  {isExpanded && (
                    <motion.div
                      initial={{ height: 0 }}
                      animate={{ height: "auto" }}
                      exit={{ height: 0 }}
                      transition={{ duration: 0.2 }}
                      className="overflow-hidden"
                    >
                      <div className="px-5 pb-5 pt-2 border-t border-slate-800/50 bg-slate-950/40">
                        <div className="rounded-xl bg-slate-950/60 border border-slate-800/60 p-4 leading-relaxed text-slate-300 text-sm md:text-base">
                          <p className="font-bold text-cyan-400 mb-2">Suggested Answer/Hint:</p>
                          <p className="whitespace-pre-line">{q.answer}</p>
                        </div>
                      </div>
                    </motion.div>
                  )}
                </AnimatePresence>
              </div>
            );
          })
        )}
      </div>
    </div>
  );
}