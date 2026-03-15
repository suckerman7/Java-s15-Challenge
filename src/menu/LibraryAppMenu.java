package menu;

public enum LibraryAppMenu {

    ADD_BOOK(1, "Add Book"),
    REMOVE_BOOK(2, "Remove Book"),
    SEARCH_BOOK_BY_ID(3, "Search Book By ID"),
    SEARCH_BOOK_BY_NAME(4, "Search Book By Name"),
    LIST_BY_CATEGORY(5, "List Books By Category"),
    LIST_BY_AUTHOR(6, "List Books By Author"),
    BORROW_BOOK(7, "Borrow Book"),
    RETURN_BOOK(8, "Return Book"),
    UPDATE_BOOK_INFO(9, "Update Book's Info"),
    SHOW_BORROWED(10, "Show Borrowed Books"),
    SHOW_INVOICES(11, "Show Invoices"),

    EXIT(0, "Exit App");

    private final int menuOption;
    private final String menuDescription;

    LibraryAppMenu(int menuOption, String menuDescription) {
        this.menuOption = menuOption;
        this.menuDescription = menuDescription;
    }

    public int getMenuOption() {
        return menuOption;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public static LibraryAppMenu fromValue(int value) {

        for (LibraryAppMenu menuOption : values()) {
            if (menuOption.menuOption == value) {
                return menuOption;
            }
        }

        return null;
    }

    public static void printMenuOptions() {

        System.out.println("\n==== LIBRARY MENU ====");

        for (LibraryAppMenu menuOption : values()) {
            System.out.println(menuOption.getMenuOption() + ": " + menuOption.getMenuDescription());
        }
    }
}
