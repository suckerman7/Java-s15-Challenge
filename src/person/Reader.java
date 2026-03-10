package person;

import book.Book;
import book.BookStatus;
import book.Borrowable;

import java.util.ArrayList;
import java.util.List;

public abstract class Reader extends Person implements Borrowable {

    private List<Book> borrowedBooks;
    private static final int MAX_BOOK_LIMIT = 5;

    public Reader(int id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber);
        this.borrowedBooks = new ArrayList<>();
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getBorrowedBookCount() {
        return borrowedBooks.size();
    }

    public boolean hasReachedLimit() {
        return borrowedBooks.size() >= MAX_BOOK_LIMIT;
    }

    @Override
    public void borrowBook(Book book) {

        if (book == null) {
            System.out.println("This book is invalid!");
            return;
        }

        if (hasReachedLimit()) {
            System.out.println("The borrow limit has been reached! You cannot borrow any more books!");
            return;
        }

        if (borrowedBooks.contains(book)) {
            System.out.println(book.getName() + " has already been borrowed!");
            return;
        }

        if (book.getStatus() != BookStatus.AVAILABLE) {
            System.out.println(book.getName() + " is not available to borrow right now!");
            return;
        }

        borrowedBooks.add(book);
        book.setStatus(BookStatus.BORROWED);

        System.out.println(getName() + " has borrowed " + book.getName() + ".");
    }

    @Override
    public void returnBook(Book book) {

        if (!borrowedBooks.contains(book)) {
            System.out.println(getName() + " did not borrow this book.");
            return;
        }

        borrowedBooks.remove(book);
        book.setStatus(BookStatus.AVAILABLE);

        System.out.println(getName() + " has returned " + book.getName());
    }
}
