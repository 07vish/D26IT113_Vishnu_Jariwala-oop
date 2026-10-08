public class Book extends Media {
    public Book(String title) { super(title); }
    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 2.0; // ₹2 per day
    }
}
