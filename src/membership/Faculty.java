package membership;

import person.Reader;

public class Faculty extends Reader {

    public Faculty(int id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber);
    }

    @Override
    public String whoYouAre() {
        return "I have a faculty membership.";
    }
}
