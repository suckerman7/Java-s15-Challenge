package person;

public class Librarian extends Person {

    public Librarian(int id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber);
    }

    @Override
    public String whoYouAre() {
        return "I am the librarian.";
    }
}
