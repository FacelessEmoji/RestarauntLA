package domain;

import java.util.List;

public class Inventory {
    private IInventoryRepository repository;

    public Inventory(IInventoryRepository repository) {
        this.repository = repository;
    }

    // Добавление нового продукта
    public void addNewProduct(Product product) {
        repository.addProduct(product);
    }

    // Списание продукта (при использовании для приготовления)
    public void useProduct(String productId, int amount) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Продукт не найден"));
        product.removeQuantity(amount);
        repository.updateProduct(product);
    }

    // Списание просроченных продуктов
    public void writeOffExpiredProducts() {
        List<Product> products = repository.findAllProducts();
        for(Product product : products) {
            if(product.isExpired()) {
                // Можно пометить продукт как списанный или удалить из репозитория
                product.removeQuantity(productQuantity(product)); // например, списать всё
                repository.updateProduct(product);
            }
        }
    }

    // Корректировка запасов (инвентаризация)
    public void adjustInventory(String productId, int newQuantity) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Продукт не найден"));
        // Здесь можно задать более сложную логику корректировки
        // Например, установить новое количество
        // Для простоты: заменим количество на новое
        // (Предполагаем, что есть метод setQuantity)
        // product.setQuantity(newQuantity);
        // repository.updateProduct(product);
    }

    // Генерация отчётов о текущих запасах и отслеживание критических уровней
    public void generateInventoryReport() {
        List<Product> products = repository.findAllProducts();
        System.out.println("=== Отчёт по запасам ===");
        for (Product product : products) {
            System.out.println(product);
            if(product.isCritical()){
                System.out.println("   ** Критический уровень запаса!");
            }
        }
    }

    // Метод для получения количества продукта (может быть дополнительным, если нужно)
    private int productQuantity(Product product) {
        // Например, возвращаем текущие количество
        // Если необходимо, можно расширить функциональность
        return product.getQuantity();
    }
}