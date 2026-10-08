public class MiniBank {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("MiniBank Milestone 3: Object Behaviors & Nested Entities");
        System.out.println("==================================================");

        Customer.Address addr1 = new Customer.Address("104 Royal Residency", "Anand", "388001");
        Customer c1 = new Customer("Rohan Patel", "rohan@charusat.edu.in", "9988776655", addr1);
        System.out.println("Original Customer: " + c1);

        // Demonstrate clone()
        Customer clonedC1 = c1.clone();
        System.out.println("Cloned Customer:   " + clonedC1);
        System.out.println("Are references equal? " + (c1 == clonedC1));

        // Create Accounts
        Account a1 = new Account(c1.getName(), 15000);
        Account a2 = new Account("Ananya Sharma", 25000);
        Account a3 = new Account(a1.getAccountNumber(), "Rohan Patel (Duplicate Key)", 5000);

        System.out.println("\n--- Account toString() Demonstrations ---");
        System.out.println("Account 1: " + a1);
        System.out.println("Account 2: " + a2);
        System.out.println("Account 3: " + a3);

        System.out.println("\n--- Account equals() and hashCode() Test ---");
        System.out.println("Comparing a1 with a2: " + a1.equals(a2) + " (Different account numbers)");
        System.out.println("Comparing a1 with a3: " + a1.equals(a3) + " (Same account number: " + a1.getAccountNumber() + ")");
        System.out.println("Hash code a1: " + a1.hashCode() + " | Hash code a3: " + a3.hashCode());

        System.out.println("\n--- instanceof Type Checking ---");
        Object testObj = a1;
        if (testObj instanceof Account acc) {
            System.out.println("Object is verified as Account instance: Number = " + acc.getAccountNumber());
        }
    }
}
