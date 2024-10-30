import java.util.ArrayList;

public class AdminRole extends Role {


    public AdminRole() {
        super();  // Initializes the databases via the Role superclass constructor
    }

    // Admin-specific functionality to manage trainers
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

    // Role-specific abstract methods that AdminRole may not need; Implement empty methods
    @Override
    public void addMember(String memberID, String name, String membershipType, String email, String phoneNumber, String status) {
        // AdminRole does not manage members
    }

    @Override
    public ArrayList<Member> getListOfMembers() {
        return new ArrayList<>(); // AdminRole does not manage members, return empty list
    }

    @Override
    public void addClass(String classID, String className, String trainerID, int duration, int maxParticipants) {
        // AdminRole does not manage classes
    }

    @Override
    public ArrayList<Class> getListOfClasses() {
        return new ArrayList<>(); // AdminRole does not manage classes, return empty list
    }
}