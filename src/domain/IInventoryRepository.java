package domain;

import java.util.List;
import java.util.Optional;

public interface IInventoryRepository {
    void addProduct(Product product);
    Optional<Product> findById(String productId);
    List<Product> findAllProducts();
    void updateProduct(Product product);
}