package A1T1Classes;

public class Dish {
    private String dishName;
    private double price;
    private boolean available;

    public Dish(String dishName, double price, boolean available) {
        this.dishName = dishName;
        this.price = price;
        this.available = available;
    }

    public void display() {
        System.out.printf("%-20s | $%-9.2f | %-15s |%n",
                dishName,
                price,
                available ? "Available" : "Unavailable");
    }

    public double getPrice() {
        return price;
    }

    public String getDishName() {
        return dishName;
    }

    public boolean isAvailable() {
        return available;
    }
}
