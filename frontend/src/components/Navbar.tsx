"use client";

interface NavbarProps {
    profile?: {
        fullName?: string;
    };
}

export default function Navbar({ profile }: NavbarProps) {
    return (
        <nav className="fixed top-0 w-full z-50 bg-slate-950/80 backdrop-blur-xl border-b border-white/10">
            <div className="max-w-7xl mx-auto px-4 md:px-8 h-20 flex items-center justify-between">

                <h1 className="font-bold text-xl md:text-3xl bg-gradient-to-r from-cyan-400 to-purple-500 bg-clip-text text-transparent">
                    {profile?.fullName || "Portfolio"}
                </h1>

                <ul className="hidden md:flex gap-8 text-lg">
                    <li><a href="#about">About</a></li>
                    <li><a href="#skills">Skills</a></li>
                    <li><a href="#projects">Projects</a></li>
                    <li><a href="#experience">Experience</a></li>
                    <li><a href="#contact">Contact</a></li>
                </ul>

            </div>
        </nav>
    );
}