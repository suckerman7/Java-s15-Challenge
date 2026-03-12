package model;

import book.Book;
import invoice.Invoice;
import person.Reader;

import java.util.*;

public class Library {

    private List<Book> books;
    private Set<Reader> readers;
    private Map<Book, Reader> borrowedBooks;
    private List<Invoice> invoices;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new HashSet<>();
        this.borrowedBooks = new HashMap<>();
        this.invoices = new ArrayList<>();
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
