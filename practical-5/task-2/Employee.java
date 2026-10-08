public abstract class Employee {
    private final String id;
    private final String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public abstract double monthlySalary();

    @Override
    public String toString() {
        return "[" + id + "] " + name + " -> ₹" + String.format("%.2f", monthlySalary());
    }
}
