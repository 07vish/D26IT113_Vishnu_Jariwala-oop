public abstract class Shape {
    private final String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public abstract double area();

    @Override
    public String toString() {
        return name + " [Area = " + String.format("%.2f", area()) + "]";
    }
}
