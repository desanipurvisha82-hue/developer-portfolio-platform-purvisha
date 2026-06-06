"use client";

import { motion } from "framer-motion";
import { useState } from "react";

interface Project {
    id: number;
    title: string;
    description: string;
    githubUrl: string;
    liveUrl: string;
}

export default function Projects({
    projects,
}: {
    projects: Project[];
}) {
    const [showAll, setShowAll] = useState(false);

    const displayedProjects = showAll
        ? projects
        : projects.slice(0, 4);

    return (
        <section
            id="projects"
            className="max-w-7xl mx-auto px-6 py-20"
        >
            <motion.div
                initial={{ opacity: 0, y: 50 }}
                whileInView={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.6 }}
            >
                <div className="flex justify-between items-center mb-10">
                    <h2 className="text-4xl font-bold">
                        Projects
                    </h2>

                    {projects.length > 4 && (
                        <button
                            onClick={() => setShowAll(!showAll)}
                            className="
                px-5
                py-2
                rounded-xl
                bg-cyan-500
                text-black
                font-semibold
                hover:bg-cyan-400
                transition
              "
                        >
                            {showAll
                                ? "Show Less"
                                : "View More Projects"}
                        </button>
                    )}
                </div>

                <div className="grid md:grid-cols-2 lg:grid-cols-2 gap-6">
                    {displayedProjects.map((project) => (
                        <div
                            key={project.id}
                            className="
                bg-white/5
                backdrop-blur-xl
                border
                border-white/10
                rounded-3xl
                shadow-2xl
                p-6
                hover:scale-105
                hover:shadow-cyan-500/20
                transition-all
                duration-300
              "
                        >
                            <h3 className="text-2xl font-bold">
                                {project.title}
                            </h3>

                            <p className="text-gray-400 mt-4">
                                {project.description}
                            </p>

                            <div className="flex gap-6 mt-6">
                                <a
                                    href={project.githubUrl}
                                    target="_blank"
                                    rel="noopener noreferrer"
                                    className="text-cyan-400 hover:text-cyan-300"
                                >
                                    GitHub
                                </a>

                                <a
                                    href={project.liveUrl}
                                    target="_blank"
                                    rel="noopener noreferrer"
                                    className="text-cyan-400 hover:text-cyan-300"
                                >
                                    Live Demo
                                </a>
                            </div>
                        </div>
                    ))}
                </div>
            </motion.div>
        </section>
    );
}
