package book;

public class Journal extends Book {

    public Journal(int id, String name, String author, double price, BookStatus status) {
        super(id, name, author, price, status, BookCategory.JOURNAL);
    }
}
