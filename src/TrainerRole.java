import java.time.LocalDate;
import java.util.ArrayList;

public class TrainerRole {

    private MemberDatabase memberDatabase;
    private ClassDatabase classDatabase;
    MemberClassRegistrationDatabase registrationDatabase;

    public TrainerRole() {
        memberDatabase = new MemberDatabase("Members.txt");
        classDatabase = new ClassDatabase("Classes.txt");
        registrationDatabase = new MemberClassRegistrationDatabase("Registration.txt");
    }

    public void addMember(String memberID, String name, String membershipType, String email, String phoneNumber, String status) {
        Member newMember = new Member(memberID, name, membershipType, email, phoneNumber, status);
        if (!memberDatabase.contains(newMember.getSearchKey())) memberDatabase.insertRecord(newMember);
        else System.out.println("Member already exists");

    }

    public ArrayList<Member> getListOfMembers() {
        return memberDatabase.returnAllRecords();
    }

    public void addClass(String classID, String className, String trainerID, int duration, int maxParticipants) {
        Class newClass = new Class(classID, className, trainerID, duration, maxParticipants);
        if (!classDatabase.contains(newClass.getSearchKey())) classDatabase.insertRecord(newClass);
        else System.out.println("Class already exists");
    }

    public ArrayList<Class> getListOfClasses() {
        return classDatabase.returnAllRecords();
    }

    boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) {
        if (!memberDatabase.contains(memberID)) {
            System.out.println("Member does not exist");
            return false;
        }
        if (!classDatabase.contains(classID)) {
            System.out.println("Class does not exist");
            return false;
        }
        MemberClassRegistration newRegistration = new MemberClassRegistration(memberID, classID, registrationDate, "Active");

        if (classDatabase.getRecord(classID).getAvailableSeats() > 0) {
            registrationDatabase.insertRecord(newRegistration);
            classDatabase.getRecord(classID).setAvailableSeats(classDatabase.getRecord(classID).getAvailableSeats() - 1);
            return true;


        }
        return false;
    }

    public boolean cancelRegistration(String memberID, String classID) {
        if (registrationDatabase.contains(memberID + "-" + classID)) {
            MemberClassRegistration registration = registrationDatabase.getRecord(memberID +"-"+ classID);
            if (registration.getRegistrationStatus().equals("Cancelled")) {
                System.out.println("Registration already cancelled");
                return false;
            }
            if (!registration.getRegistrationDate().isBefore(LocalDate.now().minusDays(3))) {
                registrationDatabase.getRecord(memberID + "-" + classID).setRegistrationStatus("Cancelled");
                classDatabase.getRecord(classID).setAvailableSeats(classDatabase.getRecord(classID).getAvailableSeats() + 1);
                System.out.println("Registration cancelled successfully");
                return true;
            } else {
                System.out.println("Registration cannot be cancelled, more than 3 days have passed");
                return false;
            }
        } else {
            System.out.println("Registration not found");
            return false;
        }
    }

    public ArrayList<MemberClassRegistration> getListOfRegistrations() {
        return registrationDatabase.returnAllRecords();
    }

    public void logout() {
        memberDatabase.saveToFile();
        classDatabase.saveToFile();
        registrationDatabase.saveToFile();
    }

}

