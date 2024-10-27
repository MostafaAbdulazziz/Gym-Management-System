import java.util.ArrayList;

public class AdminRole {
    private TrainerDatabase database;

    public AdminRole() {
        this.database = new TrainerDatabase("src\\Trainers");
    }

    public void addTrainer(String trainerId, String name, String email, String speciality, String phoneNumber) {
        Trainer trainer = new Trainer(trainerId, name, email, speciality, phoneNumber);
        database.addRecord(trainer);
    }

    public ArrayList<Trainer> getListOfTrainers() {
        return database.returnAllRecords();
    }

    public void removeTrainer(String trainerId) {
        database.deleteRecord(trainerId);
    }

    public void logout() {
        database.saveToFile();
    }

}
