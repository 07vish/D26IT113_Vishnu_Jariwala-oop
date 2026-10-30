package test;

import model.Product;
import service.ProductCatalog;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CatalogTest {
    public static void runPersistenceTest() throws IOException, ClassNotFoundException {
        System.out.println("Running Catalog Persistence Unit Test (Save & Reload Integrity)...");

        Path tempFile = Files.createTempFile("catalog_test_", ".dat");
        try {
            ProductCatalog original = new ProductCatalog();
            original.addProduct(new Product("SKU-100", "4K OLED Monitor", 42999.0));
            original.addProduct(new Product("SKU-200", "Studio Microphone", 8500.0));
            original.addProduct(new Product("SKU-300", "Noise-Cancelling Headset", 16999.0));

            // Save original catalog to disk
            original.saveToFile(tempFile);

            // Reload into fresh catalog instance
            ProductCatalog reloaded = new ProductCatalog();
            reloaded.loadFromFile(tempFile);

            // Assert identity and content preservation
            List<Product> origList = original.getProducts();
            List<Product> reloadList = reloaded.getProducts();

            if (origList.size() != reloadList.size()) {
                throw new AssertionError("Size mismatch! Expected: " + origList.size() + ", Actual: " + reloadList.size());
            }

            for (int i = 0; i < origList.size(); i++) {
                if (!origList.get(i).equals(reloadList.get(i))) {
                    throw new AssertionError("Data mismatch at index " + i + ": " + origList.get(i) + " vs " + reloadList.get(i));
                }
            }

            System.out.println("  [PASS] Catalog saved and reloaded 3 products with 100% data integrity.");
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }
}
