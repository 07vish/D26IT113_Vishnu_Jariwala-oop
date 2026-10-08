public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Library Media Late Fee Assessment ===");
        Media[] returnedItems = {
            new Book("Clean Code"),
            new Dvd("The Matrix"),
            new Magazine("IEEE Computer"),
            new Book("Introduction to Algorithms")
        };

        int daysOverdue = 4;
        double totalFine = 0;

        for (Media m : returnedItems) {
            double fine = m.calculateLateFee(daysOverdue);
            totalFine += fine;
            System.out.printf("Item: %-30s | %d Days Late | Fine: ₹%.2f\n", m.getTitle(), daysOverdue, fine);
        }

        System.out.println("------------------------------------------------------------");
        System.out.printf("Total Overdue Fines Collected: ₹%.2f\n", totalFine);
    }
}
