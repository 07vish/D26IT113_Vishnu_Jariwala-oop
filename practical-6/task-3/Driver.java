public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Dynamic Lambda Discount Engine ===");
        double[] cartPrices = { 500.0, 1200.0, 2500.0, 8000.0 };

        DiscountRule festiveDiscount = price -> price * 0.85; // 15% discount
        DiscountRule vipClubDiscount = price -> (price > 2000.0) ? (price - 500.0) : price; // Flat ₹500 off
        DiscountRule clearanceDiscount = price -> price * 0.50; // 50% clearance

        System.out.println("--- Applying Festive 15% Discount ---");
        for (double p : cartPrices) {
            System.out.printf("Original: ₹%.2f -> Discounted: ₹%.2f\n", p, festiveDiscount.apply(p));
        }

        System.out.println("\n--- Applying VIP Club Flat Off ---");
        for (double p : cartPrices) {
            System.out.printf("Original: ₹%.2f -> Discounted: ₹%.2f\n", p, vipClubDiscount.apply(p));
        }
    }
}
