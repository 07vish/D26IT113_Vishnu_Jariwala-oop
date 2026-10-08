import java.util.Scanner;

public class TollBooth {
    public record Vehicle(String number, String type) {}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalToll = 0;
        int bikes = 0, cars = 0, trucks = 0;

        System.out.println("=== Automated Toll Plaza ===");
        System.out.println("Enter vehicle details (type 'done' as vehicle number to finish):");

        while (true) {
            System.out.print("Enter vehicle registration number: ");
            if (!scanner.hasNext()) break;
            String number = scanner.next().trim();
            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter vehicle type (bike/car/truck): ");
            if (!scanner.hasNext()) break;
            String type = scanner.next().trim().toLowerCase();

            Vehicle vehicle = new Vehicle(number, type);

            int toll = switch (vehicle.type()) {
                case "bike" -> {
                    bikes++;
                    yield 20;
                }
                case "car" -> {
                    cars++;
                    yield 50;
                }
                case "truck" -> {
                    trucks++;
                    yield 150;
                }
                default -> {
                    System.out.println("  Unrecognized vehicle type: " + vehicle.type());
                    yield 0;
                }
            };

            totalToll += toll;
            System.out.println("  Registered " + vehicle.number() + " (" + vehicle.type() + ") - Toll: ₹" + toll);
        }

        System.out.println("----------------------------------------");
        System.out.println("Total Toll Revenue: ₹" + totalToll);
        System.out.println("Vehicle Counts -> Bikes: " + bikes + ", Cars: " + cars + ", Trucks: " + trucks);

        String mostFrequent;
        if (bikes >= cars && bikes >= trucks) {
            mostFrequent = "bike (" + bikes + ")";
        } else if (cars >= bikes && cars >= trucks) {
            mostFrequent = "car (" + cars + ")";
        } else {
            mostFrequent = "truck (" + trucks + ")";
        }
        System.out.println("Most frequent vehicle category: " + mostFrequent);
        scanner.close();
    }
}
