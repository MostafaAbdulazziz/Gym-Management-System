import java.time.LocalDate;

public class MemberClassRegistration {
    private String memberId;
    private String classId;
    private String status;
    private LocalDate registerationDate;

    public MemberClassRegistration(String memberId, String classId, String status, LocalDate registerationDate) {
        this.memberId = memberId;
        this.classId = classId;
        this.status = status;
        this.registerationDate = registerationDate;
    }
    public String getMemberId() {
        return memberId;
    }
    public String getClassId() {
        return classId;
    }
    public String getRegisterationDate() {
        return registerationDate.toString();
    }
    public String getSearchKey() {
        return memberId + classId;
    }
    public String lineRepresentation() {
        return memberId + "," + classId + "," + status + "," + registerationDate;
    }
}
