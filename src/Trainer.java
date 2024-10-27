public class Trainer {
    private String trainerId;
    private String name;
    private String email;
    private String speciality;
    private String phoneNumber;

    public Trainer(String trainerId, String name, String email, String speciality, String phoneNumber) {
        this.trainerId = trainerId;
        this.name = name;
        this.email = email;
        this.speciality = speciality;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return trainerId + ',' +
                name + ',' +
                email + ',' +
                speciality + ',' +
                phoneNumber;
    }

    public String getSearchKey() {
        return trainerId;
    }

    public String lineRepresentation() {//return the data separated by commas for writing to file
        return trainerId + "," + name + "," + email + "," + speciality + "," + phoneNumber;
    }

}
