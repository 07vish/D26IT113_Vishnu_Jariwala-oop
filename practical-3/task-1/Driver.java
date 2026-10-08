public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Distinct 2D Points Evaluation ===");
        Point[] points = {
            new Point(3, 4),
            new Point(1, 2),
            new Point(3, 4), // Duplicate of points[0]
            new Point(5, 6),
            new Point(1, 2)  // Duplicate of points[1]
        };

        System.out.println("Points Array:");
        for (Point p : points) {
            System.out.print(p + " ");
        }
        System.out.println();

        int distinctCount = 0;
        for (int i = 0; i < points.length; i++) {
            boolean isDistinct = true;
            for (int j = 0; j < i; j++) {
                if (points[i].equals(points[j])) {
                    isDistinct = false;
                    break;
                }
            }
            if (isDistinct) {
                distinctCount++;
            }
        }

        System.out.println("Total Points: " + points.length);
        System.out.println("Distinct Points Count: " + distinctCount);
    }
}
