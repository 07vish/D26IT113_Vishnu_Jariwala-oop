public class ParkingLot {
    private int twoWheelers;
    private int fourWheelers;
    private final int twoCap;
    private final int fourCap;
    private static long revenue = 0;

    public ParkingLot(int twoCap, int fourCap) {
        this.twoCap = twoCap;
        this.fourCap = fourCap;
        this.twoWheelers = 0;
        this.fourWheelers = 0;
    }

    public void park(String type) {
        if (type == null) return;
        if (type.equalsIgnoreCase("two")) {
            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
                System.out.println("Parked 2-Wheeler. Slot: " + twoWheelers + "/" + twoCap + " | Fee: ₹20");
            } else {
                System.out.println("Full: 2-Wheeler parking section is at full capacity!");
            }
        } else if (type.equalsIgnoreCase("four")) {
            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
                System.out.println("Parked 4-Wheeler. Slot: " + fourWheelers + "/" + fourCap + " | Fee: ₹40");
            } else {
                System.out.println("Full: 4-Wheeler parking section is at full capacity!");
            }
        } else {
            System.out.println("Invalid vehicle category: " + type);
        }
    }

    public void leave(String type) {
        if (type == null) return;
        if (type.equalsIgnoreCase("two")) {
            if (twoWheelers > 0) {
                twoWheelers--;
                System.out.println("2-Wheeler departed. Current occupancy: " + twoWheelers + "/" + twoCap);
            }
        } else if (type.equalsIgnoreCase("four")) {
            if (fourWheelers > 0) {
                fourWheelers--;
                System.out.println("4-Wheeler departed. Current occupancy: " + fourWheelers + "/" + fourCap);
            }
        }
    }

    public int getTwoWheelers() { return twoWheelers; }
    public int getFourWheelers() { return fourWheelers; }
    public static long getRevenue() { return revenue; }

    public static void main(String[] args) {
        System.out.println("=== Smart Parking Lot Management ===");
        ParkingLot lot = new ParkingLot(2, 2);

        System.out.println("\n--- Simulating Arrivals ---");
        lot.park("two");
        lot.park("two");
        lot.park("two"); // Should be full
        lot.park("four");
        lot.park("four");
        lot.park("four"); // Should be full

        System.out.println("\n--- Simulating Departures ---");
        lot.leave("two");
        lot.leave("four");

        System.out.println("\n--- Attempting to Park after Departure ---");
        lot.park("two");

        System.out.println("\n========================================");
        System.out.println("Current Occupancy -> 2-Wheelers: " + lot.getTwoWheelers() + " | 4-Wheelers: " + lot.getFourWheelers());
        System.out.println("Total Revenue Collected: ₹" + ParkingLot.getRevenue());
    }
}
