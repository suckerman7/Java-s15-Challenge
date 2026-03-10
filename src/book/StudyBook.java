package book;

public class StudyBook extends Book {

    public StudyBook(int id, String name, String author, double price, BookStatus status) {
        super(id, name, author, price, status, BookCategory.STUDY_BOOK);
    }
}
