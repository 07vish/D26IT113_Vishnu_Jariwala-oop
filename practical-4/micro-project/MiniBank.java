public class MiniBank {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("MiniBank Milestone 4: Regex Boundary Validation & Command Parsing");
        System.out.println("==================================================");

        System.out.println("--- Testing Customer Validation Rules ---");
        System.out.println("Mobile 9876543210 Valid? " + Validator.isValidMobile("9876543210"));
        System.out.println("Mobile 1234567890 Valid? " + Validator.isValidMobile("1234567890"));
        System.out.println("Email student@charusat.ac.in Valid? " + Validator.isValidEmail("student@charusat.ac.in"));
        System.out.println("Email invalid-email Valid? " + Validator.isValidEmail("invalid-email"));
        System.out.println("PAN ABCDE1234F Valid? " + Validator.isValidPan("ABCDE1234F"));
        System.out.println("IFSC SBIN0001234 Valid? " + Validator.isValidIfsc("SBIN0001234"));

        System.out.println("\n--- Parsing Console Command ---");
        String rawInput = "DEPOSIT AC0001 5000";
        System.out.println("Raw Input: \"" + rawInput + "\"");
        Command cmd = CommandParser.parse(rawInput);
        System.out.println("Parsed Command Object -> Type: " + cmd.type() + ", Account: " + cmd.accountNumber() + ", Amount: ₹" + cmd.amount());

        System.out.println("\n--- Assembling Statement via StringBuilder ---");
        Account acc = new Account(cmd.accountNumber(), "Rohan Patel", cmd.amount());
        String statement = StatementFormatter.buildStatement(acc);
        System.out.println(statement);
    }
}
