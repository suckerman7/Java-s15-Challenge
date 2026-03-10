package book;

import java.util.List;

public interface Searchable {

    Book searchById(int id);

    List<Book> searchByName(String name);

    List<Book> searchByAuthor(String author);
}
