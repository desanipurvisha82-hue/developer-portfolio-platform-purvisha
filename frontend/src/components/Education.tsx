"use client";

import { motion } from "framer-motion";

interface Education {
    id: number;
    institution: string;
    degree: string;
    fieldOfStudy: string;
    startYear: number;
    endYear: number;
    grade: string;
}

export default function Education({
    educations,
}: {
    educations: Education[];
}) {
    return (
        <section
            id="education"
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
                    Education
                </h2>

                <div className="space-y-6">
                    {educations.map((edu) => (
                        <div
                            key={edu.id}
                            className="bg-black/20 rounded-2xl p-6"
                        >
                            <h3 className="text-2xl font-bold">
                                {edu.degree}
                            </h3>

                            <p className="text-cyan-400">
                                {edu.institution}
                            </p>

                            <p className="text-gray-400">
                                {edu.startYear} - {edu.endYear}
                            </p>

                            <p className="mt-2">
                                {edu.fieldOfStudy}
                            </p>

                            <p className="text-green-400 mt-2">
                                Grade: {edu.grade}
                            </p>
                        </div>
                    ))}
                </div>
            </motion.div>
        </section>
    );
}