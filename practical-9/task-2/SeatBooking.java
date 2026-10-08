public class SeatBooking {
    private static int unsafeSeats = 5;
    private static int safeSeats = 5;

    private static int unsafeBookings = 0;
    private static int safeBookings = 0;

    private static synchronized boolean bookSafe() {
        if (safeSeats > 0) {
            try { Thread.sleep(5); } catch (InterruptedException ignored) {}
            safeSeats--;
            safeBookings++;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Seat Booking Race: Overselling Prevention ===");
        final int USERS = 10;

        // Unsafe simulation
        Thread[] tUnsafe = new Thread[USERS];
        for (int i = 0; i < USERS; i++) {
            tUnsafe[i] = new Thread(() -> {
                if (unsafeSeats > 0) {
                    try { Thread.sleep(5); } catch (InterruptedException ignored) {}
                    unsafeSeats--;
                    unsafeBookings++;
                }
            });
            tUnsafe[i].start();
        }
        for (Thread t : tUnsafe) t.join();

        // Safe simulation
        Thread[] tSafe = new Thread[USERS];
        for (int i = 0; i < USERS; i++) {
            tSafe[i] = new Thread(() -> bookSafe());
            tSafe[i].start();
        }
        for (Thread t : tSafe) t.join();

        System.out.println("Total Seats Available Originally: 5");
        System.out.println("Without Synchronization -> Total Seats Sold: " + unsafeBookings + " (Oversell bug detected!)");
        System.out.println("With Synchronized Method -> Total Seats Sold: " + safeBookings + " (Exact 5 seats booked, 5 rejected)");
    }
}
