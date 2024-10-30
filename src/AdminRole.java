import java.util.ArrayList;

public class AdminRole {
    private TrainerDatabase Database;

    AdminRole(String fileName) {
        this.Database = new TrainerDatabase(fileName);
    }

    public void addTrainer(String trainerId, String name, String email, String specialty, String
            phoneNumber) {
        Trainer newTrainer = new Trainer(trainerId, name, email, specialty, phoneNumber);
        if (!Database.contains(newTrainer.getSearchKey())) {
            Database.insertRecord(newTrainer);
        } else {
            System.out.println("Trainer already exists.");
        }
    }

    public ArrayList<Trainer> getListOfTrainers() {
        return Database.returnAllRecords();
    }

    public void removeTrainer(String key) {
        Database.deleteRecord(key);
    }

    public void logout() {
        Database.saveToFile();
        System.out.println("All data saved. Logging out.............");

    }


}
