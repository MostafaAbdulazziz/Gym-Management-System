import java.util.ArrayList;

public class TrainerDatabase extends Database<Trainer> {

    public TrainerDatabase(String fileName) {
        super(fileName);
    }

    @Override
    protected Trainer createRecordFrom(String line) {
        String[] data = line.split(",");
        if (data.length != 5) {
            System.out.println("Invalid line format: " + line);
            return null;
        }
        return new Trainer(data[0], data[1], data[2], data[3], data[4]);
    }

    @Override
    protected String getKey(Trainer trainer) {
        return trainer.getSearchKey();
    }

    @Override
    protected String lineRepresentation(Trainer trainer) {
        return trainer.lineRepresentation();
    }
}
