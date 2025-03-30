package presentation;

import application.InventoryService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InventoryConsoleUI {
    private final InventoryService inventoryService;
    private final Scanner scanner;

    public InventoryConsoleUI(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(scanner.nextLine());
            handleMenuChoice(choice);
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println("\n===== Система управления инвентарем =====");
        System.out.println("1. Добавить продукт");
        System.out.println("2. Списать продукт при использовании");
        System.out.println("3. Списать просроченные продукты");
        System.out.println("4. Провести инвентаризацию (отчёт)");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                addProduct();
                break;
            case 2:
                useProduct();
                break;
            case 3:
                writeOffExpired();
                break;
            case 4:
                inventoryService.generateReport();
                break;
            case 0:
                System.out.println("Выход...");
                break;
            default:
                System.out.println("Неверный выбор. Повторите.");
        }
    }

    private void addProduct() {
        System.out.print("Введите ID продукта: ");
        String id = scanner.nextLine();
        System.out.print("Введите название продукта: ");
        String name = scanner.nextLine();
        System.out.print("Введите количество: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите срок годности (в формате yyyy-MM-dd): ");
        LocalDate expiryDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.print("Введите температурный режим (замороженный/охлажденный/комнатная): ");
        String tempMode = scanner.nextLine();
        System.out.print("Введите минимальный запас: ");
        int minStock = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите оптимальный запас: ");
        int optimalStock = Integer.parseInt(scanner.nextLine());

        try {
            inventoryService.addProduct(id, name, quantity, expiryDate, tempMode, minStock, optimalStock);
            System.out.println("Продукт успешно добавлен.");
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении продукта: " + e.getMessage());
        }
    }

    private void useProduct() {
        System.out.print("Введите ID продукта: ");
        String id = scanner.nextLine();
        System.out.print("Введите количество для списания: ");
        int amount = Integer.parseInt(scanner.nextLine());
        try {
            inventoryService.useProduct(id, amount);
            System.out.println("Продукт успешно списан для использования.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void writeOffExpired() {
        try {
            inventoryService.writeOffExpiredProducts();
            System.out.println("Просроченные продукты списаны.");
        } catch (Exception e) {
            System.out.println("Ошибка при списании просроченных продуктов: " + e.getMessage());
        }
    }
}