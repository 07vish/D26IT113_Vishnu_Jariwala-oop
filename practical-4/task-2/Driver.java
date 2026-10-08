public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Chat Log Filter & Aggregator ===");
        String[] logs = {
            "10:05 alice Hello there, team!",
            "10:07 bob Good morning alice",
            "10:08 SYSTEM_ERROR", // Malformed line (< 3 parts)
            "10:12 charlie Can anyone say hello to the new joinee?",
            "10:15 david Meeting started."
        };

        String keyword = "hello";
        System.out.println("Filtering logs for keyword: '" + keyword + "'\n");
        String result = ChatFilter.filterLogs(logs, keyword);
        System.out.println(result);
    }
}
