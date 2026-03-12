package person;

import util.ValidationUtil;

import java.util.Objects;

public abstract class Person {

    private int id;
    private String name;
    private String email;
    private String phoneNumber;


    public Person(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {

        ValidationUtil.requireNonNull(id, "The person's id cannot be null.");
        ValidationUtil.requirePositive(id, "The person's id cannot be less or equal to 0.");

        this.id = id;
    }

    public void setName(String name) {

        ValidationUtil.requireNonNull(name, "The person's name cannot be null.");
        ValidationUtil.requireNonEmpty(name, "The person's name cannot be empty.");

        this.name = name;
    }

    public void setEmail(String email) {

        ValidationUtil.requireNonNull(name, "The person's email cannot be null.");
        ValidationUtil.requireNonEmpty(name, "The person's email cannot be empty.");

        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {

        ValidationUtil.requireNonNull(name, "The person's phone number cannot be null.");
        ValidationUtil.requireNonEmpty(name, "The person's phone number cannot be empty.");

        this.phoneNumber = phoneNumber;
    }

    public abstract String whoYouAre();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", name=" + name +
                ", email=" + email +
                ", phone number=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
