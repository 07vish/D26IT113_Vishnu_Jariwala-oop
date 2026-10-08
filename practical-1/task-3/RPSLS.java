import java.util.Random;
import java.util.Scanner;

public class RPSLS {
    public enum Move { ROCK, PAPER, SCISSORS, LIZARD, SPOCK }

    public static int winner(Move a, Move b) {
        if (a == b) return 0;
        boolean aWins = switch (a) {
            case ROCK     -> (b == Move.LIZARD   || b == Move.SCISSORS);
            case PAPER    -> (b == Move.ROCK     || b == Move.SPOCK);
            case SCISSORS -> (b == Move.PAPER    || b == Move.LIZARD);
            case LIZARD   -> (b == Move.SPOCK    || b == Move.PAPER);
            case SPOCK    -> (b == Move.SCISSORS || b == Move.ROCK);
        };
        return aWins ? 1 : -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(42); // deterministic seed for test output
        Move[] allMoves = Move.values();
        int playerScore = 0, cpuScore = 0;

        System.out.println("=== Rock-Paper-Scissors-Lizard-Spock (Best of 5) ===");
        System.out.println("Rules: Scissors cuts Paper, Paper covers Rock, Rock crushes Lizard,");
        System.out.println("Lizard poisons Spock, Spock smashes Scissors, Scissors decapitates Lizard,");
        System.out.println("Lizard eats Paper, Paper disproves Spock, Spock vaporizes Rock, Rock crushes Scissors.");

        for (int round = 1; round <= 5; round++) {
            System.out.println("\nRound " + round + " of 5:");
            System.out.print("Choose move (ROCK, PAPER, SCISSORS, LIZARD, SPOCK): ");
            if (!scanner.hasNext()) break;
            String input = scanner.next().trim().toUpperCase();

            Move playerMove;
            try {
                playerMove = Move.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid move entered. Round forfeited!");
                cpuScore++;
                continue;
            }

            Move cpuMove = allMoves[random.nextInt(allMoves.length)];
            int outcome = winner(playerMove, cpuMove);

            System.out.println("  Player: " + playerMove + " vs Computer: " + cpuMove);
            if (outcome == 1) {
                playerScore++;
                System.out.println("  Round Result: Player wins this round!");
            } else if (outcome == -1) {
                cpuScore++;
                System.out.println("  Round Result: Computer wins this round!");
            } else {
                System.out.println("  Round Result: It's a Tie!");
            }
        }

        System.out.println("\n==============================");
        System.out.println("Final Score -> Player: " + playerScore + " | Computer: " + cpuScore);
        if (playerScore > cpuScore) {
            System.out.println("Overall Champion: Player Wins!");
        } else if (cpuScore > playerScore) {
            System.out.println("Overall Champion: Computer Wins!");
        } else {
            System.out.println("Overall Result: Tie Match!");
        }
        scanner.close();
    }
}
