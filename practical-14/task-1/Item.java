public class Item {
    private final String id;
    private final String name;
    private final double price;

    public Item(String id, String name, double price) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Item ID cannot be empty");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Item name cannot be empty");
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative: " + price);
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Item[" + id + ", " + name + ", ₹" + price + "]";
    }
}
