import java.util.*;

public class ByteMe {
    private static int date = 0;
    private long sales;
    private int orderCnt;
    private Menu menu;
    private Scanner read = new Scanner(System.in);
    private String input;
    private LinkedList<Order> orders;
    private LinkedHashMap<Integer, Order> history;
    private HashMap<String, Customer> customers;
    private MenuManagement menuManagement;
    private OrderManagement orderManagement;
    private BrowseMenu browseMenu;

    public ByteMe() {
        menu = new Menu();
        orders = new LinkedList<Order>();
        history = new LinkedHashMap<Integer, Order>();
        customers = new HashMap<String, Customer>();
        menuManagement = new MenuManagement(menu, orders, customers);
        orderManagement = new OrderManagement(orders, history);
        browseMenu = new BrowseMenu(menu);
        sales = 0; orderCnt = 0;
    }

    public void run() {
        System.out.println("Welcome to ByteMe");
        System.out.println("Do you want to enter the system? [Y/N]");
        input = read.nextLine();
        while (input.equalsIgnoreCase("Y")) {
            System.out.println("Already have an account? [Y/N]");
            input = read.nextLine();
            if (input.equalsIgnoreCase("N"))
                SignIn();
            else if (input.equalsIgnoreCase("Y"))
                LogIn();
            System.out.println("Do you want to continue? [Y/N]");
            input = read.nextLine();
        }
    }

    private void SignIn() {
        System.out.println("Sign-in as [Regular/VIP]");
        input = read.nextLine();
        if (input.equalsIgnoreCase("Regular")) {
            System.out.println("Enter your name");
            String name = read.nextLine();
            System.out.println("Enter your Password");
            String password = read.nextLine();
            Customer customer = new Customer(name, password, "Regular", menu);
            customers.put(name, customer);
            System.out.println("Successfully registered as Regular Customer");
        } else if (input.equalsIgnoreCase("VIP")) {
            System.out.println("Enter your name");
            String name = read.nextLine();
            System.out.println("Enter your Password");
            String password = read.nextLine();
            System.out.println("Confirm subscription by typing Pay");
            input = read.nextLine();
            if (input.equals("Pay")) {
                Customer customer = new Customer(name, password, "VIP", menu);
                customers.put(name, customer);
                System.out.println("Successfully registered as VIP Customer");
            } else {
                System.out.println("Sign-in Unsuccessful");
            }
        }
    }

    private void LogIn() {
        System.out.println("Log-in as [Customer/Admin]");
        input = read.nextLine();
        if (input.equalsIgnoreCase("Customer")) {
            CustomerLogIn();
        } else if (input.equalsIgnoreCase("Admin")) {
            AdminLogIn();
        }
    }

    private void CustomerLogIn() {
        System.out.println("Enter your Name");
        String name = read.nextLine();
        System.out.println("Enter your Password");
        String password = read.nextLine();
        Customer customer = customers.get(name);
        if (password.equals(customer.getPassword())) {
            CustomerInterface(customer);
        } else {
            System.out.println("Invalid Password");
        }
    }

    private void AdminLogIn() {
        System.out.println("Enter Admin Password");
        String password = read.nextLine();
        if (password.equals("Admin@ByteMe")) {
            AdminInterface();
        } else {
            System.out.println("Invalid Password");
        }
    }

    private void CustomerInterface(Customer customer) {
        System.out.println("1. Browse Menu");
        System.out.println("2. Cart Operations");
        System.out.println("3. Order Tracking");
        System.out.println("4. Item Reviews");
        System.out.println("5. Logout");
        input = read.nextLine();

        while (!input.equalsIgnoreCase("5")) {
            if (input.equals("1")) {
                BrowseMenu();
            } else if (input.equals("2")) {
                CartOperations(customer);
            } else if (input.equals("3")) {
                OrderTracking(customer);
            } else if (input.equals("4")) {
                ItemReviews(customer);
            }
            System.out.println("1. Browse Menu");
            System.out.println("2. Cart Operations");
            System.out.println("3. Order Tracking");
            System.out.println("4. Item Reviews");
            System.out.println("5. Logout");
            input = read.nextLine();
        }
    }

    private void BrowseMenu() {
        System.out.println("1. View all items");
        System.out.println("2. Search items");
        System.out.println("3. Filter by category");
        System.out.println("4. Sort by price");
        input = read.nextLine();
        if (input.equals("1")) {
            browseMenu.viewItems();
        } else if (input.equals("2")) {
            System.out.println("Enter keyword(s)");
            input = read.nextLine();
            browseMenu.search(input);
        } else if (input.equals("3")) {
            System.out.println("Enter category");
            input = read.nextLine();
            browseMenu.filter(input);
        } else {
            System.out.println("1. Ascending order");
            System.out.println("2. Descending order");
            input = read.nextLine();
            browseMenu.sort(!input.equals("1"));
        }
    }

    private void CartOperations(Customer customer) {
        System.out.println("1. Add items");
        System.out.println("2. Modify quantities");
        System.out.println("3. Remove items");
        System.out.println("4. View cart");
        System.out.println("5. Checkout process");
        input = read.nextLine();
        if (input.equals("1")) {
            System.out.println("Enter name of item");
            input = read.nextLine();
            System.out.println("Enter the quantity");
            int quantity = read.nextInt();
            read.nextLine();
            customer.add(input, quantity);
        } else if (input.equals("2")) {
            System.out.println("Enter name of item");
            input = read.nextLine();
            System.out.println("Enter the quantity");
            int quantity = read.nextInt();
            read.nextLine();
            customer.modify(input, quantity);
        } else if (input.equals("3")) {
            System.out.println("Enter name of item");
            input = read.nextLine();
            customer.remove(input);
            System.out.println("Item removed");
        } else if (input.equals("4")) {
            System.out.println(customer.getCart());
        } else {
            checkout(customer);
        }
    }

    private void OrderTracking(Customer customer) {
        System.out.println("1. View order status");
        System.out.println("2. Cancel order");
        System.out.println("3. Order history");
        System.out.println("4. Reorder from History");
        input = read.nextLine();
        if (input.equals("1")) {
            System.out.println("Enter Order ID of the order to view status");
            int orderID = read.nextInt();
            read.nextLine();
            customer.viewOrderStatus(orderID);
        } else if (input.equals("2")) {
            System.out.println("Enter Order ID of the order to cancel");
            int orderID = read.nextInt();
            read.nextLine();
            customer.cancelOrder(orderID);
        } else if (input.equals("3")) {
            customer.orderHistory();
        } else {
            reorderHistory(customer);
        }
    }

    private void reorderHistory(Customer customer) {
        System.out.println("Enter Order ID");
        int orderID = read.nextInt();
        read.nextLine();
        customer.setCart(orderID);
        checkout(customer);
    }

    private void checkout(Customer customer) {
        Order order = new Order(customer, date);
        System.out.println("Enter special request");
        input = read.nextLine();
        order.setRequest(input);
        System.out.println("Enter payment details");
        input = read.nextLine();
        order.setPaymentDetails(input);
        System.out.println("Enter address details");
        input = read.nextLine();
        order.setAddressDetails(input);
        if (validate(order)) {
            if (date == order.getDate()) {
                sales += order.getTotal();
                orderCnt++;
                for (String item : order.getCart().getItems()) {
                    Item E = menu.get(item);
                    int oldCnt = E.getCnt();
                    E.setCnt(oldCnt + 1);
                }
            }
            insert(order);
            customer.add(order);
        } else {
            System.out.println("Order is Invalid");
        }
    }

    private boolean validate(Order order) {
        for (String item : order.getCart().getItems()) {
            if (!menu.contains(item)) {
                return false;
            }
        }
        return true;
    }

    private void insert(Order order) {
        // Insertion at back for Regular
        // Iterating to find correct position for VIP
        if (order.getCustomer().getType().equals("Regular")) {
            orders.add(order);
        } else {
            ListIterator<Order> iterator = orders.listIterator();
            while (iterator.hasNext()) {
                Order order1 = iterator.next();
                    if (order1.getCustomer().getType().equals("Regular")) {
                        iterator.previous();
                        iterator.add(order);
                        break;
                    }
                }
            if (!iterator.hasNext()) {
                iterator.add(order);
            }
        }
    }

    private void ItemReviews(Customer customer) {
        System.out.println("1. Provide review");
        System.out.println("2. View reviews");
        input = read.nextLine();
        if (input.equals("1")) {
            System.out.println("Enter name of item");
            String name = read.nextLine();
            System.out.println("Enter the review");
            String review = read.nextLine();
            Item E = menu.get(name);
            if (customer.hasOrdered(name))
                E.addReview(review);
            else
                System.out.println("Item Not Ordered");
        } else {
            System.out.println("Enter name of item");
            String name = read.nextLine();
            Item E = menu.get(name);
            E.getReviews();
        }
    }

    private void AdminInterface() {
        System.out.println("1. Menu Management");
        System.out.println("2. Order Management");
        System.out.println("3. Report Generation");
        System.out.println("4. Logout");
        input = read.nextLine();

        while (!input.equalsIgnoreCase("4")) {
            if (input.equals("1")) {
                MenuManagement();
            } else if (input.equals("2")) {
                OrderManagement();
            } else {
                ReportGenerate();
            }
            System.out.println("1. Menu Management");
            System.out.println("2. Order Management");
            System.out.println("3. Report Generation");
            System.out.println("4. Logout");
            input = read.nextLine();
        }
    }

    private void MenuManagement() {
        System.out.println("1. Add new items");
        System.out.println("2. Update existing items");
        System.out.println("3. Remove items");
        input = read.nextLine();
        if (input.equals("1")) {
            System.out.println("Enter name");
            String name = read.nextLine();
            System.out.println("Enter category");
            String category = read.nextLine();
            System.out.println("Enter status [Available/Unavailable]");
            String status = read.nextLine();
            System.out.println("Enter price of item");
            int price = read.nextInt();
            read.nextLine();
            menuManagement.add(name, category, price, status);
        } else if (input.equals("2")) {
            System.out.println("Enter name");
            String name = read.nextLine();
            System.out.println("Update [price/status]");
            input = read.nextLine();
            if (input.equalsIgnoreCase("price")) {
                System.out.println("Enter price");
                int price = read.nextInt();
                read.nextLine();
                menuManagement.update(name, price);
                System.out.println("Price of " + name + " is updated to " + price);
            } else {
                System.out.println("Enter status [Available/Unavailable]");
                String status = read.nextLine();
                menuManagement.update(name, status);
                System.out.println("Price of " + name + " is updated to " + status);
            }
        } else if (input.equals("3")) {
            System.out.println("Enter name");
            input = read.nextLine();
            menuManagement.remove(input);
            System.out.println("Item " + input + " is removed");
        }
    }

    private void OrderManagement() {
        System.out.println("1. View pending orders");
        System.out.println("2. Update order status");
        System.out.println("3. Process refunds");
        System.out.println("4. Handle special requests");
        input = read.nextLine();
        if (input.equals("1")) {
            orderManagement.viewOrders();
        } else if (input.equals("2")) {
            System.out.println("Enter Order ID of the order");
            int orderID = read.nextInt();
            read.nextLine();
            ArrayList<String> options = orderManagement.options(orderID); // The possible options for status of the order
            System.out.println("Enter the status of the order " + options);
            String status = read.nextLine();
            if (options.contains(status))
                orderManagement.setStatus(orderID, status);
        } else if (input.equals("3")) {
            sales -= orderManagement.processRefunds(date);
        } else {
            System.out.println("Enter Order ID of the order");
            int orderID = read.nextInt();
            read.nextLine();
            orderManagement.handleRequest(orderID);
        }
    }

    private void ReportGenerate() {
        System.out.println("Total sales and orders of date " + date + " is " + sales + " " + orderCnt + " respectively");
        ArrayList<Item> popularity = new ArrayList<>(menu.getItems());
        popularity.sort((item1, item2) -> {
            return Integer.compare(item2.getCnt(), item1.getCnt()); // Descending order
        });
        for (int i = 0; i < popularity.size(); i++) {
            System.out.println(i + 1 + ") " + popularity.get(i).getName());
        }
        nextDay();
    }

    private void nextDay() {
        date++;
        for (Item E : menu.getItems()) {
            E.setCnt(0);
        }
        sales = 0;
        orderCnt = 0;
    }
}
