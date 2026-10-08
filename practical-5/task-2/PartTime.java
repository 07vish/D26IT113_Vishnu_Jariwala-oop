public class PartTime extends Employee {
    private final int hoursWorked;
    private final double hourlyRate;

    public PartTime(String id, String name, int hoursWorked, double hourlyRate) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double monthlySalary() {
        return hoursWorked * hourlyRate;
    }
}
