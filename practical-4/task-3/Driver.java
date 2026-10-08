public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Template Placeholder Interpolation ===");
        String template = "Dear {name}, your order #{id} is scheduled to ship on {date}. Delivery address: {city}.";
        
        String[] names = { "name", "id", "city" };
        String[] values = { "Riya", "A07", "Changa" }; // Note: {date} is deliberately unsupplied

        System.out.println("Template:\n  " + template + "\n");
        String filled = TemplateFiller.fill(template, names, values);
        System.out.println("Resulting Output:\n  " + filled);
    }
}
