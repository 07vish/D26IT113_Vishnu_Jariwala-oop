public class CommandParser {
    public static Command parse(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Command line cannot be null.");
        }
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid format. Expected: <TYPE> <ACCOUNT_NO> <AMOUNT>");
        }

        TransactionType type = TransactionType.valueOf(parts[0].toUpperCase());
        String accountNo = parts[1];
        long amount = Long.parseLong(parts[2]);

        return new Command(type, accountNo, amount);
    }
}
