import java.util.Scanner;

public class MiniBank {
    public record BankInfo(String name, String branch) {}

    public enum MenuOption {
        OPEN_ACCOUNT(1, "Open New Account"),
        DEPOSIT(2, "Deposit Funds"),
        WITHDRAW(3, "Withdraw Funds"),
        TRANSFER(4, "Transfer Funds"),
        EXIT(5, "Exit Application");

        private final int optionNumber;
        private final String label;

        MenuOption(int optionNumber, String label) {
            this.optionNumber = optionNumber;
            this.label = label;
        }

        public int getOptionNumber() { return optionNumber; }
        public String getLabel() { return label; }

        public static MenuOption fromChoice(int choice) {
            for (MenuOption opt : values()) {
                if (opt.optionNumber == choice) return opt;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        BankInfo bankInfo = new BankInfo("CHARUSAT Central Bank", "Changa Campus Branch");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("==================================================");
        System.out.println("Welcome to " + bankInfo.name());
        System.out.println("Branch: " + bankInfo.branch());
        System.out.println("==================================================");

        while (running) {
            System.out.println("\n--- MiniBank Main Menu ---");
            for (MenuOption opt : MenuOption.values()) {
                System.out.println(opt.getOptionNumber() + ". " + opt.getLabel());
            }
            System.out.print("Select an option (1-5): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid menu number.");
                if (scanner.hasNext()) scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            MenuOption selected = MenuOption.fromChoice(choice);

            if (selected == null) {
                System.out.println("Error: Option " + choice + " is out of range. Choose between 1 and 5.");
                continue;
            }

            String response = switch (selected) {
                case OPEN_ACCOUNT -> "Action: Open Account — Feature will be initialized in Practical 2 (Account/Customer entities).";
                case DEPOSIT      -> "Action: Deposit — Core transaction engine will be attached in Practical 2.";
                case WITHDRAW     -> "Action: Withdraw — Balance debit and validation will be implemented in Practical 2.";
                case TRANSFER     -> "Action: Transfer — Multi-account fund transfer module active in Practical 8.";
                case EXIT         -> {
                    running = false;
                    yield "Thank you for using " + bankInfo.name() + ". Goodbye!";
                }
            };

            System.out.println(">> " + response);
        }

        scanner.close();
    }
}
