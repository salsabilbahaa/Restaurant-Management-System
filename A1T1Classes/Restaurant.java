package A1T1Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Restaurant {
    Scanner input = new Scanner(System.in);

    private List<Order> orders;
    private List<Dish> dishes;
    private List<Customer> customers;

    public Restaurant(List<Order> orders, List<Dish> dishes) {
        this.orders = orders;
        this.dishes = dishes;
        this.customers = new ArrayList<>(); // Initialize customers list
    }

    public void displayDishes() {
        // Print table header
        System.out.println("\n");
        System.out.printf("%-20s | %-10s | %-15s |%n", "Dish Name", "Price", "Availability");
        System.out.println("-----------------------------------------------------");

        // Print dishes
        for (Dish dish : dishes) {
            dish.display();
        }
        System.out.println("-----------------------------------------------------");
    }

    public void displayOrders() {
        for (Order order : orders) {
            order.display();
        }
    }

    public void addDish() {
        String dishName;
        double price;
        boolean available;
        System.out.println("Enter Dish Name: ");
        dishName = input.nextLine();
        System.out.println("Enter Dish Price: ");
        price = input.nextDouble();
        System.out.println("Is the Dish Available? (true/false): ");
        available = input.nextBoolean();
        dishes.add(new Dish(dishName, price, available));
    }

    public void addOrder(Customer customer) {

        List<Dish> selectedDishes = new ArrayList<Dish>();

        while (true) {
            displayDishes();
            System.out.println("What do you want to do? \n" +
                    "1) Add dish to order.\n" +
                    "2) Finish order.\n" +
                    "Enter 1 or 2: ");
            String choice = input.nextLine();

            while (!Objects.equals(choice, "1") && !Objects.equals(choice, "2")) {
                System.out.println("What do you want to do? \n" +
                        "1) Add dish to order.\n" +
                        "2) Finish order.\n" +
                        "Enter 1 or 2: ");
                choice = input.nextLine();
            }
            if (Objects.equals(choice, "2")) {
                break;
            }

            System.out.println("Enter dish name to be added: ");
            String dishName = input.nextLine();

            Dish selectedDish = null;
            for (Dish dish : dishes) {
                if (dish.getDishName().equalsIgnoreCase(dishName) && dish.isAvailable()) {
                    selectedDish = dish;
                    break;
                }
            }

            if (selectedDish != null) {
                selectedDishes.add(selectedDish);
                System.out.println(dishName + " added to the order.");
            }
            else {
                System.out.println("Dish not available.");
            }
        }

        if (!selectedDishes.isEmpty()) {
            Order newOrder = new Order(orders.size() + 1, customer, selectedDishes);
            orders.add(newOrder);
            customer.addOrderToHistory(newOrder);
            System.out.println("Order placed successfully!");
        }
        else {
            System.out.println("No dishes were selected. Order was not placed.");
        }
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null; // Customer is not found
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        for (Order order : orders) {
            if (orderId == order.getOrderId()) {
                order.updateStatus(newStatus);
                return;
            }
        }
        System.out.println("Order not found.");
    }

    public void cancelOrder() {
        System.out.println("Enter ID of order you want to cancel: ");
        int orderID = input.nextInt();
        input.nextLine();

        Order foundOrder = null;
        for (Order order : orders) {
            if (order.getOrderId() == orderID) {
                foundOrder = order;
                break;
            }
        }

        if (foundOrder != null) {
            orders.remove(foundOrder);
            System.out.println("Order " + orderID + " canceled.");
        } else {
            System.out.println("Order not found.");
        }
    }

    public void completeOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.updateStatus("Completed");
                System.out.println("Order " + orderId + " has been marked as completed.");
                return;
            }
        }
        System.out.println("Order ID not found.");
    }
}
