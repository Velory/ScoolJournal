package domain;

import java.io.Serializable;

public class Teacher implements Serializable{

    private int classId;
    private int kursId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (classId != teacher.classId) return false;
        return kursId == teacher.kursId;
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + kursId;
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "classId=" + classId +
                ", kursId=" + kursId +
                '}';
    }
}
