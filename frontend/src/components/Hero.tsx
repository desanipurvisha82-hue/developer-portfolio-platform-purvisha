"use client";

import { motion } from "framer-motion";

interface HeroProps {
  profile: {
    fullName: string;
    headline: string;
    about: string;
    profileImageUrl?: string;
    githubUrl?: string;
    linkedinUrl?: string;
    resumeUrl?: string;
  };
}

export default function Hero({ profile }: HeroProps) {
  return (
    <section
      id="home"
      className="
      relative
      flex
      items-center
      justify-center
      min-h-screen
      pt-32
      pb-20
      px-6
      overflow-hidden
      bg-gradient-to-r
      from-slate-950
      via-blue-950
      to-purple-950
      "
    >
      {/* Background Glow */}
      <div className="absolute inset-0 bg-gradient-to-r from-cyan-500/10 via-transparent to-purple-500/10" />

      <motion.div
        initial={{ opacity: 0, y: 40 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.8 }}
        className="
        relative
        z-10
        max-w-5xl
        mx-auto
        text-center
        flex
        flex-col
        items-center
        "
      >
        <img
          src={profile.profileImageUrl || "/profile.jpg"}
          alt={profile.fullName}
          className="
          w-40
          h-40
          md:w-52
          md:h-52
          rounded-full
          object-cover
          border-4
          border-cyan-500
          shadow-2xl
          shadow-cyan-500/30
          mb-8
          "
        />

        <h1
          className="
          text-5xl
          md:text-7xl
          lg:text-8xl
          font-extrabold
          mb-4
          bg-gradient-to-r
          from-cyan-400
          via-blue-400
          to-purple-500
          bg-clip-text
          text-transparent
          "
        >
          {profile.fullName}
        </h1>

        <h2
          className="
          text-2xl
          md:text-4xl
          font-semibold
          text-cyan-300
          mb-6
          "
        >
          {profile.headline}
        </h2>

        <p
          className="
          text-gray-300
          text-base
          md:text-xl
          max-w-3xl
          leading-relaxed
          mb-10
          "
        >
          {profile.about}
        </p>

        <div
          className="
          flex
          flex-col
          sm:flex-row
          gap-5
          justify-center
          items-center
          "
        >
          <a
            href="#projects"
            className="
            px-8
            py-4
            rounded-2xl
            bg-cyan-500
            text-black
            font-bold
            hover:scale-105
            transition-all
            "
          >
            View Projects
          </a>

          <a
            href="#contact"
            className="
            px-8
            py-4
            rounded-2xl
            border
            border-cyan-400
            text-cyan-300
            hover:bg-cyan-500
            hover:text-black
            transition-all
            "
          >
            Contact Me
          </a>
        </div>

        {/* Social Links */}

        <div className="mt-10 flex flex-wrap justify-center gap-6">
          {profile.githubUrl && (
            <a
              href={profile.githubUrl}
              target="_blank"
              rel="noopener noreferrer"
              className="text-cyan-400 hover:text-cyan-300 font-medium"
            >
              GitHub
            </a>
          )}

          {profile.linkedinUrl && (
            <a
              href={profile.linkedinUrl}
              target="_blank"
              rel="noopener noreferrer"
              className="text-cyan-400 hover:text-cyan-300 font-medium"
            >
              LinkedIn
            </a>
          )}

          {profile.resumeUrl && (
            <a
              href={profile.resumeUrl}
              target="_blank"
              rel="noopener noreferrer"
              className="text-cyan-400 hover:text-cyan-300 font-medium"
            >
              Resume
            </a>
          )}
        </div>
      </motion.div>
    </section>
  );
}