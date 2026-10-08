public class Dvd extends Media {
    public Dvd(String title) { super(title); }
    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 5.0; // ₹5 per day
    }
}
