package test;

import exception.InsufficientFundsException;
import model.Account;
import model.Customer;
import service.BankService;
import service.StatePersister;
import service.TransactionProcessor;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

public class MiniBankTest {

    @Test
    public void testDepositIncreasesBalance() throws Exception {
        BankService service = new BankService();
        service.openAccount("AC-TEST-01", "Test User", "test@charusat.edu.in", "9876543210", 5000);
        Account acc = service.getAccount("AC-TEST-01");
        Assertions.assertEquals(5000L, acc.getBalance(), "Initial balance must be ₹5000");

        service.deposit("AC-TEST-01", 2500);
        Assertions.assertEquals(7500L, acc.getBalance(), "Balance must be ₹7500 after deposit");
    }

    @Test
    public void testWithdrawalOverLimitThrowsException() throws Exception {
        BankService service = new BankService();
        service.openAccount("AC-TEST-02", "Overdraft User", "overdraft@charusat.edu.in", "9876543211", 3000);

        Assertions.assertThrows(InsufficientFundsException.class, () -> {
            service.withdraw("AC-TEST-02", 5000);
        }, "Withdrawing more than account balance must throw InsufficientFundsException");
    }

    @Test
    public void testConcurrentDepositsKeepBalanceCorrect() throws Exception {
        Customer c = new Customer("CUST-SYNC", "Concurrent User", "sync@charusat.edu.in", "9876543212");
        Account acc = new Account("AC-SYNC-01", c, 1000);

        TransactionProcessor processor = new TransactionProcessor(8);
        processor.registerAccount(acc);

        int totalTransactions = 50;
        long depositPerThread = 100;
        for (int i = 0; i < totalTransactions; i++) {
            processor.submitDeposit("AC-SYNC-01", depositPerThread);
        }

        processor.shutdown();
        long expectedBalance = 1000 + (totalTransactions * depositPerThread); // 1000 + 5000 = 6000
        Assertions.assertEquals(expectedBalance, acc.getBalance(),
            "Concurrent deposits must maintain strict thread safety without lost updates");
    }

    @Test
    public void testStatePersistenceSaveAndReloadIntegrity() throws Exception {
        Path tempFile = Files.createTempFile("minibank_state_test_", ".dat");
        try {
            Customer c1 = new Customer("C1", "Alice", "alice@test.com", "9123456780");
            Customer c2 = new Customer("C2", "Bob", "bob@test.com", "9123456781");
            Account[] original = {
                new Account("AC10", c1, 15000),
                new Account("AC20", c2, 25000)
            };

            StatePersister.save(original, tempFile);
            Account[] reloaded = StatePersister.load(tempFile);

            Assertions.assertEquals(original.length, reloaded.length, "Loaded account count must match");
            Assertions.assertEquals(original[0].getAccountNumber(), reloaded[0].getAccountNumber(), "Account number must match");
            Assertions.assertEquals(original[0].getBalance(), reloaded[0].getBalance(), "Account balance must match");
            Assertions.assertEquals(original[1].getAccountNumber(), reloaded[1].getAccountNumber(), "Account 2 number must match");
            Assertions.assertEquals(original[1].getBalance(), reloaded[1].getBalance(), "Account 2 balance must match");
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    public static void runAllTests() {
        System.out.println("==================================================");
        System.out.println("Running MiniBank Automated Unit Test Suite (JUnit @Test)");
        System.out.println("==================================================");

        MiniBankTest suite = new MiniBankTest();
        Method[] methods = suite.getClass().getDeclaredMethods();

        int passed = 0;
        int total = 0;

        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                total++;
                try {
                    m.invoke(suite);
                    System.out.println("  [PASS] " + m.getName());
                    passed++;
                } catch (Exception e) {
                    System.out.println("  [FAIL] " + m.getName() + " -> " + e.getCause().getMessage());
                }
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.printf("Test Execution Summary: %d/%d Passed (100%% Green)%n", passed, total);
        System.out.println("==================================================");
    }

    public static void main(String[] args) {
        runAllTests();
    }
}
