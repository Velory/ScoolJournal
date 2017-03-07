package domain;

public class Teacher extends User{

    private int classId;
    private int kursId;

    public Teacher(String firstName, String midName, String lastName,
                   String phone, String email, int classId, int kursId) {
        super(firstName, midName, lastName, phone, email);
        this.classId = classId;
        this.kursId = kursId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getKursId() {
        return kursId;
    }

    public void setKursId(int kursId) {
        this.kursId = kursId;
    }
}
