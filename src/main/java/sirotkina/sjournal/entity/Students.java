package sirotkina.sjournal.entity;

public class Students extends Entity{

    private String firstName;
    private String midName;
    private String lastName;
    private int age;
    private String phone;
    private String email;
    private Class classFKId;

    public Students(Integer id, String firstName, String midName, String lastName,
                    int age, String phone, String email, Class classFKId) {
        setId(id);
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.classFKId = classFKId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public Class getClassFKId() {
        return classFKId;
    }

    public void setClassFKId(Class classFKId) {
        this.classFKId = classFKId;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + getId() +
                ", firstName='" + firstName + '\'' +
                ", midName='" + midName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", classId=" + classFKId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Students students = (Students) o;

        if (age != students.age) return false;
        if (firstName != null ? !firstName.equals(students.firstName) : students.firstName != null) return false;
        if (midName != null ? !midName.equals(students.midName) : students.midName != null) return false;
        return lastName != null ? lastName.equals(students.lastName) : students.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (midName != null ? midName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
}
