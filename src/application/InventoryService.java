package application;

import domain.Inventory;
import domain.Product;
import domain.IInventoryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class InventoryService {
    private final Inventory inventory;

    public InventoryService(IInventoryRepository repository) {
        this.inventory = new Inventory(repository);
    }

    public void addProduct(String id, String name, int quantity, LocalDate expiryDate, String temperatureMode, int minimumStock, int optimalStock) {
        Product product = new Product(id, name, quantity, expiryDate, temperatureMode, minimumStock, optimalStock);
        inventory.addNewProduct(product);
    }

    public void useProduct(String productId, int amount) {
        inventory.useProduct(productId, amount);
    }

    public void writeOffExpiredProducts() {
        inventory.writeOffExpiredProducts();
    }

    public void generateReport() {
        inventory.generateInventoryReport();
    }

    // Дополнительные методы, например, корректировка запасов
    public void adjustProductQuantity(String productId, int newQuantity) {
        inventory.adjustInventory(productId, newQuantity);
    }

    public Optional<Product> getProduct(String productId) {
        // Реализация поиска через репозиторий
        // (можно добавить в Inventory или вынести в сервис)
        return Optional.empty();
    }

    public List<Product> getAllProducts() {
        // Аналогично, делегировать запрос репозиторию
        return null;
    }
}