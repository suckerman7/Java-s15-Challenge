package library;

import book.Book;
import invoice.Invoice;
import person.Reader;

import java.util.*;

public class SingletonLibrary {

    private static SingletonLibrary instance;

    private List<Book> books;
    private Set<Reader> readers;
    private Map<Book, Reader> borrowedBooks;
    private List<Invoice> invoices;

    private SingletonLibrary() {
        this.books = new ArrayList<>();
        this.readers = new HashSet<>();
        this.borrowedBooks = new HashMap<>();
        this.invoices = new ArrayList<>();
    }

    public static SingletonLibrary getInstance() {

        if (instance == null) {
            instance = new SingletonLibrary();
        }

        return instance;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Set<Reader> getReaders() {
        return readers;
    }

    public Map<Book, Reader> getBorrowedBooks() {
        return borrowedBooks;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
}
