package A1T1Classes;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerId;
    private String customerName;
    private String phoneNumber;
    private List<Order> orderHistory;

    public Customer(int customerId, String customerName, String phoneNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.orderHistory = new ArrayList<Order>();
    }

    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }

    public void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No past orders found.");
        }
        else {
            for (Order order : orderHistory) {
                order.display();
            }
        }
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCustomerId() {
        return customerId;
    }

}
