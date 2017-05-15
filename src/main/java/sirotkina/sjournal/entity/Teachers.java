package sirotkina.sjournal.entity;

public class Teachers extends Entity{

    private String firstName;
    private String midName;
    private String lastName;
    private String phone;
    private String email;
    private Kurs kursFKId;
    private Class classFKId;
    private String password;

    public Teachers(Integer id, String firstName, String midName,
                    String lastName, String phone, String email,
                    Kurs kursFKId, Class classFKId, String password) {
        setId(id);
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.kursFKId = kursFKId;
        this.classFKId = classFKId;
        this.password = password;
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

    public Kurs getKursFKId() {
        return kursFKId;
    }

    public void setKursFKId(Kurs kursFKId) {
        this.kursFKId = kursFKId;
    }

    public Class getClassFKId() {
        return classFKId;
    }

    public void setClassFKId(Class classFKId) {
        this.classFKId = classFKId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "id=" + getId() +
                ", firstName='" + firstName + '\'' +
                ", midName='" + midName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", kursId=" + kursFKId +
                ", classId=" + classFKId +
                '}';
    }
}
