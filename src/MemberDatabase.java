import java.util.ArrayList;

public class MemberDatabase extends Database<Member> {

    public MemberDatabase(String fileName) {
        super(fileName);
    }

    @Override
    protected Member createRecordFrom(String line) {
        String[] data = line.split(",");
        if (data.length != 6) {
            System.out.println("Invalid line format: " + line);
            return null;
        }
        return new Member(data[0], data[1], data[2], data[3], data[4], data[5]);
    }

    @Override
    protected String getKey(Member member) {
        return member.getSearchKey();
    }

    @Override
    protected String lineRepresentation(Member member) {
        return member.lineRepresentation();
    }
}

