package invoice;

import book.Book;
import person.Reader;

import java.time.LocalDate;

public class Invoice {

    private int invoiceId;
    private Reader reader;
    private Book book;
    private double amount;
    private LocalDate issueDate;
    private InvoiceStatus status;

    public Invoice(int invoiceId, Reader reader, Book book, double amount) {
        this.invoiceId = invoiceId;
        this.reader = reader;
        this.book = book;
        this.amount = amount;

        this.issueDate = LocalDate.now();
        this.status = InvoiceStatus.ISSUED;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void refund() {
        this.status = InvoiceStatus.REFUNDED;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId= " + invoiceId +
                ", reader= " + reader.getName() +
                ", book= " + book.getName() +
                ", amount= " + amount +
                ", issueDate= " + issueDate +
                ", status= " + status +
                "}";
    }
}
