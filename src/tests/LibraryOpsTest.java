package tests;

import book.Book;
import book.BookCategory;
import book.factory.BookFactory;
import library.SingletonLibrary;
import operations.LibraryOperations;
import membership.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryOpsTest {

    private LibraryOperations operations;
    private SingletonLibrary library;
    private Student student;

    @BeforeEach
    void setup() {

        library = SingletonLibrary.getInstance();
        library.getBooks().clear();
        library.getBorrowedBooks().clear();
        library.getInvoices().clear();

        operations = new LibraryOperations(library);

        student = new Student(
                1,
                "John Doe",
                "johndoe@test.com",
                "02125555555"
        );
    }

    @Test
    void shouldAddBookSuccessfully() {

        Book book = BookFactory.createBook(
                BookCategory.STUDY_BOOK,
                "Thinking in Java",
                "Bruce Eckel",
                100
        );

        operations.addBook(book);

        assertEquals(1, library.getBooks().size());
    }

    @Test
    void shouldRemoveBookSuccessfully() {

        Book book = BookFactory.createBook(
                BookCategory.JOURNAL,
                "Kafasini Kaybeden Adam",
                "Salih Tuna",
                75
        );

        operations.addBook(book);

        operations.removeBook(book.getId());

        assertTrue(library.getBooks().isEmpty());
    }

    @Test
    void shouldListBooksByCategory() {

        Book book1 = BookFactory.createBook(
                BookCategory.STUDY_BOOK,
                "Kim Korkar Matematikten",
                "Nazif Tepedenlioglu",
                159
        );

        Book book2 = BookFactory.createBook(
                BookCategory.STUDY_BOOK,
                "Super Zeka: Yapay Zeka Uygulamalari, Tehlikeler ve Stratejiler",
                "Nick Bostrom",
                450
        );

        operations.addBook(book1);
        operations.addBook(book2);

        var books = operations.listBooksByCategory(BookCategory.STUDY_BOOK);

        assertEquals(2, books.size());
    }

    @Test
    void shouldBorrowBookSuccessfully() {

        Book book = BookFactory.createBook(
                BookCategory.MAGAZINE,
                "Bilim Cocuk",
                "Tubitak",
                79
        );

        operations.addBook(book);

        operations.borrowBook(book.getId(), student);

        assertEquals(1, student.getBorrowedBooks().size());
    }

    @Test
    void shouldReturnBookSuccessfully() {

        Book book = BookFactory.createBook(
                BookCategory.MAGAZINE,
                "Bilim Cocuk",
                "Tubitak",
                79
        );

        operations.addBook(book);

        operations.borrowBook(book.getId(), student);

        operations.returnBook(book.getId(), student);

        assertTrue(student.getBorrowedBooks().isEmpty());
    }

    @Test
    void shouldUpdateBookInfo() {

        Book book = BookFactory.createBook(
                BookCategory.STUDY_BOOK,
                "Clean Code",
                "Robert Martin",
                120
        );

        operations.addBook(book);

        operations.updateBookInfo(book.getId(),
                "Clean Code #2",
                "Bob Martinez",
                200);

        assertEquals("Clean Code #2", book.getName());
        assertEquals("Bob Martinez", book.getAuthor());
        assertEquals(200, book.getPrice());
    }

    @Test
    void shouldReturnNullWhenBookNotFound() {

        Book book = operations.searchById(999);

        assertNull(book);
    }

    @Test
    void shouldReturnEmptyListForEmptyCategory() {

        var books = operations.listBooksByCategory(BookCategory.MAGAZINE);

        assertTrue(books.isEmpty());
    }

    @Test
    void shouldThrowExceptionForInvalidPrice() {

        assertThrows(IllegalArgumentException.class, () -> {

            BookFactory.createBook(
                    BookCategory.MAGAZINE,
                    "Negative Price",
                    "John Doe",
                    -10
            );

        });
    }

    @Test
    void shouldNotAllowBorrowMoreThanLimit() {

        for (int i = 0; i < 5; i++) {

            Book book = BookFactory.createBook(
                    BookCategory.STUDY_BOOK,
                    "Book" + i,
                    "Jane Doe",
                    50
            );

            operations.addBook(book);
            operations.borrowBook(book.getId(), student);
        }

        Book extraBook = BookFactory.createBook(
                BookCategory.STUDY_BOOK,
                "The Sixth Borrow",
                "Jane Doe",
                50
        );

        operations.addBook(extraBook);

        operations.borrowBook(extraBook.getId(), student);

        assertEquals(5, student.getBorrowedBooks().size());
    }
}
