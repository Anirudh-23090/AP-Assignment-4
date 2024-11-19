import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BrowseMenu {
    private Menu menu;
    private Collection<Item> items;

    public BrowseMenu(Menu menu) {
        this.menu = menu;
        this.items = menu.getItems();
    }

    public void viewItems() {
        for (Item E : items) {
            System.out.println(E);
        }
    }

    public void search(String name) {
        for (Item E : items) {
            if (E.getName().contains(name)) {
                System.out.println(E);
            }
        }
    }

    public void filter(String category) {
        for (Item E : items) {
            if (E.getCategory().equals(category)) {
                System.out.println(E);
            }
        }
    }

    public void sort(boolean reverse) {
        ArrayList<Item> arrayItems = new ArrayList<Item>(items);
        Collections.sort(arrayItems);
        if (reverse)
            Collections.reverse(arrayItems);
        for (Item E : arrayItems) {
            System.out.println(E);
        }
    }
}
