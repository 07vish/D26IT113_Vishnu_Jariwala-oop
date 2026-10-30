package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 201L;

    private final String sku;
    private final String name;
    private final double unitPrice;

    public Product(String sku, String name, double unitPrice) {
        this.sku = sku;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getSku() { return sku; }
    public String getName() { return name; }
    public double getUnitPrice() { return unitPrice; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(unitPrice, product.unitPrice) == 0 &&
               Objects.equals(sku, product.sku) &&
               Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, unitPrice);
    }

    @Override
    public String toString() {
        return String.format("Product[SKU: %s, Name: %-20s, Price: ₹%.2f]", sku, name, unitPrice);
    }
}
