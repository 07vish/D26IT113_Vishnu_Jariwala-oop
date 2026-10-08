package service;

import model.Account;
import util.AnnotationValidator;

public class MiniBank {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("MiniBank Milestone 7: Metadata Reflection & Annotation Validation");
        System.out.println("==================================================");

        // Intentionally create an account violating @Positive balance and @MaxLength accountNumber
        Account invalidAccount = new Account("AC999999999_OVERFLOW", "Aarav", -5000);
        Account validAccount = new Account("AC0001", "Diya Shah", 12500);

        System.out.println("Inspecting Account 1: " + invalidAccount);
        String[] errors = AnnotationValidator.validate(invalidAccount);
        System.out.println("Violations Found: " + errors.length);
        for (String err : errors) {
            System.out.println("  [Validation Fault] " + err);
        }

        System.out.println("\nInspecting Account 2: " + validAccount);
        String[] validErrors = AnnotationValidator.validate(validAccount);
        System.out.println("Violations Found: " + validErrors.length);
        if (validErrors.length == 0) {
            System.out.println("  Account passed all metadata validation rules successfully!");
        }
    }
}
