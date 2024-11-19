import java.util.ArrayList;

public class Item implements Comparable<Item> {
    private String name, category, status;
    ArrayList<String> reviews;
    private int price;
    private int cnt;

    public Item(String name, String category, int price, String status) {
        this.name = name;
        this.category = category;
        this.status = status;
        this.price = price;
        this.reviews = new ArrayList<>();
        this.cnt = 0;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getStatus() {
        return status;
    }

    public void getReviews() {
        for (String review : reviews) {
            System.out.println(review);
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public void addReview(String s) {
        reviews.add(s);
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int compareTo(Item E) {
        return price - E.price;
    }

    @Override
    public String toString() {
        return ("Name - " + name + "\nCategory - " + category + "\nPrice - " + price + "\nStatus - " + status);
    }
}
