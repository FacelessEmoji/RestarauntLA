package domain;

import java.time.LocalDate;

public class Product {
    private String id;
    private String name;
    private int quantity;
    private LocalDate expiryDate;
    private String temperatureMode; // "замороженный", "охлажденный", "комнатная"
    private int minimumStock;
    private int optimalStock;

    public Product(String id, String name, int quantity, LocalDate expiryDate, String temperatureMode, int minimumStock, int optimalStock) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.temperatureMode = temperatureMode;
        this.minimumStock = minimumStock;
        this.optimalStock = optimalStock;
    }

    // Геттеры и сеттеры

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public void removeQuantity(int amount) {
        if(amount > this.quantity) {
            throw new IllegalArgumentException("Недостаточно количества продукта " + name);
        }
        this.quantity -= amount;
    }

    public boolean isCritical() {
        return quantity <= minimumStock;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", expiryDate=" + expiryDate +
                ", temperatureMode='" + temperatureMode + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
