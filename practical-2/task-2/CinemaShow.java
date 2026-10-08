public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    public CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    public CinemaShow(String title) {
        this(title, 100);
    }

    public boolean book(int n) {
        if (n > 0 && n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        return false;
    }

    public void cancel(int n) {
        if (n > 0) {
            int seatsToRestore = Math.min(n, capacity - seatsAvailable);
            seatsAvailable += seatsToRestore;
            totalBooked -= seatsToRestore;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getTitle() {
        return title;
    }

    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {
        System.out.println("=== Cinema Show Reservation System ===");
        CinemaShow show = new CinemaShow("Interstellar (IMAX)", 150);
        System.out.println("Show Title: " + show.getTitle() + " | Total Capacity: " + show.getCapacity());

        System.out.println("\n1. Booking 50 seats: " + show.book(50));
        System.out.println("   Seats Remaining: " + show.getSeatsAvailable() + " | Global Booked: " + CinemaShow.getTotalBooked());

        System.out.println("\n2. Booking 80 seats: " + show.book(80));
        System.out.println("   Seats Remaining: " + show.getSeatsAvailable() + " | Global Booked: " + CinemaShow.getTotalBooked());

        System.out.println("\n3. Attempting to book 30 seats (exceeds available): " + show.book(30));
        System.out.println("   Seats Remaining: " + show.getSeatsAvailable() + " | Global Booked: " + CinemaShow.getTotalBooked());

        System.out.println("\n4. Cancelling 20 seats...");
        show.cancel(20);
        System.out.println("   Seats Remaining: " + show.getSeatsAvailable() + " | Global Booked: " + CinemaShow.getTotalBooked());

        System.out.println("\nFinal Total Booked Across System: " + CinemaShow.getTotalBooked());
    }
}
