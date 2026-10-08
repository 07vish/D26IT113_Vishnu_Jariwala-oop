public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Password Strength Validator ===");
        String[] testPasswords = {
            "abc",
            "password123",
            "Charusat2026",
            "Abcd1234!",
            "admin"
        };

        for (String pw : testPasswords) {
            System.out.println("\nEvaluating: \"" + pw + "\"");
            System.out.println("  Length >= 8:      " + PasswordChecker.checkLength(pw));
            System.out.println("  Has Uppercase:    " + PasswordChecker.checkUpperCase(pw));
            System.out.println("  Has Digit:        " + PasswordChecker.checkDigit(pw));
            System.out.println("  Has Special Char: " + PasswordChecker.checkSpecialChar(pw));
            System.out.println("  Final Rating:     " + PasswordChecker.strength(pw));
        }
    }
}
