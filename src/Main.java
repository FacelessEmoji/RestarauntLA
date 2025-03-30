import application.InventoryService;
import infrastructure.InMemoryInventoryRepository;
import presentation.InventoryConsoleUI;

public class Main {
    public static void main(String[] args) {
        InMemoryInventoryRepository repository = new InMemoryInventoryRepository();
        InventoryService service = new InventoryService(repository);
        InventoryConsoleUI ui = new InventoryConsoleUI(service);

        // Можно добавить тестовые данные, если нужно

        ui.start();
    }
}