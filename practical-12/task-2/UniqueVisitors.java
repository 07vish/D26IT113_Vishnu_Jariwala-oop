import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Practical 12 - Task 2: Unique Visitors Tracker");
        System.out.println("==================================================");

        List<String> rawLogins = List.of(
            "alice_dev", "bob_coder", "charlie_99", "alice_dev", "david_k",
            "bob_coder", "elena_t", "charlie_99", "fiona_w", "alice_dev"
        );

        System.out.println("Raw Login Activity Stream (" + rawLogins.size() + " entries):");
        System.out.println(rawLogins);
        System.out.println();

        // 1. HashSet for unordered unique counting and fast membership testing
        Set<String> hashSet = new HashSet<>(rawLogins);
        System.out.println("HashSet Unique Users Count: " + hashSet.size());
        System.out.println("HashSet View (Unordered): " + hashSet);

        // 2. LinkedHashSet to preserve first-seen insertion order
        Set<String> linkedHashSet = new LinkedHashSet<>(rawLogins);
        System.out.println("\nLinkedHashSet View (First-Seen Order Preserved):");
        System.out.println(linkedHashSet);

        // 3. Print unique count and first three distinct visitors in arrival order
        System.out.println("\nTotal Unique Visitors: " + linkedHashSet.size());
        System.out.println("First 3 Distinct Visitors (by initial arrival):");
        int count = 0;
        for (String user : linkedHashSet) {
            System.out.printf("  %d. %s%n", ++count, user);
            if (count == 3) break;
        }
    }
}
