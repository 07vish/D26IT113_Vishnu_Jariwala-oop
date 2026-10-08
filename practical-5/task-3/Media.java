public abstract class Media {
    private final String title;

    public Media(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }
    public abstract double calculateLateFee(int daysLate);
}
