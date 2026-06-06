"use client";

import { Bell } from "lucide-react";

export default function AdminNavbar() {
  return (
    <header
      className="
      sticky
      top-0
      z-50
      backdrop-blur-xl
      bg-[#020617]/80
      border-b
      border-white/10
      "
    >
      <div className="px-4 md:px-8 py-4 flex items-center justify-between">
        <div>
          <h1 className="text-xl md:text-3xl font-bold">
            Portfolio Admin
          </h1>

          <p className="text-gray-400 text-sm">
            Manage your portfolio
          </p>
        </div>

        <div className="flex items-center gap-4">
          <button
            className="
            w-10
            h-10
            rounded-full
            bg-white/5
            flex
            items-center
            justify-center
            "
          >
            <Bell size={18} />
          </button>

          <div
            className="
            h-12
            w-12
            rounded-full
            bg-gradient-to-r
            from-cyan-500
            to-blue-500
            flex
            items-center
            justify-center
            font-bold
            text-black
            "
          >
            A
          </div>
        </div>
      </div>
    </header>
  );
}