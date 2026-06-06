"use client";

import { motion } from "framer-motion";

interface Experience {
    id: number;
    company: string;
    position: string;
    description: string;
    startDate: string;
    endDate: string;
    currentlyWorking: boolean;
}

export default function Experience({
    experiences,
}: {
    experiences: Experience[];
}) {
    return (
        <section
            id="experience"
            className="max-w-7xl mx-auto px-8 py-20"
        >
            <motion.div
                initial={{ opacity: 0, y: 50 }}
                whileInView={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.6 }}
                className="
bg-white/5
backdrop-blur-xl
border
border-white/10
rounded-3xl
shadow-2xl
p-8
"
            >
                <h2 className="text-4xl font-bold mb-10">
                    Experience
                </h2>

                <div className="space-y-8">
                    {experiences.map((exp) => (
                        <div
                            key={exp.id}
                            className="border-l-4 border-cyan-500 pl-6"
                        >
                            <h3 className="text-2xl font-bold">
                                {exp.position}
                            </h3>

                            <p className="text-cyan-400">
                                {exp.company}
                            </p>

                            <p className="text-gray-400 mt-2">
                                {exp.startDate} -
                                {exp.currentlyWorking
                                    ? " Present"
                                    : ` ${exp.endDate}`}
                            </p>

                            <p className="mt-4 text-gray-300">
                                {exp.description}
                            </p>
                        </div>
                    ))}
                </div>
            </motion.div>
        </section>
    );
}