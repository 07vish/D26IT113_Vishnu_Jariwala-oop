public class UrgentEmailNotifier implements Notifier, Urgent {
    @Override
    public void send(String message) {
        System.out.println("[URGENT EMAIL DISPATCH] To: dev-ops@charusat.edu.in -> " + message);
    }
}
