import java.util.Scanner;

public class VendingMachine {
    public enum Coin { ONE, TWO, FIVE, TEN }

    public static void main(String[] args) {
        final int SNACK_PRICE = 15;
        int runningTotal = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Smart Vending Machine ===");
        System.out.println("Snack Price: ₹" + SNACK_PRICE);
        System.out.println("Accepted Coins: ONE, TWO, FIVE, TEN");

        while (runningTotal < SNACK_PRICE) {
            System.out.print("Insert coin: ");
            if (!scanner.hasNext()) break;
            String input = scanner.next().trim().toUpperCase();

            Coin coin;
            try {
                coin = Coin.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("  Invalid coin denomination! Try again.");
                continue;
            }

            int value = switch (coin) {
                case ONE  -> 1;
                case TWO  -> 2;
                case FIVE -> 5;
                case TEN  -> 10;
            };

            runningTotal += value;
            System.out.println("  Inserted: ₹" + value + " | Current Total: ₹" + runningTotal);
        }

        int change = runningTotal - SNACK_PRICE;
        System.out.println("Dispensing snack! Paid. Change to return: ₹" + change);
        scanner.close();
    }
}
