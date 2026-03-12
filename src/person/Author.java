package person;

public class Author extends Person {

    public Author(int id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber);
    }

    @Override
    public String whoYouAre() {
        return "I am an author.";
    }
}
