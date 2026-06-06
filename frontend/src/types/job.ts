export interface Job {
    id: string;
    title: string;
    description: string;
    redirect_url: string;

    company?: {
        display_name: string;
    };

    location?: {
        display_name: string;
    };
}