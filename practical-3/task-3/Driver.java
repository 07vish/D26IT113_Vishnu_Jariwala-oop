public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Fraction Simplification & Equality Check ===");
        Fraction f1 = new Fraction(2, 4);
        Fraction f2 = new Fraction(3, 6);
        Fraction f3 = new Fraction(10, -15);
        Fraction f4 = new Fraction(-2, 3);
        Fraction f5 = new Fraction(7, 1);

        System.out.println("f1 (2/4) reduced: " + f1);
        System.out.println("f2 (3/6) reduced: " + f2);
        System.out.println("f3 (10/-15) reduced: " + f3);
        System.out.println("f4 (-2/3) reduced: " + f4);
        System.out.println("f5 (7/1) reduced: " + f5);

        System.out.println("\nEquality Comparisons:");
        System.out.println("Does f1.equals(f2)? " + f1.equals(f2) + " (Both simplify to 1/2)");
        System.out.println("Does f3.equals(f4)? " + f3.equals(f4) + " (Both simplify to -2/3)");
        System.out.println("Does f1.equals(f3)? " + f1.equals(f3));
    }
}
