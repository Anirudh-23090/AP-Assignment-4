import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class OrderManagement {
    private LinkedList<Order> orders; // Pending orders
    private LinkedHashMap<Integer, Order> history;

    private static ArrayList<String> stages = new ArrayList<String>();
    // static block
    static {
        stages.add("Order Received");
        stages.add("Preparing");
        stages.add("Out for Delivery");
        stages.add("Delivered");
    }

    public OrderManagement(LinkedList<Order> orders, LinkedHashMap<Integer, Order> history) {
        this.orders = orders;
        this.history = history;
    }

    public void viewOrders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public long processRefunds(int date) {
        long refund = 0;
        for (Order order : orders) {
            if (order.getStatus().equals("Cancelled") || order.getStatus().equals("Denied")) {
                System.out.println("Refund of " + order.getTotal() + " to " + order.getCustomer().getType() + " " + order.getCustomer().getName());
                if (date == order.getDate())
                    refund += order.getTotal();
                orders.remove(order); // Remove from pending orders
                history.put(order.getOrderID(), order); // add to history
            }
        }
        return refund;
    }

    public void setStatus(int orderID, String status) {
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                order.setStatus(status);
                if (status.equals("Delivered")) {
                    orders.remove(orderID); // Remove from pending
                    history.put(orderID, order); // Adding to history
                }
                break;
            }
        }
    }

    public void handleRequest(int orderID) {
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                System.out.println("Request of " + order.getCustomer().getType() + " " + order.getCustomer().getName() + " of " + order.getRequest() + " is handled");
                break;
            }
        }
    }

    public ArrayList<String> options(int orderID) {
        ArrayList<String> ans = new ArrayList<>();
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                if (order.getStatus().equals("Denied") || order.getStatus().equals("Cancelled")) {
                    break;
                }
                for (int j = stages.size() - 1; j >= 0 && !stages.get(j).equals(order.getStatus()); j--) {
                    ans.add(stages.get(j));
                }
                break;
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}
