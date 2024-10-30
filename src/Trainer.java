public class Trainer extends Person {
    private String speciality;

    public Trainer(String trainerId, String name, String email, String speciality, String phoneNumber) {
        super(trainerId, name, email, phoneNumber);
        this.speciality = speciality;
    }


    @Override
    public String lineRepresentation() {
        return id + "," + name + "," + email + "," + speciality + "," + phoneNumber;
    }

    @Override
    public String getSearchKey() {
        return id;
    }
}
