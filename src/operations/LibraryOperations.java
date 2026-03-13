package operations;

import book.Book;
import book.BookCategory;
import book.Searchable;
import invoice.Invoice;
import invoice.InvoiceStatus;
import library.SingletonLibrary;
import person.Reader;
import util.IdGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class LibraryOperations implements Searchable {

    private SingletonLibrary singletonLibrary;

    public LibraryOperations(SingletonLibrary singletonLibrary) {
        this.singletonLibrary = singletonLibrary;
    }

    public void addBook(Book book) {

        if (book == null) {
            System.out.println("The book is invalid!");
            return;
        }

        singletonLibrary.getBooks().add(book);
        System.out.println(book.getName() + " has been added to the library!");
    }

    public void removeBook(int bookId) {

        Book book = searchById(bookId);

        if (book == null) {
            System.out.println("The book cannot be found!");
            return;
        }

        singletonLibrary.getBooks().remove(book);
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

        List<Book> bookList = singletonLibrary.getBooks()
                .stream()
                .filter(book -> book.getCategory() == category)
                .toList();

        if (bookList.isEmpty()) {
            System.out.println("No books have been found in this category.");
        }

        return bookList;
    }

    public List<Book> listBooksByAuthor(String author) {

        return singletonLibrary.getBooks()
                .stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public void borrowBook(int bookId, Reader reader) {

        Book book = searchById(bookId);

        if (book == null) {
            System.out.println("The book cannot be found!");
            return;
        }

        reader.borrowBook(book);

        singletonLibrary.getBorrowedBooks().put(book, reader);

        Invoice invoice = new Invoice(
                IdGenerator.generateInvoiceId(),
                reader,
                book,
                book.getPrice()
        );

        singletonLibrary.getInvoices().add(invoice);

        System.out.println("Invoice has been issued for the borrowed book.");
    }

    public void returnBook(int bookId, Reader reader) {

        Book book = searchById(bookId);

        if (book == null) {
            System.out.println("The book cannot be found!");
            return;
        }

        reader.returnBook(book);

        singletonLibrary.getBorrowedBooks().remove(book);

        for (Invoice invoice : singletonLibrary.getInvoices()) {
            if (invoice.getBook().equals(book) &&
                invoice.getReader().equals(reader) &&
                invoice.getStatus() == InvoiceStatus.ISSUED) {

                invoice.refund();

                System.out.println("Invoice refunded.");
            }
        }
    }

    public void printBorrowedBooks() {

        if (singletonLibrary.getBorrowedBooks().isEmpty()) {
            System.out.println("You haven't borrowed any books yet.");
            return;
        }

        singletonLibrary.getBorrowedBooks()
                .forEach((book, reader) ->
                        System.out.println(book.getName() + " has been borrowed by " + reader.getName()));
    }

    public void printInvoices() {

        if (singletonLibrary.getInvoices().isEmpty()) {
            System.out.println("There are no recorded invoices.");
            return;
        }

        singletonLibrary.getInvoices()
                .forEach(System.out::println);
    }

    @Override
    public Book searchById(int id) {

        return singletonLibrary.getBooks()
                .stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> searchByName(String name) {

        return singletonLibrary.getBooks()
                .stream()
                .filter(book -> book.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

    }

    @Override
    public List<Book> searchByAuthor(String author) {

        return singletonLibrary.getBooks()
                .stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }
}
