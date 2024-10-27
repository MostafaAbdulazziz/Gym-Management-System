import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create an AdminRole instance
        AdminRole admin = new AdminRole();

        // Step 2: Add some trainers
        System.out.println("Adding trainers:");
        admin.addTrainer("T1001", "John Doe", "john@example.com", "Yoga", "0123456789");
        admin.addTrainer("T1002", "Jane Smith", "jane@example.com", "Pilates", "0987654321");
        admin.addTrainer("T1003", "Emily Brown", "emily@example.com", "Strength Training", "0112233445");

        // Attempting to add a duplicate trainer to see if it's handled
        System.out.println("\nAttempting to add a duplicate trainer:");
        admin.addTrainer("T1001", "Duplicate John", "duplicate@example.com", "HIIT", "0123456789");

        // Step 3: Retrieve and display all trainers
        System.out.println("\nList of all trainers in the system:");
        ArrayList<Trainer> allTrainers = admin.getListOfTrainers();
        for (Trainer trainer : allTrainers) {
            System.out.println(trainer.lineRepresentation());
        }

        // Step 4: Remove a trainer
        System.out.println("\nRemoving trainer with ID T1002:");
        admin.removeTrainer("T1002");

        // Display trainers after removal
        System.out.println("\nList of trainers after removal:");
        allTrainers = admin.getListOfTrainers();
        for (Trainer trainer : allTrainers) {
            System.out.println(trainer.lineRepresentation());
        }

        // Step 5: Logout (this will save all changes to the Trainers.txt file)
        System.out.println("\nLogging out and saving changes...");
        admin.logout();
    }
}
