public abstract class Person {
    protected String id;
    protected String name;
    protected String email;
    protected String phoneNumber;

    public Person(String id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public abstract String getSearchKey();

    public abstract String lineRepresentation();

    @Override
    public String toString() {
        return id + ',' + name + ',' + email + ',' + phoneNumber;
    }
}

