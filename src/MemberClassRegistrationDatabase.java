import java.time.LocalDate;

public class MemberClassRegistrationDatabase extends Database<MemberClassRegistration> {

    public MemberClassRegistrationDatabase(String fileName) {
        super(fileName);
    }

    @Override
    protected MemberClassRegistration createRecordFrom(String line) {
        String[] data = line.split(",");
        if (data.length != 4) {
            System.out.println("Invalid line format: " + line);
            return null;
        }
        return new MemberClassRegistration(data[0], data[1], data[2], LocalDate.parse(data[3]));
    }

    @Override
    protected String getKey(MemberClassRegistration trainer) {
        return trainer.getSearchKey();
    }

    @Override
    protected String lineRepresentation(MemberClassRegistration trainer) {
        return trainer.lineRepresentation();
    }
}

