package menu;

public enum LibraryAppMenu {

    ADD_BOOK(1, "Add Book"),
    REMOVE_BOOK(2, "Remove Book"),
    SEARCH_BOOK_BY_ID(3, "Search Book By ID"),
    LIST_BY_CATEGORY(4, "List Books By Category"),
    BORROW_BOOK(5, "Borrow Book"),
    RETURN_BOOK(6, "Return Book"),
    SHOW_BORROWED(7, "Show Borrowed Books"),
    SHOW_INVOICES(8, "Show Invoices"),
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
            System.out.println(menuOption.menuOption + ": " + menuOption.menuDescription);
        }
    }
}
