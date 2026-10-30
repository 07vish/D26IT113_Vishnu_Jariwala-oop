package service;

import model.Account;
import model.Customer;
import repository.Repository;
import java.util.ArrayList;

public class MiniBank {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("MiniBank Milestone 13: Generic Reflective Repository");
        System.out.println("==================================================");

        // 1. Generic Repository for Account entities
        System.out.println("\n[1] Initializing Repository<Account> (Reflection @Id extraction):");
        Repository<Account> accountRepo = new Repository<>();

        Customer c1 = new Customer("CUST101", "Vishnu Jariwala", "vishnu@charusat.edu.in");
        Customer c2 = new Customer("CUST102", "Diya Shah", "diya@charusat.edu.in");
        Customer c3 = new Customer("CUST103", "Aarav Patel", "aarav@charusat.edu.in");

        Account a1 = new Account("AC0001", c1, 5000);
        Account a2 = new Account("AC0002", c2, 2000);
        Account a3 = new Account("AC0003", c3, 9000);

        // Save accounts using single save and varargs saveAll
        accountRepo.save(a1);
        accountRepo.saveAll(a2, a3);
        System.out.println("  Total accounts persisted in repository: " + accountRepo.count());

        // Find by ID via reflection-mapped key
        System.out.println("\n[2] Lookup Account by ID 'AC0002':");
        Account found = accountRepo.findById("AC0002");
        System.out.println("  Found: " + found);

        // Retrieve all accounts
        System.out.println("\n[3] Listing All Accounts via repository.findAll():");
        ArrayList<Account> allAccounts = accountRepo.findAll();
        for (Account a : allAccounts) {
            System.out.println("  " + a);
        }

        // Bounded type method for portfolio statistics
        Long[] balances = allAccounts.stream().map(Account::getBalance).toArray(Long[]::new);
        double avgBalance = Repository.average(balances);
        System.out.printf("\n[4] Portfolio Statistics (Bounded Type <N extends Number>):%n");
        System.out.printf("  Average Account Balance: ₹%.2f%n", avgBalance);

        // 2. Supplementary: Reusing the exact same generic Repository<Customer> without changes
        System.out.println("\n[5] Reusing Repository<Customer> for Customer Entities (Supplementary):");
        Repository<Customer> customerRepo = new Repository<>();
        customerRepo.saveAll(c1, c2, c3);
        System.out.println("  Total customers registered: " + customerRepo.count());
        System.out.println("  Lookup Customer 'CUST101': " + customerRepo.findById("CUST101"));

        // Delete test
        System.out.println("\n[6] Repository Deletion Test:");
        boolean deleted = accountRepo.delete("AC0002");
        System.out.println("  Deleted AC0002: " + deleted);
        System.out.println("  Remaining accounts count: " + accountRepo.count());
        System.out.println("==================================================");
    }
}
