public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Polymorphic Shape Areas & Totals ===");
        Shape[] shapes = {
            new Circle(5.0),
            new Rectangle(4.0, 6.0),
            new Triangle(3.0, 8.0),
            new Circle(2.5),
            new Rectangle(7.0, 3.0)
        };

        double totalArea = 0;
        Shape largestShape = shapes[0];

        for (Shape s : shapes) {
            double a = s.area();
            totalArea += a;
            System.out.println(s);
            if (a > largestShape.area()) {
                largestShape = s;
            }
        }

        System.out.println("----------------------------------------");
        System.out.printf("Combined Area of All Shapes: %.2f\n", totalArea);
        System.out.println("Largest Shape: " + largestShape);
    }
}
