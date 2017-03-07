package domain;

public class Student extends User {

    private int age;
    private int classId;

    public Student(String firstName, String midName, String lastName,
                   String phone, String email, int age, int classId) {
        super(firstName, midName, lastName, phone, email);
        this.age = age;
        this.classId = classId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
