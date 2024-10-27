import java.util.ArrayList;
import java.util.Collections;

public class AdminRole {
    private TrainerDatabase database;

    public AdminRole() {
        this.database = new TrainerDatabase("src//Trainers");
    }

    public void addTrainer(String trainerId, String name, String email, String speciality, String phoneNumber) {
        Trainer trainer = new Trainer(trainerId, name, email, speciality, phoneNumber);
        database.insertRecord(trainer);
    }

    public ArrayList<Trainer> getListOfTrainers() {
        return new ArrayList<>(database.returnAllRecords());
    }

    public void removeTrainer(String trainerId) {
        database.deleteRecord(trainerId);
    }

    public void logout() {
        database.saveToFile();
    }
}
