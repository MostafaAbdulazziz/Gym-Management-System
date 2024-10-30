import java.time.LocalDate;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Testing Registration and Cancellation in TrainerRole ===");
        TrainerRole trainerRole = new TrainerRole();

        // Step 1: Add a member
        trainerRole.addMember("M2001", "Charlie Adams", "Gold", "charlie@example.com", "0123456789", "active");

        // Step 2: Add a class with available seats
        trainerRole.addClass("C2001", "Meditation Basics", "T2001", 60, 10);  // Class with 10 available seats

        // Step 3: Register the member for the class
        System.out.println("\nRegistering member for the class:");
        boolean registrationSuccess = trainerRole.registerMemberForClass("M2001", "C2001", LocalDate.now());
        System.out.println("Registration successful: " + registrationSuccess);

        // Display registrations after registration
        System.out.println("\nList of Registrations after registration:");
        for (MemberClassRegistration registration : trainerRole.getListOfRegistrations()) {
            System.out.println(registration.lineRepresentation());
        }

        // Step 4: Attempt to cancel the registration within 3 days
        System.out.println("\nAttempting to cancel the registration within 3 days:");
        boolean cancellationSuccess = trainerRole.cancelRegistration("M2001", "C2001");
        System.out.println("Cancellation successful: " + cancellationSuccess);

        // Display registrations after cancellation
        System.out.println("\nList of Registrations after cancellation:");
        for (MemberClassRegistration registration : trainerRole.getListOfRegistrations()) {
            System.out.println(registration.lineRepresentation());
        }

        // Logout and save all changes
        trainerRole.logout();
        System.out.println("TrainerRole: All data saved and logged out.");
    }
}

