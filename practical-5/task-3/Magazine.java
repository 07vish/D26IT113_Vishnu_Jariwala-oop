public class Magazine extends Media {
    public Magazine(String title) { super(title); }
    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 1.5; // ₹1.5 per day
    }
}
