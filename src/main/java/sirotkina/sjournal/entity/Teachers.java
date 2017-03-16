package sirotkina.sjournal.entity;

public class Teachers {
    private int id;
    private String firstName;
    private String midName;
    private String lastName;
    private String phone;
    private String email;
    private int kursId;
    private int classId;

    public Teachers(int id, String firstName, String midName,
                    String lastName, String phone, String email, int kursId, int classId) {
        this.id = id;
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.kursId = kursId;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getKursId() {
        return kursId;
    }

    public void setKursId(int kursId) {
        this.kursId = kursId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teachers teachers = (Teachers) o;

        if (kursId != teachers.kursId) return false;
        if (classId != teachers.classId) return false;
        if (firstName != null ? !firstName.equals(teachers.firstName) : teachers.firstName != null) return false;
        if (midName != null ? !midName.equals(teachers.midName) : teachers.midName != null) return false;
        if (lastName != null ? !lastName.equals(teachers.lastName) : teachers.lastName != null) return false;
        if (phone != null ? !phone.equals(teachers.phone) : teachers.phone != null) return false;
        return email != null ? email.equals(teachers.email) : teachers.email == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (midName != null ? midName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + kursId;
        result = 31 * result + classId;
        return result;
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", midName='" + midName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", kursId=" + kursId +
                ", classId=" + classId +
                '}';
    }
}
