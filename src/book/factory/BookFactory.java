package book.factory;

import book.*;
import util.IdGenerator;
import util.ValidationUtil;

public final class BookFactory {

    private BookFactory(){}

    public static Book createBook(
            BookCategory category,
            String name,
            String author,
            double price
    ) {

        ValidationUtil.requireNonNull(category, "Book category cannot be null.");
        ValidationUtil.requireNonEmpty(name, "Book's name cannot be empty.");
        ValidationUtil.requireNonEmpty(author, "Book's author cannot be empty.");
        ValidationUtil.requirePositiveDouble(price, "Book's price cannot be less or equal to 0.");

        int id = IdGenerator.generateBookId();

        switch (category) {

            case JOURNAL:
                return new Journal(id, name, author, price, BookStatus.AVAILABLE);

            case MAGAZINE:
                return new Magazine(id, name, author, price, BookStatus.AVAILABLE);

            case STUDY_BOOK:
                return new StudyBook(id, name, author, price, BookStatus.AVAILABLE);

            default:
                throw new IllegalArgumentException("This book category is not supported.");
        }
    }
}
