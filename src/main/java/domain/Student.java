package domain;

import java.io.Serializable;

public class Student implements Serializable{

    private int age;
    private int classId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (age != student.age) return false;
        return classId == student.classId;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + classId;
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", classId=" + classId +
                '}';
    }
}
