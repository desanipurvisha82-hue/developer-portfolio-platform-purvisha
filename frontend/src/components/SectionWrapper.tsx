export default function SectionWrapper({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <section className="max-w-7xl mx-auto px-6 py-24">
      {children}
    </section>
  );
}