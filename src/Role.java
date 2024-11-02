import java.util.ArrayList;

public abstract class Role {
    protected MemberDatabase memberDatabase;
    protected ClassDatabase classDatabase;
    protected MemberClassRegistrationDatabase registrationDatabase;
    protected TrainerDatabase trainerDatabase;

    public Role() {
        this.memberDatabase = new MemberDatabase("Members.txt");
        this.classDatabase = new ClassDatabase("Classes.txt");
        this.registrationDatabase = new MemberClassRegistrationDatabase("Registration.txt");
        this.trainerDatabase = new TrainerDatabase("Trainer.txt");
    }

    public abstract void logout();

    public abstract void addMember(String memberID, String name, String membershipType, String email, String phoneNumber, String status);
    public abstract ArrayList<Member> getListOfMembers();
    public abstract void addClass(String classID, String className, String trainerID, int duration, int maxParticipants);
    public abstract ArrayList<Class> getListOfClasses();
}

