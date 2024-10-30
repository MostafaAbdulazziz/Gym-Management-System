import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TrainerRole extends Role {

    public TrainerRole() {
        super();
    }

    @Override
    public void addMember(String memberID, String name, String membershipType, String email, String phoneNumber, String status) {
        Member newMember = new Member(memberID, name, membershipType, email, phoneNumber, status);
        if (!memberDatabase.contains(memberID)) {
            memberDatabase.insertRecord(newMember);
            System.out.println("Member added successfully.");
        } else
            System.out.println("Member with ID " + memberID + " already exists.");
    }

    @Override
    public ArrayList<Member> getListOfMembers() {
        return memberDatabase.returnAllRecords();
    }

    @Override
    public void addClass(String classID, String className, String trainerID, int duration, int maxParticipants) {
        Class newClass = new Class(classID, className, trainerID, duration, maxParticipants);
        if (!classDatabase.contains(classID)) {
            classDatabase.insertRecord(newClass);
            System.out.println("Class added successfully.");
        } else
            System.out.println("Class with ID " + classID + " already exists.");
    }

    @Override
    public ArrayList<Class> getListOfClasses() {
        return classDatabase.returnAllRecords();
    }

    public boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) {
        Class classRecord = classDatabase.getRecord(classID);
        if (classRecord != null && classRecord.getAvailableSeats() > 0) {
            MemberClassRegistration registration = new MemberClassRegistration(memberID, classID, registrationDate,"active");
            if (!registrationDatabase.contains(registration.getSearchKey())) {
                registrationDatabase.insertRecord(registration);
                classRecord.setAvailableSeats(classRecord.getAvailableSeats() - 1); // Decrease seats
                System.out.println("Member registered successfully.");
                return true;
            } else
                System.out.println("this member is already registered for this class");
        } else
            System.out.println("no available seats for class with ID " + classID);
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
}
