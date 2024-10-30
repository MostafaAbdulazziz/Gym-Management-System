import java.time.LocalDate;

public class MemberClassRegistration {
    private String memberId;
    private String classId;
    private String status;
    private LocalDate registrationDate;

    public MemberClassRegistration(String memberId, String classId, LocalDate registrationDate, String status) {
        this.memberId = memberId;
        this.classId = classId;
        this.status = status;
        this.registrationDate = registrationDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getClassId() {
        return classId;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getSearchKey() {
        return memberId +"-"+ classId;
    }

    public String lineRepresentation() {
        return memberId + "," + classId + "," + registrationDate + "," + status;
    }

    public String setRegistrationStatus(String status) {
        return this.status = status;
    }

    public String getRegistrationStatus() {
        return status;
    }
}
