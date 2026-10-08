public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Corporate Payroll Processing ===");
        Employee[] staff = {
            new FullTime("EMP101", "Dr. Rajesh Sharma", 85000),
            new PartTime("EMP102", "Priya Nair", 80, 500),
            new Intern("EMP103", "Aman Verma", 15000, "Dr. Rajesh Sharma"),
            new FullTime("EMP104", "Sneha Patel", 65000)
        };

        double totalDisbursement = 0;
        for (Employee emp : staff) {
            System.out.print(emp);
            if (emp instanceof Intern intern) {
                System.out.print("  [Note: Academic Intern, Mentored by: " + intern.getMentor() + "]");
            }
            System.out.println();
            totalDisbursement += emp.monthlySalary();
        }

        System.out.println("----------------------------------------");
        System.out.printf("Total Monthly Payroll Budget: ₹%.2f\n", totalDisbursement);
    }
}
