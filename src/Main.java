import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String input;

        ByteMe BM = new ByteMe();
        GUI G = new GUI(BM);
        System.out.println("Do you want to enter ByteMe [Y/N]");
        input = read.nextLine();

        while (input.equalsIgnoreCase("Y")) {
            BM.run();
            G.display();
            System.out.println("Do you want to re-enter ByteMe");
            input = read.nextLine().trim();
        }
    }
}
