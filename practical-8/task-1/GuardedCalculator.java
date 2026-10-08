import java.util.Scanner;

public class GuardedCalculator {
    public static double compute(double a, double b, String op) throws DivideByZeroException {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (Math.abs(b) < 1e-9) {
                    throw new DivideByZeroException("Cannot divide by zero.");
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Unsupported arithmetic operator: " + op);
        };
    }

    public static void main(String[] args) {
        System.out.println("=== Robust Guarded Calculator ===");
        Scanner scanner = new Scanner(System.in);
        boolean success = false;
        int attempt = 0;

        while (!success && scanner.hasNext()) {
            attempt++;
            System.out.println("\n--- Calculation Attempt #" + attempt + " ---");
            try {
                System.out.print("Enter first operand: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid numeric input for first operand!");
                    scanner.next();
                    continue;
                }
                double num1 = scanner.nextDouble();

                System.out.print("Enter operator (+, -, *, /): ");
                String op = scanner.next();

                System.out.print("Enter second operand: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid numeric input for second operand!");
                    scanner.next();
                    continue;
                }
                double num2 = scanner.nextDouble();

                double result = compute(num1, num2, op);
                System.out.printf("Result: %.2f %s %.2f = %.2f\n", num1, op, num2, result);
                success = true;
            } catch (DivideByZeroException e) {
                System.out.println("[Handled Arithmetic Error] " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("[Handled Input Error] " + e.getMessage());
            } finally {
                System.out.println("Log: Finished processing attempt #" + attempt + " (Success status: " + success + ")");
            }
        }
        scanner.close();
    }
}
