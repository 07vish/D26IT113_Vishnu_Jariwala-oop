/**
 * CinemaShow
 */
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

    CinemaShow(String title) {
        this(title, 100);
    }

    public boolean book(int seats) {
        if (seats <= seatsAvailable) {
            seatsAvailable -= seats;
            totalBooked += seats;
            return true;
        }
        return false;
    
    }

    public void cancel(int seats) {
        if (seats <= (capacity - seatsAvailable)) {
            seatsAvailable += seats;
            totalBooked -= seats;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public String getTitle() {
        return title;
    }

    public static void main(String[] args) {
        CinemaShow show = new CinemaShow("Spider-man:Brand New Day", 200);

        System.out.println("Booking 30 seats: " + show.book(30));
        System.out.println("Seats available: " + show.getSeatsAvailable());

        System.out.println("Booking 40 seats: " + show.book(40));
        System.out.println("Seats available: " + show.getSeatsAvailable());

        System.out.println("Booking 50 seats: " + show.book(50));
        System.out.println("Seats available: " + show.getSeatsAvailable());

        System.out.println("Canceling 50 seats...");
        show.cancel(50);
        System.out.println("Seats available after cancellation: " + show.getSeatsAvailable());

        System.out.println("Booking 60 seats: " + show.book(60));
        System.out.println("Seats available: " + show.getSeatsAvailable());


    }
}