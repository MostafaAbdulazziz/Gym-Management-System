import java.util.ArrayList;

public abstract class Role {
    protected MemberDatabase memberDatabase;
    protected ClassDatabase classDatabase;
    protected MemberClassRegistrationDatabase registrationDatabase;
    protected TrainerDatabase trainerDatabase;

    public Role() {
        this.memberDatabase = new MemberDatabase("src//Members");
        this.classDatabase = new ClassDatabase("src//Classes");
        this.registrationDatabase = new MemberClassRegistrationDatabase("src//Registrations");
        this.trainerDatabase = new TrainerDatabase("src//Trainers");
    }

    public void logout() {
        memberDatabase.saveToFile();
        classDatabase.saveToFile();
        registrationDatabase.saveToFile();
        trainerDatabase.saveToFile();
        System.out.println("All data saved. Logging out.");
    }

    public abstract void addMember(String memberID, String name, String membershipType, String email, String phoneNumber, String status);
    public abstract ArrayList<Member> getListOfMembers();
    public abstract void addClass(String classID, String className, String trainerID, int duration, int maxParticipants);
    public abstract ArrayList<Class> getListOfClasses();
}

