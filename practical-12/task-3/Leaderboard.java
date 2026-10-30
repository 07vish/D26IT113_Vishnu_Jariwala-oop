import java.util.*;

public class Leaderboard {
    public record Player(String username, int score, String country) {}

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Practical 12 - Task 3: Gaming Leaderboard System");
        System.out.println("==================================================");

        Map<String, Player> playerScores = new HashMap<>();
        playerScores.put("dragon_slayer", new Player("dragon_slayer", 9450, "IND"));
        playerScores.put("cyber_ninja",   new Player("cyber_ninja", 8800, "JPN"));
        playerScores.put("pixel_queen",   new Player("pixel_queen", 9450, "USA"));
        playerScores.put("shadow_knight", new Player("shadow_knight", 7200, "GER"));
        playerScores.put("hyper_sonic",   new Player("hyper_sonic", 9950, "IND"));
        playerScores.put("blaze_master",  new Player("blaze_master", 8800, "CAN"));
        playerScores.put("quantum_echo",  new Player("quantum_echo", 6400, "UK"));

        System.out.println("Registered Players in Map: " + playerScores.size());

        // Sort players: Score descending, tie-breaker: username alphabetically
        Comparator<Player> scoreComparator = Comparator
            .comparingInt(Player::score).reversed()
            .thenComparing(Player::username);

        List<Player> rankedList = new ArrayList<>(playerScores.values());
        rankedList.sort(scoreComparator);

        int topN = 5;
        System.out.println("\nTop " + topN + " Global Leaderboard Standings:");
        System.out.printf("%-6s | %-16s | %-8s | %-6s%n", "Rank", "Player", "Score", "Country");
        System.out.println("-------+------------------+----------+--------");

        for (int i = 0; i < Math.min(topN, rankedList.size()); i++) {
            Player p = rankedList.get(i);
            System.out.printf("#%-5d | %-16s | %-8d | %-6s%n", (i + 1), p.username(), p.score(), p.country());
        }
    }
}
