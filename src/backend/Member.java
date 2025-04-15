package backend;

public class Member extends Person {
    private String membershipType;
    private String status;

    public Member(String memberId, String name, String membershipType, String email, String phoneNumber, String status) {
        super(memberId, name, email, phoneNumber);
        this.membershipType = membershipType;
        this.status = status;
    }

    @Override
    public String getSearchKey() {
        return id;
    }

    @Override
    public String lineRepresentation() {
        return id + "," + name + "," + membershipType + "," + email + "," + phoneNumber + "," + status;
    }
}
