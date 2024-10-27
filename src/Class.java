public class Class {
    private String classId;
    private String className;
    private String trainerId;
    private int duration;
    private int availableSeats;

    public Class(String classId, String className, String trainerId, int duration, int availableSeats) {
        this.classId = classId;
        this.className = className;
        this.trainerId = trainerId;
        this.duration = duration;
        this.availableSeats = availableSeats;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    public String lineRepresentation() {
        return classId + "," + className + "," + trainerId + "," + duration + "," + availableSeats;
    }
    public String getSearchKey() {
        return classId;
    }

}
