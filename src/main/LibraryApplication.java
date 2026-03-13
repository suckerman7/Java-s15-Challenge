package main;

import book.Book;
import book.BookCategory;
import book.factory.BookFactory;
import membership.Student;
import library.SingletonLibrary;
import menu.LibraryAppMenu;
import operations.LibraryOperations;

import java.util.List;
import java.util.Scanner;

public class LibraryApplication {

    public static void main(String[] args) {

        SingletonLibrary singletonLibrary = SingletonLibrary.getInstance();
        LibraryOperations operation = new LibraryOperations(singletonLibrary);

        Scanner scanner = new Scanner(System.in);

        Student student = new Student(
                1,
                "Mert",
                "mrty.yksl@gmail.com",
                "05317705875"
        );

        while (true) {

            LibraryAppMenu.printMenuOptions();

            int choice = scanner.nextInt();

            LibraryAppMenu menuOption = LibraryAppMenu.fromValue(choice);

            if (menuOption == null) {
                System.out.println("Invalid choice!");
                continue;
            }

            switch (menuOption) {

                case ADD_BOOK -> addBook(scanner, operation);

                case REMOVE_BOOK -> removeBook(scanner, operation);

                case SEARCH_BOOK_BY_ID -> searchBook(scanner, operation);

                case LIST_BY_CATEGORY -> listBooksByCategory(scanner, operation);

                case BORROW_BOOK -> borrowBook(scanner, operation, student);

                case RETURN_BOOK -> returnBook(scanner, operation, student);

                case SHOW_BORROWED -> operation.printBorrowedBooks();

                case SHOW_INVOICES -> operation.printInvoices();

                case EXIT -> {
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void addBook(Scanner scanner, LibraryOperations operation) {

        scanner.nextLine();

        System.out.println("Enter Book Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Author:");
        String author = scanner.nextLine();

        System.out.println("Enter Price:");
        double price = scanner.nextDouble();

        BookCategory category = selectCategory(scanner);

        if (category != null) {

            Book book = BookFactory.createBook(category, name, author, price);

            operation.addBook(book);
        }
    }

    private static void removeBook(Scanner scanner, LibraryOperations operation) {

        System.out.println("Enter Book ID:");
        int id = scanner.nextInt();

        operation.removeBook(id);
    }

    private static void searchBook(Scanner scanner, LibraryOperations operation) {

        System.out.println("Enter Book ID:");
        int id = scanner.nextInt();

        Book book = operation.searchById(id);

        if (book != null)
            System.out.println(book);
        else
            System.out.println("Book not found.");
    }

    private static void listBooksByCategory(Scanner scanner, LibraryOperations operation) {

        BookCategory category = selectCategory(scanner);

        if (category == null)
            return;

        List<Book> books = operation.listBooksByCategory(category);

        books.forEach(System.out::println);
    }

    private static void borrowBook(Scanner scanner, LibraryOperations operation, Student student) {

        System.out.println("Enter Book ID:");
        int id = scanner.nextInt();

        operation.borrowBook(id, student);
    }

    private static void returnBook(Scanner scanner, LibraryOperations operation, Student student) {

        System.out.println("Enter Book ID:");
        int id = scanner.nextInt();

        operation.returnBook(id, student);
    }

    private static BookCategory selectCategory(Scanner scanner) {

        System.out.println("Select book category:");
        System.out.println("1: JOURNAL");
        System.out.println("2: STUDY BOOK");
        System.out.println("3: MAGAZINE");

        int choice = scanner.nextInt();

        return switch (choice) {
            case 1 -> BookCategory.JOURNAL;
            case 2 -> BookCategory.STUDY_BOOK;
            case 3 -> BookCategory.MAGAZINE;
            default -> {
                System.out.println("Invalid category!");
                yield null;
            }
        };
    }
}
