import java.util.*;

public class Cart {
    private long total;
    private Menu menu;
    private LinkedHashMap<String, Integer> cnt; // frequency count of each item

    public Cart(Menu menu) {
        this.menu = menu;
        this.cnt = new LinkedHashMap<String, Integer>();
        this.total = 0;
    }

    // Copy constructor for Cart class
    public Cart(Cart cart) {
        total = cart.getTotal();
        cnt = cart.getCnt();
        menu = cart.getMenu();
    }

    public Menu getMenu() {
        return menu;
    }

    public boolean contains(String name) {
        return cnt.get(name) != null && cnt.get(name) > 0;
    }

    public void add(String name, int quantity) {
        cnt.merge(name, quantity, Integer::sum); // item exists and available in shop
        int price = menu.get(name).getPrice();
        total += (long) price * quantity;
    }

    public LinkedHashMap<String, Integer> getCnt() {
        return cnt;
    }

    public void modify(String name, int quantity) {
        Item E = menu.get(name);
        int price = E.getPrice(), old_quantity = cnt.get(name);
        cnt.put(name, quantity);
        total -= (long) price * old_quantity;
        total += (long) price * quantity;
        System.out.println("Quantity modified from " + old_quantity + " to " + quantity);
    }

    public void remove(String name) {
        int price = menu.get(name).getPrice(), quantity = cnt.get(name);
        cnt.remove(name);
        total -= (long) price * quantity;
    }

    public long getTotal() {
        return total;
    }

    public void reset() {
         total = 0;
         cnt = new LinkedHashMap<String, Integer>();
    }

    public Set<String> getItems() {
        return cnt.keySet();
    }

    @Override
    public String toString() {
        StringBuilder cart = new StringBuilder("Total " + total + "\n");
        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            cart.append("Name ").append(entry.getKey()).append(" Count ").append(entry.getValue()).append("\n");
        }
        cart.deleteCharAt(cart.length() - 1);
        return cart.toString();
    }
}
