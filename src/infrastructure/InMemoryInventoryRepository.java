package infrastructure;

import domain.Product;
import domain.IInventoryRepository;

import java.util.*;

public class InMemoryInventoryRepository implements IInventoryRepository {
    private final Map<String, Product> products = new HashMap<>();

    @Override
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Optional<Product> findById(String productId) {
        return Optional.ofNullable(products.get(productId));
    }

    @Override
    public List<Product> findAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void updateProduct(Product product) {
        products.put(product.getId(), product);
    }
}
