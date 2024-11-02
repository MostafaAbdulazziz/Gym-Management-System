package backend;

import java.io.*;
import java.util.ArrayList;

public abstract class Database<T> {
    protected ArrayList<T> records;
    protected String fileName;

    public Database(String fileName) {
        this.fileName = fileName;
        this.records = new ArrayList<>();
        readFromFile();
    }

    protected void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T record = createRecordFrom(line);
                if (record != null) {
                    records.add(record);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading from file: " + fileName);
        }
    }

    protected abstract T createRecordFrom(String line);

    public ArrayList<T> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        for (T record : records)
            if (getKey(record).equals(key))
                return true;
        return false;
    }

    public T getRecord(String key) {
        for (T record : records)
            if (getKey(record).equals(key))
                return record;
        return null;
    }

    public void insertRecord(T record) {
        if (!contains(getKey(record))) {
            records.add(record);
            System.out.println("record added successfully.");
        } else
            System.out.println("record already exists.");
    }

    public void deleteRecord(String key) {
        T record = getRecord(key);
        if (record != null) {
            records.remove(record);
            System.out.println("record removed successfully.");
        } else
            System.out.println("record not found.");
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            for (T record : records) {
                writer.write(lineRepresentation(record));
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("error while writing to file: " + fileName);
        }
    }

    protected abstract String getKey(T record);

    protected abstract String lineRepresentation(T record);
}

