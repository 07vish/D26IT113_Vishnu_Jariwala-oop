public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Notification Broadcast & Marker Interfaces ===");

        // Lambda standard SMS
        Notifier smsSender = msg -> System.out.println("[SMS ALERT] To: +91-9876543210 -> " + msg);

        // Lambda standard Push
        Notifier pushSender = msg -> System.out.println("[APP PUSH] Notification: " + msg);

        // Urgent Class instance
        Notifier urgentEmail = new UrgentEmailNotifier();

        Notifier[] broadcastChannels = { smsSender, urgentEmail, pushSender };
        String broadcastPayload = "CRITICAL: Database primary node failover in progress!";

        System.out.println("Broadcasting Message: \"" + broadcastPayload + "\"\n");
        for (Notifier channel : broadcastChannels) {
            channel.send(broadcastPayload);
            if (channel instanceof Urgent) {
                System.out.println("  -> [Marker: Urgent Detected] Re-sending confirmation payload:");
                channel.send(broadcastPayload);
            }
            System.out.println();
        }
    }
}
