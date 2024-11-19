import java.util.HashSet;
import java.util.LinkedList;

public class Customer {
    private String name, type, password;
    private Cart cart;
    private LinkedList<Order> history;
    private HashSet<String> ordered;
    private Menu menu;

    public Customer(String name, String password, String type, Menu menu) {
        this.name = name;
        this.password = password;
        this.type = type;
        this.history = new LinkedList<Order>();
        this.ordered = new HashSet<String>();
        this.menu = menu;
        this.cart = new Cart(menu);
    }

    public Cart getCart() {
        return cart;
    }

    public void add(String item, int quantity) {
        Item E = menu.get(item);
        if (E != null && E.getStatus().equals("Available")) {
            cart.add(item, quantity);
            System.out.println("Added " + quantity + " " + item);
        } else {
            System.out.println("Invalid addition");
        }
    }

    public void add(Order order) {
        history.add(order);
        cart.reset();
    }

    public void add(String name) {
        ordered.add(name);
    }

    public void remove(String item) {
        cart.remove(item);
    }

    public void modify(String name, int quantity) {
        Item E = menu.get(name);
        if (E != null && E.getStatus().equals("Available")) {
            cart.modify(name, quantity);
        } else {
            cart.remove(name);
        }
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean hasOrdered(String name) {
        return ordered.contains(name);
    }

    public void cancelOrder(int orderID) {
        for (Order order : history) {
            String status = order.getStatus();
            if (order.getOrderID() == orderID && !(status.equals("Delivered") || status.equals("Out for Delivery") || status.equals("Denied"))) {
                order.setStatus("Cancelled");
            }
        }
    }

    public void setCart(int orderID) {
        for (Order order : history) {
            if (order.getOrderID() == orderID) {
                cart = new Cart(order.getCart());
                return;
            }
        }
    }

    public void orderHistory() {
        for (Order order : history) {
            System.out.println(order);
        }
    }

    public void viewOrderStatus(int orderID) {
        for (Order order : history) {
            if (order.getOrderID() == orderID) {
                System.out.println(order.getStatus());
            }
        }
    }
}
