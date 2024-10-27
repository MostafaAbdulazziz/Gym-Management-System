import java.time.LocalDate;
import java.time.temporal.Temporal;

public class MemberClassRegistration {
    private String memberId;
    private String classId;
    private String status;
    private LocalDate registrationDate;

    public MemberClassRegistration(String memberId, String classId, String status, LocalDate registerationDate) {
        this.memberId = memberId;
        this.classId = classId;
        this.status = status;
        this.registrationDate = registerationDate;
    }
    public String getMemberId() {
        return memberId;
    }
    public String getClassId() {
        return classId;
    }
    public Temporal getRegistrationDate() {
        return registrationDate;
    }
    public String getSearchKey() {
        return memberId + classId;
    }
    public String lineRepresentation() {
        return memberId + "," + classId + "," + status + "," + registrationDate;
    }
}
