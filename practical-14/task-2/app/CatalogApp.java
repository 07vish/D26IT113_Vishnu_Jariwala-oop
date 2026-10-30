package app;

import model.Product;
import service.ProductCatalog;
import test.CatalogTest;

public class CatalogApp {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Practical 14 - Task 2: Packaged App & Runnable JAR");
        System.out.println("==================================================");

        try {
            // Execute automated persistence unit test
            CatalogTest.runPersistenceTest();

            // Run packaged catalog workflow
            System.out.println("\nInitializing Production Catalog Service...");
            ProductCatalog catalog = new ProductCatalog();
            catalog.addProduct(new Product("SKU-PRO-01", "Developer Laptop 32GB", 115000.0));
            catalog.addProduct(new Product("SKU-PRO-02", "Mechanical Keyboard RGB", 4500.0));
            catalog.addProduct(new Product("SKU-PRO-03", "Ergonomic Vertical Mouse", 2800.0));

            System.out.println("Displaying Catalog Contents (" + catalog.getProducts().size() + " active items):");
            for (Product p : catalog.getProducts()) {
                System.out.println("  * " + p);
            }

            System.out.println("\nRunnable JAR execution verified successfully!");
        } catch (Exception e) {
            System.err.println("Fatal execution failure: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
