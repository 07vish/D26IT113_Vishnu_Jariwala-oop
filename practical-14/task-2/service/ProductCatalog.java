package service;

import model.Product;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductCatalog {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null");
        products.add(product);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void saveToFile(Path targetFile) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(targetFile.toFile()))) {
            oos.writeObject(new ArrayList<>(products));
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(Path sourceFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(sourceFile.toFile()))) {
            List<Product> loaded = (List<Product>) ois.readObject();
            products.clear();
            products.addAll(loaded);
        }
    }
}
