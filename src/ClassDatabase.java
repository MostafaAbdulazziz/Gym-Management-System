import java.util.ArrayList;

public class ClassDatabase extends Database<Class> {

    public ClassDatabase(String fileName) {
        super(fileName);
    }

    @Override
    protected Class createRecordFrom(String line) {
        String[] data = line.split(",");
        if (data.length != 5) {
            System.out.println("Invalid line format: " + line);
            return null;
        }
        return new Class(data[0], data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
    }

    @Override
    protected String getKey(Class class1) {
        return class1.getSearchKey();
    }

    @Override
    protected String lineRepresentation(Class class1) {
        return class1.lineRepresentation();
    }
}

