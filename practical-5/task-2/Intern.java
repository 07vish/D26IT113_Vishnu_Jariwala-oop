public class Intern extends Employee {
    private final double stipend;
    private final String mentor;

    public Intern(String id, String name, double stipend, String mentor) {
        super(id, name);
        this.stipend = stipend;
        this.mentor = mentor;
    }

    public String getMentor() { return mentor; }

    @Override
    public double monthlySalary() {
        return stipend;
    }
}
