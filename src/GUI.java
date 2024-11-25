import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GUI {
    ByteMe BM;
    private Menu menu;

    public GUI(ByteMe BM) {
        this.BM = BM;
        this.menu = BM.getMenu();
    }

    public void display() {
        // Create a Container and Set Layout
        JPanel cardPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Create Components
        JPanel panel1 = createPendingOrders();
        JPanel panel2 = createMenuTable();

        // Add Components to Container
        cardPanel.add(panel1, "Card 1");
        cardPanel.add(panel2, "Card 2");

        // Create Navigation Buttons
        JButton nextButton = new JButton("Menu");
        JButton previousButton = new JButton("Pending Orders");

        // Add Action Listeners to Buttons
        nextButton.addActionListener(e -> cardLayout.show(cardPanel, "Card 2"));
        previousButton.addActionListener(e -> cardLayout.show(cardPanel, "Card 1"));

        // Set Up the Main Frame
        JFrame frame = new JFrame("ByteMe");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Add Navigation Buttons and CardPanel to Frame
        frame.add(cardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Display the Frame
        frame.setSize(1280, 720);
        frame.setVisible(true);
    }

    private JPanel createPendingOrders() {
        JPanel pendingOrdersPanel = new JPanel(new BorderLayout());
        String[][] data = getPendingOrders();
        String[] column = {"Order Number", "Items Ordered", "Status"};
        JTable jt = new JTable(data, column);
        JScrollPane sp = new JScrollPane(jt);
        pendingOrdersPanel.add(sp, BorderLayout.CENTER);
        return pendingOrdersPanel;
    }

    private String[][] getPendingOrders() {
        LinkedList<Order> orders = BM.getOrders();
        String[][] data = new String[orders.size()][3];

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            data[i][0] = String.valueOf(order.getOrderID());
            data[i][1] = String.join(", ", order.getItems());
            data[i][2] = order.getStatus();
        }
        return data;
    }

    private JPanel createMenuTable() {
        JPanel menuPanel = new JPanel(new BorderLayout());
        String[][] data = getMenu();
        String[] column = {"Name", "Status", "Price"};
        JTable jt = new JTable(data, column);
        JScrollPane sp = new JScrollPane(jt);
        menuPanel.add(sp, BorderLayout.CENTER);
        return menuPanel;
    }

    private String[][] getMenu() {
        ArrayList<Item> menu = new ArrayList<>(this.menu.getItems());
        String[][] data = new String[menu.size()][3];
        for (int i = 0; i < menu.size(); i++) {
            Item item = menu.get(i);
            data[i][0] = item.getName();
            data[i][1] = item.getStatus();
            data[i][2] = String.valueOf(item.getPrice());
        }
        return data;
    }
}
