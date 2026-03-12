package libraryApp;

import book.Book;
import book.BookCategory;
import book.factory.BookFactory;
import membership.Student;
import model.Library;
import service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class LibraryApplication {

    public static void main(String[] args) {

        Library library = new Library();
        LibraryService service = new LibraryService(library);

        Scanner scanner = new Scanner(System.in);

        Student student = new Student(
                1,
                "Mert",
                "mrty.yksl@gmail.com",
                "05317705875"
        );

        while (true) {

            printMenu();

            int choice = scanner.nextInt();

            switch (choice) {

                case 1 -> addBook(scanner, service);

                case 2 -> removeBook(scanner, service);

                case 3 -> searchBook(scanner, service);

                case 4 -> listBooksByCategory(scanner, service);

                case 5 -> borrowBook(scanner, service, student);

                case 6 -> returnBook(scanner, service, student);

                case 7 -> service.printBorrowedBooks();

                case 8 -> service.printInvoices();

                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void printMenu() {

        System.out.println("\n==== LIBRARYAPP MENU ====");
        System.out.println("1: Add Book");
        System.out.println("2: Remove Book");
        System.out.println("3: Search Book By ID");
        System.out.println("4: List Books By Category");
        System.out.println("5: Borrow Book");
        System.out.println("6: Return Book");
        System.out.println("7: Show Borrowed Books");
        System.out.println("8: Show Invoices");
        System.out.println("0: Exit");
    }

    private static void addBook(Scanner scanner, LibraryService service) {

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

            service.addBook(book);
        }
    }

    private static void removeBook(Scanner scanner, LibraryService service) {

        System.out.println("Enter Book ID:");
        int id = scanner.nextInt();

        service.removeBook(id);
    }

    private static void searchBook(Scanner scanner, LibraryService service) {

        System.out.println("Enter Book ID:");
        int id = scanner.nextInt();

        Book book = service.searchById(id);

        if (book != null)
            System.out.println(book);
        else
            System.out.println("Book not found.");
    }

    private static void listBooksByCategory(Scanner scanner, LibraryService service) {

        BookCategory category = selectCategory(scanner);

        if (category == null)
            return;

        List<Book> books = service.listBooksByCategory(category);

        books.forEach(System.out::println);
    }

    private static void borrowBook(Scanner scanner, LibraryService service, Student student) {

        System.out.println("Enter Book ID:");
        int id = scanner.nextInt();

        service.borrowBook(id, student);
    }

    private static void returnBook(Scanner scanner, LibraryService service, Student student) {

        System.out.println("Enter Book ID:");
        int id = scanner.nextInt();

        service.returnBook(id, student);
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
