public class FullTime extends Employee {
    private final double baseMonthly;

    public FullTime(String id, String name, double baseMonthly) {
        super(id, name);
        this.baseMonthly = baseMonthly;
    }

    @Override
    public double monthlySalary() {
        return baseMonthly;
    }
}
