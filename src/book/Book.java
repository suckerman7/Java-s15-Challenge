package book;

import util.ValidationUtil;

import java.util.Objects;

public abstract class Book {

    private int id;
    private String name;
    private String author;
    private double price;
    private BookStatus status;
    private BookCategory category;

    public Book(int id, String name, String author, double price, BookStatus status, BookCategory category) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.status = status;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public BookStatus getStatus() {
        return status;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setId(int id) {

        ValidationUtil.requirePositiveLong((long) id, "This book cannot be found!");
        ValidationUtil.requirePositive(id, "The book's id cannot be less or equal to 0.");

        this.id = id;
    }

    public void setName(String name) {

        ValidationUtil.requireNonNull(name, "The book's name cannot be null.");
        ValidationUtil.requireNonEmpty(name, "The book's name cannot be empty.");

        this.name = name;
    }

    public void setAuthor(String author) {

        ValidationUtil.requireNonNull(author, "The book's author cannot be null.");
        ValidationUtil.requireNonEmpty(author, "The book's author cannot be empty.");

        this.author = author;
    }

    public void setPrice(double price) {

        ValidationUtil.requirePositiveDouble(price, "The book's price cannot be less or equal to 0.");

        this.price = price;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name=" + name + '\'' +
                ", author=" + author + '\'' +
                ", price=" + price + '\'' +
                ", status=" + status + '\'' +
                ", category=" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
