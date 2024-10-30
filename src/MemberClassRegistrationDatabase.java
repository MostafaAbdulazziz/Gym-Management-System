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
        return new MemberClassRegistration(data[0], data[1], LocalDate.parse(data[2]), data[3]);
    }

    @Override
    protected String getKey(MemberClassRegistration registration) {
        return registration.getSearchKey();
    }

    @Override
    protected String lineRepresentation(MemberClassRegistration registration) {
        return registration.lineRepresentation();
    }
}

