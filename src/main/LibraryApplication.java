package main;

import book.Book;
import book.BookCategory;
import book.factory.BookFactory;
import membership.Student;
import library.SingletonLibrary;
import menu.LibraryAppMenu;
import operations.LibraryOperations;
import util.ValidationUtil;

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

                case SEARCH_BOOK_BY_ID -> searchBookById(scanner, operation);

                case SEARCH_BOOK_BY_NAME -> searchBookByName(scanner, operation);

                case LIST_BY_CATEGORY -> listBooksByCategory(scanner, operation);

                case LIST_BY_AUTHOR -> listBooksByAuthor(scanner, operation);

                case BORROW_BOOK -> borrowBook(scanner, operation, student);

                case RETURN_BOOK -> returnBook(scanner, operation, student);

                case UPDATE_BOOK_INFO -> updateBookInfo(scanner, operation);

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

    private static void paginateBooks(List<Book> books, Scanner scanner) {

        final int PAGE_SIZE = 5;
        int currentPage = 0;

        while (true) {

            int start = currentPage * PAGE_SIZE;
            int end = Math.min(start + PAGE_SIZE, books.size());

            if (start >= books.size()) {
                System.out.println("There are no more books in the list.");
                return;
            }

            System.out.println("\n--- Page " + (currentPage + 1) + " ---");

            for (int i = start; i < end; i++) {
                System.out.println(books.get(i));
            }

            System.out.println("\nN - Next Page");
            System.out.println("P - Previous Page");
            System.out.println("E - Exit");

            String input = scanner.next().toUpperCase();

            switch (input) {

                case "N" -> {
                    if ((currentPage + 1) * PAGE_SIZE >= books.size()) {
                        System.out.println("There is no next page.");
                    } else {
                        currentPage++;
                    }
                }

                case "P" -> {
                    if (currentPage == 0) {
                        System.out.println("You're already at the first page.");
                    } else {
                        currentPage--;
                    }
                }

                case "E" -> {
                    return;
                }

                default -> System.out.println("Invalid option.");
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
        double price = Double.parseDouble(scanner.nextLine());

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

    private static void searchBookById(Scanner scanner, LibraryOperations operation) {

        System.out.println("Enter Book ID:");
        int id = scanner.nextInt();

        Book book = operation.searchById(id);

        if (book != null)
            System.out.println(book);
        else
            System.out.println("Book not found.");
    }

    private static void searchBookByName(Scanner scanner, LibraryOperations operation) {

        scanner.nextLine();

        System.out.println("Enter book name:");
        String name = scanner.nextLine();

        ValidationUtil.requireNonEmpty(name, "The book's name cannot be left empty!");

        List<Book> books = operation.searchByName(name);

        if (books.isEmpty()) {
            System.out.println("No book is found by that name.");
            return;
        }

        paginateBooks(books, scanner);
    }

    private static void listBooksByCategory(Scanner scanner, LibraryOperations operation) {

        BookCategory category = selectCategory(scanner);

        if (category == null)
            return;

        List<Book> books = operation.listBooksByCategory(category);

        paginateBooks(books, scanner);
    }

    private static void listBooksByAuthor(Scanner scanner, LibraryOperations operation) {

        scanner.nextLine();

        System.out.println("Enter the name of author:");
        String author = scanner.nextLine();

        ValidationUtil.requireNonEmpty(author, "Author's name cannot be left empty!");

        List<Book> books = operation.listBooksByAuthor(author);

        if (books.isEmpty()) {
            System.out.println("No books were found from that author.");
            return;
        }

        paginateBooks(books, scanner);
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

    private static void updateBookInfo(Scanner scanner, LibraryOperations operation) {

        scanner.nextLine();

        System.out.println("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the book's new name: (leave blank to skip)");
        String name = scanner.nextLine();

        System.out.println("Enter the book's new author: (leave blank to skip)");
        String author = scanner.nextLine();

        System.out.println("Enter the book's new price: (type 0 to skip)");
        double price = scanner.nextDouble();

        operation.updateBookInfo(id, name, author, price);
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
