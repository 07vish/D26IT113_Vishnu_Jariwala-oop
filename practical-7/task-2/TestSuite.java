public class TestSuite {
    @Run
    public void testDatabaseConnection() {
        System.out.println("  -> Executing: testDatabaseConnection() [PASSED]");
    }

    public void helperMethodIgnored() {
        System.out.println("  -> Helper method - should NOT be executed");
    }

    @Run
    public void testAuthentication() {
        System.out.println("  -> Executing: testAuthentication() [PASSED]");
    }

    @Run
    public void testPaymentGateway() {
        System.out.println("  -> Executing: testPaymentGateway() [PASSED]");
    }
}
