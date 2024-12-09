import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

public class Order {
    private static int counter = 0;
    private int orderID, date;
    private long total;
    private Cart cart;
    private Customer customer;
    private String status, request, paymentDetails, addressDetails;

    public Order(Customer customer, int date) {
        this.customer = customer;
        this.cart = new Cart(customer.getCart());
        this.status = "Order Received";
        this.orderID = counter++;
        this.total = customer.getCart().getTotal();
        this.date = date;
    }

    /*
    Stages of order
    1) Order Received
    2) Preparing
    3) Out for Delivery
    4) Delivered
    5) Denied
    6) Cancelled
    */

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getDate() {
        return date;
    }

    public long getTotal() {
        return total;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public String getRequest() {
        return request;
    }

    public String getStatus() {
        return status;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setStatus(String status) {
        this.status = status;
        if (status.equals("Delivered")) {
            // Adding the item to the ordered Hashset of customer
            for (String name : cart.getCnt().keySet()) {
                customer.add(name);
            }
        }
    }

    public Set<String> getItems() {
        return cart.getItems();
    }

    @Override
    public String toString() {
        return "OrderID " + orderID + " Order Status " + status + " Date " + date + " Customer " + customer.getType() + " " + customer.getName() + "\n" + cart;
    }
}
