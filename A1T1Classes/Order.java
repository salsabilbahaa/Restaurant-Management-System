package A1T1Classes;

import java.util.List;

public class Order {
    private int orderId;
    private String status;
    private List<Dish> dishes;
    private double totalPrice;
    private Customer customer;
    private boolean isPaid;
    private String paymentMethod;

    public Order(int orderId, Customer customer, List<Dish> dishes) {
        this.orderId = orderId;
        this.customer = customer;
        this.dishes = dishes;
        this.status = "Pending";
        calculateTotal(); // Auto calculate total price
    }

    private void calculateTotal() {
        totalPrice = 0;
        for (Dish dish : dishes) {
            totalPrice += dish.getPrice();
        }
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Order " + orderId +
                " status updated to: " + newStatus);
    }

    public void display() {
        System.out.println("\nOrder ID: " + orderId +
                "\nStatus: " + status +
                "\nPaid: " + (isPaid ? "Yes (" + paymentMethod + ")" : "No"));
        System.out.println("Dishes:");
        for (Dish dish : dishes) {
            System.out.println("- " + dish.getDishName() + " ($" + dish.getPrice() + ")");
        }
        System.out.println("Total Price: $" + totalPrice);
    }

    public int getOrderId() {
        return orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setPaid(boolean isPaid, String paymentMethod) {
        this.isPaid = isPaid;
        this.paymentMethod = paymentMethod;
    }

}
