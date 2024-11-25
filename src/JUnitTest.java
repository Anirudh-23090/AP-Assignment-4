import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JUnitTest {
    @Test
    public void loginTest() {
        ByteMe BM = new ByteMe();
        HashMap<String, String> userData = new HashMap<>();
        String name = "Anirudh Bharatiya", password = "AB@BM";
        Customer customer = new Customer(name, password, "Regular", BM.getMenu());
        userData.put(name, password);

        String outUser = get(BM, userData, "AnirudhBharatiya\nAB@BM\n");
        assertEquals("User doesn't exist", outUser); // Test for User doesn't exist

        String outPassword = get(BM, userData, "Anirudh Bharatiya\nAB-BM\n");
        assertEquals("Invalid Password", outPassword); // Test for Invalid Password
    }

    private String get(ByteMe BM, HashMap<String, String> userData, String input) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream out = System.out;
        InputStream in = System.in;

        try {
            System.setOut(new PrintStream(stream));
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            BM.setRead(new Scanner(System.in));
            BM.CustomerLogIn(userData);
        } finally {
            System.setOut(out);
            System.setIn(in);
        }

        String[] lines = stream.toString().trim().split("\n");
        return lines[lines.length - 1].trim();
    }

    @Test
    public void orderTest() {
        ByteMe BM = new ByteMe();

        String name = "Anirudh Bharatiya", password = "AB@BM";
        Customer customer = new Customer(name, password, "Regular", BM.getMenu());

        Cart cart = new Cart(BM.getMenu());
        Item E = new Item("Item", "Category", 100, "Unavailable"), F = new Item("item", "Category", 100, "Available");

        Menu menu = BM.getMenu();
        menu.add(E);
        menu.add(F);

        cart.add(F.getName(), 1);
        customer.setCart(cart);

        menu.remove(F.getName());

        String outContain = get(BM, customer, "x\ny\nz\n");
        assertEquals("Order is Invalid", outContain); // Test for Item doesn't exist

        cart = new Cart(BM.getMenu());
        cart.add(E.getName(), 1);
        String outAvailable = get(BM, customer, "x\ny\nz\n");
        assertEquals("Order is Invalid", outAvailable); // Test for Item Unavailable
    }

    public String get(ByteMe BM, Customer customer, String input) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream out = System.out;
        InputStream in = System.in;

        try {
            System.setOut(new PrintStream(stream));
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            BM.setRead(new Scanner(System.in));
            BM.checkout(customer);
        } finally {
            System.setOut(out);
            System.setIn(in);
        }

        String[] lines = stream.toString().trim().split("\n");
        return lines[lines.length - 1].trim();
    }
}
