public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Playing Cards Duplicate Verification ===");
        Card[] hand = {
            new Card("Ace", "Spades"),
            new Card("King", "Hearts"),
            new Card("10", "Diamonds"),
            new Card("Ace", "Spades"), // Duplicate
            new Card("Queen", "Clubs")
        };

        System.out.println("Hand of Cards:");
        for (int i = 0; i < hand.length; i++) {
            System.out.println("  [" + i + "] " + hand[i]);
        }

        boolean foundDuplicate = false;
        for (int i = 0; i < hand.length; i++) {
            for (int j = i + 1; j < hand.length; j++) {
                if (hand[i].equals(hand[j])) {
                    System.out.println("\nDuplicate Card Identified: '" + hand[i] + "' at index " + i + " and " + j);
                    foundDuplicate = true;
                }
            }
        }

        if (!foundDuplicate) {
            System.out.println("\nNo duplicates found in hand.");
        }
    }
}
