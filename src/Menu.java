import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Menu {
    private HashMap<String, Item> items;

    public Menu() {
        items = new HashMap<>();
    }

    public Item get(String name) {
        return items.get(name);
    }

    public boolean contains(String name) { return items.containsKey(name); }

    public void add(Item E) {
        items.put(E.getName(), E);
    }

    public void remove(String name) {
        items.remove(name);
    }

    public Collection<Item> getItems() {
        return items.values();
    }
}
