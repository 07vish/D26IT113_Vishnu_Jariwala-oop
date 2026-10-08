public class MiniBank {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("MiniBank Core Entity Engine (Milestone 2)");
        System.out.println("==================================================");

        Customer c1 = new Customer("Aarav Patel", "aarav@charusat.edu.in", "9876543210");
        Customer c2 = new Customer("Diya Shah", "diya@charusat.edu.in", "9823456789");
        Customer c3 = new Customer("Kabir Mehta", "kabir@charusat.edu.in", "9812345678");

        System.out.println("Created Customers:");
        System.out.println("  ID: " + c1.getCustomerId() + " | Name: " + c1.getName() + " | Email: " + c1.getEmail());
        System.out.println("  ID: " + c2.getCustomerId() + " | Name: " + c2.getName() + " | Email: " + c2.getEmail());
        System.out.println("  ID: " + c3.getCustomerId() + " | Name: " + c3.getName() + " | Email: " + c3.getEmail());

        Account[] accounts = new Account[] {
            new Account(c1.getName(), 5000),
            new Account(c2.getName(), 12000),
            new Account(c3.getName()) // opens with zero balance via constructor chaining
        };

        System.out.println("\n--- Account Initialization Summary ---");
        for (Account acc : accounts) {
            System.out.println("Account: " + acc.getAccountNumber() + " | Owner: " + acc.getOwnerName() + " | Balance: ₹" + acc.getBalance());
        }

        System.out.println("\n--- Performing Transactions ---");
        accounts[0].deposit(2500);
        accounts[0].withdraw(3000);
        accounts[1].withdraw(15000); // Exceeds balance
        accounts[1].withdraw(4000);
        accounts[2].deposit(10000);
        accounts[2].withdraw(2500);

        System.out.println("\n--- Final Account Balances ---");
        for (Account acc : accounts) {
            System.out.println("Account [" + acc.getAccountNumber() + "] Owner: " + acc.getOwnerName() + " -> ₹" + acc.getBalance());
        }
    }
}
