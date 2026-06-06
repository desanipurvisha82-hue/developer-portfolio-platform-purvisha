"use client";

import { motion } from "framer-motion";

interface Certificate {
    id: number;
    title: string;
    issuer: string;
    certificateUrl: string;
}

export default function Certificates({
    certificates,
}: {
    certificates: Certificate[];
}) {
    return (
        <section
            id="certificates"
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
                    Certificates
                </h2>

                <div className="grid md:grid-cols-2 gap-6">
                    {certificates.map((cert) => (
                        <div
                            key={cert.id}
                            className="bg-black/20 rounded-2xl p-6 hover:border-cyan-500 border border-white/10 transition"
                        >
                            <h3 className="text-xl font-bold">
                                {cert.title}
                            </h3>

                            <p className="text-cyan-400 mt-2">
                                {cert.issuer}
                            </p>

                            <a
                                href={cert.certificateUrl}
                                target="_blank"
                                className="text-cyan-400 mt-4 inline-block"
                            >
                                View Certificate
                            </a>
                        </div>
                    ))}
                </div>
            </motion.div>
        </section>
    );
}