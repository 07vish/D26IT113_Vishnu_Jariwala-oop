public class MiniBank {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("MiniBank Milestone 5: Abstract Types & Polymorphic Accounts");
        System.out.println("==================================================");

        Account[] portfolio = {
            new SavingsAccount("SB1001", "Rohan Patel", 25000, 2000),
            new CurrentAccount("CA2001", "TechCorp Pvt Ltd", 15000, 50000),
            new FixedDepositAccount("FD3001", "Meera Desai", 100000)
        };

        System.out.println("--- Processing Accounts via Run-Time Polymorphism ---");
        for (Account acc : portfolio) {
            System.out.println(acc);
            System.out.println("  Yield Rate: " + acc.interestRate() + "% APR");

            // Pattern matching for instanceof check
            if (acc instanceof CurrentAccount ca) {
                System.out.println("  -> Special Note: Current Account equipped with ₹" + ca.getOverdraftLimit() + " Overdraft Limit.");
            } else if (acc instanceof SavingsAccount sa) {
                System.out.println("  -> Minimum Balance Threshold: ₹" + sa.getMinBalance());
            } else if (acc instanceof FixedDepositAccount) {
                System.out.println("  -> Term Deposit: Premature withdrawal blocked.");
            }
        }

        System.out.println("\n--- Testing Polymorphic Withdrawal Rules ---");
        System.out.println("1. Attempting ₹24000 from Savings (Min Bal ₹2000): " + portfolio[0].withdraw(24000) + " (Leaves ₹1000 < minBal -> Denied)");
        System.out.println("2. Attempting ₹20000 from Savings (Min Bal ₹2000): " + portfolio[0].withdraw(20000) + " (Approved)");
        System.out.println("3. Attempting ₹50000 from Current (Balance ₹15000, OD ₹50000): " + portfolio[1].withdraw(50000) + " (Overdraft Approved)");
        System.out.println("4. Attempting ₹1000 from Fixed Deposit: " + portfolio[2].withdraw(1000) + " (Locked -> Denied)");

        System.out.println("\n--- Balances After Execution ---");
        for (Account acc : portfolio) {
            System.out.println(acc.getAccountNumber() + " [" + acc.getOwnerName() + "]: Balance = ₹" + acc.getBalance());
        }
    }
}
