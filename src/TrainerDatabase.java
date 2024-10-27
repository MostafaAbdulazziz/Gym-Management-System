import java.io.*;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TrainerDatabase {
    private ArrayList<Trainer> records;
    private String fileName;

    public TrainerDatabase(String fileName) {
        this.fileName = fileName;
        records = new ArrayList<>();
        readFromFile();
    }

    private void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Trainer trainer = createRecordFrom(line);
                records.add(trainer);
            }

        } catch (IOException e) {
            System.out.println("error reading from the file");
        }
    }

    private Trainer createRecordFrom(String line) {
        String[] data = line.split(",");
        if (data.length != 5) {
            System.out.println("Invalid line format: " + line);
            return null;
        }
        return new Trainer(data[0], data[1], data[2], data[3], data[4]);
    }

    public ArrayList<Trainer> returnAllRecords() {
        return records;
    }

    public Boolean contains(String key) {
        for (Trainer trainer : records)
            if (trainer.getSearchKey().equals(key))
                return true;
        return false;
    }

    public Trainer getRecord(String key) {
        for (Trainer trainer : records) {
            if (trainer.getSearchKey().equals(key)) {
                return trainer;
            }
        }
        return null;
    }

    public void addRecord(Trainer record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
            System.out.println("trainer added successfully");
        } else System.out.println("inserted record already exists");
    }

    public void deleteRecord(String key) {
        Trainer trainer = getRecord(key);
        if (trainer != null) {
            records.remove(trainer);
            System.out.println("trainer removed successfully");
        } else
            System.out.println("record not found");
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Trainer trainer : records) {
                writer.write(trainer.lineRepresentation());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("error writing to the file");
        }
    }

}
