package backend;

import java.util.ArrayList;

public class AdminRole implements Role {
    protected TrainerDatabase trainerDatabase;


    public AdminRole() {

        this.trainerDatabase = new TrainerDatabase("backend.Trainer.txt");

    }

    public void addTrainer(String trainerId, String name, String email, String speciality, String phoneNumber) {
        Trainer trainer = new Trainer(trainerId, name, email, speciality, phoneNumber);
        trainerDatabase.insertRecord(trainer);
    }

    public ArrayList<Trainer> getListOfTrainers() {
        return new ArrayList<>(trainerDatabase.returnAllRecords());
    }

    public void removeTrainer(String trainerId) {
        trainerDatabase.deleteRecord(trainerId);
    }








    @Override
    public void logout() {
        trainerDatabase.saveToFile();
    }
}