package sirotkina.sjournal.entity;

public class Students extends Entity{

    private String firstName;
    private String midName;
    private String lastName;
    private int age;
    private String phone;
    private String email;
    private Class classFKId;
    private String password;

    public Students(Integer id, String firstName, String midName, String lastName,
                    int age, String phone, String email, Class classFKId, String password) {
        setId(id);
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}
