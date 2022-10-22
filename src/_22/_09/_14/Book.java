package _22._09._14;

public class Book {

    private String name;

    private String authorName;

    private int price;

    private int quantity;

    public Book(String name, String authorName, int price, int quantity) {
        this.name = name;
        this.authorName = authorName;
        this.price = price;
        this.quantity = quantity;
    }

    public int value() {
        return price * quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", authorName='" + authorName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
