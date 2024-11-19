import java.util.HashMap;
import java.util.LinkedList;

public class MenuManagement {
    private Menu menu;
    private LinkedList<Order> orders; // Pending orders
    private HashMap<String, Customer> customers;

    public MenuManagement(Menu menu, LinkedList<Order> orders, HashMap<String, Customer> customers) {
        this.menu = menu;
        this.orders = orders;
        this.customers = customers;
    }

    public void add(String name, String category, int price, String status) {
        Item E = new Item(name, category, price, status);
        menu.add(E);
    }

    public void update(String name, String status) {
        // code to update status of item e
        Item E = menu.get(name);
        E.setStatus(status);
    }

    public void update(String name, int price) {
        // code to update price of item e
        Item E = menu.get(name);
        E.setPrice(price);
    }

    public void remove(String name) {
        // code to remove item e
        menu.remove(name);
        // change status of the 'pending' orders with this item as 'denied'
        for (Order order : orders) {
            String status = order.getStatus();
            if (order.getCart().contains(name) && !(status.equals("Delivered") || status.equals("Out for Delivery"))) {
                order.setStatus("Denied");
                System.out.println(order);
            }
        }
        for (Customer customer : customers.values()) {
            if (customer.getCart().contains(name)) {
                customer.remove(name);
                System.out.println("Removed " + name + " " + customer.getType() + " " + customer.getName());
            }
        }
    }
}
