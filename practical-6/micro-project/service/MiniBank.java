package service;

import model.Account;
import model.SavingsAccount;
import model.Premium;
import static java.lang.Math.max;

public class MiniBank {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("MiniBank Milestone 6: Clean Architecture & Interfaces");
        System.out.println("==================================================");

        SavingsAccount sa = new SavingsAccount("SA901", "Rohan Patel", 50000, 5000);
        System.out.println("Account Created: " + sa);
        System.out.println("Yearly Estimated Interest: ₹" + sa.yearlyInterest());

        // Check Marker Interface
        if (sa instanceof Premium) {
            System.out.println("Customer Status: Recognized as [Premium] Account holder.");
        }

        // Functional Interface with Anonymous Inner Class
        WithdrawRule dailyLimitRule = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                long maxDaily = 25000;
                return amount <= maxDaily && account.canWithdraw(amount);
            }
        };

        // Functional Interface with Lambda Expression
        WithdrawRule fraudAuditRule = (acc, amt) -> amt < 100000 && acc.canWithdraw(amt);

        long withdrawalAttempt = 20000;
        System.out.println("\nTesting ₹" + withdrawalAttempt + " withdrawal:");
        System.out.println("  Allowed by Daily Limit Rule (Anonymous): " + dailyLimitRule.allow(sa, withdrawalAttempt));
        System.out.println("  Allowed by Fraud Audit Rule (Lambda):     " + fraudAuditRule.allow(sa, withdrawalAttempt));

        sa.withdraw(withdrawalAttempt);
        System.out.println("Balance after authorized debit: ₹" + sa.getBalance());
        System.out.println("Calculated math ceiling via static import: ₹" + max(sa.getBalance(), 100000));
    }
}
