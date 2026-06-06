"use client";

import AdminSidebar from "@/components/admin/AdminSidebar";
import AuthGuard from "@/components/AuthGuard";

export default function AdminLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <AuthGuard>
      <div className="flex bg-[#020617] min-h-screen text-white">
        <AdminSidebar />

        <main className="flex-1 p-8">
          {children}
        </main>
      </div>
    </AuthGuard>
  );
}