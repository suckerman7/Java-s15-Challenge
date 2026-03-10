package book;

public class Magazine extends Book {

    public Magazine(int id, String name, String author, double price, BookStatus status) {
        super(id, name, author, price, status, BookCategory.MAGAZINE);
    }
}
