import {
    Mail,
    Link,
    Globe,
} from "lucide-react";

interface FooterProps {
    profile?: {
        fullName?: string;
        headline?: string;
    };
}

export default function Footer({ profile }: FooterProps) {
    return (
        <footer className="mt-32 border-t border-white/10">
            <div className="max-w-7xl mx-auto px-6 py-16">

                <h3 className="text-3xl font-bold text-center">
                    {profile?.fullName || "Portfolio"}
                </h3>

                <p className="text-center text-gray-400 mt-3">
                    {profile?.headline || "Full Stack Developer"}
                </p>

                <div className="flex justify-center gap-6 mt-8">
                    <Globe />
                    <Link />
                    <Mail />
                </div>

                <p className="text-center text-gray-500 mt-8">
                    © {new Date().getFullYear()} {profile?.fullName || "Portfolio"}. All Rights Reserved.
                </p>

            </div>
        </footer>
    );
}