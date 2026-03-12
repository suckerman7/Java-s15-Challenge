package util;

public class IdGenerator {

    private static int bookId = 1;
    private static int userId = 1;
    private static int invoiceId = 1;

    public static int generateBookId() {
        return bookId++;
    }

    public static int generateUserId() {
        return userId++;
    }

    public static int generateInvoiceId() {
        return invoiceId++;
    }
}
