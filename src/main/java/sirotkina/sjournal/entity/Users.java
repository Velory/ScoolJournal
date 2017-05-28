package sirotkina.sjournal.entity;

public class Users extends Entity {
    private String firstName;
    private String midName;
    private String lastName;
    private int age;
    private String phone;
    private String email;
    private Class classFKId;
    private Kurs kursFKId;
    private String password;
    private Role roleId;

    public Users(Integer id, String firstName, String midName, String lastName, int age, String phone, String email,
                 Class classFKId, Kurs kursFKId, String password, Role roleId) {
        setId(id);
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.classFKId = classFKId;
        this.kursFKId = kursFKId;
        this.password = password;
        this.roleId = roleId;
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

    public Kurs getKursFKId() {
        return kursFKId;
    }

    public void setKursFKId(Kurs kursFKId) {
        this.kursFKId = kursFKId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Users: " +
                "firstName='" + firstName + '\'' +
                ", midName='" + midName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", classFKId=" + classFKId +
                ", kursFKId=" + kursFKId +
                ", password='" + password + '\'' +
                ", roleId=" + roleId;
    }
}
