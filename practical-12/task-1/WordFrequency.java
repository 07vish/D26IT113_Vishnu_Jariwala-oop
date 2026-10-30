import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Practical 12 - Task 1: Word Frequency Counter");
        System.out.println("==================================================");

        String text = "Java is a robust programming language. Java collections provide powerful "
                    + "data structures. The Java Collections Framework includes Map, List, and Set. "
                    + "Java maps store key-value pairs efficiently.";

        System.out.println("Input Paragraph:\n\"" + text + "\"\n");

        // Clean and tokenize text
        String cleaned = text.toLowerCase().replaceAll("[^a-z0-9\\s]", " ");
        String[] tokens = cleaned.split("\\s+");

        // Count occurrences in HashMap
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : tokens) {
            if (!word.isBlank()) {
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            }
        }

        // Sort entries by frequency (descending) using Comparator, tie-break alphabetically
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(freqMap.entrySet());
        entryList.sort(
            Comparator.<Map.Entry<String, Integer>, Integer>comparing(Map.Entry::getValue).reversed()
                .thenComparing(Map.Entry::getKey)
        );

        System.out.printf("%-15s | %s%n", "Word", "Frequency");
        System.out.println("----------------+----------");
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.printf("%-15s | %d%n", entry.getKey(), entry.getValue());
        }

        System.out.println("\nTotal Distinct Words: " + freqMap.size());
    }
}
