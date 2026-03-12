package service;

import book.Book;
import book.BookCategory;
import book.Searchable;
import invoice.Invoice;
import invoice.InvoiceStatus;
import model.Library;
import person.Reader;
import util.IdGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class LibraryService implements Searchable {

    private final Library library;

    public LibraryService(Library library) {
        this.library = library;
    }

    public void addBook(Book book) {

        if (book == null) {
            System.out.println("The book is invalid!");
            return;
        }

        library.getBooks().add(book);
        System.out.println(book.getName() + " has been added to the library!");
    }

    public void removeBook(int bookId) {

        Book book = searchById(bookId);

        if (book == null) {
            System.out.println("The book cannot be found!");
            return;
        }

        library.getBooks().remove(book);
        System.out.println("The book has been removed: " + book.getName());
    }

    public void updateBookPrice(int bookId, double newPrice) {

        Book book = searchById(bookId);

        if (book == null) {
            System.out.println("The book cannot be found!");
            return;
        }

        book.setPrice(newPrice);

        System.out.println("The price of " + book.getName() + " has been updated.");
    }

    public List<Book> listBooksByCategory(BookCategory category) {

        return library.getBooks()
                .stream()
                .filter(book -> book.getCategory() == category)
                .collect(Collectors.toList());
    }

    public List<Book> listBooksByAuthor(String author) {

        return library.getBooks()
                .stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public void borrowBook(int bookId, Reader reader) {

        Book book = searchById(bookId);

        Invoice invoice = new Invoice(
                IdGenerator.generateInvoiceId(),
                reader,
                book,
                book.getPrice()
        );

        library.getInvoices().add(invoice);

        if (book == null) {
            System.out.println("The book cannot be found!");
            return;
        }

        reader.borrowBook(book);

        library.getBorrowedBooks().put(book, reader);
    }

    public void returnBook(int bookId, Reader reader) {

        Book book = searchById(bookId);

        if (book == null) {
            System.out.println("The book cannot be found!");
            return;
        }

        reader.returnBook(book);

        library.getBorrowedBooks().remove(book);

        for (Invoice invoice : library.getInvoices()) {
            if (invoice.getBook().equals(book) &&
                invoice.getReader().equals(reader) &&
                invoice.getStatus() == InvoiceStatus.ISSUED) {

                invoice.refund();

                System.out.println("Invoice refunded.");
            }
        }
    }

    public void printBorrowedBooks() {

        library.getBorrowedBooks()
                .forEach((book, reader) ->
                        System.out.println(book.getName() + " has been borrowed by " + reader.getName()));
    }

    public void printInvoices() {

        library.getInvoices()
                .forEach(System.out::println);
    }

    @Override
    public Book searchById(int id) {

        return library.getBooks()
                .stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> searchByName(String name) {

        return library.getBooks()
                .stream()
                .filter(book -> book.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

    }

    @Override
    public List<Book> searchByAuthor(String author) {

        return library.getBooks()
                .stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }
}
