package model;

import book.Book;
import person.Reader;

import java.util.*;

public class Library {

    private List<Book> books;
    private Set<Reader> readers;
    private Map<Book, Reader> borrowedBooks;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new HashSet<>();
        this.borrowedBooks = new HashMap<>();
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
}
