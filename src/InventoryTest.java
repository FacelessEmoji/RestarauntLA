import application.InventoryService;
import infrastructure.InMemoryInventoryRepository;

import java.time.LocalDate;

public class InventoryTest {
    public static void main(String[] args) {
        // Создаем репозиторий и сервис для инвентаризации
        InMemoryInventoryRepository repository = new InMemoryInventoryRepository();
        InventoryService inventoryService = new InventoryService(repository);

        // Тест: добавление продуктов
        System.out.println("=== Тест: Добавление продуктов ===");
        inventoryService.addProduct("P001", "Куриное филе", 100, LocalDate.now().plusDays(10), "охлажденный", 20, 50);
        inventoryService.addProduct("P002", "Картофель", 200, LocalDate.now().plusDays(5), "комнатная", 30, 100);

        // Тест: использование продукта (списание при приготовлении блюда)
        System.out.println("\n=== Тест: Использование продукта ===");
        inventoryService.useProduct("P001", 10); // уменьшение количества с 100 до 90

        // Тест: списание просроченных продуктов
        System.out.println("\n=== Тест: Списание просроченных продуктов ===");
        // Добавляем продукт с истекшим сроком годности
        inventoryService.addProduct("P003", "Свежий салат", 50, LocalDate.now().minusDays(1), "охлажденный", 10, 30);
        inventoryService.writeOffExpiredProducts();

        // Тест: корректировка запасов (например, ручная корректировка количества)
        System.out.println("\n=== Тест: Корректировка запасов ===");
        // Здесь можно реализовать корректировку, например, установить количество для P002 равным 180.
        inventoryService.adjustProductQuantity("P002", 180);

        // Тест: генерация отчета по запасам
        System.out.println("\n=== Отчет по запасам ===");
        inventoryService.generateReport();
    }
}
