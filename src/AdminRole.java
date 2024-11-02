import java.util.ArrayList;

public class AdminRole extends Role {


    public AdminRole() {
        super();
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
    public void addMember(String memberID, String name, String membershipType, String email, String phoneNumber, String status) {
        // AdminRole does not manage members
    }

    @Override
    public ArrayList<Member> getListOfMembers() {
        return new ArrayList<>();
    }

    @Override
    public void addClass(String classID, String className, String trainerID, int duration, int maxParticipants) {

    }

    @Override
    public ArrayList<Class> getListOfClasses() {
        return new ArrayList<>();
    }
    @Override
    public void logout() {
        trainerDatabase.saveToFile();
    }
}