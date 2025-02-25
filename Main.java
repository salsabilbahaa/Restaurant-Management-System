import A1T1Classes.Customer;
import A1T1Classes.Dish;
import A1T1Classes.Order;
import A1T1Classes.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Dish> dishes = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        // Add the starting menu
        Dish dish1 = new Dish("Cheese Burger", 23, true);
        dishes.add(dish1);
        Dish dish2 = new Dish("Spicy Chicken", 34, true);
        dishes.add(dish2);
        Dish dish3 = new Dish("Pizza", 45, true);
        dishes.add(dish3);
        Dish dish4 = new Dish("Hot dog", 20, false);
        dishes.add(dish4);
        Dish dish5 = new Dish("Fruit Salad", 13, true);
        dishes.add(dish5);
        Dish dish6 = new Dish("French Fries", 15, true);
        dishes.add(dish6);
        Dish dish7 = new Dish("Orange Juice", 4, true);
        dishes.add(dish7);
        Dish dish8 = new Dish("Iced Tea", 2, false);
        dishes.add(dish8);
        Dish dish9 = new Dish("Coffee", 3, true);
        dishes.add(dish9);
        Dish dish10 = new Dish("Milk Shake", 4, true);
        dishes.add(dish10);

        Restaurant restaurant = new Restaurant(orders, dishes);
        Scanner input = new Scanner(System.in);
        int customerId = 0;

        while (true) {
            System.out.println("\n**Welcome to Restaurant Management System**\n" +
                    "What do you want to do?\n" +
                    "1) Display current menu.\n" +
                    "2) Display current orders.\n" +
                    "3) Add a dish to menu\n" +
                    "4) Add an order.\n" +
                    "5) View order history.\n" +
                    "6) Cancel an order.\n" +
                    "7) Complete an order.\n" +
                    "8) Exit.\n\n" +
                    "Enter your choice (from 1 to 8): ");
            String choice1 = input.nextLine();

            switch (choice1) {
                case "1": // Display menu
                    restaurant.displayDishes();
                    break;

                case "2": // Display current orders
                    restaurant.displayOrders();
                    break;

                case "3": // Add dish to the menu
                    restaurant.addDish();
                    break;

                case "4": // Add an order
                    System.out.println("Enter customer name: ");
                    String customerName = input.nextLine();
                    System.out.println("Enter customer phone number: ");
                    String phoneNumber = input.nextLine();
                    Customer customer = new Customer(++customerId, customerName, phoneNumber);
                    System.out.println("Customer data is added.");
                    restaurant.addCustomer(customer);
                    restaurant.addOrder(customer);
                    break;

                case "5": // View order history of a customer
                    System.out.println("Enter customer ID to view history: ");
                    int id = input.nextInt();
                    input.nextLine();

                    Customer foundCustomer = restaurant.findCustomerById(id);

                    if (foundCustomer != null) {
                        foundCustomer.viewOrderHistory();
                    }
                    else {
                        System.out.println("Customer not found.");
                    }

                    break;

                case "6": // Cancel an order
                    restaurant.cancelOrder();
                    break;

                case "7":  // Completing an order
                    System.out.println("Enter order ID to mark as completed: ");
                    int completedOrderId = input.nextInt();
                    input.nextLine();

                    // Find the order
                    Order neededOrder = null;
                    for (Order order : orders) {
                        if (order.getOrderId() == completedOrderId) {
                            neededOrder = order;
                            break;
                        }
                    }

                    if (neededOrder == null) {
                        System.out.println("Order ID not found.");
                        break;
                    }

                    // Process payment
                    System.out.println("Total amount: $" + neededOrder.getTotalPrice());
                    System.out.println("Enter payment method (Cash/Card): ");
                    String paymentMethod = input.nextLine();
                    neededOrder.setPaid(true, paymentMethod);
                    restaurant.completeOrder(completedOrderId);
                    break;

                case "8": // Exit the program
                    System.out.println("Exiting.\n");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid input. Try again.");
                    break;
            }
        }
    }
}
