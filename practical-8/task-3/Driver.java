public class Driver {
    public static void main(String[] args) {
        System.out.println("=== AutoCloseable Try-With-Resources Guarantee ===");

        System.out.println("Scenario 1: Normal Execution");
        try (CustomResource r1 = new CustomResource("AuditLogStream")) {
            r1.performAction(false);
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        System.out.println("\nScenario 2: Failure Scenario with Exception");
        try (CustomResource r2 = new CustomResource("SecureTransactionChannel")) {
            r2.performAction(true);
        } catch (Exception e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }
        System.out.println("Verified: Resource closed before exception was caught!");
    }
}
