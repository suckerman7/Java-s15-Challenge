package membership;

import person.Reader;

public class Student extends Reader {

    public Student(int id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber);
    }

    @Override
    public String whoYouAre() {
        return "I have a student membership.";
    }
}
